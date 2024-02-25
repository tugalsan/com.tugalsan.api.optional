package com.tugalsan.api.optional.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TGS_OptionalBoolean {

    private TGS_OptionalBoolean(TGS_Optional< Boolean> driver) {
        this.payload = driver.payload;
        this.info = driver.info;
    }
    final public Optional<Boolean> payload;
    public List<CharSequence> info = new ArrayList();

    public static TGS_OptionalBoolean ofTrue() {
        return new TGS_OptionalBoolean(TGS_Optional.of(true));
    }

    public static TGS_OptionalBoolean ofFalse(Exception... newInfo) {
        var op = TGS_Optional.of(false);
        op.addInfo(newInfo);
        return new TGS_OptionalBoolean(op);
    }

    public static TGS_OptionalBoolean ofFalse(CharSequence... newInfo) {
        var op = TGS_Optional.of(false);
        op.addInfo(newInfo);
        return new TGS_OptionalBoolean(op);
    }

    public CharSequence getFirstInfoOr(CharSequence noInfo) {
        return info.isEmpty() ? noInfo : info.getFirst();
    }

    public boolean orThrowFirstInfo() {
        if (payload.isEmpty()) {
            if (info.isEmpty()) {
                throw new RuntimeException("info is empty");
            }
            throw new RuntimeException(info.getFirst().toString());
        }
        return payload.get();
    }

    public boolean orThrowAllInfo() {
        if (payload.isEmpty()) {
            if (info.isEmpty()) {
                throw new RuntimeException("info is empty");
            }
            throw new RuntimeException(String.join(" | ", info));
        }
        return payload.get();
    }

    public boolean orElse(Boolean elseIfEmpty) {
        return payload.isEmpty() ? elseIfEmpty : payload.get();
    }

    @Override
    public String toString() {
        return TGS_OptionalBoolean.class.getSimpleName() + "{" + "payload=" + payload + ", info=" + info + '}';
    }
}
