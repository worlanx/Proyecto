<%-- 
    Document   : jefedeestudio
    Created on : 17-oct-2019, 21:45:01
    Author     : Worlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${sessionScope.persona == null && sessionScope.rol != 3}">  
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
                        <a class="nav-link"><i class="fas fa-envelope"></i> <span
                                class="clearfix d-none d-sm-inline-block">${sessionScope.persona.nombre}
                                ${sessionScope.persona.apellidoPaterno} ${sessionScope.persona.apellidoMaterno}</span></a>
                    </li>
                    <li class="nav-item">
                        <a href="LoginServlet" class="nav-link"><i class="far fa-comments"></i> <span
                                class="clearfix d-none d-sm-inline-block">Cerrar Sesión</span></a>
                    </li>
                </ul>
            </nav>
            <!-- /.Navbar -->
            <div class="container">
                <div class="row end-xs">
                    <a href="jefedeestudio.jsp"><button class="btn btn-success">volver</button></a>
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
                            <th scope="col">Run</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Paterno</th>
                            <th scope="col">Materno</th>
                            <th scope="col">Encuestas Realizadas</th>
                            <th scope="col">Detalle</th>                            
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${sessionScope.cantidad != 0}">
                            <tbody>
                                <c:forEach items="${sessionScope.resumenes}" var="resumen">
                                    <tr>
                                        <th scope="row">
                                            ${resumen.run}-${resumen.dv}
                                        </th>
                                        <td>
                                            ${resumen.nombre}
                                        </td>
                                        <td>
                                            ${resumen.paterno}
                                        </td>
                                        <td>
                                            ${resumen.materno}
                                        </td>
                                        <td  style="text-align:center;">
                                            ${resumen.total}
                                        </td>
                                        <td>
                                            <form method="POST" action="CargarEncuestadorServler">
                                                <input type="hidden" name="resumen_id" value="${resumen.id}">
                                                <input type="image" src="img/align-center-2x-ver.png" border="0" alt="Submit"  /> 
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>                                
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <tbody>
                                <tr>
                                    <th scope="row">n/a</th>
                                    <td>n/a</td>
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
