function setMarkerPosition(lat, lng) {
            var clickLatLng = new google.maps.LatLng(lat, lng);
            marker.setPosition(clickLatLng);
        }
function startJumping(){
    marker.setAnimation(google.maps.Animation.BOUNCE);
}

function stopJumping(){
    marker.setAnimation(null);
}

function setMapCenter(lat, lng) {
    var latlng = new google.maps.LatLng(lat, lng);
    map.setCenter(latlng);
}

function switchSatellite() {
    var mapOptions = {
        mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    map.setOptions(mapOptions);
    //setLightMarkerIcon();
}

function switchRoadmap() {
    var mapOptions = {
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map.setOptions(mapOptions);
    //setDarkMarkerIcon();
}

function switchHybrid() {
    var mapOptions = {
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    map.setOptions(mapOptions);
}

function switchTerrain() {
    var mapOptions = {
        mapTypeId: google.maps.MapTypeId.TERRAIN
    };
    map.setOptions(mapOptions);
    //setDarkMarkerIcon();
}

function setDarkMarkerIcon() {
    marker.setIcon("img/Pin.png");
}

function setLightMarkerIcon() {
    marker.setIcon("img/Pin_s.png");
}

//-- funcao para as shapes --//
function openShape(i) {

	if(layers[i].getMap()==null) {
 		layers[i].setMap(map);
	}
	else {
 			layers[i].setMap(null);
	}
	
 }

function getCoordClick(event){
	
	var location = event.latLng;
	var lat = location.lat();
	var lng = location.lng();
	
	setMarkerPosition(lat, lng);
	
	obterUTMDMSMapClick (lat,lng);

}

function setMarkerPosition (lat, lng) {

	var clickLatLng = new google.maps.LatLng(lat, lng);

	// limpar marcadores antigos dos cliques //
	marker.setMap(null);

	
	// limpar marcadores antigos das buscas por endereços //
	markers.forEach(function(marker) {
	  marker.setMap(null);
	});
	markers = [];


	// criar um novo marcador a partir da posição clicada //
	marker = new google.maps.Marker({
	    position: clickLatLng,
	    map: map,
	    title: 'Coordenada clicada'
	  });

	console.log(lat + ',' + lng);

}

function obterUTMDMS (typeCoordinate, lat,lon) {
	// conversão DD para DMS //
 	var lat = Dms.parseDMS(lat);
 	var lon = Dms.parseDMS(lon);
 	var latLon = new LatLon(lat, lon); // 51.4778°N, 000.0015°W
 	
 	// conversão DD para UTM //
 	var toUTM = latLon.toUtm();
 	
 	var resultadoLatLon = latLon + ' e ' + toUTM;
	
 	app.handle (typeCoordinate, lat + "," + lon, latLon, toUTM);
	//console.log(latLon + ' e ' + toUTM);
 	setMarkerPosition(lat, lon);
 	setMapCenter(lat, lon);
	
}

function obterDDUTM (typeCoordinate, latDMS, lonDMS) {
	
	// conversão da coordenada convertida DMS em DD //
	var latDMSToDD = Dms.parseDMS(latDMS); // Dms.toLat (utmToDMSaa.lat, 'dms', 0);
	var lonDMSToDD = Dms.parseDMS(lonDMS); //Dms.toLon (utmToDMSaa.lon, 'dms', 0);
	
	
	// conversão DD para UTM //
 	var latLon2UTM = new LatLon (latDMSToDD, lonDMSToDD);
	var toUTM = latLon2UTM.toUtm();
	
	if (latDMSToDD.toString().length > 11) {
		
		latDMSToDD = latDMSToDD.toString().substring (0,11)
 		}
	
	if (lonDMSToDD.toString().length > 11) {
		lonDMSToDD = lonDMSToDD.toString().substring (0,11);
		}
	 
		
	app.handle (typeCoordinate, latDMSToDD + "," + lonDMSToDD, latDMS + "," + lonDMS, toUTM);
	
	setMarkerPosition(latDMSToDD, lonDMSToDD);
	setMapCenter(latDMSToDD, lonDMSToDD);
}

function obterDDDMS (typeCoordinate, utm) {
	
	// reversão da coordenada convertida UTM para DMS
		var UTMTODMS = Utm.parse(utm);
		var utmToDMS = UTMTODMS.toLatLonE();

	// reversão da coordenada convertida DMS em DD //
	var latDMSToDD = Dms.parseDMS(utmToDMS.lat); // Dms.toLat (utmToDMSaa.lat, 'dms', 0);
	var lonDMSToDD = Dms.parseDMS(utmToDMS.lon); //Dms.toLon (utmToDMSaa.lon, 'dms', 0);
	
	if (latDMSToDD.toString().length > 11) {
		
		latDMSToDD = latDMSToDD.toString().substring (0,11)
 		}
	
	if (lonDMSToDD.toString().length > 11) {
		lonDMSToDD = lonDMSToDD.toString().substring (0,11);
		}
	
	app.handle (typeCoordinate, latDMSToDD + "," + lonDMSToDD, utmToDMS, utm);
	
	setMarkerPosition(latDMSToDD, lonDMSToDD);
	setMapCenter(latDMSToDD, lonDMSToDD);
 	
}

function obterUTMDMSMapClick (lat,lon) {
	// conversão DD para DMS //
 	var lat = Dms.parseDMS(lat);
 	var lon = Dms.parseDMS(lon);
 	var latLon = new LatLon(lat, lon); // 51.4778°N, 000.0015°W
 	
 	// conversão DD para UTM //
 	var toUTM = latLon.toUtm();
 	
 	var resultadoLatLon = latLon + ' e ' + toUTM;
 	
 	
 	if (lat.toString().length > 11) {
		
 		lat = lat.toString().substring (0,11)
 		}
	
	if (lon.toString().length > 11) {
		lon = lon.toString().substring (0,11);
		}
	
 	app.setAllCoords(lat + ',' + lon, latLon, toUTM);
 	app.setCoords(lat, lon);
 	
}

function setZoomIn () {
	
	var zoom = map.getZoom() + 2;
	
	map.setZoom(zoom);
	map.setCenter(marker.getPosition());

}

function setZoomOut () {
	
	var zoom = map.getZoom() - 2;
	
	map.setZoom(zoom);
	map.setCenter(marker.getPosition());

}
