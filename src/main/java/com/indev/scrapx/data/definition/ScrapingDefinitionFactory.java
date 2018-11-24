package com.indev.scrapx.data.definition;

import com.indev.scrapx.data.ScrapedElement;
import com.indev.scrapx.data.ScrapedText;
import com.indev.scrapx.data.ScrapingUrl;
import com.indev.scrapx.data.manager.ScrapedDTOManager;
import com.indev.scrapx.scraper.ScraperApi;
import com.indev.scrapx.scraper.ScraperService;
import com.indev.scrapx.scraper.ScrapingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ScrapingDefinitionFactory {
    private static ScrapedDTOManager scrapedDTOManager = new ScrapedDTOManager();
    private static ScraperService scrapingService = new ScraperService();

    public static <T> ScrapingDefinition of(Class<T> searchListScraperClass) {
        Annotation[] declaredAnnotations = searchListScraperClass.getAnnotations();
        Optional<String> scrapingUrl = Optional.empty();
        Optional<String> elementQuery = Optional.empty();
        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof ScrapedElement) {
                elementQuery = Optional.ofNullable(((ScrapedElement) annotation).value());
            }
            if (annotation instanceof ScrapingUrl) {
                scrapingUrl = Optional.ofNullable(((ScrapingUrl) annotation).value());
            }
        }
        if (!scrapingUrl.isPresent()) {
            throw new ScrapingException(searchListScraperClass + " should be annotated with @ScrapingUrl");
        }

        if (!elementQuery.isPresent()) {
            elementQuery = Optional.of("body");
        }
        ScrapingDefinition scrapingDefinition = new ScrapingDefinition(new ScraperApi(scrapingUrl.get(), scrapingService), searchListScraperClass, elementQuery.get());

        Map<String, String> fields = new HashMap<>();
        for (Field field : searchListScraperClass.getDeclaredFields()) {
            for (Annotation fieldAnnotation : field.getAnnotations()) {
                if (fieldAnnotation instanceof ScrapedText) {
                    String fieldQuery = ((ScrapedText) fieldAnnotation).value();
                    fields.put(field.getName(), fieldQuery);
                }
            }
        }

        scrapingDefinition.setFields(fields);
        scrapingDefinition.setScrapedDTOManager(scrapedDTOManager);
        return scrapingDefinition;
    }
}
