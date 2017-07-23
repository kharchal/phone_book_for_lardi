<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    <script>
        user = {
            name : "111",
            login : "eee",
            pass : "vvv"
        };

        function foo() {
            user.pass = $("input[name='password']").val();
            user.login = $("input[name='login']").val();
            user.name = $("input[name='name']").val();

            json = JSON.stringify(user);
            $.post("/ajax/sss", {json: json}, function (response) {

                alert(user + "\n" + response);
            });
        }
        var prev = "";
        function setLoginInputBorderRed() {
            $("#login").css("border", "3px solid red");
        }
        $(document).ready(function () {
            setLoginInputBorderRed();
            $("#login").on("keyup input", function (){
                var logx = $("#login").val();
                if (logx.length < 4) {
                    setLoginInputBorderRed();
                }
                if (logx.length >= 4 && prev != logx) {
                    $.ajax({
                        url: "/ajax/checklogin",
                        method: "POST",
                        cache: false,
                        data: "log=" + logx,
                        success: function(response, status){
                            if (status === "success") {
                                var color = (response) ? "#66ff33" : "red";
                                $("#login").css("border", "3px solid " + color);
                            }
                        }
                    });
                }
                prev = logx;
            });
        });
    </script>

</head>
<body>

<c:import url="navbar.jsp"/>


<div style="text-align: center">
    <br>
    <h3>
        Registration form:
    </h3>
    <span style="color: red">${msg}</span><br>
    <form action='/register' method='post' class="form-inline">
        <table border=0 align="center">
            <tr>
                <td>

                    <input type='text'
                           class="form-control"
                           value="${user.login}"
                           id="login"
                           name='login'
                           placeholder='Login'
                           size=20
                           required=true
                           title='Letters only (3-10 symbols)'/>

                </td>
                <td>
                    <input type='password'
                           class="form-control"
                           name='password'
                           placeholder='Password'
                           size=20
                           required=true
                           title='Letters or digits, starts with a letter (5-10 symbols)'/>

                </td>
                <td>
                    <input type="text"
                           class="form-control"
                           value="${user.name}"
                           name="name"
                           size="20"
                           required="true"
                           title="Enter your name"
                           placeholder="Name"/>
                </td>
                <td>
                    <input type='submit' value='Send' class="btn btn-info"/>
                </td>


            </tr>

            <tr>
                <td><span style="color: red; font-size: small">${errors.login}</span></td>
                <td><span style="color: red; font-size: small">${errors.password}</span></td>
                <td><span style="color: red; font-size: small">${errors.name}</span></td>
            </tr>
        </table>
    </form>
    Here you enter your login, password and name.
    <hr>
    (c) HAV, 2017</div>

</body>
</html>