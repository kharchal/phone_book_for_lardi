<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <title>
            Login page - Phone book
        </title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:import url="navbar.jsp"/>
        <hr>

        <div style="text-align: center">
            <br>
            <span style="color: red;">${msg}</span>
            <h3>
                Login or <a href="/regform">register</a> please: &nbsp;
            </h3>
            <table border=0 align="center">
                <tr>
                    <td valign='center'>
                        <form action='/login' method='post' class="form-inline">
                            <input type='text'  class="form-control"
                                   name='login'
                                   placeholder='Login'
                                   size=10
                                   required=true
                                   title='Letters only (3-10)'/>
                            <input type='password'  class="form-control"
                                   name='password'
                                   placeholder='Password'
                                   size=10
                                   required=true
                                   title='Letters or digits, starts with a letter (5-10 symbols)'/>
                            <input type='submit' value='Login' class="btn btn-info"/>

                        </form>
                    </td>
                </tr>
            </table>
            <hr>
            (c) HAV, 2017
        </div>

    </body>
</html>