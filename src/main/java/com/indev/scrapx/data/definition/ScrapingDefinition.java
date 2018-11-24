package com.indev.scrapx.data.definition;

import com.indev.scrapx.scraper.ScraperApi;
import com.indev.scrapx.data.manager.ScrapedDTOManager;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingDefinition<T> {
    private Class<T> resultObjectClass;
    private String elementQuery;
    private Map<String, String> fields = new HashMap<>();
    private ScrapedDTOManager scrapedDTOManager;
    private ScraperApi scraperApi;


    public ScrapingDefinition(ScraperApi scraperApi, Class<T> resultObjectClass, String elementQuery) {
        this.scraperApi = scraperApi;
        this.resultObjectClass = resultObjectClass;
        this.elementQuery = elementQuery;
    }

    public T scrap() {
        return scraperApi.forFirst(elementQuery, elements -> {
            final T newObject = convert(elements.get(0));
            return newObject;
        });
    }


    public List<T> scrapAll() {
        return scraperApi.forEach(elementQuery, element -> {
            final T newObject = convert(element);
            return newObject;
        });
    }

    private T convert(Element element) {
        Map<String, String> fieldValues = new HashMap<>();
        for (String fieldName : fields.keySet()) {
            String value = element.select(fields.get(fieldName)).text();
            fieldValues.put(fieldName, value);
        }
        return scrapedDTOManager.createNew(resultObjectClass, fieldValues);
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public ScrapedDTOManager getScrapedDTOManager() {
        return scrapedDTOManager;
    }

    public void setScrapedDTOManager(ScrapedDTOManager scrapedDTOManager) {
        this.scrapedDTOManager = scrapedDTOManager;
    }
}
