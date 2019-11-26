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
        <script src="js/chart.js"></script>
        <script src="js/generarchart.js"></script>
        <title>Encuestado2</title>
    </head>
    <body>
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
            <c:forEach items="${sessionScope.preguntas}" var="pregunta" varStatus="paso">
                <br>
                <br>
                <div class="container">
                    <div class="row justify-content-center">
                        <h2>${pregunta.titulo}</h2>
                        <canvas id="myChart${pregunta.id}"></canvas>
                        <script>
                            var ctx = document.getElementById('myChart${pregunta.id}').getContext('2d');
                            var data = ${sessionScope.dataset.get(paso.index)};
                            var chart = new Chart(ctx, data);
                        </script>
                    </div>
                </div>

            </c:forEach>
        </article>
    </body>
</html>
