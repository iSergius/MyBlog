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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
                        <li><a href="/file">Files</a></li>
                        <li class="active"><a href="/user">User</a></li>
                        <li><a href="/setting">Settings</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="/logout" method="post">
                        <input class="btn btn-link" type="submit" value="Log out" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </nav>
        </header>
        <div class="container">
            <div class="row">
                <div class="col-lg-5">
                    <p class="text-right"><strong>Username:</strong></p>
                    <p class="text-right"><strong>Full name:</strong></p>
                    <p class="text-right"><strong>About:</strong></p>
                    <p class="text-right"><strong>Email:</strong></p>
                </div>
                <div class="col-lg-2">
                    <c:if test="${not empty user}" >
                        <p>${user.username}</p>
                        <p>${user.fullName}</p>
                        <p>${user.about}</p>
                        <p>${user.email}</p>
                        <a href="/user/edit" class="btn btn-primary">Change Information or/and Password</a>
                    </c:if>
                </div>
            </div>

        </div>
        <footer class="text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
