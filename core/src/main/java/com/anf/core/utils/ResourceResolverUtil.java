package com.anf.core.utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.anf.core.constants.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

// ***Begin Code - Sheetal Salam***

public class ResourceResolverUtil {
    private ResourceResolverUtil() {

    }

	public static final String ADMIN_USER = GlobalConstants.SUBSERVICE_USER;
    /**
     * @param  resourceResolverFactory 
     * @return 
     * @throws LoginException 
     */

    public static ResourceResolver newResolver( ResourceResolverFactory resourceResolverFactory ) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put( ResourceResolverFactory.SUBSERVICE, ADMIN_USER);
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
        return resolver;
    }
}

// ***END Code*****