
$(document).ready(function () {
    $("#add").click(function () {

        var divPre = $("<div>");
        //pregunta
        var rowPregunta = $("<div class=\"row center-xs\">");
        var divPregunta = $("<div class=\"col-xs-12 col-md-8\">");
        var divGrupoPregunta = $("<div class=\"pre form-group\">");
        var id = document.getElementsByClassName("pre").length + 1;
        var pregunta = $("<textarea name=\"pregunta" + id + "\" class=\"fieldname form-control\" required=\"true\" placeholder=\"ej: ¿Qué le parece...?\" maxlength=\"100\"></textarea>");

        var fieldsetAlternativa = $("<fieldset id=\"fieldsetAlternativa" + id + "\"/>");

        //Opciones Pregunta        
        var divOpciones = $("<div class=\"row center-xs\">");
        //agregar
        var divAgregarAlternativa = $("<div class=\"col-xs-12 col-md-2\">");
        var divGrupoAgregarAlternativa = $("<div class=\"form-group\">");
        var botonAgregarAlternativa = $("<button class=\"btn btn-success btn-sm\" id=\"agregar" + id + "\" type=\"button\"><span>Nueva Alternativa</span></button>");

        botonAgregarAlternativa.click(function () {
            //alternativas
            var divAlternativa = $("<div class=\"alternativa" + id + "\ row center-xs\">");
            var subId = document.getElementsByClassName("alternativa" + id).length + 1;
            var asideAlternativa = $("<aside class=\"row col-xs-12 col-md-8\">");
            //alternativa            
            var divAlt = $("<div class=\"col-xs-12 col-md-9\">");
            var divGrupoAlt = $("<div class=\"form-group\">");
            var alt = $("<input type=\"text\" class=\"form-control\" name=\"alt" + id + "" + subId + "\" required=\"true\" placeholder=\"ej: conforme\">");
            //eliminar alternativa
            var divElimanarAlt = $("<div class=\"col-xs-12 col-md-3\">");
            var divGrupoEliminarAlt = $("<div class=\"form-group\">");
            var botonEliminarAlt = $("<button class=\"remove btn btn-danger btn-sm\" id=\"remove\" type=\"button\"><span>X Alternativa</span></button>");

            botonEliminarAlt.click(function () {
                $(this).parent().parent().parent().parent().remove();
            });

            divGrupoAlt.append(alt);
            divAlt.append(divGrupoAlt);

            divGrupoEliminarAlt.append(botonEliminarAlt);
            divElimanarAlt.append(divGrupoEliminarAlt);

            asideAlternativa.append(divAlt);
            asideAlternativa.append(divElimanarAlt);

            divAlternativa.append(asideAlternativa);

            $("#fieldsetAlternativa" + id).append(divAlternativa);
        });

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

        divPre.append(rowPregunta);
        divPre.append(divOpciones);

        $("#preguntas").append(divPre);
        $("#preguntas").append(fieldsetAlternativa);


    });


});