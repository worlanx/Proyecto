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
        </header>
        <br>
        <br>
        <div class="container">
            <div class="row end-md">
                <div class="col-xs-12 col-md-2">
                    <a href="crearcuentausuario.jsp"><button class="btn btn-success">Ver Encuestadores</button></a>
                </div>
                <div class="col-xs-12 col-md-2">
                    <form method="POST" action="CargarEncuestadoresServlet">
                        <input name="Cerrar" class="btn btn-success" id="Cerrar" type="submit"  value="Crear Encuesta">                        
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
                            <th scope="col">Encuesta</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Modificar encuesta</th>
                            <th scope="col">Activar Encuesta</th>
                            <th scope="col">Cerrar encuesta</th>
                            <th scope="col">Realizadas</th>
                            <th scope="col">Resultados</th>
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${sessionScope.cantidad != 0}">
                            <tbody>
                                <c:forEach items="${sessionScope.encuestas}" var="encuesta">
                                    <tr>
                                        <th scope="row">
                                            ${encuesta.id}
                                        </th>
                                        <td>
                                            ${encuesta.titulo}
                                        </td>
                                        <td>
                                            $${encuesta.valor}
                                        </td>
                                        <td>
                                            <form method="POST" action="">
                                                <input type="hidden" name="encuesta_id" value="${encuesta.id}">
                                                <c:choose>
                                                    <c:when test="${encuesta.estado.id == 1}">
                                                        <input name="Modificar" class="btn btn-warning" id="modificar" type="submit" value="Modificar">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="Modificar" class="btn btn-warning" id="modificar" disabled type="submit" value="Modificar">
                                                    </c:otherwise>
                                                </c:choose>

                                            </form>
                                        </td>
                                        <td>
                                            <form method="POST" id="activarEnceusta${encuesta.id}" action="ActivarEncuestaServlet">
                                                <input type="hidden" name="encuesta_id" value="${encuesta.id}">
                                                <c:choose>
                                                    <c:when test="${encuesta.estado.id == 1}">
                                                        <input name="Activar" class="btn btn-success" id="Activar" type="button" onclick="activar(${encuesta.id})"  value="Activar">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="Activar" class="btn btn-success" id="Activar" disabled type="submit" value="Activar">
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="POST" id="cerrarEnceusta${encuesta.id}" action="CerrarEncuestaServlet">
                                                <input type="hidden" name="encuesta_id" value="${encuesta.id}">
                                                <c:choose>
                                                    <c:when test="${encuesta.estado.id == 2}">
                                                        <input name="Cerrar" class="btn btn-danger" id="Cerrar" type="button" onclick="cerrar(${encuesta.id})" value="Cerrar">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="Cerrar" class="btn btn-danger" id="Cerrar" disabled type="button"  value="Cerrar">
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </td>
                                        <td>
                                             ${encuesta.realizadas}
                                        </td>
                                        <td>
                                            <form method="POST"  action="CargarResultadosServlet">
                                                <input type="hidden" name="encuesta_id" value="${encuesta.id}">
                                                <c:choose>
                                                    <c:when test="${encuesta.estado.id != 1}">
                                                        <input name="Ver" class="btn btn-success" id="ver" type="submit" value="Ver">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="Ver" class="btn btn-success" id="ver" disabled type="submit" value="Ver">
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </td>
                                    </tr>                                    
                                </c:forEach>
                            </tbody>
                        </c:when>
                    </c:choose>
                </table>
            </div>
        </div>
    </body>
    <script>
        function cerrar(id) {
            if (confirm("¿Seguro que desea cerrar la Encuesta?, Los encuestadores no podrán realizar más la encuesta si es cerrada")) {
                document.getElementById("cerrarEnceusta" + id).submit();
                console.log("true");
            } else
            {
                console.log("false");
            }
        }
        function activar(id) {
            if (confirm("¿Seguro que desea activar la Encuesta?, No se podrá módificar si es activada")) {
                document.getElementById("activarEnceusta" + id).submit();
                console.log("true");
            } else
            {
                console.log("false");
            }
        }
    </script>
</html>
