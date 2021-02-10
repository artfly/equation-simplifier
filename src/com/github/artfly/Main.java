package com.github.artfly;

import com.github.artfly.lexer.Lexer;

import java.io.BufferedReader;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        String s = """
                (a + b)
                c + de
                """;
        BufferedReader br = new BufferedReader(new StringReader(s));
        Lexer lexer = new Lexer(br);
        System.out.println(lexer.scan());
    }
}
