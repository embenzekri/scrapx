package com.indev.scrapx.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // the annotation will be available during runtime
@Target(ElementType.FIELD)
public @interface ScrapedText {
    String value();
}
