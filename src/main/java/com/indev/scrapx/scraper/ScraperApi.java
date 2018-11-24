package com.indev.scrapx.scraper;

import com.indev.scrapx.URLUtils;
import com.indev.scrapx.converter.ElementConverter;
import com.indev.scrapx.converter.ElementsConverter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elmahdibenzekri on 02/08/2017.
 */
public class ScraperApi {

    private String url;

    private ScraperService scraperService;

    public ScraperApi(String url, ScraperService scraperService) {
        this.url = URLUtils.encodeURL(url);;
        this.scraperService = scraperService;
        scraperService.refreshPage(url);
    }

    public <T> T forFirst(String query, ElementsConverter<T> converter) {
        try {
            Elements elements = scraperService.getElements(url, query);
            T convert = converter.convert(elements);
            return convert;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ScrapingException("Exception while getting " + query + " from " + url);
        }
    }

    public <T> List<T> forEach(String query, ElementConverter<T> converter) {
        try {
            Elements elements = scraperService.getElements(url, query);
            List<T> result = new ArrayList<>();
            for (Element element : elements) {
                T convert = converter.convert(element);
                result.add(convert);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ScrapingException("Exception while getting " + query + " from " + url);
        }
    }

}
