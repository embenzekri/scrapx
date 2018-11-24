package com.indev.scrapx.scraper;

public class ScrapingException extends RuntimeException {
    public ScrapingException(String s) {
        super(s);
    }

    public ScrapingException(String s, Exception ex) {
        super(s, ex);
    }
}
