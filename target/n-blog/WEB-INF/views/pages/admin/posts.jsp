<%-- 
    Document   : post
    Created on : Oct 9, 2016, 9:14:10 PM
    Author     : nhutlm
--%>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="n" uri="http://nblog.com/tlibs/taglib" %>

<br>
<n:notification dataSource="${data.result}"></n:notification>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">
            <div class="row">
                <div class="col-lg-8 text-left"> Posts <a href="postdetail.html" class="btn btn-info">Add new</a></div>
                <div class="col-lg-4 text-right">
                    <div class="form-group input-group" style="margin-top: 5px;">
                        <f:input path="searchKeyword" class="form-control"/>
                        <span class="input-group-btn">
                            <a class="btn btn-default" onclick="sendRequest('');"><i class="fa fa-search"></i>
                            </a>
                        </span>
                    </div>
                </div>
            </div>
        </h1>
    </div>  
</div>
<div class="row">
    <div class="col-lg-12">
        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
            <thead>
                <tr>
                    <th class="col-lg-1">ID</th>
                    <th class="col-lg-4">Title</th>
                    <th class="col-lg-2">Author</th>
                    <th class="col-lg-2">Date</th>
                    <th class="col-lg-1">Status</th>
                    <th class="col-lg-2 text-center">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${data.postList}">
                    <tr class="odd gradeX">
                        <td>${post.postId}</td>
                        <td>${post.postTitle}</td>
                        <td>${post.postAuthor.userName}</td>
                        <td><fmt:formatDate pattern="hh:mm dd/MM/yyyy" value="${post.dateCreated}" /></td>
                        <td class="center">
                            <input type="checkbox"  <c:if test="${post.isPublished=='Y'}">checked</c:if> disabled   readonly/>
                        </td>  
                        <td class="text-center">
                            <a class="btn btn-success" href="postdetail.html?id=${post.postId}">
                                <i class="fa fa-edit"></i>
                            </a>
                            <a class="btn btn-danger" onclick="confirmDelete(${post.postId},'${post.postTitle}');">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </td>  
                    </tr>  
                </c:forEach>
            </tbody>
        </table>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm</h4>
            </div>
            <div class="modal-body">
             Are you sure that you want to permanently delete the selected post?
             <br>
             <b><div id="postTitle"></div></b>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a class="btn btn-danger" onclick="sendRequest('delete')">Delete</a>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<f:hidden path="selectedPost" id="selectedPost"></f:hidden>
<script>
    function confirmDelete(postID, postTitle) {
        $("#selectedPost").val(postID);
        $("#postTitle").html(postTitle);
        $('#myModal').modal('show');
    }
</script>

