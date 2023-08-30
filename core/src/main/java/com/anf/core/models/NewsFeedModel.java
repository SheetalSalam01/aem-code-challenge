package com.anf.core.models;

import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// ***Begin Code - Sheetal Salam*** 

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsFeedModel {

    @ValueMapValue
    @Default(values = "/var/commerce/products/anf-code-challenge/newsData")
    public String newsFeedPath;

    @SlingObject
    public ResourceResolver resourceResolver;

    private List<News> newsList = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource resource = resourceResolver.getResource(newsFeedPath);
        if (resource != null) {
            Iterator<Resource> newsitem = resource.listChildren();
            while (newsitem.hasNext()) {
                Resource newsResource = newsitem.next();
                newsList.add(getNewsItem(newsResource));
            }
        }
    }

    private News getNewsItem(Resource newsResource) {
        ValueMap valueMap = newsResource.getValueMap();

        News news = new News();
        news.setAuthor(valueMap.get("author", String.class));
        news.setContent(valueMap.get("content", String.class));
        news.setDescription(valueMap.get("description", String.class));
        news.setTitle(valueMap.get("title", String.class));
        news.setUrl(valueMap.get("url", String.class));
        news.setUrlImage(valueMap.get("urlImage", String.class));
        return news;
    }

    public String getNewsFeedPath() {
        return newsFeedPath;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
// ***END Code*****