package org.dddjava.jig.domain.model.implementation.raw;

import java.util.List;

/**
 * バイナリソース一覧
 */
public class BinarySources {

    List<BinarySource> list;

    public BinarySources(List<BinarySource> list) {
        this.list = list;
    }

    public BinarySource toBinarySource() {
        return list.stream().reduce(new BinarySource(), BinarySource::merge);
    }

    List<BinarySource> list() {
        return list;
    }

    public boolean nothing() {
        return list.isEmpty();
    }
}
