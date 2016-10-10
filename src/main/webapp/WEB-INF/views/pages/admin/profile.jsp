<%-- 
    Document   : profile
    Created on : Oct 10, 2016, 5:50:41 PM
    Author     : nhutlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="http://nblog.com/tlibs/taglib" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<br>
<n:notification dataSource="${data.result}"></n:notification>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                <div class="row">
                    <div class="col-lg-12 text-left"> Profiles </div>                
                </div>
            </h1>
        </div>  
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="form-group">
                <label>Fullname</label>
            <f:input cssClass="form-control" path="user.fullName"/>           
        </div>
        <div class="form-group">
            <label>Current password</label>
            <f:input cssClass="form-control" path="password" type="password"/> 
            <p class="help-block">Leave password blank if don't want to change</p>
        </div>
        <div class="form-group">
            <label>New password</label>
            <f:input cssClass="form-control" path="newpassword" type="password"/> 

        </div>
        <div class="form-group">
            <label>Confirm new password</label>
            <f:input cssClass="form-control" path="confirmpassword" type="password"/>        
        </div>
        <div class="form-group text-right">
            <input type="submit" class="btn btn-info" value="Update"/>         
        </div>
    </div>
</div>