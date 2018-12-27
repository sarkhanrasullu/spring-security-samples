<%-- 
    Document   : user
    Created on : Nov 7, 2018, 3:19:38 PM
    Author     : tabrizguliyev
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Task</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            function setUpdate(id, user) {
                var elem = document.getElementById("updateMyUser");
                elem.querySelector('[id=userIdUpdate]').value = id;
                elem.querySelector('[id=name]').value = user.name;
                elem.querySelector('[id=surname]').value = user.surname;
                elem.querySelector('[id=username]').value = user.username;
                elem.querySelector('[id=blocked]').value =user.blocked;
            }
            function setDelete(id) {
                document.getElementById("userIdDelete").value = id;
            }
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
            <ul class="nav navbar-nav">
                <li><a href="#showAllUsers">
                        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#get">Show/Hide All Users</button>
                    </a></li>

                <li><a href="#addUser">
                        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#insert">Add User</button>
                    </a></li>
            </ul>
        <form:form>
            <div id="get" class="collapse collapse col-md-12">
                <br>
                <br>
                <br>
                <div class="container-fluid">
                    <table class="table">
                        <thead  >
                            <tr>
                                <th width="8%" scope="col">#</th>
                                <th  scope="col">name</th>
                                <th  scope="col">surname</th>
                                <th  scope="col">username</th>
                                <th  scope="col">blocked</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="item">

                                <tr>
                                    <td>
                                        ${item.id} 
                                    </td>
                                    <td>
                                        ${item.name} 
                                    </td>
                                    <td>
                                        ${item.surname} 
                                    </td>
                                    <td>
                                        ${item.username} 
                                    </td>
                                    <td>
                                        ${item.blocked} 
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updateMyUser"

                                                onclick="setUpdate('${item.id}',
                                                                {
                                                                    name: '${item.name}',
                                                                    surname: '${item.surname}',
                                                                    username: '${item.username}',
                                                                    blocked: '${item.blocked}',
                                                                })">Update</button>

                                        <button  type="button" class="btn btn-danger"  data-toggle="modal" data-target="#deleteMyUser" 

                                                 onclick="setDelete('${item.id}')">Delete</button>
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>


                </div>


            </div>


        </form:form>


        <form:form
            method="POST"
            action="/user/add" 
            modelAttribute="user"
            >

            <div id="insert" class="collapse collapse col-md-12 " >
                <br>
                <br>
                <br>
                <table class="table">
                    <br>
                    <tr>

                        <td><form:label  path="name">Name</form:label></td>
                        <td><form:input path="name"/></td>
                    </tr>
                    <tr>
                        <td><form:label  path="surname">SurName</form:label></td>
                        <td><form:input path="surname"/></td>
                    </tr>
                    <tr>
                        <td><form:label  path="username">UserName</form:label></td>
                        <td><form:input path="username"/></td>
                    </tr>
                    <tr>
                        <td><form:label  path="password">Password</form:label></td>
                        <td><form:input path="password"/></td>
                    </tr>



                    <td>
                        <input class="btn-info" type="submit" value="Submit"/>
                    </td>

                </table>
            </div>

        </form:form>

        <form:form
            method="POST" 
            action="user/update"
            modelAttribute="user"
            >
            <div class="container">
                <div class="modal fade" id="updateMyUser" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">   
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times</button>
                                <h4 class="modal-title">Update User</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="form-group">

                                        <form:label  path="name">Name</form:label>
                                        <form:input   path="name"/>
                                        <form:label  path="surname">Surname</form:label>
                                        <form:input   path="surname"/>
                                        <form:label  path="username">Username</form:label>
                                        <form:input   path="username"/>
                                        <form:label  path="password">Password</form:label>
                                        <form:input   path="password"/>

                                        <form:label  path="blocked">blocked</form:label>
                                        <form:input   path="blocked"/>


                                    </div >
                                </div>
                            </div>
                            <div class="modal-footer">
                                <form:hidden path ="id" id="userIdUpdate"/>
                                <input type="submit" class="btn btn-default"   value="Ok">
                                <button type="button"  class="btn btn-danger" data-dismiss="modal">Cancel</button> 
                            </div>
                        </div>

                    </div>
                </div>
            </div>



        </form:form>     




         





    </body>
</html>
