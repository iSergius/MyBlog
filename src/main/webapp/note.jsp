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
    <div class="">
        <header class="row">

        </header>
        <div class="row">
            <div id="articlesTable" class="col-lg-8">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Published</th>
                    </tr>
                    <c:forEach items="${articles}" var="article">
                    <tr>
                        <td># ${article.id}</td>
                        <td>${article.title}</td>
                        <td>${article.publishedDate}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="markersTable" class="col-lg-4">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                    </tr>
                    <c:forEach items="${markers}" var="marker">
                    <tr>
                        <td>${marker.id}</td>
                        <td>${marker.title}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <footer class="text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
