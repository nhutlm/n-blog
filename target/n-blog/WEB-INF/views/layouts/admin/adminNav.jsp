<%-- 
    Document   : adminNav
    Created on : Oct 9, 2016, 4:30:15 PM
    Author     : nhutlm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="nblog.utility.Helper"%>
<%@page import="nblog.dtos.UserDTO"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Menu</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="../home.html">N-BLOG Admin</a>
    </div>
    <!-- /.navbar-header -->
    <%
        UserDTO user = Helper.getUserSession(request);
    %>

    <ul class="nav navbar-top-links navbar-right">
        <li>
            Xin chào <%=user.fullName%>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="profile.html"><i class="fa fa-user fa-fw"></i> Your Profile</a>
                </li>

                <li class="divider"></li>
                <li><a href="../login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu"> 
                <li>
                    <a href="#"><i class="fa fa-file fa-fw"></i> Posts<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">                       
                        <li>
                            <a href="posts.html">All posts</a>
                        </li>                     
                        <li>
                            <a href="postdetail.html">Add new</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-user fa-fw"></i> Users<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                         <% if(user.userRole.equalsIgnoreCase("ADMIN")){ %>
                        <li>
                            <a href="users.html">All users</a>
                        </li>
                        <li>
                            <a href="userdetail.html">Add new</a>
                        </li>
                         <% } %>
                        <li>
                            <a href="profile.html">Your profile</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>                

            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
