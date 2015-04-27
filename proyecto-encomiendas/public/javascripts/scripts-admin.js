
$(document).ready(function(){$(".alert").addClass("in").fadeOut(4500);

/* swap open/close side menu icons */
$('[data-toggle=collapse]').click(function(){
  	// toggle icon
  	$(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
});

$('[id=listar]').click(function(){
    $('[id=panelAgregar]').removeClass("in");
    $('[id=busqueda]').addClass("in");
    $('[id=tablaLista]').addClass("in");
});

$('[id=agregar]').click(function(){
       $('[id=busqueda]').removeClass("in");
       $('[id=tablaLista]').removeClass("in");
       $('[id=panelAgregar]').addClass("in");
   });

/*$('[id=botonGuardar]').click(function(){
    var form= document.getElementById("formAgregar");

    $('[id=guardar]').modal({ backdrop: 'static', keyboard: false })
        .on('click', '#botonAgregar', function() {
            form.submit();
        });

});*/

$('[id=botonAgregar]').click(function(){
    $('[id=panelAgregar]').removeClass("in");
});

$('[id=eliminar]').click(function(){

    /*var elementListar = document.getElementById("tablaLista");*/

    if ($('[id=tablaLista]').hasClass("in")){
        var tabla = document.getElementById("puntosDeVenta");
            var rowCount = tabla.rows.length;
            var count = 0;
            for (var i = 0; i < rowCount; i++){
                var row=tabla.rows[i];
                var chkbox=row.cells[3].childNodes[0];
                if(null!=chkbox&&true==chkbox.checked){
                    count++;
                }
            }

            if (count === 0){
                document.getElementById("eliminar").setAttribute("href", "#errorBorrar");
            } else {
                document.getElementById("eliminar").setAttribute("href","#borrar");
            }
    } else {
            $('[id=panelAgregar]').removeClass("in");
            $('[id=busqueda]').addClass("in");
            $('[id=tablaLista]').addClass("in");
    }

});



$('[id=botonBorrar]').click(function(){

    var tabla = document.getElementById("puntosDeVenta");
    var rowCount = tabla.rows.length;
    for (var i = 0; i < rowCount; i++){
        var row=tabla.rows[i];
        var chkbox=row.cells[3].childNodes[0];
        if(null!=chkbox&&true==chkbox.checked){

            tabla.rows[i].remove();
            rowCount--;
            i--;
        }
    }
    /*var checkeados = document.getElementsByTagName("input");
    for (var j = 0; j < checkeados.length; j += 1){
        if (checkeados[j].className === 'cb' && checkeados[j].checked === true){
            checkeados[j].closest("tr").remove();
        }
    }*/



    /*document.getElementsByTagName("table")[0].setAttribute("id","tableid");
    document.getElementById("tableid").deleteRow($(this).is(':checked'));*/
});

/*$('[type=checkbox]').click(function(){
    $('[href=#modificar]').toggle('href');
    if($(this).is(':checked')){
        $('[href=#modificar]').removeAttr('data-toggle');
    } else {
        $('[href=#modificar]').attr('data-toggle');
    }
});*/

});

