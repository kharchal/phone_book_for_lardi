<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

      
          <table class='table table-hover' >

              <tr>
                  <th align=center width="30">ID</th>
                  <th width=100>NAME</th>
                  <th width=100>L.NAME</th>
                  <th width=100>M.NAME</th>
                  <th width=150>MOB. PHONE</th>
                  <th width=150>HOME PHONE</th>
                  <th width=200>ADDRESS</th>
                  <th width="150">EMAIL</th>
                  <th>ACTIONS</th>
              </tr>
            <tr>
              <form action='/contact/save' method='post'>
              <td>
                <input type='text' class='form-control' name='id' value='${contact.id}' readonly="true">
              </td>
              <td>
                <input type='text' class='form-control' name='firstName' value='${contact.firstName}'
                <%--pattern="[A-ZА-Я]{1}[a-zа-я]{2,9}"--%>
                title="10 letters long, starts from capital"/>
                  <span style="color: red; font-size: small">${errors.firstName}</span>              </td>
              <td>
                <input type='text' class='form-control' name='lastName' value='${contact.lastName}'
                       <%--pattern="[A-ZА-Я]{1}[a-zа-я]{2,9}"--%>
                       title="10 letters long, starts from capital"/>
                  <span style="color: red; font-size: small">${errors.lastName}</span>
              </td>
              <td>
                <input type='text' class='form-control' name='middleName' value='${contact.middleName}'
                       <%--pattern="[A-ZА-Я]{1}[a-zа-я]{2,9}"--%>
                       title="10 letters long, starts from capital"/>
                  <span style="color: red; font-size: small">${errors.middleName}</span>
              </td>
              <td>
                <input type='text' class='form-control' name='mobile' value='${contact.mobile}'
                       <%--pattern="^\+38\(0[0-9]{2,3}\)[0-9]{6,7}$"--%>
                       <%--required="true"--%>
                       title="+38(0XX)XXXXXXX"/>
                  <span style="color: red; font-size: small">${errors.mobile}</span>
              </td>
              <td>
                <input type='text' class='form-control' name='home' value='${contact.home}'
                       <%--pattern="^\+38\(0[0-9]{2,3}\)[0-9]{6,7}$"--%>
                       <%--required="true"--%>
                       title="+38(0XX)XXXXXXX"/>
                  <span style="color: red; font-size: small">${errors.home}</span>

              </td>
              <td>
                <input type='text' class='form-control' name='address' value='${contact.address}'/>
                  <span style="color: red; font-size: small">${errors.address}</span>
                <%--pattern="^.{1,35}$">--%>
              </td>
              <td>
                <input type="text" class="form-control" name="email" value="${contact.email}"
                <%--pattern="^.+@.+\..+$"--%>
                       <%--maxlength="10"--%>
                title="xxx@xxx.xxx"/>
                  <span style="color: red; font-size: small">${errors.email}</span>
              </td>
              <td>
                  <c:set var="capt" value=""/>
                  <c:if test="${empty contact.id}">
                    <c:set var="capt" value=" new"/>
                  </c:if>
                <input type='submit' class="btn btn-success" value='Save${capt} contact' name='id'>
              </td>
              </form>
            </tr>




          </table>

      <hr>
    <div align="center">(c) Sunny, 2017</div>
  </body>
</html>