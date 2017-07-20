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

              <input type='text'  class="form-control" value="${user.login}"
                     id="login"
                     name='login' 
                     placeholder='Login' 
                     size=10 
                     required=true
                     <%--pattern='[A-Za-z]{1}[A-Za-z0-9]{3,9}'--%>
                     title='Letters or digits, starts from a letter, 4-10 symbols long'/>

            </td><td>
              <input type='password'  class="form-control"
                     name='password' 
                     placeholder='Password' 
                     size=10
                     required=true
                     <%--pattern='[A-Za-z]{1}[A-Za-z0-9]{3,9}' --%>
                     title='Letters or digits, starts from a letter, 4-10 symbols long'/>

        </td><td>
                <input type="text" class="form-control" value="${user.name}"
                    name="name" required="true" placeholder="Name"/>
        </td><td>
              <input type='submit' value='Send' class="btn btn-info"/>
        </td>


          </td>
        </tr>
          <tr>
              <td><span style="color: red;">${errors.login}</span></td>
              <td><span style="color: red;">${errors.password}</span></td>
              <td><span style="color: red;">${errors.name}</span></td>
          </tr>
      </table>
   </form>
      <hr>
      (c) HAV, 2017</div>

  <a href="#" id="but">.......</a>
  </body>
</html>