package com.indev.scrapx.demo;

import com.indev.scrapx.data.ScrapedText;
import com.indev.scrapx.data.ScrapingUrl;

@ScrapingUrl("https://fr.aliexpress.com/af/pocophone-f1-leather.html?SearchText=pocophone+f1+leather")
public class SearchListScraper {
    @ScrapedText(".list-item h3 a")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchListScraper{" +
                "title='" + title + '\'' +
                '}';
    }
}
