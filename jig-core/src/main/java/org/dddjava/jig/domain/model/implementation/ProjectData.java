package org.dddjava.jig.domain.model.implementation;

import org.dddjava.jig.domain.model.characteristic.CharacterizedMethods;
import org.dddjava.jig.domain.model.characteristic.CharacterizedTypeFactory;
import org.dddjava.jig.domain.model.characteristic.CharacterizedTypes;
import org.dddjava.jig.domain.model.declaration.annotation.FieldAnnotations;
import org.dddjava.jig.domain.model.declaration.annotation.MethodAnnotations;
import org.dddjava.jig.domain.model.declaration.annotation.TypeAnnotations;
import org.dddjava.jig.domain.model.declaration.field.FieldDeclarations;
import org.dddjava.jig.domain.model.declaration.field.StaticFieldDeclarations;
import org.dddjava.jig.domain.model.declaration.method.Methods;
import org.dddjava.jig.domain.model.declaration.type.Types;
import org.dddjava.jig.domain.model.implementation.bytecode.ImplementationMethods;
import org.dddjava.jig.domain.model.implementation.bytecode.MethodRelations;
import org.dddjava.jig.domain.model.implementation.bytecode.MethodUsingFields;
import org.dddjava.jig.domain.model.implementation.bytecode.TypeByteCodes;
import org.dddjava.jig.domain.model.implementation.datasource.Sqls;
import org.dddjava.jig.domain.model.networks.type.TypeDependencies;
import org.dddjava.jig.domain.model.values.ValueTypes;

/**
 * プロジェクトから読み取った情報
 */
public class ProjectData {

    private Types types;
    // メソッド
    private Methods methods;
    // フィールド
    private StaticFieldDeclarations staticFieldDeclarations;
    private FieldDeclarations fieldDeclarations;
    // アノテーション
    private TypeAnnotations typeAnnotations;
    private FieldAnnotations fieldAnnotations;
    private MethodAnnotations methodAnnotations;

    // データソースアクセス
    private ImplementationMethods implementationMethods;
    private Sqls sqls;

    // 関連
    private MethodRelations methodRelations;
    private MethodUsingFields methodUsingFields;
    private TypeDependencies typeDependencies;

    // 特徴とセットになったもの
    private ValueTypes valueTypes;
    private CharacterizedTypes characterizedTypes;
    private CharacterizedMethods characterizedMethods;

    public ProjectData(TypeByteCodes typeByteCodes, Sqls sqls, CharacterizedTypeFactory characterizedTypeFactory) {
        this.types = typeByteCodes.types();
        this.methods = typeByteCodes.instanceMethods();

        this.typeAnnotations = typeByteCodes.typeAnnotations();
        this.fieldAnnotations = typeByteCodes.annotatedFields();
        this.methodAnnotations = typeByteCodes.annotatedMethods();

        this.fieldDeclarations = typeByteCodes.instanceFields();
        this.staticFieldDeclarations = typeByteCodes.staticFields();

        this.implementationMethods = new ImplementationMethods(typeByteCodes);

        this.methodRelations = new MethodRelations(typeByteCodes);
        this.methodUsingFields = new MethodUsingFields(typeByteCodes);
        this.typeDependencies = new TypeDependencies(typeByteCodes);

        CharacterizedTypes characterizedTypes = new CharacterizedTypes(typeByteCodes, characterizedTypeFactory);
        this.characterizedTypes = characterizedTypes;
        this.characterizedMethods = new CharacterizedMethods(typeByteCodes.instanceMethodByteCodes(), characterizedTypes);
        this.valueTypes = new ValueTypes(typeByteCodes);

        this.sqls = sqls;
    }

    public ImplementationMethods implementationMethods() {
        return implementationMethods;
    }

    public MethodRelations methodRelations() {
        return methodRelations;
    }

    public Sqls sqls() {
        return sqls;
    }

    public CharacterizedTypes characterizedTypes() {
        return characterizedTypes;
    }

    public TypeDependencies typeDependencies() {
        return typeDependencies;
    }

    public FieldDeclarations fieldDeclarations() {
        return fieldDeclarations;
    }

    public StaticFieldDeclarations staticFieldDeclarations() {
        return staticFieldDeclarations;
    }

    public ValueTypes valueTypes() {
        return valueTypes;
    }

    public MethodUsingFields methodUsingFields() {
        return methodUsingFields;
    }

    public CharacterizedMethods characterizedMethods() {
        return characterizedMethods;
    }

    public TypeAnnotations typeAnnotations() {
        return typeAnnotations;
    }

    public FieldAnnotations fieldAnnotations() {
        return fieldAnnotations;
    }

    public MethodAnnotations methodAnnotations() {
        return methodAnnotations;
    }

    public Types types() {
        return types;
    }

    public Methods methods() {
        return methods;
    }

    public Methods controllerMethods() {
        return methods().controllerMethods(characterizedTypes);
    }
}
