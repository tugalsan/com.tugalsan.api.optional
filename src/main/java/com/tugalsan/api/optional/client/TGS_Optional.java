package com.tugalsan.api.optional.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TGS_Optional<T> {

    private TGS_Optional() {
        payload = Optional.empty();
    }

    private TGS_Optional(T value) {
        payload = Optional.of(value);
    }
    final public Optional<T> payload;
    public List<CharSequence> info = new ArrayList();

    public static <T> TGS_Optional<T> of(T value) {
        return new TGS_Optional(value);
    }

    public static <T> TGS_Optional<T> ofEmpty() {
        return new TGS_Optional();
    }

    public static <T> TGS_Optional<T> ofEmpty(Exception... newInfo) {
        return new TGS_Optional().addInfo(newInfo);
    }

    public static <T> TGS_Optional<T> ofEmpty(CharSequence... newInfo) {
        return new TGS_Optional().addInfo(newInfo);
    }

    public TGS_Optional<T> addInfo(Exception... newInfo) {
        Arrays.stream(newInfo).filter(info -> info != null).map(e -> {
            var msg = e.getMessage();
            if (msg != null && !msg.isEmpty()) {
                return msg;
            }
            return e.getClass().getName();
        }).forEachOrdered(cs -> info.add(cs));
        return this;
    }

    public TGS_Optional<T> addInfo(CharSequence... newInfo) {
        Arrays.stream(newInfo).filter(info -> info != null).forEachOrdered(cs -> info.add(cs));
        return this;
    }

    public CharSequence getFirstInfoOr(CharSequence noInfo) {
        return info.isEmpty() ? noInfo : info.getFirst();
    }

    public T orThrowFirstInfo() {
        if (payload.isEmpty()) {
            if (info.isEmpty()) {
                throw new RuntimeException("info is empty");
            }
            throw new RuntimeException(info.getFirst().toString());
        }
        return payload.get();
    }

    public T orElse(T elseIfEmpty) {
        return payload.isEmpty() ? elseIfEmpty : payload.get();
    }

    @Override
    public String toString() {
        return TGS_Optional.class.getSimpleName() + "{" + "payload=" + payload + ", info=" + info + '}';
    }
}
