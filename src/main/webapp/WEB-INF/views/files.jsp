<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 25.10.15
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>${title}</title>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <header class="row">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="/">Home</a></li>
                        <li><a href="/note">Note</a></li>
                        <li class="active"><a href="/file">Files</a></li>
                        <li><a href="/user">User</a></li>
                        <li><a href="/setting">Settings</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="/logout" method="post">
                        <input class="btn btn-link" type="submit" value="Log out" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </nav>
        </header>
        <div class="row">
            <div id="articlesTable" class="col-lg-8">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Published</th>
                        <th>
                            <form action="/file/edit?${_csrf.parameterName}=${_csrf.token}"
                                  method="post" enctype="multipart/form-data" class="form-inline">
                                <input type="submit" name="" value="New" class="btn btn-sm btn-success pull-right" >
                                <input type="file" name="fileData" class="pull-right">
                            </form>
                        </th>
                    </tr>
                    <c:forEach items="${files.result()}" var="fileMetadata">
                    <tr>
                        <td># ${fileMetadata.id}</td>
                        <td>${fileMetadata.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${fn:contains(fileMetadata.mimeType,\"image/\")}">
                                    <img class="img-rounded" src="/file/${fileMetadata.name}" alt="${fileMetadata.name}" height="100px">
                                </c:when>
                                <c:otherwise>
                                    <a href="/file/${fileMetadata.title}"><span class="glyphicon glyphicon-file"></span></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form action="/file/${fileMetadata.id}/delete" method="post" class="form-inline">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" name="delete" class="btn btn-sm btn-danger pull-right" value="Delete"/>
                            </form>
                            <a href="/file/${fileMetadata.id}/edit" class="btn btn-sm btn-primary pull-right">Edit</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="?page=${files.backwardPagination()}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="${files.beginPagination()}" end="${files.endPagination()}" var="page">
                            <c:choose>
                                <c:when test="${page eq files.page}">
                                    <li class="active"><a href="#">${page}<span class="sr-only"></span></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="<c:url value="?page=${page}"/>">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li>
                            <a href="?page=${files.forwardPagination()}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <footer class="text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
