function closeAllInfoWindow(){
	for(var i=0; i!=infowindows.length; i++){
		infowindows[i].close();
	}
}

function closeAllAnimations(){
	for(var i=0; i!=infowindows.length; i++){
		markers[i].setAnimation(null);
	}
}

function addMarkerEvent(marker, codigoSucursarl, info){
	var markerEvent = marker;
    google.maps.event.addListener(markerEvent, 'click', function() {
    	closeAllInfoWindow();
    	closeAllAnimations();
    	updateSucursal(codigoSucursarl);
        //map.setZoom(14);
        map.setCenter(markerEvent.getPosition());
        markerEvent.setAnimation(google.maps.Animation.BOUNCE);
        info.open(map, markerEvent);
 	});
}

function mostrarMarkers(){
	for(var i=0; i!=sucursales.length; i++){
	 	var marker = new google.maps.Marker({
            position: new google.maps.LatLng(sucursales[i].altitud, sucursales[i].longitud),
            map: map
        });
	 	
	 	var contenido = sucursales[i].descripcion;
		var info = new InfoBubble({
			content:contenido,
			maxWidth: 500,
			padding:15,
			arrowPosition:75,
			arrowStyle:1,
			arrowSize:40,
			borderColor:'#999999',
			borderWidth:1,
			backgroundColor:'#FFFFFF'
		});
	 	
        infowindows.push(info);
        addMarkerEvent(marker, sucursales[i].codigo, info);
        markers.push(marker);
	}
}

function crearMapa(){
	//ROADMAP, SATELLITE, HYBRID, TERRAIN
    var mapOptions = {
      center: new google.maps.LatLng(centroMapaLatitud, centroMapaLongitud),
      zoom: zoomMapa,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

	infowindows = new Array();
	markers = new Array();
	mostrarMarkers();
}

function Sucursal(codigo, altitud, longitud, descripcion ) {
	this.codigo = codigo;
	this.altitud = altitud; 
	this.longitud = longitud;
	this.descripcion =  descripcion;
}