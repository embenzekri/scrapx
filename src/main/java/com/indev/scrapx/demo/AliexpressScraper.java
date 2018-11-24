package com.indev.scrapx.demo;

import com.indev.scrapx.data.definition.ScrapingDefinition;
import com.indev.scrapx.data.definition.ScrapingDefinitionFactory;

import java.util.List;

public class AliexpressScraper {

    public static void main(String[] args) {
        ScrapingDefinition<SearchItemScraper> definition = ScrapingDefinitionFactory.of(SearchItemScraper.class);
        SearchItemScraper result = definition.scrap();
        List<SearchItemScraper> results = definition.scrapAll();

        System.out.println(result);
        System.out.println(results.size());
    }
}
