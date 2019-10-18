<%-- 
    Document   : crearcuentausuario
    Created on : 16-oct-2019, 19:50:12
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
        <script src="js/jquery.min.js"></script>
        <script>
                var prov = [<c:forEach items="${sessionScope.provincias}"var="provincias" varStatus="loopStatus">'${provincias}'<c:if test="${!loopStatus.last}">,</c:if></c:forEach>];
                        var com = [<c:forEach items="${sessionScope.comunas}"var="comunas" varStatus="loopStatus">'${comunas}'<c:if test="${!loopStatus.last}">,</c:if></c:forEach>];
                $(function () {
                    var llenarProvincia = function () {
                        var selecionado = $('#region').val();
                        $('#provincia').empty();
                        for (var i = 0; i < prov.length; i++)
                        {
                            var res = prov[i].split("*");
                            if (res[1] == selecionado)
                            {
                                r = res[0].split("-");
                                $('#provincia').append('<option value="' + r[0] + '">' + r[1] + '</option>');
                            }
                        }
                    }
                    $('#region').change(llenarProvincia);
                    llenarProvincia();
                });

                $(function () {
                    var llenarComuna = function () {
                        var selecionado = $('#provincia').val();
                        $('#comuna').empty();
                        for (var i = 0; i < com.length; i++)
                        {
                            var res = com[i].split("*");
                            if (res[1] == selecionado)
                            {
                                r = res[0].split("-");
                                $('#comuna').append('<option value="' + r[0] + '">' + r[1] + '</option>');
                            }
                        }
                    }
                    $('#provincia').change(llenarComuna);
                    llenarComuna();
                });
                </script>        
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
                    <a href="administrador.jsp"><button class="btn btn-success">volver</button></a>
                </div>
            </div>            
        </header>
        <br>
        <br>
        <div class="container">
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="box">
                        <c:if test="${sessionScope.cuentaError != null}">
                            <p style="color: red" class="error">${sessionScope.cuentaError.toString()}</p>
                            <c:set var="cuentaError" value="${null}" scope="session"></c:set>
                        </c:if>
                        <c:if test="${sessionScope.cuentaExito != null}">
                            <p style="color: greenyellow" class="error">${sessionScope.cuentaExito.toString()}</p>
                            <c:set var="cuentaExito" value="${null}" scope="session"></c:set>
                        </c:if>
                    </div>
                </div>
            </div>
            <form action="CrearUsuarioServlet" method="POST">
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="run">Run(*)</label>
                            <input type="text" class="form-control" id="run" name="run" required="true" pattern="([0-9]{7,8}-)([kK0-9]{1})" placeholder="ej: 12345678-k" title="12345678-k">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="nombre">Nombre(*)</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required="true" minlength="2" placeholder="ej: Juan" pattern="[A-zÀ-ú\s]{2,45}" title="Solo texto">
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="paterno">Apellido Paterno(*)</label>
                            <input type="text" class="form-control" id="paterno" name="paterno" required="true" minlength="2" placeholder="ej: Rojas" pattern="[A-zÀ-ú\s]{2,45}" title="Solo texto">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="materno">Apellido Materno(*)</label>
                            <input type="text" class="form-control" id="materno" name="materno" required="true" minlength="2" placeholder="ej: Flores" pattern="[A-zÀ-ú\s]{2,45}" title="Solo texto">
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="fecha">Fecha de Nacimiento(*)</label>
                            <input type="date" class="form-control" id="fecha" name="fecha" required="true" minlength="2" placeholder="1-1-1990">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="genero">Género(*)</label>                           
                            <select class="form-control" id="genero" name="genero">
                                <c:forEach items="${sessionScope.generos}" var="generos">
                                    <option value="${generos.id}">${generos.descripcion}</option>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="region">Región(*)</label>
                            <select class="form-control" id="region" name="region">
                                <c:forEach items="${sessionScope.regiones}" var="regiones">
                                    <option value="${regiones.id}">${regiones.descripcion}</option>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-2">
                        <div class="form-group">
                            <label for="provincia">Provincia(*)</label>
                            <select class="form-control" id="provincia" name="provincia">       
                                <option>seleccione</option>
                            </select>    
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-2">
                        <div class="form-group">
                            <label for="comuna">Comuna(*)</label>
                            <select class="form-control" id="comuna" name="comuna">
                                <option>seleccione</option>
                            </select>    
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-5">
                        <div class="form-group ">
                            <label for="direccion">Dirección(*)</label>
                            <input type="text" class="form-control" id="direccion" name="direccion" required="true" minlength="2" placeholder="ej: Calle falsa #123">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group">
                            <label for="departamento">Departamento, estudio o piso</label>
                            <input type="text" class="form-control" id="departamento" name="departamento" placeholder="ej: dept 101">
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="celular">Teléfono Celular(*)</label>
                            <input type="tel" class="form-control" id="celular" name="celular" required="true" placeholder="ej: 991234567" pattern="(0?9)(\s?)[9876543]\d{7}" title="991234567">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="fijo">Teléfono fijo</label>
                            <input type="tel" class="form-control" id="fijo" name="fijo" placeholder="ej: 228521234" pattern="[0-9]\d{7,9}" title="228521234">
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="email">Email(*)</label>
                            <input type="email" class="form-control" id="email" name="email" required="true" placeholder="jrojasf@cuenta.cl">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="rol">Tipo Usuario(*)</label>
                            <select class="form-control" id="rol" name="rol">
                                <c:forEach items="${sessionScope.roles}" var="roles">
                                    <option value="${roles.id}">${roles.descripcion}</option>
                                </c:forEach>
                            </select>                           
                        </div>
                    </div>                    
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">
                            <label for="pass">Contraseña(*)</label>
                            <input type="password" class="form-control" id="pass" name="pass" required="true" minlength="6" placeholder="6 caracteres mínimo">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label for="confpass">Confirmar Contraseña(*)</label>
                            <input type="password" class="form-control" id="confpass" name="confpass" required="true" minlength="6" placeholder="6 caracteres mínimo">
                        </div>
                    </div>
                </div>
                <div class="row center-xs">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group ">                                    
                            <input type="submit" class="btn btn-success" id="registar" name="registar" value="Registrar" >
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <script>
            var pass = document.getElementById('pass');
            var confpass = document.getElementById('confpass');

            pass.onchange = confpass.onkeyup = passwordMatch;

            function passwordMatch()
            {
                if (pass.value !== confpass.value)
                    confpass.setCustomValidity('Las contraseñas no coinciden');
                else
                    confpass.setCustomValidity('');
            }
        </script>
    </body>
</html>