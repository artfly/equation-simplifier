package com.github.artfly;

import com.github.artfly.parser.model.*;

public interface ExpressionVisitor<T> {

    T visit(Binary binary);

    T visit(Negated negated);

    T visit(Var v);

    T visit(Const c);
}