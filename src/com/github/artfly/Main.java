package com.github.artfly;

import com.github.artfly.lexer.Lexer;
import com.github.artfly.lexer.Token;
import com.github.artfly.parser.Parser;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String s = """
                -x^2 + -y + (3 + 7) * z^(2^3)
                x
                """;
        BufferedReader br = new BufferedReader(new StringReader(s));
        Lexer lexer = new Lexer(br);
        List<Token> tokens = lexer.scan();
        Parser parser = new Parser(tokens);
        System.out.println(parser.parse());
    }
}
