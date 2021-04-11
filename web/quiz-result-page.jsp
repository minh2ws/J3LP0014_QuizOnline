<%-- 
    Document   : quiz-result-page
    Created on : Mar 11, 2021, 9:11:42 PM
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
        <title>Quiz Result Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container-fluid">
            <div class="row justify-content-center my-5">
                <div class="col-6">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="text-center">Quiz Result</h3>
                        </div>
                        <div class="card-body">
                            <c:set var="result" value="${requestScope.RESULT}"/>
                            <span style="font-weight: bold">
                                UserId: 
                            </span> ${result.email}
                            <br>
                            <span style="font-weight: bold">
                                Date did quiz: 
                            </span> ${result.dateDidQuiz}
                            <br>
                            <span style="font-weight: bold">
                                Number of Correct: 
                            </span>${result.numberOfCorrectAnswer}/${result.numberQuestionOfQuiz}
                            <br>
                            <span style="font-weight: bold">
                                Score: 
                            </span>${result.score} out of 10.00
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <c:set var="questionList" value="${requestScope.QUESTION_LIST}"/>
                    <c:set var="mapQuestionVsAnswer" value="${requestScope.MAP_QUESTION_VS_ANSWER}"/>
                    <!--Question card-->
                    <div class="row">
                        <c:forEach var="question" items="${questionList}">

                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <div class="card my-4">
                                    <div class="card-header">
                                        <div class="row">
                                            <div class="col-sm-12 col-md-6 col-lg-6 text-left">
                                                <label><strong>Question Id:</strong> ${question.questionId}</label>
                                            </div>
                                            <div class="col-sm-12 col-md-6 col-lg-6 text-right">
                                                <label><strong>Subject Id:</strong> ${question.subId}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><strong>${question.questionContent}</strong></li>
                                            <li class="list-group-item">A. ${question.optionA}</li>
                                            <li class="list-group-item">B. ${question.optionB}</li>
                                            <li class="list-group-item">C. ${question.optionC}</li>
                                            <li class="list-group-item">D. ${question.optionD}</li>
                                            <li class="list-group-item">Answer Correct: ${question.answerCorrect}</li>
                                        </ul>
                                    </div>
                                    <div class="card-footer">
                                        <c:set var="answer" value="${mapQuestionVsAnswer[question.questionId]}"/>
                                        <c:if test="${not empty answer}">
                                            <c:if test="${question.answerCorrect eq answer}">
                                                <h4 style="color: green">Your answer: ${answer} is correct </h4>
                                            </c:if>
                                            <c:if test="${question.answerCorrect ne answer}">
                                                <h4 style="color: red">Your answer: ${answer} is not correct </h4>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty answer}">
                                            <h4 style="color: red">You are not answer this question</h4>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>         
        </div>
    </body>
</html>
