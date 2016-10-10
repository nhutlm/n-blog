<%-- 
    Document   : newuser
    Created on : Oct 10, 2016, 6:46:36 PM
    Author     : nhutlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="http://nblog.com/tlibs/taglib" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<n:notification dataSource="${data.result}"></n:notification>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                <div class="row">
                    <div class="col-lg-12 text-left"> 
                    <c:if test="${data.newUser==false}">Edit User <a href="userdetail.html" class="btn btn-info">Add new</a></c:if>
                    <c:if test="${data.newUser==true}"> Add new user </c:if>

                    </div>                
                </div>
            </h1>
        </div>  
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="form-group">
                <label>UserName</label>
            <f:input cssClass="form-control" path="user.userName" disabled="${data.newUser==false}"/>           
        </div>

        <div class="form-group">
            <label>Fullname</label>
            <f:input cssClass="form-control" path="user.fullName"/>           
        </div>
        <div class="form-group">
            <label>Password</label>
            <f:input cssClass="form-control" path="password" type="password"/> 
             <c:if test="${data.newUser==false}"><p class="help-block">Leave password blank if don't want to change</p></c:if>
        </div>

        <div class="form-group">
            <label>Role</label>
            <f:select path="user.userRole" cssClass="form-control" cssStyle="width:150px;">
                <f:option value="USER" label="USER"/>
                <f:option value="ADMIN" label="ADMIN"/>
            </f:select>

        </div>
        <div class="form-group">
            <label>Active</label>
            <f:checkbox  path="user.active" /> 

        </div>

        <div class="form-group text-right">
            <c:if test="${data.newUser==false}"> <input type="submit" class="btn btn-info" value="Update"/> </c:if>
            <c:if test="${data.newUser==true}">  <input type="submit" class="btn btn-info" value="Add new"/> </c:if>

        </div>
    </div>
</div>