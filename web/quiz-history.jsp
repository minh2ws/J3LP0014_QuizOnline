<%-- 
    Document   : quiz-history
    Created on : Mar 12, 2021, 5:14:41 AM
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
        <title>Quiz History Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container-fluid">
            <div class="row justify-content-center">
                <!--search form-->
                <div class=" col-sm-10 col-md-10 col-lg-11 my-4">
                    <div class="card border border-dark">
                        <div class="card-header text-dark text-uppercase text-center">
                            <h3 style="font-weight: bold">Search Quiz Base on Subject Name</h3>
                        </div>
                        <c:set var="searchSubject" value="${param.cmbSubject}" />
                        <c:set var="searchTimeTakeQuiz" value="${param.cmbTimeTakeQuiz}" />
                        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />

                        <div class="card-body">
                            <form action="search-quiz" class="form-group" method="POST">
                                <label>Subject</label>
                                <select id="subjectValue" name="cmbSubject" class="form-control">
                                    <option></option>
                                    <c:forEach var="subject" items="${subjectList}">
                                        <option value="${subject.subId}"
                                                <c:if test="${subject.subId eq searchSubject}">
                                                    selected="true"
                                                </c:if>>
                                            ${subject.subName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <label>Time Take Quiz</label>
                                <select name="cmbTimeTakeQuiz"  class="form-control">
                                    <option></option>
                                    <option value="1" 
                                            <c:if test="${searchTimeTakeQuiz eq '1'}">
                                                selected="true"
                                            </c:if>>
                                        1
                                    </option>
                                    <option value="5"
                                            <c:if test="${searchTimeTakeQuiz eq '5'}">
                                                selected="true"
                                            </c:if>>
                                        5
                                    </option>
                                    <option value="10"
                                            <c:if test="${searchTimeTakeQuiz eq '10'}">
                                                selected="true"
                                            </c:if>>
                                        10
                                    </option>
                                    <option value="20"
                                            <c:if test="${searchTimeTakeQuiz eq '20'}">
                                                selected="true"
                                            </c:if>>
                                        20
                                    </option>
                                    <option value="30"
                                            <c:if test="${searchTimeTakeQuiz eq '30'}">
                                                selected="true"
                                            </c:if>>
                                        30
                                    </option>
                                    <option value="40"
                                            <c:if test="${searchTimeTakeQuiz eq '40'}">
                                                selected="true"
                                            </c:if>>
                                        40
                                    </option>
                                </select>
                                <div class="text-center">
                                    <input type="submit" value="Search Quiz" name="btAction" class="btn btn-success my-3"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--End of search form-->
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <h2 class="text-center">Quiz History</h2>
                    <c:set var="quizList" value="${requestScope.QUIZ_LIST}"/>
                    <c:if test="${not empty quizList}">
                        <div class="row">
                            <c:forEach var="quiz" items="${quizList}">
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <div class="card my-4">
                                        <div class="card-header">
                                            <div class="row">
                                                <div class="col-sm-12 col-md-6 col-lg-6 text-left">
                                                    <label><strong>Quiz Id:</strong> ${quiz.quizId}</label>
                                                </div>
                                                <div class="col-sm-12 col-md-6 col-lg-6 text-right">
                                                    <label><strong>Subject Id:</strong> ${quiz.subId}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"><strong>Email: ${quiz.email}</strong></li>
                                                <li class="list-group-item">Score: ${quiz.score}</li>
                                                <li class="list-group-item">Number correct answer: ${quiz.numberOfCorrectAnswer} corrects</li>
                                                <li class="list-group-item">Time Do Quiz: ${quiz.timeDoQuiz} minutes</li>
                                                <li class="list-group-item">Number question of quiz: ${quiz.numberQuestionOfQuiz} questions</li>
                                                <li class="list-group-item">Date did quiz: ${quiz.dateDidQuiz}</li>
                                            </ul>
                                        </div>
                                        <div class="card-footer justify-content-center">
                                            <div class="row">
                                                <div class="col-sm-12 col-md-6 col-lg-6 text-left">
                                                    <form action="load-quiz-result-page-servlet" method="POST">
                                                        <input type="hidden" name="quizId" value="${quiz.quizId}" />
                                                        <input type="hidden" name="cmbSubject" value="${searchSubject}" />
                                                        <input type="hidden" name="cmbTimeTakeQuiz" value="${searchTimeTakeQuiz}" />
                                                        <input type="submit" value="View Detail" name="btAction" class="btn btn-primary" />
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${empty quizList}">
                        <h1 class="text-center">Question not found</h1>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
