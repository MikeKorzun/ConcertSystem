<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 08.10.2017
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <title>Event Tracklist</title>
</head>

<body>
<a href="<c:url value="/Admin"/>" >Back</a>
<br/>
<br/>

<h1>Event Tracklist</h1>

<c:if test="${!empty eventTracklist}">
    <table class="table table-bordered table-hover">
        <tr>
            <th>ID</th>
            <th>Artist</th>
            <th>Title</th>
            <th>Votes</th>
        </tr>
        <c:forEach items="${eventTracklist}" var="tracklist">
            <tr>
                <td>${tracklist.tracklistSongID}</td>
                <td>${tracklist.tracklistSongArtist}</td>
                <td>${tracklist.tracklistSongTitle}</td>
                <td>${tracklist.tracklistSongVotes}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
