
$(document).ready(function(){$(".alert").addClass("in").fadeOut(4500);

/* swap open/close side menu icons */
$('[data-toggle=collapse]').click(function(){
  	// toggle icon
  	$(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
});

$('[id=listarPV]').click(function(){
    $('[id=panelAgregarPV]').removeClass("in");
    $('[id=tablaListaPV]').addClass("in");
});

$('[id=agregarPV]').click(function(){
    $('[id=tablaListaPV]').removeClass("in");
    $('[id=panelAgregarPV]').addClass("in");
});

$('[id=botonAgregarPV]').click(function(){
    $('[id=panelAgregarPV]').removeClass("in");
});

});

