<%-- 
    Document   : submit-errror-page
    Created on : Mar 11, 2021, 9:11:16 PM
    Author     : minhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="assets/css/bootstrap.min.css">
        <link rel="stylesheet"  href="assets/css/customize.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Submit Error Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container-fluid">
            <div class="row justify-content-center mt-4">
                <div class="col-8">
                    <div class="card text-center">
                        <div class="card-header">
                            <h1 style="color: red">Warning</h1>
                        </div>
                        <div class="card-body">
                            <h3>This quiz is already submitted!</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
