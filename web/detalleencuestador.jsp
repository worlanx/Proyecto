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
        <c:if test="${sessionScope.persona == null && sessionScope.rol != 2}">  
            <script type="text/javascript">
                window.location = "login.jsp";
            </script>
        </c:if>
        <style>
            #seleccionar {
                cursor:pointer;
            }
        </style>
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
            <div class="container">
                <div class="row end-xs">
                    <a href="detalleencuestadores.jsp"><button class="btn btn-success">volver</button></a>
                </div>
            </div>            
        </header>
        <br>
        <br>
        <div class="container">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Título</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Relizadas</th>
                            <th scope="col">Total </th>                                             
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${sessionScope.cantidadE != 0}">
                            <tbody>
                                <c:forEach items="${sessionScope.encuestasE}" var="encuestador">
                                    <tr>
                                        <td>
                                            ${encuestador.titulo}
                                        </td>
                                        <td>
                                            $${encuestador.valor}
                                        </td>
                                        <td>
                                            ${encuestador.cantidad}
                                        </td>
                                        <td>
                                            $${encuestador.totalPago}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <tbody>
                                <tr>                                   
                                    <td>n/a</td>
                                    <td>n/a</td>
                                    <td>n/a</td>
                                    <td>n/a</td>
                                </tr>
                            </tbody>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </body>
</html>
