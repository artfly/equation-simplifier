package com.github.artfly.parser;

import com.github.artfly.lexer.Token;
import com.github.artfly.lexer.TokenType;
import com.github.artfly.parser.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Expression> parse() {
        List<Expression> expressions = new ArrayList<>();
        while (peek().tokenType() != TokenType.EOF) {
            expressions.add(parseTerm());
            if (peek().tokenType() != TokenType.EOF) pos++;
        }
        return expressions;
    }

    private Expression parseTerm() {
        Expression expression = parseFactor();
        while (matches(peek(), TokenType.PLUS, TokenType.MINUS)) {
            Token token = advance();
            Operation operation = token.tokenType() == TokenType.PLUS ? Operation.ADD : Operation.SUBTRACT;
            expression = new Binary(operation, expression, parseFactor());
        }
        return expression;
    }

    private Expression parseFactor() {
        Expression expression = parsePower();
        while (matches(peek(), TokenType.SLASH, TokenType.STAR)) {
            Token token = advance();
            Operation operation = token.tokenType() == TokenType.STAR ? Operation.MULTIPLY : Operation.DIVIDE;
            expression = new Binary(operation, expression, parsePower());
        }
        return expression;
    }

    private Expression parsePower() {
        Expression expression = parseNegated();
        while (matches(peek(), TokenType.POW)) {
            advance();
            expression = new Binary(Operation.POW, expression, parseNegated());
        }
        return expression;
    }

    private Expression parseNegated() {
        if (matches(peek(), TokenType.MINUS)) {
            advance();
            return new Negated(parsePrimary());
        }
        return parsePrimary();
    }

    private Expression parsePrimary() {
        Token token = advance();
        TokenType tokenType = token.tokenType();
        if (tokenType == TokenType.IDENTIFIER) {
            return new Var(token.value());
        }
        if (tokenType == TokenType.NUMBER) {
            return new Const(Integer.parseInt(token.value()));
        }
        if (tokenType == TokenType.OP_BRACE) {
            Expression expression = parseTerm();
            if (advance().tokenType() != TokenType.CL_BRACE) {
                throw error(String.format("No closing bracket for bracket at line %d", token.line()));
            }
            return expression;
        }
        throw error(String.format("Expected number or identifier, got %s at line %d", tokenType, token.line()));
    }

    private static RuntimeException error(String message) {
        return new IllegalStateException(message);
    }

    private static boolean matches(Token token, TokenType... expectedTokens) {
        TokenType tokenType = token.tokenType();
        return Arrays.stream(expectedTokens).anyMatch(expected -> expected == tokenType);
    }

    private Token advance() {
        Token token = tokens.get(pos);
        if (token.tokenType() != TokenType.EOL) pos++;
        return token;
    }

    private Token peek() {
        return tokens.get(pos);
    }
}
