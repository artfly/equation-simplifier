package com.github.artfly.parser.model;

import com.github.artfly.ExpressionVisitor;

public record Var(String name) implements Expression {

    @Override
    public <T> T apply(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}