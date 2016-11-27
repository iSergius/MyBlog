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
<div class="container">
  <header class="jumbotron row">
    <div class="col-lg-3">
      <a href="/"><h1 class="page-header">${blogTitle}</h1></a>
    </div>
    <div class="markers col-lg-6">
      <c:forEach items="${markers}" var="marker">
         <h3> <span class="marker pull-left text-primary"> <a href="/filter/marker/${marker.id}"> ${marker.title} </a> </span> </h3>
      </c:forEach>
    </div>
  </header>
  <c:forEach items="${articles.result()}" var="article">
  <div class="row">
    <div class="col-lg-offset-1 col-lg-10" >
      <article class="panel panel-default">

        <div class="panel-heading">
          <a href="/article/${article.id}"><h2 class="panel-title pull-left">${article.title}</h2></a>
          <span class="date pull-right">${article.publishedDate}</span>
          <span class="clearfix"></span>
        </div>
        <div class="panel-body">
          ${article.disclaimer}
          <br>
          <a href="/article/${article.id}">Read more</a>
        </div>
        <div class="panel-footer">
          <c:forEach items="${article.markers}" var="marker">
            <a href="/filter/marker/${marker.id}"><span class="marker text-primary">${marker.title}</span></a>
          </c:forEach>
        </div>

      </article>
    </div>
  </div>
  </c:forEach>
  <nav>
    <ul class="pagination center">
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
            <li><a href="?page=${page}">${page}</a></li>
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
  <footer class="well text-center">
    iSergius Copyright 2015
  </footer>
</div>
</body>
</html>
