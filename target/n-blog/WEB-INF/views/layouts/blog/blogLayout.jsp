<%-- 
    Document   : blogLayout
    Created on : Oct 8, 2016, 10:20:33 PM
    Author     : nhutlm
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><tiles:insertAttribute name="title" /></title>
        <jsp:include page="blogHead.jsp" />
    </head>
    <body>
        <f:form id="frmmain" name="frmmain" modelAttribute="data" method="POST" enctype="multipart/form-data">
            <jsp:include page="blogNav.jsp" />
            <!-- Main Content --> 
            <tiles:insertAttribute name="body" />            

            <hr>
            <jsp:include page="blogFooter.jsp" />
            <f:hidden id="hdAction" path="action"/>
        </f:form>
    </body>
</html>

