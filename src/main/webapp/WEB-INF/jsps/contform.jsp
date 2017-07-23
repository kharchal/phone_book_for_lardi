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

    <div class="container centered">
        <div class="col-xs-3"></div>
        <div class="col-xs-6">
            <table class='table table-hover' width="50%">

                <tr>
                    <th width="100">LABEL</th>
                    <th width="400">INPUT</th>
                </tr>
                <form action='/contact/save' method='post'>
                    <c:if test="${not empty contact.id}">
                        <tr>
                            <td>ID</td>
                            <td>
                                <input type='text' class='form-control' name='id' value='${contact.id}' readonly="true">
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>LAST NAME</td>
                        <td>
                            <input type='text' class='form-control' name='lastName' value='${contact.lastName}'
                                   title="4 - 10 letters long"/>
                            <span style="color: red; font-size: small">${errors.lastName}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>FIRST NAME</td>
                        <td>
                            <input type='text' class='form-control' name='firstName' value='${contact.firstName}'
                                   title="4 - 10 letters long"/>
                            <span style="color: red; font-size: small">${errors.firstName}</span>              </td>
                    </tr>

                    <tr>
                        <td>FATHER's NAME</td>
                        <td>
                            <input type='text' class='form-control' name='middleName' value='${contact.middleName}'
                                   title="4 - 10 letters long"/>
                            <span style="color: red; font-size: small">${errors.middleName}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>MOBILE PHONE</td>
                        <td>
                            <input type='text' class='form-control' name='mobile' value='${contact.mobile}'
                                   title="use the following pattern: +38(0ZZ)XXXXXXX Z - two digits, X - seven"/>
                            <span style="color: red; font-size: small">${errors.mobile}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>HOME PHONE</td>
                        <td>
                            <input type='text' class='form-control' name='home' value='${contact.home}'
                                   title="use the following pattern: +38(0ZZ)XXXXXXX Z - two digits, X - seven"/>
                            <span style="color: red; font-size: small">${errors.home}</span>

                        </td>
                    </tr>
                    <tr>
                        <td>ADDRESS</td>
                        <td align="left">
                            <input type="text" class='form-control' name='address'
                                   title="Your address"
                                   value="${contact.address}"/>
                            <span style="color: red; font-size: small">${errors.address}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>EMAIL</td>
                        <td>
                            <input type="text" class="form-control" name="email" value="${contact.email}"
                                   title="use the following pattern: 'user@name.domain'"/>
                            <span style="color: red; font-size: small">${errors.email}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <c:set var="capt" value=""/>
                            <c:if test="${empty contact.id}">
                                <c:set var="capt" value=" new"/>
                            </c:if>
                            <input type='submit' class="btn btn-success" value='Save${capt} contact'/>
                        </td>
                    </tr>
                </form>

            </table>
        </div>
        <div class="col-xs-3"></div>
    </div>

    <hr>
    <div align="center">(c) Sunny, 2017</div>
</body>
</html>