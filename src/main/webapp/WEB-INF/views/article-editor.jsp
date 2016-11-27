<%--
  Created by IntelliJ IDEA.
  User: isergius
  Date: 26.10.15
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                    <li class="active"><a href="/note">Note</a></li>
                    <li><a href="/file">Files</a></li>
                    <li><a href="/user">User</a></li>
                    <li><a href="/setting">Settings</a></li>
                </ul>
                <form class="navbar-form navbar-right" action="/logout" method="post">
                    <input class="btn btn-link" type="submit" value="Log out"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </nav>
    </header>
    <div class="container">
        <article>
            <form:form method="post" modelAttribute="article" action="edit" cssClass="row" acceptCharset="UTF-8">
                <form:hidden path="id"/>
                <div class="col-lg-10">
                    <div class="form-group">
                        <form:label path="title">Title</form:label>
                        <form:input cssClass="form-control" size="140" path="title"/>
                    </div>
                    <div class="form-group">
                        <form:label path="disclaimer">Disclaimer</form:label>
                        <form:textarea rows="8" cols="140" cssClass="form-control" path="disclaimer"/>
                    </div>
                    <div class="form-group">
                        <form:label path="content">Content</form:label>
                        <form:textarea rows="20" cols="140" cssClass="form-control" path="content"/>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="form-group">
                        <form:checkboxes title="Markers:" path="markers" id="markers" items="${markers}"
                                         itemLabel="title" itemValue="id" element="div"/>
                    </div>
                    <div class="form-group">
                        <label>Published date</label>
                        <p>${article.publishedDate}</p>
                    </div>
                    <div class="form-group">
                        <form:label path="published">Is Published</form:label>
                        <form:checkbox cssClass="form-control" path="published"/>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary btn-block" type="submit" value="Save"/>
                    </div>
                </div>
            </form:form>
        </article>
        <footer class="text-center">
            iSergius Copyright 2015
        </footer>
    </div>
</div>
</body>
</html>
