package org.dddjava.jig.domain.model.services;

import org.dddjava.jig.domain.model.controllers.ControllerMethods;
import org.dddjava.jig.domain.model.datasources.DatasourceMethods;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.MethodDeclaration;
import org.dddjava.jig.domain.model.implementation.analyzed.declaration.method.MethodDeclarations;
import org.dddjava.jig.domain.model.implementation.analyzed.networks.method.MethodRelations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * サービスの切り口一覧
 */
public class ServiceAngles {

    List<ServiceAngle> list;

    private ServiceAngles(List<ServiceAngle> list) {
        this.list = list;
    }

    public List<ServiceAngle> list() {
        return list;
    }

    public ServiceAngles(ServiceMethods serviceMethods, MethodRelations methodRelations, ControllerMethods controllerMethods, DatasourceMethods datasourceMethods) {
        List<ServiceAngle> list = new ArrayList<>();
        for (ServiceMethod serviceMethod : serviceMethods.list()) {
            list.add(new ServiceAngle(serviceMethod, methodRelations, controllerMethods, serviceMethods, datasourceMethods));
        }
        this.list = list;
    }

    public ServiceAngles filterReturnsBoolean() {
        List<ServiceAngle> collect = list.stream()
                .filter(serviceAngle -> serviceAngle.method().methodReturn().isBoolean())
                .collect(Collectors.toList());
        return new ServiceAngles(collect);
    }

    public MethodDeclarations userServiceMethods() {
        return list.stream()
                .flatMap(serviceAngle -> serviceAngle.userServiceMethods().list().stream())
                .distinct()
                .collect(MethodDeclarations.collector());
    }

    public MethodDeclarations userControllerMethods() {
        return list.stream()
                .flatMap(serviceAngle -> serviceAngle.userControllerMethods().list().stream())
                .distinct()
                .collect(MethodDeclarations.collector());
    }

    public boolean notContains(MethodDeclaration methodDeclaration) {
        return list.stream()
                .noneMatch(serviceAngle -> serviceAngle.method().sameIdentifier(methodDeclaration));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
