package com.anf.core.listeners;
import com.anf.core.constants.GlobalConstants;
import java.util.List;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Reference;
import com.anf.core.utils.ResourceResolverUtil;

// ***Begin Code - Sheetal Salam***

@Component(service = ResourceChangeListener.class, immediate = true, property = {
        ResourceChangeListener.PATHS + "=" +GlobalConstants.PAGE_CREATION_PATH,
        ResourceChangeListener.CHANGES + "=" + GlobalConstants.PAGE_CREATION_ADDED
})
public class PageCreationHandler implements ResourceChangeListener {

    private static final Logger LOG = LoggerFactory.getLogger(PageCreationHandler.class);
    private static final String ErrorMsg = GlobalConstants.PAGE_CREATION_ERRORMSG + " {}";

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public void onChange(List<ResourceChange> list) {
        for (ResourceChange resourceChange : list) {
            try (ResourceResolver resourceResolver = ResourceResolverUtil.newResolver(resourceResolverFactory)){
                Resource resource = resourceResolver.getResource(resourceChange.getPath());
                if (resource != null) {
                    Node node = resource.adaptTo(Node.class);
                    if (node != null && node.isNodeType(GlobalConstants.PAGE_PROPERTY_CQ_PAGE)) {
                        ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
                        if (valueMap != null) {
                            valueMap.put(GlobalConstants.PAGE_PROPERTY_PAGE_CREATED, true);
                            resourceResolver.commit();
                        }
                    }
                }
            }catch (Exception e) {
                LOG.info(ErrorMsg, e.getMessage());
            }            
        }
    }
}
// ***END Code*****