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
<script>
    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("show");
        switching = true;
        //Set the sorting direction to ascending:
        dir = "asc";
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.getElementsByTagName("TR");
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                /*check if the two rows should switch place,
                 based on the direction, asc or desc:*/
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        //if so, mark as a switch and break the loop:
                        shouldSwitch= true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        //if so, mark as a switch and break the loop:
                        shouldSwitch= true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                //Each time a switch is done, increase this count by 1:
                switchcount ++;
            } else {
                /*If no switching has been done AND the direction is "asc",
                 set the direction to "desc" and run the while loop again.*/
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
</script>

</head>
<body>

<c:import url="navbar.jsp"/>



<div class="row">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-10">
        <table class='table table-hover' id="show">
            <tr>
                <th onclick="sortTable(0);">ID</th>
                <th onclick="sortTable(1);">LAST NAME</th>
                <th onclick="sortTable(2);">FIRST NAME</th>
                <th onclick="sortTable(3);">FATHER's NAME</th>
                <th onclick="sortTable(4);">MOBILE PHONE</th>
                <th onclick="sortTable(5);">HOME PHONE</th>
                <th onclick="sortTable(6);">ADDRESS</th>
                <th onclick="sortTable(7);">EMAIL</th>
                <th></th>
            </tr>



            <c:forEach var='c' items='${contacts}'>
                <tr>
                    <td><a href='/contact/edit/${c.id}'>${c.id}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.lastName}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.firstName}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.middleName}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.mobile}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.home}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.address}</a></td>
                    <td><a href='/contact/edit/${c.id}'>${c.email}</a></td>
                    <td>
                            <%--<a class="btn btn-info" onclick="c1(confirm('are you shure?'), '/contact/edit/${c.id}');">Edit</a>--%>
                        <div class="dropdown">
                            <button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">
                                Delete
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#"><span style="color: green">Cancel</span></a></li>
                                <li><a href="/contact/delete/${c.id}">
                                    <span style="color: red">
                                  Confirm deleting </span>
                                    (id=${c.id})

                                </a>
                                </li>

                            </ul>
                        </div>

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