<%-- 
    Document   : admin-page
    Created on : Mar 6, 2021, 7:45:22 AM
    Author     : minhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Admin Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <div class="container-fluid">
            <div class="row justify-content-center">
                <!--search form-->
                <div class=" col-sm-10 col-md-10 col-lg-11 my-4">
                    <div class="card border border-dark">
                        <div class="card-header text-dark text-uppercase text-center">
                            <h3 style="font-weight: bold">Search Question</h3>
                        </div>

                        <c:set var="searchValue" value="${param.txtSearch}" />
                        <c:set var="searchSubject" value="${param.cmbSubject}" />
                        <c:set var="searchStatus" value="${param.cmbStatus}" />
                        <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />

                        <div class="card-body">
                            <form action="search-question" class="form-group" method="POST">
                                <label>Name</label>
                                <input id="searchValue" type="text" name="txtSearch" class="form-control" value="${searchValue}" placeholder="enter question name" />
                                <label>Status</label>
                                <select name="cmbStatus" class="form-control">
                                    <option></option>
                                    <option value="Active"
                                            <c:if test="${searchStatus eq 'Active'}">
                                                selected="true"
                                            </c:if>>
                                        Active
                                    </option>
                                    <option value="Inactive"
                                            <c:if test="${searchStatus eq 'Inactive'}">
                                                selected="true"
                                            </c:if>>
                                        Inactive
                                    </option>
                                </select>
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
                                <div class="text-center">
                                    <input type="submit" value="Search" name="btAction" class="btn btn-success my-3"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--End of search form-->
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <h2 class="text-center">Question</h2>
                    <c:set var="questionList" value="${requestScope.QUESTION_LIST}"/>
                    <c:if test="${not empty questionList}">

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
                                        <div class="card-footer justify-content-center">
                                            <div class="row">
                                                <div class="col-sm-12 col-md-6 col-lg-6 text-left">
                                                    <form action="load-update-question-page" method="POST">
                                                        <input type="hidden" name="questionId" value="${question.questionId}" />
                                                        <input type="hidden" name="txtSearch" value="${searchValue}" />
                                                        <input type="hidden" name="cmbSubject" value="${searchSubject}" />
                                                        <input type="hidden" name="cmbStatus" value="${searchStatus}" />
                                                        <input type="submit" value="Update Question" name="btAction" class="btn btn-primary" />
                                                    </form>
                                                </div>
                                                <div class="col-sm-12 col-md-6 col-lg-6 text-right">
                                                    <c:if test="${question.status eq 'Active'}">
                                                        <a href="#updateStatus${question.questionId}" class="delete" data-toggle="modal">
                                                            <button type="button" class="btn btn-success">Active</button>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${question.status eq 'Inactive'}">
                                                        <a href="#updateStatus${question.questionId}" class="delete" data-toggle="modal">
                                                            <button type="button" class="btn btn-danger">Inactive</button>
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <!--Pop up-->
                            <!--Delete Modal Car-->
                            <c:forEach var="question" items="${questionList}">
                                <div class="modal fade" id="updateStatus${question.questionId}" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <form action="update-status-question" method="POST">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Update Status Question</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Do you want to 
                                                        <strong></strong>
                                                        <c:if test="${question.status eq 'Active'}">
                                                            <strong>Inactive</strong>
                                                        </c:if>
                                                        <c:if test="${question.status eq 'Inactive'}">
                                                            <strong>Active</strong>
                                                        </c:if> 
                                                        <strong${question.questionContent}</strong> ?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <input type="hidden" name="questionId" value="${question.questionId}" />
                                                    <input type="hidden" name="status" value="${question.status}" />
                                                    <input type="hidden" name="txtSearch" value="${searchValue}" />
                                                    <input type="hidden" name="cmbSubject" value="${searchSubject}" />
                                                    <input type="hidden" name="cmbStatus" value="${searchStatus}" />
                                                    <input type="submit" class="btn btn-secondary" data-dismiss="modal" value="Cancel" />
                                                    <input type="submit" class="btn btn-primary" value="Update Status" />
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <!--error modal-->
                        <c:set var="error" value="${requestScope.UPDATE_QUESTION_ERRORS}"/>
                        <c:if test="${not empty error}">
                            <!--JavaScript awake check-->
                            <script>
                                $(document).ready(function () {
                                    $("#errorModal").modal();
                                });
                            </script>
                            <!--Check Modal-->
                            <div class="modal fade" id="errorModal" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <img src="assets/image/images.png" alt="warning" width="40" height="40"><br/>
                                            <h4 class="modal-title">Warning</h4>
                                            <button type="button" class="close" data-dismiss="modal">×</button>
                                        </div>
                                        <div class="modal-body">
                                            <p class="text-dark" style="font-weight: bold">${error.updateQuestionError}</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <!--get current page-->
                        <c:set var="curPage" value="${requestScope.DEFAULT_PAGES}"/>
                        <c:if test="${empty curPage}">
                            <c:set var="curPage" value="${param.page}"/>
                        </c:if>

                        <c:set var="totalPage" value="${requestScope.TOTAL_PAGES}"/>
                        <!-- Page indicator -->
                        <nav>
                            <ul class="pagination justify-content-center mt-4">
                                <c:forEach var="page" begin="1" end="${totalPage}" step="1">
                                    <c:url var="urlPaging" value="search-question">
                                        <c:param name="txtSearch" value="${searchValue}"/>
                                        <c:param name="cmbSubject" value="${searchSubject}"/>
                                        <c:param name="cmbStatus" value="${searchStatus}"/>
                                        <c:param name="page" value="${page}"/>
                                    </c:url>
                                    <c:if test="${curPage eq page}">
                                        <li class="page-item active">
                                            <a class="page-link" href="${urlPaging}">${page}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${curPage ne page}">
                                        <li class="page-item">
                                            <a class="page-link" href="${urlPaging}">${page}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${empty questionList}">
                        <h1 class="text-center">Question not found</h1>
                    </c:if>
                </div>
            </div>
            <script src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/validation.js"></script>
        </div>
    </body>
</html>
