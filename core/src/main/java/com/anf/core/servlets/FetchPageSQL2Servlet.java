package com.anf.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

// ***Begin Code - Sheetal Salam*** 

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/fetchPagessql2query" })
public class FetchPageSQL2Servlet extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchPageSQL2Servlet.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        String sqlQuery = "SELECT * FROM [cq:Page] AS page "
        + "WHERE ISDESCENDANTNODE(page ,\"/content/anf-code-challenge/us/en\") "
        + "AND [jcr:content/anfCodeChallenge] = \"true\" "
        + "ORDER BY page.[jcr:created] ASC";
        
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);

            Query query = session.getWorkspace().getQueryManager().createQuery(sqlQuery, Query.JCR_SQL2);
            query.setLimit(10);
            QueryResult result = query.execute();

            Iterator<Node> nodes = result.getNodes();
            while (nodes.hasNext()) {
                Node pageNode = nodes.next();
                String pageTitle = pageNode.getName();
                response.getWriter().write("Page Title: " + pageTitle + "<br>");
            }

            resourceResolver.close();
        } catch (RepositoryException e) {
            LOGGER.error("Error executing query", e);
            response.getWriter().write("An error occurred.");
        }
    }
}

