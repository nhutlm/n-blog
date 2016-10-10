<%-- 
    Document   : blogHeader
    Created on : Oct 8, 2016, 10:58:44 PM
    Author     : nhutlm
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="nblog.utility.Helper"%>
<%@page import="nblog.dtos.UserDTO"%>
<%
    UserDTO user = Helper.getUserSession(request);
%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="/n-blog/">N-BLOG</a>
        </div>



        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="home.html">Home</a>
                </li>
                <li>
                    <a href="viewpost.html?id=1">About</a>
                </li> 
                <li>
                    <a href="admin/posts.html"><%=user==null?"LOGIN":"ADMIN"%></a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

