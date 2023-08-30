package com.anf.core.servlets;

import com.anf.core.constants.GlobalConstants;
import com.anf.core.services.ContentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

// ***Begin Code - Sheetal Salam*** 

@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/saveUserDetails")
public class UserServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private ContentService contentService;

    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        // Make use of ContentService to write the business logic
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
            PrintWriter out = resp.getWriter();
        try {
            JSONObject requestData = new JSONObject(req.getReader().readLine());

            String firstname = requestData.optString(GlobalConstants.USER_FIRSTNAME);
            String lastname = requestData.optString(GlobalConstants.USER_LASTNAME);
            int age = requestData.optInt(GlobalConstants.USER_AGE);
            String country = requestData.optString(GlobalConstants.USER_COUNTRY);

            if (contentService.commitUserDetails(firstname, lastname, age, country)) {
                out.print(new JSONObject("{'Message':'User added successfully'}"));
            } else {
                out.print(new JSONObject("{'errorMessage':'You are not eligible'}"));
            }
        } catch (Exception e) {
            LOG.info("Exception {}", e);
        }
    }
}
// ***END Code*****