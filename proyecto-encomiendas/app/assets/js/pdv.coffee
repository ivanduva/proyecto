$ ->
  $.get "/admin/puntos-de-venta/listar", (puntos) ->
    $.each puntos, (index, punto) ->
     @obj = $('#puntos').append $("<tr>")
     @obj.append ($("<td>").text punto.puntoId)
     @obj.append (($('<td style="cursor:pointer" data-toggle="modal" href="#modificar">').append $("<u>").append $('<font color="blue">').text punto.nombre))
     @obj.append ($("<td>").text punto.tipo)
     @obj.append ($("<td>").text punto.localidad)
     @obj.append ($("<td>").text punto.direccion)
     @obj.append ($("<td>").text punto.email)
     @obj.append ($("<td>").text punto.nombreResponsable)
     @obj.append ($("<td>").text punto.telefono)
     @obj.append ($("<td>").append $('<input name="cb" id="cb" type="checkbox">'))

