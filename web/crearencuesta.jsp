<%-- 
    Document   : crearencuesta
    Created on : 17-oct-2019, 22:02:37
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
        <script src="js/jquery.min.js"></script>
        <script src="js/generar.js"></script>
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
                    <a href="jefedeestudio.jsp"><button class="btn btn-success">Volver</button></a>
                </div>
            </div>
        </div>
        <article>            
            <form action="CrearEncuestaServlet" method="POST" autocomplete="off">
                <div class="container">     
                    <div class="row center-xs middle-xs">
                        <div class="col-xs-12 col-md-4">
                            <div class="box">
                                <c:if test="${sessionScope.encuestaError != null}">
                                    <p style="color: red" class="error">${sessionScope.encuestaError.toString()}</p>
                                    <c:set var="encuestaError" value="${null}" scope="session"></c:set>
                                </c:if>
                                <c:if test="${sessionScope.encuestaExito != null}">
                                    <p style="color: greenyellow" class="error">${sessionScope.encuestaExito.toString()}</p>
                                    <c:set var="encuestaExito" value="${null}" scope="session"></c:set>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="row center-xs">
                        <div class="col-xs-12  col-md-8">
                            <div class="form-group">
                                <label for="titulo">Título(*)</label>
                                <textarea name="titulo" class="form-control" id="titulo" required="true"
                                          placeholder="ej: Encuesta de satisfacción de x producto" maxlength="100"></textarea>

                            </div>
                        </div>
                    </div>
                    <div class="row start-xs">
                        <div class="col-xs-12 col-md-3 col-md-offset-2">
                            <div class="form-group">
                                <label for="valor">Valor base(*)</label>
                                <input name="valor" class="form-control" id="valor" required="true" type="text"
                                       placeholder="ej: 1500" pattern="[0-9]{1,9}">
                            </div>
                        </div>
                    </div>
                    <div class="row start-xs">
                        <div class="col-xs-12 col-md-3 col-md-offset-2">
                            <div class="form-group">
                                <button class="add btn btn-success" id="add" type="button">Nueva pregunta</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Preguntas -->

                <div class="container">
                    <fieldset id="preguntas" class="preguntas">            
                </div>

                <br>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-8">
                        <div class="form-group ">
                            <input name="registar" class="btn btn-success" id="registar" type="submit" value="Registrar">
                        </div>
                    </div>
                </div>
            </form>
        </article>
    </body>

</html>