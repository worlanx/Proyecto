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
        <script src="js/jquery.min.js"></script>    
        <script src="js/jspdf.min.js"></script>    
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
                    <a href="listapago.jsp"><button class="btn btn-success">volver</button></a>
                </div>
            </div>   
        </header>
        <br>
        <br>
        <div id="pdf">
            <div class="container">
                <div class="row justify-content-center">
                    <h2>Detalle de Pago</h2>        
                    <br>
                    <br>
                    <br>                
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Orden id:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${ord.id}</label>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Run:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${enc.run}-${enc.dv}</label>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Nombre:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${enc.nombre} ${enc.apellidoPaterno} ${enc.apellidoMaterno}</label>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Fecha orden:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${sessionScope.formato.format(sessionScope.ord.fecha)}</label>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Teléfono:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${enc.telefono.get(0).numero}</label>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <div class="col-xs-12 col-md-2">
                        <div class="box">
                            <label>Dirección:</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>${enc.direccion.descripcion} ${enc.direccion.detalle}</label>
                        </div>
                    </div>
                </div>
            </div>
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
                        <tbody>
                            <c:forEach items="${sessionScope.resumenes}" var="encuestador">
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
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    $${ord.total}
                                </td>
                            </tr>
                        </tbody>                        
                    </table>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row justify-content-center">
                <button onclick="generar()" class="btn btn-success">Exportar pdf</button>
            </div>
        </div>
        <script>
            function generar()
            {
                var doc = new jsPDF();
                var elementHTML = $('#pdf').html();
                var specialElementHandlers = {
                    '#elementH': function (element, renderer) {
                        return true;
                    }
                };
                doc.fromHTML(elementHTML, 15, 15, {
                    'width': 170,
                    'elementHandlers': specialElementHandlers
                });

// Save the PDF
                doc.save('orden_pago_${ord.id}.pdf');
            }
        </script>
    </body>
</html>
