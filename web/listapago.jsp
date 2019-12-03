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
                    <a href="jefepersonal.jsp"><button class="btn btn-success">volver</button></a>
                </div>
            </div>
        </header>
        <br>
        <br>
        <div class="container">
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="box">
                        <c:if test="${sessionScope.ordenCorrecto != null}">
                            <p style="color: green" class="error">${sessionScope.ordenCorrecto.toString()}</p>
                            <c:set var="ordenCorrecto" value="${null}" scope="session"></c:set>
                        </c:if>
                        <c:if test="${sessionScope.ordenError != null}">
                            <p style="color: red" class="error">${sessionScope.ordenError.toString()}</p>
                            <c:set var="ordenError" value="${null}" scope="session"></c:set>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="row end-md middle-xs">
                <div class="col-xs-12 col-md-6">
                    <form method="POST"  id="generarOrden" action="CargarOrdenesServlet">
                        <table>
                            <tr>
                                <td>
                                    <label for="fecha">Desde</label>
                                </td>
                                <td></td>
                                <td>
                                    <input type="date" class="form-control" id="fecha_desde" name="fecha_desde" required="true" minlength="2" placeholder="1-1-1990">
                                </td>
                                <td>
                                    <label for="fecha">Hasta</label>
                                </td>
                                <td></td>
                                <td>
                                    <input type="date" class="form-control" id="fecha_hasta" name="fecha_hasta" required="true" minlength="2" placeholder="1-1-2000">
                                </td>
                                <td></td>
                                <td>
                                    <input name="Cerrar" class="btn btn-success" id="Cerrar" type="button" onclick="validar()"  value="Generar Pago">
                                </td>
                            </tr>
                        </table>                    
                    </form>
                </div>
            </div>
        </div>   
        <br>
        <br>
        <div class="container">
            <div class="row justify-content-center">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Monto</th>
                            <th scope="col">Detalles</th>                                                    
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${sessionScope.cantidadO != 0}">
                            <tbody>
                                <c:forEach items="${sessionScope.ordenes}" var="orden">
                                    <tr>
                                        <th>
                                            ${orden.id}
                                        </th>
                                        <td>
                                            ${orden.fecha}
                                        </td>
                                        <td>
                                            $${orden.total}
                                        </td>
                                        <td>
                                            <form method="POST" action="CargarOrdenServlet">
                                                <input type="hidden" name="resumen_id" value="${orden.id}">
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
                                </tr>
                            </tbody>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
        <script>
            function validar()
            {
                var fecha_actual = new Date();
                var desde = document.getElementById("fecha_desde").value;
                var hasta = document.getElementById("fecha_hasta").value;
                var fecha_desde = new Date(desde);
                var fecha_hasta = new Date(hasta);
                if (fecha_hasta.getTime() >= fecha_desde.getTime())
                {
                    if (fecha_actual.getTime() >= fecha_hasta.getTime())
                    {
                        document.getElementById("generarOrden").submit();
                    } else
                    {
                        alert("fecha no puede ser mayor que la fecha actual");
                    }
                } else
                {
                    alert("fecha desde no puede ser mayor que límite");
                }
            }
        </script>
    </body>
</html>
