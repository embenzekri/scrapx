package com.indev.scrapx;

import com.google.common.net.UrlEscapers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by elmahdibenzekri on 15/01/2018.
 */
public class URLUtils {
    public static String encodeURL(String rawUrl) {
        try {
            URL url = new URL(rawUrl);
            StringBuilder encodedUrl = new StringBuilder(url.getProtocol());
            String path = url.getPath().substring(1, url.getPath().length());
            String escapedPath = Arrays.stream(path.split("/")).map(pathSegment -> UrlEscapers.urlPathSegmentEscaper().escape(pathSegment)).collect(Collectors.joining("/"));
            encodedUrl.append("://").append(url.getHost()).append("/").append(escapedPath);
            if (url.getQuery() != null) {
                encodedUrl.append("?").append(url.getQuery());
            }
            return encodedUrl.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return rawUrl;
        }
    }
}
