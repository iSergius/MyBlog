
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
            <div class="col-lg-4">
                <form action="/file/${fileMetadata.id}/edit" method="post" enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" value="${fileMetadata.id}">
                    <div class="form-group">
                        <label>Title</label>
                        <input class="form-control" type="text" name="title" value="${fileMetadata.name}">
                    </div>
                    <div class="form-group">
                        <label>Mime type</label>
                        <input class="form-control" type="text" name="mimeType" value="${fileMetadata.mimeType}">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-block" value="Save">
                    </div>
                </form>
                <form action="/file/${fileMetadata.id}/edit?${_csrf.parameterName}=${_csrf.token}"
                      method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="file" name="fileData" class="form-control">
                    </div>
                    <input type="submit" value="Change" class="btn btn-success btn-block" >
                </form>
            </div>
            <div class="col-lg-8">
                <c:choose>
                    <c:when test="${fn:contains(fileMetadata.mimeType,\"image/\")}">
                        <img src="/file/${fileMetadata.name}" alt="${file.name}">
                    </c:when>
                    <c:otherwise>
                        <a href="/file/${fileMetadata.name}"><span class="glyphicon glyphicon-file"></span></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <footer class="text-center">
        iSergius Copyright 2015
    </footer>
</div>
</body>
</html>
