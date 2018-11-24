package com.indev.scrapx.demo;

import com.indev.scrapx.data.ScrapedElement;
import com.indev.scrapx.data.ScrapedText;
import com.indev.scrapx.data.ScrapingUrl;

@ScrapingUrl("https://fr.aliexpress.com/af/pocophone-f1-leather.html?SearchText=pocophone+f1+leather")
@ScrapedElement(".list-item")
public class SearchItemScraper {
    @ScrapedText("h3 a")
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
