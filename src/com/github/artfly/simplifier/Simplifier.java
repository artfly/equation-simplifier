package com.github.artfly.simplifier;

import com.github.artfly.parser.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Simplifier {

    public static List<Expression> simplify(List<Expression> expressions) {
        return expressions.stream().map(Simplifier::simplify).collect(Collectors.toList());
    }

    private static Expression simplify(Expression expression) {
        ExpressionTransformer negationsTransformer = new NegationsApplier();
        return expression.apply(negationsTransformer);
    }
}
