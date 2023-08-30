package com.anf.core.servlets;

import com.anf.core.constants.GlobalConstants;
import com.anf.core.models.KeyValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.adobe.cq.commerce.common.ValueMapDecorator;

import com.day.cq.commons.jcr.JcrConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

// ***Begin Code - Sheetal Salam*** 

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/dropdownListing")
public class CountriesDropdownServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesDropdownServlet.class);
    private static final String PROP_VALUE = "value";
    private static final String PROP_TEXT = "text";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            String countriesFilePath = GlobalConstants.COUNTRY_JSON_PATH;
            ResourceResolver resourceResolver = request.getResourceResolver();
            Resource countriesJsonResource = resourceResolver.getResource(countriesFilePath);
            Resource jsonContentResource = countriesJsonResource.getChild(JcrConstants.JCR_CONTENT);
            List<KeyValue> dropDownCountryList = new ArrayList<>();

            if (jsonContentResource != null) {
                ValueMap valueMap = jsonContentResource.getValueMap();
                String jsonData = valueMap.get(JcrConstants.JCR_DATA, String.class);
                if (jsonData != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(jsonData);
                    jsonNode.fields().forEachRemaining(entry -> {
                        String key = entry.getKey();
                        String value = entry.getValue().asText();
                        dropDownCountryList.add(new KeyValue(key, value));
                    });

                    // Data source object
                    @SuppressWarnings("unchecked")
                    DataSource datasource = new SimpleDataSource(new TransformIterator(dropDownCountryList.iterator(),
                            input -> {
                                KeyValue keyValue = (KeyValue) input;
                                ValueMap vm = new ValueMapDecorator(new HashMap<>());
                                vm.put(PROP_VALUE, keyValue.value);
                                vm.put(PROP_TEXT, keyValue.key);
                                return new ValueMapResource(
                                        resourceResolver, new ResourceMetadata(),
                                        JcrConstants.NT_UNSTRUCTURED, vm);
                            }));
                    request.setAttribute(DataSource.class.getName(), datasource);

                } else {
                    LOG.info("dropdownlist error");

                }
            }
        } catch (Exception e) {
            LOG.info("Countries Exception : {} ", e);
        }
    }

}
// ***END Code*****