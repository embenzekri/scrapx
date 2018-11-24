package com.indev.scrapx.data.manager;

import com.indev.scrapx.scraper.ScrapingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ScrapedDTOManager {
    public <T> T createNew(Class<T> resultObjectClass, Map<String, String> fieldValues) {
        final T newObject;
        try {
            newObject = resultObjectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ScrapingException("Cannot instantiate " + resultObjectClass, ex);
        }
        for (String fieldName : fieldValues.keySet()) {

            try {
                Method declaredMethod = resultObjectClass.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                declaredMethod.invoke(newObject, fieldValues.get(fieldName));
            } catch (NoSuchMethodException e) {
                throw new ScrapingException("Setter not found for field " + fieldName + " in " + resultObjectClass);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new ScrapingException("Error calling setter for field " + fieldName + " in " + resultObjectClass);
            }
        }
        return newObject;
    }
}
