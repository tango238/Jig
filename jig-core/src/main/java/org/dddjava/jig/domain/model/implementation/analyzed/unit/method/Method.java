package org.dddjava.jig.domain.model.implementation.analyzed.unit.method;

import org.dddjava.jig.domain.model.implementation.analyzed.bytecode.MethodByteCode;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.annotation.MethodAnnotations;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.Accessor;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.DecisionNumber;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.MethodDeclaration;

/**
 * メソッド
 */
public class Method {

    MethodDeclaration methodDeclaration;
    DecisionNumber decisionNumber;
    MethodAnnotations methodAnnotations;
    Accessor accessor;
    UsingFields usingFields;
    UsingMethods usingMethods;

    public Method(MethodByteCode methodByteCode) {
        this.methodDeclaration = methodByteCode.methodDeclaration();
        this.decisionNumber = methodByteCode.decisionNumber();
        this.methodAnnotations = methodByteCode.annotatedMethods();
        this.accessor = methodByteCode.accessor();
        this.usingFields = new UsingFields(methodByteCode.usingFields().list());
        this.usingMethods = new UsingMethods(methodByteCode.usingMethods());
    }

    public MethodDeclaration declaration() {
        return methodDeclaration;
    }

    public DecisionNumber decisionNumber() {
        return decisionNumber;
    }

    public MethodAnnotations methodAnnotations() {
        return methodAnnotations;
    }

    public boolean isPublic() {
        return accessor.isPublic();
    }

    public UsingFields usingFields() {
        return usingFields;
    }

    public UsingMethods usingMethods() {
        return usingMethods;
    }
}
