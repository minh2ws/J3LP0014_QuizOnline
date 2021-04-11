<%-- 
    Document   : student-page
    Created on : Mar 6, 2021, 7:45:32 AM
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
        <title>Student Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <h2 class="text-center">Quiz - Fighting!!! - Good luck</h2>

                        <!--Quiz card-->
                        <div class="row">
                        <c:set var="quiz" value="${sessionScope.QUIZ}"/>
                        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />
                        <c:forEach var="subject" items="${subjectList}">
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <div class="card my-4">
                                    <div class="card-header">
                                        <div class="row">
                                            <div class="col-sm-12 col-md-6 col-lg-6 text-left">
                                                <label><strong>Subject Name:</strong> ${subject.subName}</label>
                                            </div>
                                            <div class="col-sm-12 col-md-6 col-lg-6 text-right">
                                                <label><strong>Subject Id:</strong> ${subject.subId}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><label><strong>Time Take Quiz:</strong> ${subject.timeDoQuiz} minutes</label></li>
                                            <li class="list-group-item"><label><strong>Number Of Question:</strong> ${subject.numberQuestionOfQuiz} questions</label></li>
                                        </ul>
                                    </div>
                                    <div class="card-footer">
                                        <c:if test="${empty quiz}">
                                            <form action="attempt-quiz" method="POST" class="text-center">
                                                <input type="hidden" name="subId" value="${subject.subId}"/>
                                                <input type="hidden" name="subName" value="${subject.subName}"/>
                                                <input type="hidden" name="timeDoQuiz" value="${subject.timeDoQuiz}"/>
                                                <input type="hidden" name="numberQuestionOfQuiz" value="${subject.numberQuestionOfQuiz}"/>
                                                <input type="submit" value="Attempt Quiz" name="btAction" class="btn btn-primary" />
                                            </form>
                                        </c:if>
                                        <c:if test="${not empty quiz}">
                                            <c:if test="${quiz.subId.equals(subject.subId)}">
                                                <form action="resume-quiz" method="POST" class="text-center">
                                                    <input type="hidden" name="subId" value="${subject.subId}"/>
                                                    <input type="hidden" name="subName" value="${subject.subName}"/>
                                                    <input type="hidden" name="timeDoQuiz" value="${subject.timeDoQuiz}"/>
                                                    <input type="hidden" name="numberQuestionOfQuiz" value="${subject.numberQuestionOfQuiz}"/>
                                                    <input type="submit" value="Resume Quiz" name="btAction" class="btn btn-primary" />
                                                </form>
                                            </c:if>
                                            <c:if test="${!quiz.subId.equals(subject.subId)}">
                                                <div class="text-center" style="color: red; font-weight: bold">
                                                    You must finish the quiz
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/validation.js"></script>
    </body>
</html>
