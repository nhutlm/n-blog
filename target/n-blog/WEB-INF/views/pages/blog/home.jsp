<%-- 
    Document   : home
    Created on : Oct 9, 2016, 8:09:36 AM
    Author     : nhutlm
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('static/blog/img/home-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>N-BLOG</h1>
                    <hr class="small">
                    <span class="subheading">A Java Blog by nhutlm</span>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <c:forEach var="post" items="${data.postList}">
                <div class="post-preview">
                    <a href="viewpost.html?id=${post.postId}">
                        <h2 class="post-title">
                            ${post.postTitle}
                        </h2>
                        <h3 class="post-subtitle">
                            ${post.postBody} 
                        </h3>
                        
                    </a>
                    <p class="post-meta">Posted by <a href="#">${post.postAuthor.userName}</a> on <fmt:formatDate pattern="hh:mm dd/MM/yyyy" value="${post.dateCreated}" /></p>
                </div>
                <hr>
            </c:forEach>       
            <!-- Pager -->
            <ul class="pager">
                <c:if test="${data.currentPage>0}">
                    <li class="previous">
                        <a href="#" onclick="sendRequest('prev');">&larr; Newer Posts</a>
                    </li>
                </c:if>
                <c:if test="${data.currentPage != data.totalPage}">
                    <li class="next">
                        <a href="#" onclick="sendRequest('next');">Older Posts &rarr;</a>
                    </li>
                </c:if>
            </ul>

        </div>
    </div>
</div>
<f:hidden id="hdcp" path="currentPage"/>