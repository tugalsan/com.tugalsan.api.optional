package com.tugalsan.api.optional.client;

import java.util.List;
import java.util.Optional;

public class class TGS_Optional<T> {

	private TGS_Optional(T value) {
		payload = Optional.of(value);
	}
	final Optional payload;
	public List<? extends CharSequence> info = new ArrayList();
}