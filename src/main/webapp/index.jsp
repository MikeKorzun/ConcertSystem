<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
    .mainContainer {
        display: flex;
        justify-content: space-around;
    }
    .mainContainerLeft {
        width: 50%;
        text-align: justify;
    }
    .mainContainerRight {
        width: 50%;
    }
    .mainContainerLeft h2 {
        text-align: center;
    }
    .mainContainerRight h2 {
        text-align: center;
    }
    .mainContainerRight>ul {
        padding-left: 20%;
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

    <title>Concert System</title>
</head>

<body>
<h3 align="center">Concert System</h3>
<br/>
<div align="center">
<a href="<c:url value="/SongsList"/>"> Enter the WebApp </a>
</div>
<br/>
<br/>

<div class="mainContainer">
    <div class="mainContainerLeft">
        <h2>Описание приложения</h2>
        Разработать систему для формирования программы концерта по заявкам. Пользователи (фанаты)
        регистрируются в системе и выбирают песни из предложенного списка (или добавляют свои).
        Каждый пользователь может выбрать любое количество песен. Когда время подачи заявок
        оканчивается, формируется программа концерта, включающая песни, набравшие наибольшее
        число заявок (количество песен определяется заранее).
    </div>
    <div class="mainContainerRight">
        <h2>Список использованных технологий</h2>
        <ul>
            <li>БД MySQL</li>
            <li>Hibernate</li>
            <li>Spring</li>
            <ul>
                <li>Spring-MVC</li>
                <li>Spring Security</li>
            </ul>
            <li>JSP, JSTL</li>
            <li>Bootstrap</li>
        </ul>
    </div>
</div>
<br/>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
