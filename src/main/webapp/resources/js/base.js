$(function(){
	// Tabs
	$('#consultar').tabs();		
	$('#tabsGenerales').tabs();
});

$(document).ready(function () {
	//Bind Enter para Submit de "Buscar"
	/*$('#nombreBuscar').keypress(function(e){
		if(e.which == 13){
			$('form#formBuscar').submit();
		}
	});*/

	//Activar/Desativar boton "Me Gusta"
	$('.meGusta').toggle(function() {
		$(this).html('<img src="../img/botonMeGustaActivo.png" alt="me gusta" height="14" width="14" />');
	}, function() {
		$(this).html('<img src="../img/botonMeGustaDesactivo.png" alt="me gusta" height="14" width="14" />');
	});
	
});

//Desplegar / Ocultar (colsutar)
function desp_ocul_div(idSpam, idDiv) {
	var documento = document.getElementById(idSpam);
	
	if($(idDiv).is(':hidden')){
		documento.innerHTML = ('<img src="../img/flechitaDown.png" width="12" height="12" />');
		$(idDiv).show("slow");
	}else{		
		documento.innerHTML = ('<img src="../img/flechitaUp.png" width="12" height="12" />');
		$(idDiv).hide("slow");
	}
}

//Habilita DEMO de "Mostrar Antiguos"
function mostrarAntiguos() {
	$('#demo').show("slow");
	$('#mostrarAnteriores').text("[No Tiene Más Notificaciones]");
}