<%-- 
    Document   : login
    Created on : Sep 19, 2019, 4:04:50 PM
    Author     : Laptop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es-CL">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/flexboxgrid.min.css">
        <script src="js/bootstrap.min.js"></script>
        <title>Encuestado2</title>
        <meta charset="UTF-8">	
    </head>
    <body>
        <div class="container">
            <div class="row center-xs middle-xs">
                <form action="LoginServlet" method="POST">
                    <div class="col-xs-12 col-md-6">
                        <div class="box">
                            <label>Encuestado2</label>
                        </div>                        
                    </div>
            </div>
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group box">
                        <label for="run">Run</label>
                        <input type="text" class="form-control" id="run" name="run" required="true" pattern="([0-9]{7,8}-)([kK0-9]{1})" placeholder="ej: 12345678-k" title="12345678-k">
                    </div>
                </div>
            </div>
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group box">
                        <label for="pass">Contraseña</label>
                        <input type="password" class="form-control" id="pass" name="pass" required="true" minlength="6">
                    </div>
                </div>
            </div>
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group box">                                    
                        <input type="submit" class="btn btn-success" id="login" name="ingresar" value="Ingresar">
                    </div>
                </div>
            </div>
            <div class="row center-xs middle-xs">
                <div class="col-xs-12 col-md-4">
                    <div class="box">
                        <c:if test="${sessionScope.loginError != null}">
                            <p class="error">${sessionScope.loginError.toString()}</p>
                            <c:set var="loginError" value="${null}" scope="session"></c:set>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>