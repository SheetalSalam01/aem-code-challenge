/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.anftestform;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class anftestform_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_container = null;
Object _global_grid = null;
out.write("<div id=\"anf-test-form\" class=\"anf-test-form\">\n    <h1>ANF FORMS</h1>\n    <p class=\"anf-form-message\" style=\"background-color: rgb(237, 158, 158); display: none;\">You are not eligible</p>\n\n   ");
_global_container = renderContext.call("use", com.adobe.cq.wcm.core.components.models.form.Container.class.getName(), obj());
_global_grid = renderContext.call("use", com.day.cq.wcm.foundation.model.responsivegrid.ResponsiveGrid.class.getName(), obj());
out.write("<form");
{
    Object var_attrvalue0 = renderContext.getObjectModel().resolveProperty(_global_container, "id");
    {
        Object var_attrcontent1 = renderContext.call("xss", var_attrvalue0, "attribute");
        {
            Object var_shoulddisplayattr3 = ((renderContext.getObjectModel().toBoolean(var_attrcontent1) ? var_attrcontent1 : ("false".equals(var_attrvalue0))));
            if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr3)) {
                out.write(" id");
                {
                    boolean var_istrueattr2 = (var_attrvalue0.equals(true));
                    if (!var_istrueattr2) {
                        out.write("=\"");
                        out.write(renderContext.getObjectModel().toString(var_attrcontent1));
                        out.write("\"");
                    }
                }
            }
        }
    }
}
{
    Object var_attrvalue4 = renderContext.getObjectModel().resolveProperty(_global_container, "name");
    {
        Object var_attrcontent5 = renderContext.call("xss", var_attrvalue4, "attribute");
        {
            Object var_shoulddisplayattr7 = ((renderContext.getObjectModel().toBoolean(var_attrcontent5) ? var_attrcontent5 : ("false".equals(var_attrvalue4))));
            if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr7)) {
                out.write(" name");
                {
                    boolean var_istrueattr6 = (var_attrvalue4.equals(true));
                    if (!var_istrueattr6) {
                        out.write("=\"");
                        out.write(renderContext.getObjectModel().toString(var_attrcontent5));
                        out.write("\"");
                    }
                }
            }
        }
    }
}
{
    String var_attrcontent8 = ("cmp-form " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_grid, "cssClass"), "attribute")));
    {
        Object var_shoulddisplayattr9 = ((renderContext.getObjectModel().toBoolean(var_attrcontent8) ? var_attrcontent8 : ("false".equals(var_attrcontent8))));
        if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr9)) {
            out.write(" class=\"");
            out.write(renderContext.getObjectModel().toString(var_attrcontent8));
            out.write("\"");
        }
    }
}
out.write(">\n  \n    <div>");
{
    Object var_resourcecontent10 = renderContext.call("includeResource", "firstname", obj().with("resourceType", "/apps/anf-code-challenge/components/form/text"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent10));
}
out.write("</div>\n    <div>");
{
    Object var_resourcecontent11 = renderContext.call("includeResource", "lastname", obj().with("resourceType", "/apps/anf-code-challenge/components/form/text"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent11));
}
out.write("</div>\n    <div>");
{
    Object var_resourcecontent12 = renderContext.call("includeResource", "age", obj().with("resourceType", "/apps/anf-code-challenge/components/form/text"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent12));
}
out.write("</div>\n   <div>\n     <div>");
{
    Object var_resourcecontent13 = renderContext.call("includeResource", "country", obj().with("resourceType", "/apps/anf-code-challenge/components/form/country"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent13));
}
out.write("</div>\n     </div>\n    <div>");
{
    Object var_resourcecontent14 = renderContext.call("includeResource", "submit", obj().with("resourceType", "/apps/anf-code-challenge/components/form/button"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent14));
}
out.write("</div>\n</form>\n</div>\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

