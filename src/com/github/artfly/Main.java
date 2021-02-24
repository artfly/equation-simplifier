package com.github.artfly;

import com.github.artfly.lexer.Lexer;
import com.github.artfly.lexer.Token;
import com.github.artfly.parser.Parser;
import com.github.artfly.parser.model.Expression;
import com.github.artfly.simplifier.Simplifier;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String s = """
                -(-(x - y))
                -(-(-(-x)))
                """;
        BufferedReader br = new BufferedReader(new StringReader(s));
        Lexer lexer = new Lexer(br);
        List<Token> tokens = lexer.scan();
        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();
        expressions = Simplifier.simplify(expressions);
        expressions.forEach(ExpressionPrinter::print);
    }
}
