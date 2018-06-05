<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/font-awesome.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js">
        <link
         href="css/bootstrap.css" rel="stylesheet" />
          <link href="css/font-awesome.css" rel="stylesheet" />
          <link href="css/style.css" rel="stylesheet" />

           <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
              <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.tablesorter.js"></script>
    <script src="js/jquery.tablesorter.pager.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
    	$("#myTable").tablesorter();
        });

    </script>
</head>
<body>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <%@include file="/WEB-INF/jspf/leftMenu.jspf"%>
        </div>
        <!-- The Modal -->
        <form action="Controller.do?command=usersByCourse" method="POST">
                <input type="text" name="courseName" placeHolder="course">
                <button type="submit">Submit</button>

        <div class="col-9">
            <table id="myTable" class="table">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Количсетво студентов<th>
                    <th>Преподаватель</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="lesson" items="${lessonsByCourse}">
                    <tr>
                        <td>${lesson.name}</td>
                        <td>${lesson.maxCount}</td>
                        <td>${lesson.professor}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <p font-color="red">${error}</p>
        </div>
    </div>
</div>
</body>
</html>