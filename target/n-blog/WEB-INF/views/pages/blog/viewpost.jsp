<%-- 
    Document   : viewpost
    Created on : Oct 9, 2016, 10:40:43 AM
    Author     : nhutlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('static/blog/img/post-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="post-heading">
                    <h1>${data.post.postTitle}</h1>
                    <h2 class="subheading">${data.post.postSubTitle}</h2>
                    <span class="meta">Posted by <b>${data.post.postAuthor.userName}</b> 
                        on <fmt:formatDate pattern="hh:mm dd/MM/yyyy" value="${data.post.dateCreated}" /></span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Post Content -->
<article>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                ${data.post.postBody}
            </div>
        </div>
    </div>
</article>
