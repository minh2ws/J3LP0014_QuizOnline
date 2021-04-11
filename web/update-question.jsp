<%-- 
    Document   : update-question
    Created on : Mar 7, 2021, 7:23:54 AM
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
        <title>Update Question Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />
        <c:set var="searchValue" value="${param.txtSearch}" />
        <c:set var="searchSubject" value="${param.cmbSubject}" />
        <c:set var="searchStatus" value="${param.cmbStatus}" />
        <c:set var="question" value="${requestScope.QUESTION}"/>
        <div class="card my-4 mx-3">
            <h2 class="text-center">Create Question</h2>

            <form class="form-group" action="update-question" method="POST" onsubmit="return validateQuestion()">
                <strong>Content</strong>
                <textarea id="questionContent" class="form-control" aria-label="Question Content" name="txtQuestionContent" placeholder="Question Content" required >${question.questionContent}</textarea>
                <strong>Option A</strong>
                <input id="optionA" class="form-control" type="text" name="txtOptionA" value="${question.optionA}" placeholder="Answer A" required/>
                <strong>Option B</strong>
                <input id="optionB" class="form-control" type="text" name="txtOptionB" value="${question.optionB}" placeholder="Answer B"  required/>
                <strong>Option C</strong>
                <input id="optionC" class="form-control" type="text" name="txtOptionC" value="${question.optionC}" placeholder="Answer C"  required/>
                <strong>Option D</strong>
                <input id="optionD" class="form-control" type="text" name="txtOptionD" value="${question.optionD}" placeholder="Answer D"  required/>
                <strong>Correct Option</strong>
                <select name="cmbCorrectAnswer" class="form-control">
                    <option value="A" <c:if test="${'A' eq question.answerCorrect}">
                            selected="true"
                        </c:if>>
                        A
                    </option>
                    <option value="B" <c:if test="${'B' eq question.answerCorrect}">
                            selected="true"
                        </c:if>>
                        B
                    </option>
                    <option value="C" <c:if test="${'C' eq question.answerCorrect}">
                            selected="true"
                        </c:if>>
                        C
                    </option>
                    <option value="D" <c:if test="${'D' eq question.answerCorrect}">
                            selected="true"
                        </c:if>>
                        D
                    </option>
                </select>
                <strong>Subject</strong>
                <select name="cmbQuestionSubject" class="form-control">
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.subId}"
                                <c:if test="${subject.subId eq question.subId}">
                                    selected="true"
                                </c:if>>
                            ${subject.subName}
                        </option>
                    </c:forEach>
                </select>
                <strong>Status</strong>
                <select name="cmbQuestionStatus" class="form-control">
                    <option value="Active"
                            <c:if test="${question.status eq 'Active'}">
                                selected="true"
                            </c:if>>
                        Active
                    </option>
                    <option value="Inactive"
                            <c:if test="${question.status eq 'Inactive'}">
                                selected="true"
                            </c:if>>
                        Deactivate
                    </option>
                </select>
                <input type="hidden" name="questionId" value="${question.questionId}" />
                <input type="hidden" name="txtSearch" value="${searchValue}" />
                <input type="hidden" name="cmbSubject" value="${searchSubject}" />
                <input type="hidden" name="cmbStatus" value="${searchStatus}" />
                <input type="submit" value="Submit" name="btAction" class="btn btn-success my-3"/>
            </form>
        </div>

        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
