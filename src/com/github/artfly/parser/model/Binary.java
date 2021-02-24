package com.github.artfly.parser.model;

import com.github.artfly.ExpressionVisitor;

public record Binary(Operation operation, Expression lhs, Expression rhs) implements Expression {
    @Override
    public <T> T apply(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}