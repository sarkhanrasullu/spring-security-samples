<%-- 
    Document   : task
    Created on : Nov 7, 2018, 3:18:35 PM
    Author     : tabrizguliyev
--%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.tabrizguliyev.Task.util.SecurityUtil" %>


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
            function setUpdate(id, task) {
                var elem = document.getElementById("updateMyTask");
                elem.querySelector('[id=taskIdUpdate]').value = id;
                elem.querySelector('[id=title]').value = task.title;
                elem.querySelector('[id=description]').value = task.description;
                elem.querySelector('[id=finishDate]').value = task.finishDate;
                elem.querySelector('[id=dueDate]').value = task.dueDate;
                setOptionsSelected('userId.id', task.userId);
            }

            function setOptionsSelected(idElem, id) {
                var elem = document.getElementById("updateMyTask");
                var options = elem.querySelector('[id="' + idElem + '"]');
                for (var i = 0; i < options.length; i++) {
                    if (options[i].value == id) {
                        options[i].selected = true;
                        return;
                    }
                }

            }
        </script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>

            <ul class="nav navbar-nav">
                <li>
                    <a href="#showAll">
                        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#get">Show/Hide All Tasks</button>
                    </a>
                </li>
            <%
                  if(SecurityUtil.hasRole("ADMIN")){
            %>
            <li>
                <a href="#add">
                    <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#insert">Add Task</button>
                </a>
            </li>
            <% } %>
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
                                <th  scope="col">title</th>
                                <th  scope="col">description</th>
                                <th  scope="col">user_id</th>
                                <th  scope="col">due_date</th>
                                <th  scope="col">finish_date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${taskList}" var="item">
                                <tr>
                                    <td>
                                        ${item.id} 
                                    </td>
                                    <td>
                                        ${item.title} 
                                    </td>
                                    <td>
                                        ${item.description} 
                                    </td>
                                    <td>
                                        ${item.userId.name} ${item.userId.surname} 
                                    </td>
                                    <td>
                                        ${item.dueDate} 
                                    </td>
                                    <td>
                                        ${item.finishDate} 
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updateMyTask"
                                                onclick="setUpdate('${item.id}',
                                                                {
                                                                    title: '${item.title}',
                                                                    description: '${item.description}',
                                                                    userId: '${item.userId.id}',
                                                                    dueDate: '${item.dueDate}',
                                                                    finishDate: '${item.finishDate}',
                                                                })"  
                                                >Update</button>
                                        <button  type="button" class="btn btn-danger"  data-toggle="modal" data-target="#deleteMyTask" 
                                                 onclick="setDelete('${item.id}')"
                                                 >
                                            Delete
                                        </button>
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
            action="/task/add" 
            modelAttribute="task"
            >
            <div id="insert" class="collapse collapse col-md-12 " >
                <br>
                <br>
                <br>
                <table class="table">
                    <br>

                    <tr>
                        <td><form:label  path="title">Title</form:label></td>
                        <td><form:input path="title"/></td>
                    </tr>
                    <tr>
                        <td><form:label  path="description">Description</form:label></td>
                        <td><form:input path="description"/></td>
                    </tr>
                    <tr>
                        <td><form:label  path="userId.id">User</form:label></td>
                            <td>
                            <form:select  path="userId.id">
                                <form:options 
                                    items="${users}" 
                                    itemValue="id" 
                                    itemLabel="username"
                                    ></form:options>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label  path="dueDate">Due date</form:label></td>
                        <td><form:input  path="dueDate"  placeholder="yyyy-MM-dd"/></td>
                    </tr>
                    <td>
                        <input class="btn-info" type="submit" value="Submit"/>
                    </td>
                </table>
            </div>
        </form:form>

        <form:form
            method="POST" 
            action="task/update"
            modelAttribute="task"
            >
            <div class="container">
                <div class="modal fade" id="updateMyTask" role="dialog">
                    <div class="modal-dialog"> 
                        <!-- Modal content-->
                        <div class="modal-content">   
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times</button>
                                <h4 class="modal-title">Update Task</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="form-group">
                                        <form:label  path="title">Title</form:label>
                                        <form:input path="title"/>

                                        <form:label  path="description">Description</form:label>
                                        <form:input path="description"/>
                                        <%
                                           if(SecurityUtil.hasRole("ADMIN")){
                                        %>
                                        <form:label  path="userId.id">User</form:label>
                                        <form:select  path="userId.id">
                                            <form:options 
                                                items="${users}" 
                                                itemValue="id" 
                                                itemLabel="username"
                                                ></form:options>
                                        </form:select>

                                        <form:label  path="dueDate">Due date</form:label>
                                        <form:input  path="dueDate"  placeholder="yyyy-MM-dd"/>
                                        <%}%>
                                        <form:label  path="finishDate">Finish date</form:label>
                                        <form:input  path="finishDate"  placeholder="yyyy-MM-dd"/>
                                    </div >
                                </div>
                            </div>
                            <div class="modal-footer">
                                <form:hidden path ="id" id="taskIdUpdate"/>
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
