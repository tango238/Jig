package org.dddjava.jig.presentation.view.graphvizj;

import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.MethodDeclaration;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.type.TypeIdentifier;
import org.dddjava.jig.domain.model.implementation.analyzed.japanese.JapaneseNameFinder;
import org.dddjava.jig.domain.model.implementation.analyzed.japanese.TypeJapaneseName;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;

public enum MethodNodeLabelStyle {
    /** method(ArgumentTypes) : ReturnType */
    SIMPLE,
    /** method(引数型) : 戻り値型 */
    JAPANESE;

    public String apply(MethodDeclaration method, JapaneseNameFinder japaneseNameFinder) {
        if (this == JAPANESE) {
            Function<TypeIdentifier, String> func = typeIdentifier -> {
                TypeJapaneseName typeJapaneseName = japaneseNameFinder.find(typeIdentifier);
                if (typeJapaneseName.exists()) {
                    return typeJapaneseName.japaneseName().summarySentence();
                }
                return typeIdentifier.asSimpleText();
            };

            return method.methodSignature().methodName()
                    + "("
                    + method.methodSignature().arguments().stream().map(func).collect(joining(", "))
                    + ")"
                    + " : "
                    + func.apply(method.methodReturn().typeIdentifier());
        }

        return method.asSignatureSimpleText() + " : " + method.methodReturn().typeIdentifier().asSimpleText();
    }

    public String typeNameAndMethodName(MethodDeclaration methodDeclaration, JapaneseNameFinder japaneseNameFinder) {
        return methodDeclaration.declaringType().asSimpleText() + "." + apply(methodDeclaration, japaneseNameFinder);
    }
}
