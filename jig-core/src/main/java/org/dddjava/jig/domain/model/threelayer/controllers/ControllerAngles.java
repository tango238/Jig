package org.dddjava.jig.domain.model.threelayer.controllers;

import org.dddjava.jig.domain.model.angle.unit.method.Method;
import org.dddjava.jig.domain.model.declaration.annotation.TypeAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * コントローラーの切り口一覧
 */
public class ControllerAngles {

    List<ControllerAngle> list;

    public List<ControllerAngle> list() {
        return list;
    }

    public ControllerAngles(ControllerMethods controllerMethods, TypeAnnotations typeAnnotations) {
        List<ControllerAngle> list = new ArrayList<>();
        for (Method method : controllerMethods.list()) {
            list.add(new ControllerAngle(method, typeAnnotations.filter(method.declaration().identifier().declaringType())));
        }
        this.list = list;
    }
}