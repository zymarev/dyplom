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
<div class="container-fluid" style="background-color: #F9F9F2">
    <div class="row">
        <div class="col-3">
            <%@include file="/WEB-INF/jspf/leftMenu.jspf"%>
        </div>
        <div class="col-9">
        <form action="Controller.do?command=getGroups" method="POST">
                        <input type="text" name="courseNameForGroups" placeHolder="${courseName}">
                        <button type="submit">Submit</button>
                        <c:if test="${empty groupsForCourse}">
                        Groups have not been formed yet. Do you want to<a href="Controller.do?command=formGroups&courseNameForGroups=${courseName}"> generate</a> it now?
                        </c:if>
                        <c:if test="${not empty groupsForCourse}">
       <table class="table table-responsive table-hover">

           <tbody>
           <c:forEach var="map" items="${groupsForCourse}">

               <tr class="clickable waves-effect" data-toggle="collapse" id="${map.lesson.name}" data-target=".${map.lesson.name}">

                   <td>${map.lesson.name}</td>
                 	<td>${map.lesson.professor}</td>
                   <td>${map.lesson.maxCount}</td>
               </tr>
               <c:forEach var="user" items="${map.users}">
               <tr class="collapse ${map.lesson.name}">

                           <td style="background-color: #F6F3CC">${user.firstName}</td>
                         	<td style="background-color: #F6F3CC">${user.lastName}</td>
                           <td style="background-color: #F6F3CC">${user.avgMark}</td>

                       </tr>
                </c:forEach>



               </c:forEach>
           </tbody>
       </table>
       </c:if>
    </div>
    </div>
</div>
</body>
</html>