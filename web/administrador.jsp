<%-- 
    Document   : administrador
    Created on : 16-oct-2019, 17:47:08
    Author     : Worlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${sessionScope.persona == null && sessionScope.rol != 1}">  
            <script type="text/javascript">
                window.location = "login.jsp";
            </script>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/flexboxgrid.min.css">
        <script src="js/bootstrap.min.js"></script>        
        <title>Encuestado2</title>
    </head>
    <body>
        <!--Double navigation-->
        <header>
            <!-- Sidebar navigation -->            
            <!--/. Sidebar navigation -->
            <!-- Navbar -->
            <nav class="navbar navbar-toggleable-md navbar-expand-lg scrolling-navbar double-nav">
                <!-- SideNav slide-out button -->     

                <ul class="nav navbar-nav nav-flex-icons ml-auto">
                    <li class="nav-item">
                        <a class="nav-link"><i class="fas fa-envelope"></i> <span class="clearfix d-none d-sm-inline-block">${sessionScope.persona.nombre} ${sessionScope.persona.apellidoPaterno} ${sessionScope.persona.apellidoMaterno}</span></a>
                    </li>
                    <li class="nav-item">
                        <a href="LoginServlet" class="nav-link"><i class="far fa-comments"></i> <span class="clearfix d-none d-sm-inline-block">Cerrar Sesión</span></a>
                    </li>                                   
                </ul>
            </nav>
            <!-- /.Navbar -->
        </header>
        <br>
        <br>
        <div class="container">
            <div class="row justify-content-end">
                <a href="crearcuenta.jsp"><button class="btn btn-success">Registrar Usuario</button></a>
            </div>
        </div>
        <br>
        <br>
        <div class="container">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Run</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Paterno</th>
                            <th scope="col">Materno</th>
                            <th scope="col">Tipo</th>
                            <th scope="col" colspan="2">Administrar</th>                            
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${sessionScope.cantidad != 0}">
                            <tbody>
                                <c:forEach items="${sessionScope.personas}" var="persona">
                                <form method="POST" action="ModificarCuentaServlet">
                                    <tr>
                                        <th scope="row">
                                            ${persona.run}-${persona.dv}
                                        </th>
                                    <input type="hidden" name="persona_id" value="${persona.id}">
                                    <input type="hidden" name="persona_run" value="${persona.run}">
                                    <input type="hidden" name="persona_dv" value="${persona.dv}">
                                    <td>
                                        ${persona.nombre}
                                    </td>
                                    <td>
                                        ${persona.apellidoPaterno}
                                    </td>
                                    <td>
                                        ${persona.apellidoMaterno}
                                    </td>
                                    <td>
                                        ${persona.cuenta.rol.descripcion}
                                    </td>
                                    <td>
                                        <input type="image" src="img/align-center-2x.png" border="0" alt="Submit" /> Editar
                                    </td>
                                    <td>
                                        <form method="POST" id="myForm" action="EliminarCuentaServlet">
                                            <input type="hidden" name="persona_id" value="${persona.id}">
                                            <input type="hidden" name="persona_run" value="${persona.run}">
                                            <input type="hidden" name="persona_dv" value="${persona.dv}">
                                            <img src="img/circle-x-2x.png" border="0" onclick="myFunction()" /> Eliminar
                                        </form>
                                    </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </c:when>
                    </c:choose>
            </div>
        </div>
    </body>
    <script>
        function myFunction() {
            if (confirm("¿Seguro que desea eliminar?")) {
                document.getElementById("myForm").submit();
                console.log("true");
            } else
            {
                console.log("false");
            }
        }
    </script>
</html>
