<%-- 
    Document   : login
    Created on : Mar 5, 2021, 4:35:53 PM
    Author     : minhv
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
        ></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="assets/css/style.css" />
        <title>Sign in & Sign up Form</title>
    </head>
    <body>
        <c:set var="error" value="${requestScope.CREATE_ACCOUNT_ERRORS}"/>
        <div class="container">
            <div class="forms-container">
                <div class="signin-signup">
                    <form action="login" method="POST" class="sign-in-form">
                        <h2 class="title">Sign in</h2>
                        <div class="input-field">
                            <i class="fas fa-envelope"></i>
                            <input type="text" name="txtEmail" placeholder="Email" required="Please input email"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-lock"></i>
                            <input type="password" name="txtPassword" placeholder="Password" required="Please input password"/>
                        </div>
                        <input type="submit" value="Login" class="btn solid" />
                    </form>
                    <form action="sign-up" method="POST" class="sign-up-form" onsubmit="return validateSignUp()">
                        <h2 class="title">Sign up</h2>
                        <div class="input-field">
                            <i class="fas fa-envelope"></i>
                            <input id="txtEmail" type="email" name="txtEmail" placeholder="Email" required="Please input email"/>
                        </div>
                        <c:if test="${not empty error.emailExisted}">
                            <div class="text-danger">
                                <p class="text-danger">${error.emailExisted}</p>
                            </div>

                            <!--Java Script awake check-->
                            <script>
                                const container = document.querySelector(".container");
                                container.classList.add("sign-up-mode");
                            </script>
                        </c:if>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input id="txtName" type="text" name="txtName" placeholder="Name" required="Please input name"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-lock"></i>
                            <input id="txtPassword" type="password" name="txtPassword" placeholder="Password" required="Please input password"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-lock"></i>
                            <input id="txtConfirmPassword" type="password" name="txtConfirmPassword" placeholder="Confirm Password" required="Please input confirm password"/>
                        </div>
                        <input type="submit" class="btn" value="Sign up" />
                    </form>
                </div>
            </div>

            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="content">
                        <h3>New here ?</h3>
                        <p>
                            Sign up for become one of us?
                        </p>
                        <button class="btn transparent" id="sign-up-btn">
                            Sign up
                        </button>
                    </div>
                    <img src="assets/image/log.svg" class="image" alt="" />
                </div>
                <div class="panel right-panel">
                    <div class="content">
                        <h3>One of us ?</h3>
                        <p>
                            Sign in for using own page!
                        </p>
                        <c:if test="${not empty error.emailExisted}">
                            <form action="login-page">
                                <button class="btn transparent" href="login-page" id="sign-in-btn">
                                    Sign in
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${empty error.emailExisted}">
                            <button class="btn transparent" id="sign-in-btn">
                                Sign in
                            </button>
                        </c:if>
                    </div>
                    <img src="assets/image/register.svg" class="image" alt="" />
                </div>
            </div>
        </div>

        <script src="assets/js/app.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
