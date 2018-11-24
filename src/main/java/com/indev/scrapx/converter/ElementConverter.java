package com.indev.scrapx.converter;

import org.jsoup.nodes.Element;

import java.io.IOException;

@FunctionalInterface
public interface ElementConverter<T> {
    T convert(Element element) throws IOException;
}
