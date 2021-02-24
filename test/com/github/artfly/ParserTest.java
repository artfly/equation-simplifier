package com.github.artfly;

import com.github.artfly.lexer.Lexer;
import com.github.artfly.lexer.Token;
import com.github.artfly.parser.Parser;
import com.github.artfly.parser.model.Const;
import com.github.artfly.parser.model.Expression;
import com.github.artfly.parser.model.Negated;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

public class ParserTest {

    @Test
    public void testConst() {
        List<Token> tokens = createTokens("23");
        Expression expression = parseExpression(tokens);
        Assert.assertEquals(new Const(23), expression);
    }

    @Test
    public void testNegatedConst() {
        List<Token> tokens = createTokens("-23");
        Expression expression = parseExpression(tokens);
        Assert.assertEquals(new Negated(new Const(23)), expression);
    }

    private static Expression parseExpression(List<Token> tokens) {
        List<Expression> expressions = new Parser(tokens).parse();
        Assert.assertEquals(1, expressions.size());
        return expressions.get(0);
    }

    private List<Token> createTokens(String expressionText) {
        BufferedReader br = new BufferedReader(new StringReader(expressionText));
        Lexer lexer = new Lexer(br);
        return lexer.scan();
    }
}
