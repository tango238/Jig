package org.dddjava.jig.domain.model.implementation.relation.dependency;

public class DependencyNumber {
    int value;

    public DependencyNumber(int value) {
        this.value = value;
    }

    public String asText() {
        return Integer.toString(value);
    }
}