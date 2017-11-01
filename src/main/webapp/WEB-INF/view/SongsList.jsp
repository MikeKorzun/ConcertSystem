<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 26.09.2017
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<style>

    .mycontainer{
        display: flex;
        justify-content: space-around;
    }
    .MenuContainer{
        background-color: #b3d7ff;
        border-top: 1px solid #007bff;
        border-bottom: 1px solid #007bff;
        text-align: right;
    }
    .MenuContainer h2{
        padding-right: 10px;
    }
    .mycontainer td{
        border: 1px solid #000000;
        padding: 10px;
    }
    .mycontainer2{
        margin-top: 150px;
    }
    .mycontainer1 h1{
        text-align: center;
    }

</style>

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

    <title>Song List</title>
</head>
<body>
<br/>

<div class="MenuContainer">
  <h2>Welcome ${pageContext.request.userPrincipal.name} | <a href="/logout">Logout</a></h2>
</div>

<br/>

<div class="mycontainer">
<div class="mycontainer1">
<h1>Add Song</h1>
<c:url var="addSong" value="/SongsList/add" />

<form:form action="${addSong}" commandName="song">
    <table>
        <c:if test="${!empty song.songTitle}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td>
                <form:label path="songArtist">
                    <spring:message text="Artist"/>
                </form:label>
            </td>
            <td>
                <form:input path="songArtist"/>
                <div class="has-error">
                    <form:errors path="songArtist" class="help-inline"/>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="songTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="songTitle"/>
                <div class="has-error">
                    <form:errors path="songTitle" class="help-inline"/>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty song.songTitle}">
                    <input type="submit"
                           value="<spring:message text="Edit Song"/>"/>
                </c:if>
                <c:if test="${empty song.songTitle}">
                    <input type="submit"
                           value="<spring:message text="Add Song"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>

<h1>Song List</h1>

<c:if test="${!empty songsList}">
<table>
    <tr>
        <th>ID</th>
        <th>Artist</th>
        <th>Title</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>Add to Tracklist</th>
    </tr>
    <c:forEach items="${songsList}" var="song">
    <tr>
        <td>${song.id}</td>
        <td>${song.songArtist}</td>
        <td><a href="SongData/${song.id}">${song.songTitle}</a></td>

        <td><a href="<c:url value='/edit/${song.id}'/>">Edit</a> </td>
        <td><a href="<c:url value='/delete/${song.id}'/>">Delete</a> </td>
        <td>
            <c:if test="${(activeEvent.status=='Active') && (!song.songInTracklist)}">
            <form action='/addToTracklist/${song.id}'>
                    <button>Add to Tracklist</button>
            </form>
        </c:if>
        </td>
    </tr>
    </c:forEach>
</table>
</c:if>
</div>

<div class="mycontainer2">


        <a href="/ratingtracklist" class="badge badge-info" style="font-size: 20px">TOP RATING</a>


   <h1>Event Tracklist</h1>

<c:if test="${!empty tracklistSongList}">
    <table>
        <tr>
            <th hidden>ID</th>
            <th>Artist</th>
            <th>Title</th>
            <th>Votes</th>
            <th>Button</th>
        </tr>
        <c:forEach items="${tracklistSongList}" var="song">
            <tr>
                <td hidden>${song.tracklistSongID}</td>
                <td>${song.tracklistSongArtist}</td>
                <td>${song.tracklistSongTitle}</td>
                <td>${song.tracklistSongVotes}</td>
                <td>
                    <c:if test="${activeEvent.status=='Active'}">
                    <form action='/vote/${song.tracklistSongID}'>
                        <button>Vote</button>
                    </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
