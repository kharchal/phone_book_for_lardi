<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse">
    <%--<div class="container">--%>
        <div class="container-fluid">
                <div class="navbar-header">
                    <!-- Logo section -->
                    <a class="navbar-brand" href="/login">
                        <span class="glyphicon glyphicon-earphone"></span>&nbsp;
                        The Phone Book
                    </a>
                </div>
            <ul class="nav navbar-nav">


                <!-- Menu section -->

                <c:set var="uri" value="${pageContext.request.requestURI}"/>
                <c:set var="dotind" value="${uri.indexOf('.')}"/>
                <c:set var="path" value="${uri.substring(14, dotind)}"/>
                <%--path = [${path}]<br>--%>
                <c:if test="${path eq 'login'}">
                    <c:if test="${not empty loggeduser}">
                        <li><a href="/contact/">List</a></li>
                        <li class="active"><a href="/contact/new">Add new</a></li>
                    </c:if>
                    <c:if test="${empty loggeduser}">
                        <li><a href="/regform">Register</a></li>
                        <li class="active"><a href="#">Login</a></li>
                    </c:if>
                </c:if>
                <c:if test="${path eq 'regpage'}">
                    <c:if test="${not empty loggeduser}">
                        <li><a href="/contact/">List</a></li>
                        <li class="active">
                            <a href="#">
                                <span class="glyphicon glyphicon-plus-sign"></span>&nbsp; Add new
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty loggeduser}">
                        <li class="active"><a href="#">Register</a></li>
                        <li><a href="/login">Login</a></li>
                    </c:if>
                </c:if>
                <c:if test="${path eq 'show'}">
                    <li class="active"><a href="#">List</a></li>
                    <li><a href="/contact/new"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp; Add new</a></li>
                </c:if>
                <c:if test="${path eq 'contform'}">
                    <li><a href="/contact/">List</a></li>
                    <c:set var="edit">Edit</c:set>
                    <c:if test="${empty contact.id}">
                        <c:set var="edit">Add</c:set>
                    </c:if>
                    <li class="active"><a href="#">${edit}</a></li>
                </c:if>
                <li><a href="#" onclick="window.history.back();">Back</a></li>

        <%--</ul>--%>
                <%--<li class="active"><a href="#">Contact List</a></li>--%>
                <%--<li><a href="/contact/new">Add New Contact</a></li>--%>


                <!-- Search section -->

                <c:if test="${path eq 'show'}">
                <form action="/contact/search" method="post" class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" name="search" class="form-control" placeholder="Search">
                    </div>
                    <button class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </form>
                </c:if>

            </ul>
            <ul class="nav navbar-nav navbar-right">




                <!-- User section -->

                <c:if test="${not empty loggeduser}">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;${loggeduser.name}</a></li>
                </c:if>
                <c:if test="${empty loggeduser}">
                    <li><a href="/regform"><span class="glyphicon glyphicon-user"></span>&nbsp;Register</a></li>
                </c:if>




                <%--<li><a href="/regform"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>


                <li>
                <!-- Log in/out section -->

                <c:if test="${not empty loggeduser}">
                    <a class="btn btn-link dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-log-out"></span>&nbsp;Logout
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span style="color: green">Cancel</span></a></li>
                        <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout as:
                            <span class="glyphicon glyphicon-user"></span>
                                ${loggeduser.name}</a></li>
                    </ul>
                </c:if>
                <c:if test="${empty loggeduser}">
                    <a href="/login"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Login</a>
                </c:if>



                </li>
            </ul>
        </div>
    <%--</div>--%>
</nav>