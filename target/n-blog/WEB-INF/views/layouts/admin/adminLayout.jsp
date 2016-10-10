<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>

    <head>
        <title><tiles:insertAttribute name="title" /></title>
        <jsp:include page="adminHead.jsp" />
    </head>

    <body>
        <div id="wrapper">
            <f:form id="frmmain" name="frmmain" modelAttribute="data" method="POST" enctype="multipart/form-data">
                <jsp:include page="adminNav.jsp" />

                <div id="page-wrapper">
                    <tiles:insertAttribute name="body" />          
                </div>
                <!-- /#page-wrapper -->
                <f:hidden id="hdAction" path="action"/>
            </f:form>
        </div>
        <!-- /#wrapper -->    

    </body>

</html>
