<%-- 
    Document   : setup-quiz
    Created on : Mar 10, 2021, 5:22:27 AM
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
        <title>Setup Quiz Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container-fluid">
            <div class="row justify-content-center">
                <!--search form-->
                <div class=" col-sm-10 col-md-10 col-lg-11 my-4">
                    <div class="card border border-dark">
                        <div class="card-header text-dark text-uppercase text-center">
                            <h3 style="font-weight: bold">Setup Quiz</h3>
                        </div>

                        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />
                        <c:set var="numberOfQuestionInQuiz" value="${requestScope.NUMBER_OF_QUESTION_IN_QUIZ}"/>

                        <div class="card-body">
                            <form action="setup-quiz" class="form-group" method="POST">
                                <label>Subject</label>
                                <select id="subjectValue" name="cmbQuestionSubject" class="form-control">
                                    <c:forEach var="subject" items="${subjectList}">
                                        <option value="${subject.subId}"
                                                <c:if test="${subject.subId eq searchSubject}">
                                                    selected="true"
                                                </c:if>>
                                            ${subject.subName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <label>Number Of Question For Quiz</label>
                                <select name="cmbNumberOfQuestionInQuiz" class="form-control">
                                    <c:forEach var="i" begin="5" end="${numberOfQuestionInQuiz}" step="5">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                                <label>Time Take Quiz</label>
                                <select name="cmbTimeTakeQuiz"  class="form-control">
                                    <option value="1">1</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="30">30</option>
                                    <option value="40">40</option>
                                </select>
                                <div class="text-center">
                                    <input type="submit" value="Set up" name="btAction" class="btn btn-success my-3"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--End of search form-->
                </div>
            </div>
            <script src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/validation.js"></script>
        </div>
    </body>
</html>
