package com.github.artfly.simplifier;

import com.github.artfly.parser.model.*;

public class AdditiveConstantFolder implements ExpressionTransformer {

    public Expression fold(Expression expression) {
        return addConstant(expression.apply(this));
    }

    private int value;

    @Override
    public Expression visit(Binary binary) {
        Operation operation = binary.operation();
        Expression lhs = binary.lhs().apply(this);
        Expression rhs = binary.rhs().apply(this);
        if (lhs instanceof Const && rhs instanceof Const) {
            return new Const(operation.apply(((Const) lhs).value(), ((Const) rhs).value()));
        }
        if (operation != Operation.ADD && operation != Operation.SUBTRACT) {
            return addConstant(new Binary(operation, lhs, rhs));
        }
        if (lhs instanceof Const c) {
            value += c.value();
            return rhs;
        }
        if (rhs instanceof Const c) {
            value += operation == Operation.ADD ? c.value() : -c.value();
            return lhs;
        }
        return new Binary(operation, lhs, rhs);
    }

    private Expression addConstant(Expression expression) {
        if (value == 0) return expression;
        Binary binary = new Binary(Operation.ADD, expression, new Const(value));
        value = 0;
        return binary;
    }

    @Override
    public Expression visit(Negated negated) {
        return new Negated(negated.expression().apply(this));
    }

    @Override
    public Expression visit(Var v) {
        return v;
    }

    @Override
    public Expression visit(Const c) {
        return c;
    }
}
