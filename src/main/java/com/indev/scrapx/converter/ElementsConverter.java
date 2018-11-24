package com.indev.scrapx.converter;

import org.jsoup.select.Elements;

import java.io.IOException;

@FunctionalInterface
public interface ElementsConverter<T> {
    T convert(Elements elements) throws IOException;
}
