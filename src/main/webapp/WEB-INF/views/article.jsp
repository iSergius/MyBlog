<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 16.10.15
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.title} - MyBlog</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <header class="jumbotron row">
            <div class="col-lg-3">
                <h1 class="page-header">MyBlog</h1>
            </div>
            <div class="markers col-lg-6">
                <c:forEach items="${markers}" var="marker">
                    <h3> <span class="marker pull-left text-primary"> <a href="/?marker=${marker.id}"> ${marker.title} </a> </span> </h3>
                </c:forEach>
            </div>
        </header>
        <div class="row">
        <div class="col-lg-offset-1 col-lg-10" >
            <article class="panel panel-default">

                <div class="panel-heading">
                    <h2 class="panel-title pull-left">${article.title}</h2>
                    <span class="date pull-right">${article.publishedDate}</span>
                    <span class="clearfix"></span>
                </div>
                <div class="panel-body">
                    ${article.content}
                </div>
                <div class="panel-footer">
                    <c:forEach items="${article.markers}" var="marker">
                        <a href="/?marker=${marker.id}"><span class="marker text-primary">${marker.title}</span></a>
                    </c:forEach>
                </div>

            </article>
        </div>
        </div>
        <footer class="well text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
