<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 25.10.15
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <li class="active"><a href="/user">User</a></li>
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
                <form method="post" action="/user/edit" class="col-lg-offset-4 col-lg-4"">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <div class="form-group">
                        <label>Full name</label>
                        <input type="text" name="fullName" class="form-control" size="140" value="${user.fullName}"/>
                    </div>
                    <div class="form-group">
                        <label>About</label>
                        <textarea name="about" rows="5" cols="100" class="form-control">${user.about}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Username</label>
                        <c:if test="${not empty userNameError}">
                            <div class="alert alert-warning">
                                <p>${userNameError}</p>
                            </div>
                        </c:if>
                        <input type="text" name="userName" class="form-control" size="140" value="${user.username}"/>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-group">
                                <label>New Email</label>
                                <input type="email" name="newEmail" class="form-control" size="140"/>
                            </div>
                            <div class="form-group">
                                <label>Confirm Email</label>
                                <input type="email" name="confirmEmail" class="form-control" size="140"/>
                            </div>
                            <c:if test="${not empty errorConfirmEmail}">
                                <div class="alert alert-warning">
                                    <p>${errorConfirmEmail}</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-group">
                                <label>New Password</label>
                                <input type="password" name="newPassword" class="form-control" size="140"/>
                            </div>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input type="password" name="confirmPassword" class="form-control" size="140"/>
                            </div>
                            <c:if test="${not empty errorConfirmPassword}">
                                <div class="alert alert-warning">
                                    <p>${errorConfirmPassword}</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label >Password</label>
                        <c:if test="${not empty passwordError}">
                            <div class="alert alert-warning">
                                <p>${passwordError}</p>
                            </div>
                        </c:if>
                        <input type="password" name="password" class="form-control" size="140"/>
                    </div>
                    <div class="form-group">
                        <c:if test="${not empty error}">
                            <div class="alert alert-warning">
                                <p>${error}</p>
                            </div>
                        </c:if>
                        <input type="submit" name="submit" class="btn btn-primary btn-block" value="Save"/>
                    </div>
                </form>
            </div>
        </div>
        <footer class="text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</body>
</html>
