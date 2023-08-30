package com.anf.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

// ***Begin Code - Sheetal Salam*** 

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/fetchPagesQuerybuilder"
})
public class FetchPageQueryServlet extends SlingAllMethodsServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchPageQueryServlet.class);

    @Reference
    private QueryBuilder queryBuilder;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("type", "cq:Page");
        queryParams.put("path", "/content/anf-code-challenge/us/en");
        queryParams.put("property", "jcr:content/anfCodeChallenge");
        queryParams.put("property.operation", "exists");
        queryParams.put("orderby", "@jcr:content/jcr:created");
        queryParams.put("orderby.sort", "asc");
        queryParams.put("p.limit", "10");

        try {

            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(PredicateGroup.create(queryParams), session);
            SearchResult result = query.getResult();

            Iterator<Hit> hits = result.getHits().iterator();
            while (hits.hasNext()) {
                Hit hit = hits.next();
                Resource resource = hit.getResource();
                String pageName = resource.getName();
                response.getWriter().write("Page : " + pageName + "<br>");
            }

            resourceResolver.close();
        } catch (Exception e) {
            LOGGER.error("Error executing query", e);
            response.getWriter().write("An error occurred.");
        }
    }
}
// ***END Code*****