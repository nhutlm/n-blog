<%-- 
    Document   : home
    Created on : Oct 9, 2016, 8:09:36 AM
    Author     : nhutlm
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<br>
<n:notification dataSource="${data.result}"></n:notification>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">
            <div class="row">
                <div class="col-lg-12 text-left"> 
                    Setting
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
            <p class="help-block">Leave password blank if don't want to change</p>
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