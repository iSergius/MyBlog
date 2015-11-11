<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 25.10.15
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-lg-offset-5">
                <div class="container-fluid">
                    <form name="loginform" action="/login" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <input class="form-control" name="username" type="email" placeholder="email"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" type="password" placeholder="password">
                        </div>
                        <c:if test="${not empty error}">
                            <div class="alert alert-warning">
                                <p>${error}</p>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="checkbox" name="remember" value="Remember Me">
                        </div>
                        <div class="form-group">
                            <input class="form-control btn btn-primary" name="submit" type="submit" value="LogIn">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
