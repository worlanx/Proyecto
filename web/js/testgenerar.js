
$(document).ready(function () {
    $("#add").click(function () {

        var cantidad = 3;

        var divPre = $("<div  \">");
        //pregunta
        var rowPregunta = $("<div class=\"row center-xs\">");
        var divPregunta = $("<div class=\"col-xs-12 col-md-8\">");
        var divGrupoPregunta = $("<div class=\"pre form-group\">");
        var id = document.getElementsByClassName("pre").length + 1;
        var pregunta = $("<textarea name=\"pregunta" + id + "\" class=\"fieldname form-control\" required=\"true\" placeholder=\"ej: ¿Qué le parece...?\" maxlength=\"100\"></textarea>");

        //Opciones Pregunta        
        var divOpciones = $("<div class=\"row center-xs\">");
        //agregar
        var divAgregarAlternativa = $("<div class=\"col-xs-12 col-md-2\">");
        var divGrupoAgregarAlternativa = $("<div class=\"form-group\">");
        var botonAgregarAlternativa = $("<button class=\"btn btn-success btn-sm\" id=\"agregar" + id + "\" type=\"button\"><span>Nueva Alternativa</span></button>");

        //eliminar
        var divEliminarPregunta = $("<div class=\"col-xs-12 col-md-2\">");
        var divGrupoEliminarPreguntar = $(" <div class=\"form-group\">");
        var botonEliminarPregunta = $("<button class=\"btn btn-danger btn-sm\" id=\"eliminar\" type=\"button\"><span>Remover Pregunta</span></button>");

        botonEliminarPregunta.click(function () {
            $(this).parent().parent().parent().parent().remove();
        });
        //múltiple
        var divMultiple = $("<div class=\"col-xs-12 col-md-3\">");
        var divGrupoMultiple = $(" <div class=\"form-group\">");
        var checkMultiple = $("<input type=\"checkbox\" name=\"multiple" + id + "\" value=\"1\"><span>Selección Múltiple</span>");

        //alternativa default
        var divAlternativas = $("<div class=\"alternativa" + id + "\">");       
        var divUno=$("<div class=\"row start-xs\">");
        var divAltUno = $("<div class=\"col-xs-12 col-md-6 col-md-offset-2 \">");
        var divGrupoAltUno = $("<div class=\"form-group\">");
        var altUno = $("<input type=\"text\" class=\"form-control\" name=\"alt" + id + "" + 1 + "\" required=\"true\" placeholder=\"ej: conforme 1\">");
        var divDos=$("<div class=\"row start-xs\">");
        var divAltDos = $("<div class=\"col-xs-12 col-md-6 col-md-offset-2 \">");
        var divGrupoAltDos = $("<div class=\"form-group\">");
        var altDos = $("<input type=\"text\" class=\"form-control\" name=\"alt" + id + "" + 2 + "\" required=\"true\" placeholder=\"ej: conforme 2\">");

        botonAgregarAlternativa.click(function () {
            //alternativas
            
            
            var asideAlternativa = $("<div class=\"alternativa" + id +" row start-xs\">");
            var subId = document.getElementsByClassName("alternativa" + id).length + 2;
            //alternativa            
            var divAlt = $("<div class=\"col-xs-12 col-md-6 col-md-offset-2\">");
            var divGrupoAlt = $("<div class=\"form-group\">");
            var alt = $("<input type=\"text\" class=\"form-control\" name=\"alt" + id + "" +  cantidad + "\" required=\"true\" placeholder=\"ej: conforme " +subId+"\">");
            cantidad++;
            //eliminar alternativa
            var divElimanarAlt = $("<div class=\"col-xs-12 col-md-3\">");
            var divGrupoEliminarAlt = $("<div class=\"form-group\">");
            var botonEliminarAlt = $("<button class=\"remove btn btn-danger btn-sm\" id=\"remove\" type=\"button\"><span>X Alternativa</span></button>");

            botonEliminarAlt.click(function () {
                $(this).parent().parent().parent().remove();
            });
            divGrupoAlt.append(alt);
            divAlt.append(divGrupoAlt);

            divGrupoEliminarAlt.append(botonEliminarAlt);
            divElimanarAlt.append(divGrupoEliminarAlt);

            asideAlternativa.append(divAlt);
            asideAlternativa.append(divElimanarAlt);

            divAlternativas.append(asideAlternativa);
            
        });


        //generar agregar
        divGrupoAgregarAlternativa.append(botonAgregarAlternativa);
        divAgregarAlternativa.append(divGrupoAgregarAlternativa);
        //generar eliminar
        divGrupoEliminarPreguntar.append(botonEliminarPregunta);
        divEliminarPregunta.append(divGrupoEliminarPreguntar);
        //generar múltiple
        divGrupoMultiple.append(checkMultiple);
        divMultiple.append(divGrupoMultiple);

        divOpciones.append(divAgregarAlternativa);
        divOpciones.append(divEliminarPregunta);
        divOpciones.append(divMultiple);

        //generar pregunta
        divGrupoPregunta.append(pregunta);
        divPregunta.append(divGrupoPregunta);
        rowPregunta.append(divPregunta);

        //generar alternativa default
        divGrupoAltUno.append(altUno);
        divAltUno.append(divGrupoAltUno);
        divUno.append(divAltUno);
        divAlternativas.append(divUno);
        divGrupoAltDos.append(altDos);
        divAltDos.append(divGrupoAltDos);
        divDos.append(divAltDos);
        divAlternativas.append(divDos);

        divPre.append(rowPregunta);
        divPre.append(divOpciones);
        divPre.append(divAlternativas);

        $("#preguntas").append(divPre);
        $("#preguntas").append("<hr>");
    });
});