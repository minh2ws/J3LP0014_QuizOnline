<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Navigation bar-->
<c:set var="account" value="${sessionScope.ACCOUNT}"/>
<nav class="navbar navbar-dark navbar-expand-sm bg-warning">
    <ul class="navbar-nav mr-auto py-0">
        <c:if test="${account.role eq 'Admin'}">
            <li class="nav-item">
                <a class="nav-link active" href="load-admin-page">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="load-create-question-page">
                    Create Question
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="load-setup-quiz-page">
                    Setup Quiz
                </a>
            </li>
        </c:if>
        <c:if test="${account.role eq 'Student'}">
            <li class="nav-item">
                <a class="nav-link active" href="load-student-page">Home</a>
            </li>
        </c:if>
        <li class="nav-item">
            <a class="nav-link" href="load-quiz-history-servlet">Quiz History</a>
        </li>
    </ul>
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



