package org.dddjava.jig.domain.model.decisions;

import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.MethodDeclaration;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.type.TypeIdentifier;

/**
 * 文字列比較の切り口
 */
public class StringComparingAngle {

    MethodDeclaration methodDeclaration;

    public StringComparingAngle(MethodDeclaration methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    public TypeIdentifier declaringType() {
        return methodDeclaration.declaringType();
    }

    public MethodDeclaration methodDeclaration() {
        return methodDeclaration;
    }
}
