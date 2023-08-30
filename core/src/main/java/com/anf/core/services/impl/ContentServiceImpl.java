package com.anf.core.services.impl;

import com.day.cq.commons.jcr.JcrConstants;
import com.anf.core.constants.GlobalConstants;
import com.anf.core.services.ContentService;
import com.anf.core.utils.ResourceResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.Session;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

// ***Begin Code - Sheetal Salam*** 

@Component(immediate = true, service = ContentService.class)
public class ContentServiceImpl implements ContentService {

    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    ResourceResolver resourceResolver;
    Session session;

    @Override
    public boolean commitUserDetails(String firstname, String lastname, int age, String country) {

        int[] ageRange = getAge();
        int count = 0;

        if (age >= ageRange[0] && age <= ageRange[1]) {
            try (ResourceResolver resolver = ResourceResolverUtil.newResolver(resourceResolverFactory)) {
                Resource userNode = ResourceUtil.getOrCreateResource(resolver, GlobalConstants.USER_DETAILS_PATH,
                        JcrConstants.NT_UNSTRUCTURED, null, Boolean.TRUE);

                if (userNode != null) {
                    Iterator<Resource> userItem = userNode.listChildren();
                    if (userItem.hasNext()) {
                        while (userItem.hasNext()) {
                            count++;
                            userItem.next();
                        }
                    }
                    try {
                        Map<String, Object> userProperties = new HashMap<>();
                        userProperties.put(GlobalConstants.USER_FIRSTNAME, firstname);
                        userProperties.put(GlobalConstants.USER_LASTNAME, lastname);
                        userProperties.put(GlobalConstants.USER_AGE, age);
                        userProperties.put(GlobalConstants.USER_COUNTRY, country);
                        userProperties.put(JcrConstants.JCR_PRIMARYTYPE, JcrConstants.NT_UNSTRUCTURED);
                        Resource userResource = resourceResolver.create(userNode, "user" + (count + 1), userProperties);
                    } catch (Exception e) {
                        LOG.info("Exception {}", e);
                    }
                    resourceResolver.commit();

                } else {
                    LOG.info("user node is null");
                }
            } catch (Exception e) {
                LOG.error("commitUserDetails {}", e);
            }
            return true;
        } else {
            LOG.info("age not accepted {}", age);
            return false;
        }
    }

    public int[] getAge() {
        int[] ages = new int[2];
        try {
            resourceResolver = ResourceResolverUtil.newResolver(resourceResolverFactory);
            Resource ageResourcePath = resourceResolver.getResource(GlobalConstants.AGES_PATH);
            Node ageNode = ageResourcePath.adaptTo(Node.class);
            int maxAge = Integer.parseInt(ageNode.getProperty(GlobalConstants.PROP_MAX_AGE).getString());
            int minAge = Integer.parseInt(ageNode.getProperty(GlobalConstants.PROP_MIN_AGE).getString());
            ages[0] = minAge;
            ages[1] = maxAge;
        } catch (Exception e) {
            LOG.info("Exception {}", e);
        }
        return ages;
    }
}
// ***END Code*****