<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>
      Main page - Phone book
    </title>
    <%--<style>--%>
      <%--td {text-align: center}--%>
      <%--th {text-align: right}--%>
    <%--</style>--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>

  <c:import url="navbar.jsp"/>



      <div class="row">
          <div class="col-xs-1">
          </div>
          <div class="col-xs-10">
          <table class='table table-hover'>
              <tr>
                  <th>ID</th>
                  <th>NAME</th>
                  <th>LAST NAME</th>
                  <th>MIDDLE NAME</th>
                  <th>MOBILE PHONE</th>
                  <th>HOME PHONE</th>
                  <th>ADDRESS</th>
                  <th>EMAIL</th>
                  <th></th>
              </tr>



            <c:forEach var='c' items='${contacts}'>
              <tr>
                <td><a href='/contact/edit/${c.id}'>${c.id}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.firstName}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.lastName}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.middleName}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.mobile}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.home}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.address}</a></td>
                <td><a href='/contact/edit/${c.id}'>${c.email}</a></td>
                <td>
                  <%--<a class="btn btn-info" onclick="c1(confirm('are you shure?'), '/contact/edit/${c.id}');">Edit</a>--%>
                      <div class="dropdown">
                          <button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">Delete
                              <span class="caret"></span></button>
                          <ul class="dropdown-menu">
                              <li><a href="#"><span style="color: green">Cancel</span></a></li>
                              <li><a href="/contact/delete/${c.id}"><span style="color: red">Confirm delete</span></a></li>

                          </ul>
                      </div>
                  <%--<a class="btn" onclick="check('/contact/delete/', ${c.id});">--%>
                      <%--<span style="color: red" class="glyphicon glyphicon-remove"> Delete</span>--%>
                  <%--</a>--%>
                </td>
              </tr>
            </c:forEach>
          </table>
      </div>
    <div class="col-xs-1">
    </div>
      </div>
      <hr>
    <div align="center">(c) Sunny, 2017</div>

  </body>
</html>