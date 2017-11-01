<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 16.10.2017
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

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

    <title>TOP RATING</title>
</head>

<body>

<a href="<c:url value="/SongsList"/>" class="badge badge-info" style="font-size: 20px">Back to main page</a>

<c:if test="${!empty sortedTrackList}">
    <table class="table table-bordered table-hover">
        <tr>
            <th hidden>ID</th>
            <th>Artist</th>
            <th>Title</th>
            <th>Votes</th>

        </tr>
        <c:forEach items="${sortedTrackList}" var="song">
            <tr>
                <td hidden>${song.tracklistSongID}</td>
                <td>${song.tracklistSongArtist}</td>
                <td>${song.tracklistSongTitle}</td>
                <td>${song.tracklistSongVotes}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
