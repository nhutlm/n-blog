<%-- 
    Document   : adminHead
    Created on : Oct 9, 2016, 4:27:55 PM
    Author     : nhutlm
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<!-- Bootstrap Core CSS -->
<link href="../static/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../static/admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../static/admin/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="../static/admin/vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../static/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- jQuery -->
<script src="../static/admin/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../static/admin/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../static/admin/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="../static/admin/vendor/raphael/raphael.min.js"></script>
<script src="../static/admin/vendor/morrisjs/morris.min.js"></script>
<script src="../static/admin/data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../static/admin/dist/js/sb-admin-2.js"></script>
<script>
    function sendRequest(action) {
        document.frmmain.hdAction.value = action;
        document.frmmain.submit();
    }
</script>