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
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.newsfeed;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class newsfeed_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_newsfeedmodel = null;
Collection var_collectionvar0_list_coerced$ = null;
Object _global_url = null;
Object _global_urlimage = null;
Object _global_title = null;
Object _global_desc = null;
Object _global_content = null;
Object _global_author = null;
_global_newsfeedmodel = renderContext.call("use", com.anf.core.models.NewsFeedModel.class.getName(), obj());
out.write("<div>\n\t");
{
    Object var_collectionvar0 = renderContext.getObjectModel().resolveProperty(_global_newsfeedmodel, "newsList");
    {
        long var_size1 = ((var_collectionvar0_list_coerced$ == null ? (var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0)) : var_collectionvar0_list_coerced$).size());
        {
            boolean var_notempty2 = (var_size1 > 0);
            if (var_notempty2) {
                {
                    long var_end5 = var_size1;
                    {
                        boolean var_validstartstepend6 = (((0 < var_size1) && true) && (var_end5 > 0));
                        if (var_validstartstepend6) {
                            out.write("<div>");
                            if (var_collectionvar0_list_coerced$ == null) {
                                var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0);
                            }
                            long var_index7 = 0;
                            for (Object newsfeed : var_collectionvar0_list_coerced$) {
                                {
                                    boolean var_traversal9 = (((var_index7 >= 0) && (var_index7 <= var_end5)) && true);
                                    if (var_traversal9) {
                                        out.write("\n        ");
_global_url = renderContext.getObjectModel().resolveProperty(newsfeed, "url");
                                        if (renderContext.getObjectModel().toBoolean(_global_url)) {
                                            out.write("<a");
                                            {
                                                Object var_attrvalue10 = _global_url;
                                                {
                                                    Object var_attrcontent11 = renderContext.call("xss", var_attrvalue10, "uri");
                                                    {
                                                        Object var_shoulddisplayattr13 = ((renderContext.getObjectModel().toBoolean(var_attrcontent11) ? var_attrcontent11 : ("false".equals(var_attrvalue10))));
                                                        if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr13)) {
                                                            out.write(" href");
                                                            {
                                                                boolean var_istrueattr12 = (var_attrvalue10.equals(true));
                                                                if (!var_istrueattr12) {
                                                                    out.write("=\"");
                                                                    out.write(renderContext.getObjectModel().toString(var_attrcontent11));
                                                                    out.write("\"");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            out.write(">\n            <div>\n                ");
_global_urlimage = renderContext.getObjectModel().resolveProperty(newsfeed, "urlImage");
                                            if (renderContext.getObjectModel().toBoolean(_global_urlimage)) {
                                                out.write("<img");
                                                {
                                                    Object var_attrvalue14 = _global_urlimage;
                                                    {
                                                        Object var_attrcontent15 = renderContext.call("xss", var_attrvalue14, "uri");
                                                        {
                                                            Object var_shoulddisplayattr17 = ((renderContext.getObjectModel().toBoolean(var_attrcontent15) ? var_attrcontent15 : ("false".equals(var_attrvalue14))));
                                                            if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr17)) {
                                                                out.write(" src");
                                                                {
                                                                    boolean var_istrueattr16 = (var_attrvalue14.equals(true));
                                                                    if (!var_istrueattr16) {
                                                                        out.write("=\"");
                                                                        out.write(renderContext.getObjectModel().toString(var_attrcontent15));
                                                                        out.write("\"");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                {
                                                    Object var_attrvalue18 = renderContext.getObjectModel().resolveProperty(newsfeed, "title");
                                                    {
                                                        Object var_attrcontent19 = renderContext.call("xss", var_attrvalue18, "attribute");
                                                        {
                                                            Object var_shoulddisplayattr21 = ((renderContext.getObjectModel().toBoolean(var_attrcontent19) ? var_attrcontent19 : ("false".equals(var_attrvalue18))));
                                                            if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr21)) {
                                                                out.write(" alt");
                                                                {
                                                                    boolean var_istrueattr20 = (var_attrvalue18.equals(true));
                                                                    if (!var_istrueattr20) {
                                                                        out.write("=\"");
                                                                        out.write(renderContext.getObjectModel().toString(var_attrcontent19));
                                                                        out.write("\"");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                out.write("/>");
                                            }
                                            out.write("\n            </div>\n            <div>\n                ");
_global_title = renderContext.getObjectModel().resolveProperty(newsfeed, "title");
                                            if (renderContext.getObjectModel().toBoolean(_global_title)) {
                                                out.write("<p>");
                                                {
                                                    Object var_22 = renderContext.call("xss", _global_title, "text");
                                                    out.write(renderContext.getObjectModel().toString(var_22));
                                                }
                                                out.write("</p>");
                                            }
                                            out.write("\n                ");
_global_desc = renderContext.getObjectModel().resolveProperty(newsfeed, "description");
                                            if (renderContext.getObjectModel().toBoolean(_global_desc)) {
                                                out.write("<p>");
                                                {
                                                    Object var_23 = renderContext.call("xss", _global_desc, "html");
                                                    out.write(renderContext.getObjectModel().toString(var_23));
                                                }
                                                out.write("</p>");
                                            }
                                            out.write("\n                ");
_global_content = renderContext.getObjectModel().resolveProperty(newsfeed, "content");
                                            if (renderContext.getObjectModel().toBoolean(_global_content)) {
                                                out.write("<p>");
                                                {
                                                    Object var_24 = renderContext.call("xss", _global_content, "html");
                                                    out.write(renderContext.getObjectModel().toString(var_24));
                                                }
                                                out.write("</p>");
                                            }
                                            out.write("             \n                ");
_global_author = renderContext.getObjectModel().resolveProperty(newsfeed, "author");
                                            if (renderContext.getObjectModel().toBoolean(_global_author)) {
                                                out.write("<p>");
                                                {
                                                    String var_25 = ("By " + renderContext.getObjectModel().toString(renderContext.call("xss", _global_author, "text")));
                                                    out.write(renderContext.getObjectModel().toString(var_25));
                                                }
                                                out.write("</p>");
                                            }
                                            out.write("\n            </div>\n        </a>");
                                        }
                                        out.write("\n\t");
                                    }
                                }
                                var_index7++;
                            }
                            out.write("</div>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar0_list_coerced$ = null;
}
out.write("\n</div>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

