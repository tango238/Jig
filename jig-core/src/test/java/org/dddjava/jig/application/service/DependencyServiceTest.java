package org.dddjava.jig.application.service;

import org.dddjava.jig.domain.model.implementation.analyzed.bytecode.TypeByteCodes;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.namespace.PackageIdentifier;
import org.dddjava.jig.domain.model.implementation.analyzed.networks.packages.PackageNetwork;
import org.dddjava.jig.domain.model.implementation.raw.RawSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testing.JigTestExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(JigTestExtension.class)
public class DependencyServiceTest {

    @Test
    void パッケージ依存(DependencyService dependencyService, ImplementationService implementationService, RawSource source) {
        TypeByteCodes typeByteCodes = implementationService.readProjectData(source);

        PackageNetwork packageNetwork = dependencyService.packageDependencies(typeByteCodes);

        // パッケージのリストアップ
        List<String> packageNames = packageNetwork.allPackages().stream()
                .map(packageIdentifier -> packageIdentifier.format(value -> value))
                .collect(Collectors.toList());
        assertThat(packageNames)
                .containsExactlyInAnyOrder(
                        "stub.domain.model",
                        "stub.domain.model.booleans",
                        "stub.domain.model.category",
                        "stub.domain.model.relation",
                        "stub.domain.model.relation.clz",
                        "stub.domain.model.relation.method",
                        "stub.domain.model.relation.field",
                        "stub.domain.model.relation.annotation",
                        "stub.domain.model.relation.enumeration",
                        "stub.domain.model.type",
                        "stub.domain.model.type.fuga",
                        "stub.domain.model.relation.constant.to_primitive_constant",
                        "stub.domain.model.relation.constant.to_primitive_wrapper_constant"
                );

        // パッケージの関連
        assertThat(packageNetwork.packageDependencies().list())
                .extracting(dependency -> {
                    PackageIdentifier from = dependency.from();
                    PackageIdentifier to = dependency.to();
                    return from.format(value -> value) + " -> " + to.format(value -> value);
                })
                .containsExactlyInAnyOrder(
                        "stub.domain.model -> stub.domain.model.relation.annotation",
                        "stub.domain.model.relation -> stub.domain.model.relation.clz",
                        "stub.domain.model.relation -> stub.domain.model.relation.field",
                        "stub.domain.model.relation -> stub.domain.model.relation.method",
                        "stub.domain.model.relation -> stub.domain.model.relation.enumeration",
                        "stub.domain.model.relation -> stub.domain.model.relation.constant.to_primitive_wrapper_constant"
                );
    }
}
