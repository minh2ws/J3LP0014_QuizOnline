<%-- 
    Document   : create-question
    Created on : Mar 7, 2021, 7:23:43 AM
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
        <title>Create Question Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />
        <div class="card my-4 mx-3">
            <h2 class="text-center">Create Question</h2>
            
            <form id="create-question" class="form-group" action="create-question" method="POST" onsubmit="return validateQuestion()">
                <strong>Content</strong>
                <textarea id="questionContent" class="form-control" aria-label="Question Content" name="txtQuestionContent" placeholder="Question Content" required ></textarea>
                <strong>Option A</strong>
                <input id="optionA" class="form-control" type="text" name="txtOptionA" value="" placeholder="Answer A" required/>
                <strong>Option B</strong>
                <input id="optionB" class="form-control" type="text" name="txtOptionB" value="" placeholder="Answer B"  required/>
                <strong>Option C</strong>
                <input id="optionC" class="form-control" type="text" name="txtOptionC" value="" placeholder="Answer C"  required/>
                <strong>Option D</strong>
                <input id="optionD" class="form-control" type="text" name="txtOptionD" value="" placeholder="Answer D"  required/>
                <strong>Correct Option</strong>
                <select name="cmbCorrectAnswer" class="form-control">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select>
                <strong>Subject</strong>
                <select name="cmbQuestionSubject" class="form-control">
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.subId}"
                                <c:if test="${subject.subId eq subject}">
                                    selected="true"
                                </c:if>>
                            ${subject.subName}
                        </option>
                    </c:forEach>
                </select>
                <strong>Status</strong>
                <select name="cmbQuestionStatus" class="form-control">
                    <option value="Active"
                            <c:if test="${status eq 'Active'}">
                                selected="true"
                            </c:if>>
                        Active
                    </option>
                    <option value="Inactive"
                            <c:if test="${status eq 'Inactive'}">
                                selected="true"
                            </c:if>>
                        Deactivate
                    </option>
                </select>
                <input type="submit" value="Submit" name="btAction" class="btn btn-success my-3"/>
            </form>
        </div>

        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/script.js"></script>
    </body>
</html>
