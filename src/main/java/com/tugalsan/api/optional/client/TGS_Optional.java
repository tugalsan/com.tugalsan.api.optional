package com.tugalsan.api.optional.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TGS_Optional<T> {

    private TGS_Optional() {
        payload = Optional.empty();
    }

    private TGS_Optional(T value) {
        payload = Optional.of(value);
    }
    final public Optional payload;
    public List<? extends CharSequence> info = new ArrayList();

    public static <T> TGS_Optional<T> of(T value) {
        return new TGS_Optional(value);
    }

    public static <T> TGS_Optional<T> ofEmpty() {
        return new TGS_Optional();
    }
}
