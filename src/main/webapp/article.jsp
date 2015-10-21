<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 16.10.15
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Article</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <header class="jumbotron">
            <h1>MyBlog</h1>
        </header>
        <article class="well">
            <h2>${article.title}</h2>
            <div>${article.content}</div>
        </article>
        <footer>
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
