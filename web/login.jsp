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
        <div class="container-fluid">
            <div class="row center-xs middle-xs">
                <form class="form-horizontal" action="LoginServlet" method="POST">
                    <div class="row justify-content-center">
                        <span class="login100-form-title p-b-51">
                            Encuestado2
                        </span>
                    </div>
                    <div class="row justify-content-center">                
                        <div class="form-group" >
                            <label class="col-xs-12 control-label" for="Run">Run</label>  
                            <div class="col-md-12">
                                <input id="Run" name="run" type="text" placeholder="12345678-k" class="form-control input-md" required="" pattern="([0-9]{7,8}-)([kK0-9]{1})">                                       
                            </div>
                        </div>                
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group">
                            <label class="col-md-12 control-label" for="Contraseña">Contraseña</label>
                            <div class="col-md-12">
                                <input id="Contraseña" name="pass" type="password" placeholder="" class="form-control input-md" required="">                                
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="form-group">
                            <label class="col-md-6 control-label" for=""></label>
                            <div class="col-md-6">
                                <input type="submit" name="login" value="Ingresar" class="btn btn-success"></button>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <c:if test="${sessionScope.loginError != null}">
                            <p class="error">${sessionScope.loginError.toString()}</p>
                            <c:set var="loginError" value="${null}" scope="session"></c:set>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>