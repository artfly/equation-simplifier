package com.github.artfly.parser.model;

import com.github.artfly.ExpressionVisitor;

public interface Expression {
    <T> T apply(ExpressionVisitor<T> visitor);
}
