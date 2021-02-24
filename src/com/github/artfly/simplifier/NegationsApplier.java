package com.github.artfly.simplifier;

import com.github.artfly.parser.model.*;

class NegationsApplier implements ExpressionTransformer {

    private boolean isNegated;

    @Override
    public Expression visit(Binary binary) {
        Operation operation = binary.operation();
        Expression lhs = binary.lhs().apply(this);
        if (operation != Operation.SUBTRACT) {
            Expression rhs = binary.rhs().apply(this);
            return new Binary(operation, lhs, rhs);
        }
        isNegated = !isNegated;
        operation = Operation.ADD;
        Expression rhs = binary.rhs().apply(this);
        isNegated = !isNegated;
        return new Binary(operation, lhs, rhs);
    }

    @Override
    public Expression visit(Negated negated) {
        isNegated = !isNegated;
        Expression expression = negated.expression().apply(this);
        isNegated = !isNegated;
        return expression;
    }

    @Override
    public Expression visit(Var v) {
        return isNegated ? new Negated(v) : v;
    }

    @Override
    public Expression visit(Const c) {
        return isNegated ? new Const(-c.value()) : c;
    }
}
