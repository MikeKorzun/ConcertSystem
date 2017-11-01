<%--
  Created by IntelliJ IDEA.
  User: MK
  Date: 08.10.2017
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
    .eventListTable th,td,tr {
        border: 1px solid #000000;
        text-align: center;
        padding: 5px;
    }
    .eventListTable caption {
        caption-side: top;
        text-align: center;
        font-size: 20px;
        color: #000000;
    }
    .form-container {
        position: static;
        width:50%;
        margin-left: 10px;
        margin-top: 10px;
        margin-bottom: 10px;
        padding: 10px;
        background-color: #E8E1E1;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .floatRight{
        float:right;
        margin-right: 18px;
    }

    .has-error{
        color:red;
    }


    .success{
        position:fixed;
        width:50%;
        margin-left: 50px;
        margin-top:20px;
        padding: 20px;
        background-color: #E8E1E1;
        border: 1px solid #ddd;
        border-radius: 4px;
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

    <title>Title</title>
</head>
<body>
<br/>
<div class="MenuContainer">
<h2>Welcome ${pageContext.request.userPrincipal.name} | <a href="${contextPath}/logout">Logout</a></h2>
</div>
<br/>
<br/>
<a href="<c:url value="/UsersList"/>" class="badge badge-info" style="font-size: 20px">USERS List</a>
<br/>
<br/>

<c:url var="addEvent" value="/Admin/add" />



    <h1>Add Event</h1>

<form:form action="${addEvent}" method="POST" modelAttribute="event" class="form-horizontal">

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="eventName">Event Name</label>
            <div class="col-md-7">
                <form:input type="text" path="eventName" id="eventName" class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="eventName" class="help-inline"/>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="ratingSize">Last Name</label>
            <div class="col-md-7">
                <form:input type="text" path="ratingSize" id="ratingSize" class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="ratingSize" class="help-inline"/>
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Add Event" class="btn btn-primary btn-sm">
        </div>
    </div>
</form:form>


<%--
<form:form action="${addEvent}" commandName="event">
    <table class="eventListTable">
        <caption>Add Event</caption>
        <tr>
            <td>
                <form:label path="eventName">
                    <spring:message text="Event Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="eventName"/>
                <div class="has-error">
                    <form:errors path="eventName" class="help-inline"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="ratingSize">
                    <spring:message text="Event Rating Size"/>
                </form:label>
            </td>
            <td>
                <form:input path="ratingSize"/>
                <div class="has-error">
                    <form:errors path="ratingSize" class="help-inline"/>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${empty event.eventName}">
                    <input type="submit"
                           value="<spring:message text="Add Event"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
--%>
<div class="table-container">
<c:if test="${!empty eventList}">
    <table class="eventListTable">
        <caption>Event list</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Event Name</th>
            <th>TrackList</th>
            <th>Status</th>
            <th>Stop Button</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${eventList}" var="event">
            <tr>
                <td>${event.id}</td>
                <td>${event.eventName}</td>
                <td>
                    <c:if test="${event.status=='Active'}">
                    <a href="EventData/${event.id}">TrackList</a>
                    </c:if>
                </td>
                <td>${event.status}</td>
                <td>
                    <c:if test="${event.status=='Active'}">
                    <form action='/stop/${event.id}'>
                        <button>Stop event</button>
                    </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
