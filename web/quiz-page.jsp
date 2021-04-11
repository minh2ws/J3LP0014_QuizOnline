<%-- 
    Document   : quiz-page
    Created on : Mar 10, 2021, 8:32:56 AM
    Author     : minhv
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="http://1892.yn.lt/blogger/JQuery/Pagging/js/jquery.twbsPagination.js" type="text/javascript"></script>
        <title>Quiz Page</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <nav class="navbar navbar-dark navbar-expand-sm bg-warning">
            <ul class="navbar-nav ml-auto text-center">
                <!-- Welcome and logout form -->
                <li class="nav-item">
                    <div class="welcome-user">Welcome, ${account.name}</div>		
                </li>
                <li class="nav-item">
                    <form action="logout">
                        <input type="submit" value="Logout" class="btn btn-primary btn-sm my-2 my-sm-0 mx-3"/>
                    </form>
                </li>
            </ul>
        </nav>

        <div class="container-fluid">
            <h3 style="font-weight: bold" class="text-center">Quiz</h3>
            <h4 style="font-weight: bold" class="text-center">Try Your Best</h4>

            <!--Button number page-->
            <div class="row justify-content-center mt-2">
                <ul id="pagination"></ul>
            </div>
            <!--Question & Submit form-->
            <div class="row justify-content-center mt-2">
                <c:set var="quiz" value="${sessionScope.QUIZ}"/>
                <div class="col-8">
                    <c:set var="questionList" value="${quiz.listQuestion}"/>
                    <!--Question form-->
                    <form id="submit" action="submit-quiz" method="POST">
                        <c:forEach var="question" items="${questionList}" varStatus="counter">
                            <div class="contentPage">
                                <div class="card">
                                    <div class="card-header">
                                        <h3><strong>Question: ${counter.count}</strong></h3>
                                    </div>
                                    <div class="card-body">
                                        <input type="hidden" name="questionId${counter.count}" value="${question.questionId}" />
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><strong>${question.questionContent}</strong></li>
                                            <li class="list-group-item"><input type="radio" name="txtAnswer${counter.count}" value="A">A. ${question.optionA}</li>
                                            <li class="list-group-item"><input type="radio" name="txtAnswer${counter.count}" value="B">B. ${question.optionB}</li>
                                            <li class="list-group-item"><input type="radio" name="txtAnswer${counter.count}" value="C">C. ${question.optionC}</li>
                                            <li class="list-group-item"><input type="radio" name="txtAnswer${counter.count}" value="D">D. ${question.optionD}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                </div>

                <!--Count time & submit form-->
                <div class="col-2">
                    <div class="card text-center">
                        <div class="card-header">
                            <h4>Subject Name: ${sessionScope.SUBJECT_NAME}</h4>
                        </div>
                        <div class="card-body">
                            Time left: <span id="timer"></span>
                        </div>
                        <div class="card-footer">
                            <input class="btn btn-primary" type="submit" value="Submit Quiz" form="submit"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Script for paging question-->
        <script>
            $(function () {
                var pageSize = 1; //Show 1 one question per page
                showPage = function (page) {
                    $(".contentPage").hide();
                    $(".contentPage").each(function (n) {
                        if (n >= pageSize * (page - 1) && n < pageSize * page)
                            $(this).show();
                    });
                };
                showPage(1);

                var totalRows = ${sessionScope.NUMBER_OF_QUESTION}; //Total question to show
                var btnPage = 5; //The number of buttons displaye for choose page
                var iTotalPages = Math.ceil(totalRows / pageSize);

                var obj = $('#pagination').twbsPagination({
                    totalPages: iTotalPages,
                    visiblePages: btnPage,
                    onPageClick: function (event, page) {
                        showPage(page);
                    }
                });
            });

            window.onload = function () {
                var endTime = ${quiz.timeEndQuiz.time};
                var currentTime = new Date().getTime();
                var duration = Math.round((endTime - currentTime) / 1000);
                startTime(duration);
            };
        </script>
        <script src="assets/js/takeQuiz.js"></script>
    </body>
</html>
