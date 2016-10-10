/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nblog.utility;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import nblog.dtos.ErrorDTO;
import nblog.dtos.ResultDTO;
import nblog.dtos.ResultMsgDTO;
import nblog.dtos.WarningDTO;

/**
 *
 * @author nhutlm
 */
public class NotificationTag extends SimpleTagSupport {

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the displayed
     */
    public Boolean getDisplayed() {
        return displayed;
    }

    /**
     * @param displayed the displayed to set
     */
    public void setDisplayed(Boolean displayed) {
        this.displayed = displayed;
    }

    /**
     * @return the dataSource
     */
    public ResultDTO getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    public void setDataSource(ResultDTO dataSource) {
        this.dataSource = dataSource;
    }

    public String type;
    public Boolean displayed;
    public ResultDTO dataSource;

    @Override
    public void doTag() throws JspException, IOException {
        try {

            String _err = "error";
            String _dan = "danger";
            String _suc = "success";
            String _inf = "info";
            String _war = "warning";
            String cssClass;
            StringBuilder htmlOut = new StringBuilder();
            ArrayList<String> msg = new ArrayList<String>();

            StringWriter sw = new StringWriter();
            try {
                getJspBody().invoke(sw);//get tag body
            } catch (Exception ex) {
                //--->do nothing
            }
            if (displayed == null) {
                displayed = (!sw.toString().isEmpty() || dataSource != null || (dataSource.errors != null && dataSource.errors.length > 0)) || (dataSource.warnings != null && dataSource.warnings.length > 0) || (dataSource.resultMsgs != null && dataSource.resultMsgs.length > 0);

            }
            if (!displayed) {
                return;
            }
//            if ((type != null) || !type.isEmpty()) {
//                if (dataSource != null) {
//                    if (dataSource.errors != null && dataSource.errors.length > 0) {
//                        for (ErrorDTO ms : dataSource.errors) {
//                            msg.add(ms.errorMsg);
//                        }
//                    } else {
//                        if (dataSource.resultMsgs != null && dataSource.resultMsgs.length > 0) {
//                            for (ResultMsgDTO ms : dataSource.resultMsgs) {
//                                msg.add(ms.msg);
//                            }
//                        }
//                        if (dataSource.warnings != null && dataSource.warnings.length > 0) {
//                            for (WarningDTO ms : dataSource.warnings) {
//                                msg.add(ms.warningMsg);
//                            }
//                        }
//                    }
//
//                }
//                if (!type.equalsIgnoreCase(_err) && !type.equalsIgnoreCase(_suc) && !type.equalsIgnoreCase(_war) && !type.equalsIgnoreCase(_inf)) {
//                    cssClass = _inf;
//                } else if (type.equalsIgnoreCase(_err)) {
//                    cssClass = _dan;
//                } else {
//                    cssClass = type.toLowerCase();
//                }
//                htmlOut.append("<div class=\"alert alert-").append(cssClass).append("\">\n"
//                        + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>");
//                if (!msg.isEmpty()) {
//                    htmlOut.append("<ul>");
//                    for (String ms : msg) {
//                        htmlOut.append("<li>").append(ms).append("</li>");
//                    }
//                    htmlOut.append("</ul>");
//                }
//                htmlOut.append(sw.toString());
//                htmlOut.append("</div>");
//            } else {
//                
//
//            }
            if (dataSource != null) {
                if (dataSource.errors != null && dataSource.errors.length > 0) {
                    htmlOut.append("<div class=\"alert alert-").append(_dan).append("\">\n"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><ul>");
                    for (ErrorDTO ms : dataSource.errors) {
                        htmlOut.append("<li>").append(ms.errorMsg).append("</li>");
                    }
                    htmlOut.append("</ul></div>");

                    htmlOut.append("<script>$(document).ready(function() {");
                    boolean l_hasc = false;
//                        for (ErrorDTO ms : dataSource.errors) {
//                            if (Common.isEmpty(ms.uiFieldRef)) {
//                                continue;
//                            }
//
//                            if (l_hasc == false) {
//                                htmlOut.append(MessageFormat.format("addMarkInputError(''{0}'',''Y'');", ms.uiFieldRef));
//                                l_hasc = true;
//
//                            } else {
//                                htmlOut.append(MessageFormat.format("addMarkInputError(''{0}'',''N'');", ms.uiFieldRef));
//                            }
//                        }
                    htmlOut.append("});</script>");

                } else {
                    if (dataSource.resultMsgs != null && dataSource.resultMsgs.length > 0) {
                        htmlOut.append("<div class=\"alert alert-").append(_suc).append("\">\n"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><ul>");
                        for (ResultMsgDTO ms : dataSource.resultMsgs) {
                            htmlOut.append("<li>").append(ms.msg).append("</li>");
                        }
                        htmlOut.append("</ul></div>");
                    }

                }
                if (dataSource.warnings != null && dataSource.warnings.length > 0) {
                    htmlOut.append("<div class=\"alert alert-").append(_war).append("\">\n"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><ul>");
                    for (WarningDTO ms : dataSource.warnings) {
                        htmlOut.append("<li>").append(ms.warningMsg).append("</li>");
                    }
                    htmlOut.append("</ul></div>");
                }

            }
            if (!sw.toString().isEmpty()) {
                htmlOut.append("<div class=\"alert alert-").append(_inf).append("\">\n"
                        + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>");
                htmlOut.append(sw.toString());
                htmlOut.append("</div>");
            }
            getJspContext().getOut().print(htmlOut);
        } catch (Exception ex) {
        }
    }

}
