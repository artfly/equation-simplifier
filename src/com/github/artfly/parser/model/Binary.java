package com.github.artfly.parser.model;

public record Binary(Operation operation, Expression lhs, Expression rhs) implements Expression {}