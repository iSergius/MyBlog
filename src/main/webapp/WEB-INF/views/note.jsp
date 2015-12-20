<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 25.10.15
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class="active"><a href="/note">Note</a></li>
                        <li><a href="/file">Files</a></li>
                        <li><a href="/user">User</a></li>
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
                        <th><a href="/article/new" class="btn btn-sm btn-success pull-right"><span class="glyphicon glyphicon-plus"></span></a></th>
                    </tr>
                    <c:forEach items="${articles.result()}" var="article">
                    <tr>
                        <td># ${article.id}</td>
                        <td>${article.title}</td>
                        <td>${article.publishedDate}</td>
                        <td>
                            <a href="/article/${article.id}/edit" class="btn btn-sm btn-primary pull-right">Edit</a>
                            <form action="/article/${article.id}/delete" method="post" class="form-inline">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" name="delete" class="btn btn-sm btn-danger pull-right" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="?page=${articles.backwardPagination()}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="${articles.beginPagination()}" end="${articles.endPagination()}" var="page">
                            <c:choose>
                                <c:when test="${page eq articles.page}">
                                    <li class="active"><a href="#">${page}<span class="sr-only"></span></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="<c:url value="/note?markerPage=${markers.page}&articlePage=${page}"/>">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li>
                            <a href="?page=${articles.forwardPagination()}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div id="markersTable" class="col-lg-4">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>
                            <form action="/marker/edit" method="post" class="form-inline pull-right">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="text" name="title" class="form-control input-sm">
                                <input type="submit" name="submit" class="btn btn-sm btn-success" value="New">
                            </form>
                        </th>
                    </tr>
                    <c:forEach items="${markers.result()}" var="marker">
                    <tr>
                        <td># ${marker.id}</td>
                        <td>${marker.title}</td>
                        <td>
                            <form action="/marker/edit" method="post" class="form-inline pull-right">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="id" value="${marker.id}"/>
                                <input type="text" name="title" class="form-control input-sm">
                                <input type="submit" name="submit" class="btn btn-sm btn-primary" value="Save">
                            </form>
                            <form action="/marker/${marker.id}/delete" method="post" class="form-inline pull-right">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" name="delete" class="btn btn-sm btn-danger" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="?page=${markers.backwardPagination()}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="${markers.beginPagination()}" end="${markers.endPagination()}" var="page">
                            <c:choose>
                                <c:when test="${page eq markers.page}">
                                    <li class="active"><a href="#">${page}<span class="sr-only"></span></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="<c:url value="/note?markerPage=${page}&articlePage=${articles.page}"/>">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <li>
                            <a href="?page=${markers.forwardPagination()}" aria-label="Next">
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
