package com.anf.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
public class NewsFeedModelTest {

    private final AemContext aemContext = new AemContext();

    @Mock
    private Resource resource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private ValueMap valueMap;

    @InjectMocks
    private NewsFeedModel newsFeedModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aemContext.registerService(ResourceResolver.class, resourceResolver);
        newsFeedModel.resourceResolver = resourceResolver;
    }

    @Test
    void testInitMethod() {
        List<Resource> newsResources = new ArrayList<>();
        newsResources.add(resource);
        
        // Fix the stubbing for the getResource method call to handle any string argument
        when(resourceResolver.getResource(ArgumentMatchers.notNull())).thenReturn(resource);    
    
    when(resource.listChildren()).thenReturn(newsResources.iterator());
    when(resource.getValueMap()).thenReturn(valueMap);

        News expectedNews = new News();
        expectedNews.setAuthor("Author");
        expectedNews.setContent("Content");
        expectedNews.setDescription("Description");
        expectedNews.setTitle("Title");
        expectedNews.setUrl("URL");
        expectedNews.setUrlImage("URL Image");

        when(valueMap.get("author", String.class)).thenReturn(expectedNews.getAuthor());
        when(valueMap.get("content", String.class)).thenReturn(expectedNews.getContent());
        when(valueMap.get("description", String.class)).thenReturn(expectedNews.getDescription());
        when(valueMap.get("title", String.class)).thenReturn(expectedNews.getTitle());
        when(valueMap.get("url", String.class)).thenReturn(expectedNews.getUrl());
        when(valueMap.get("urlImage", String.class)).thenReturn(expectedNews.getUrlImage());

        newsFeedModel.init();
        assertEquals(1, newsFeedModel.getNewsList().size());
        assertEquals(expectedNews, newsFeedModel.getNewsList().get(0));
    }
}

// ***END Code*****