package com.github.artfly;

import com.github.artfly.parser.model.*;

public class ExpressionPrinter implements ExpressionVisitor<String> {

    public static void print(Expression expression) {
        System.out.println(expression.apply(new ExpressionPrinter()));
    }

    @Override
    public String visit(Binary binary) {
        return String.format("(%s) %s (%s)",
                binary.lhs().apply(this), binary.operation().value(), binary.rhs().apply(this));
    }

    @Override
    public String visit(Negated negated) {
        return "-" + negated.expression().apply(this);
    }

    @Override
    public String visit(Var v) {
        return v.name();
    }

    @Override
    public String visit(Const c) {
        return String.valueOf(c.value());
    }
}
