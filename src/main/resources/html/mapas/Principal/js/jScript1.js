var map;
var marker;

function get_click_position(event){
	
    	var location = event.latLng;
    	var lat = location.lat();
    	var lng = location.lng();
    	setMarkerPosition(lat, lng);

    	//-- preencher os campos do input com as coordenas--//
    	document.getElementById('lat').value = lat;  // não vai usar mais
    	document.getElementById('lng').value = lng;  // não  vai usar mais
    	document.getElementById('latlng').value = lat + "," + lng;
		var endMap = "";
    	//app.handle(lat, lng, endMap);
    	//app.handle(lat, lng);
    }

function setMarkerPosition(lat, lng) {
	
        var clickLatLng = new google.maps.LatLng(lat, lng);
        marker.setPosition(clickLatLng);
    }

function startJumping(){
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }

function stopJumping(){
        marker.setAnimation(google.maps.Animation.BOUNCE);
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
        //setLightMarkerIcon();
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


//-- array de shapes --//
var layers = [];
	
//-- funcao para as shapes --//
function toggleLayers(i) {

  		if(layers[i].getMap()==null) {
     		layers[i].setMap(map);
  		}
  		else {
     			layers[i].setMap(null);
  		}
  	 	document.getElementById('status').innerHTML += "toggleLayers("+i+") [setMap("+layers[i].getMap()+"] returns status: "+layers[i].getStatus()+"<br>";
	
		
}

//--INICIALIZACAO JAVASCRIPT --//
function initAutocomplete() {

	//-- inicializar as variaveis de shapes --//
	layers[0] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/Unidades_Hidrograficas_70.kmz',{ preserveViewport: true });

	layers[1] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/riosDF110000.kmz',{ preserveViewport: true });

	layers[2] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/fraturadoDF.kmz',{ preserveViewport: true });

	layers[3] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/PorosoDF.kmz',{ preserveViewport: true });

	layers[4] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/riosUniao.kmz',{ preserveViewport: true });

	layers[5] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/zonasUTM.kmz',{ preserveViewport: true });

	layers[6] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/baciasHidrograficas.kmz',{ preserveViewport: true });
	 
	layers[7] = new google.maps.TrafficLayer();
	 
	//-- ADASA -15.775073004902042, -47.940351677729836    --//
	 var defLatLng = new google.maps.LatLng(-15.790631073109617, -47.74939032660592); // coordenada para centralizar o mapa do df no google maps
	 var adasa = new google.maps.LatLng(-15.775073004902042, -47.940351677729836); // coordenada adasa
		
	//-- opcoes do mapa --//
	 var mapOptions = {
	        center: defLatLng,
	        zoom: 11,
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        scaleControl: true,
	        disableDefaultUI: true // desabilitar controles
	    	//streetViewControl: true
	    };
	 		map = new google.maps.Map(document.getElementById("map"), mapOptions);

	 		google.maps.event.addListener(map, 'click', get_click_position);

	    marker = new google.maps.Marker({
	        position: adasa,
	        map: map,
	        
	        //icon: "img/Pin.png"
	    }
      
      );
      
        var geocoder = new google.maps.Geocoder;
  		var infowindow = new google.maps.InfoWindow;

 		document.getElementById('submit').addEventListener('click', function() {
   		geocodeLatLng(geocoder, map, infowindow);
 	});
      
    function geocodeLatLng(geocoder, map, infowindow) {
	  	var input = document.getElementById('latlng').value;
	  	var latlngStr = input.split(',', 2);
	  	var latlng = {lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1])};
		  geocoder.geocode({'location': latlng}, function(results, status) {
		    if (status === 'OK') {
		      if (results[1]) {
		        map.setZoom(15);
		        var marker = new google.maps.Marker({
		          position: latlng,
		          map: map
		        });
            map.setCenter(marker.getPosition());
	        infowindow.setContent(results[1].formatted_address);
	        infowindow.open(map, marker);
	        //-- variável de endereço --//
	        
	        var endMap = results[1].formatted_address;
	        //-- passar para os inputs lat e lng
	        document.getElementById('lat').value = latlng.lat;  // não vai usar mais
 	    	document.getElementById('lng').value = latlng.lng;  // não  vai usar mais
	        //-- passar as coordenadas com a variável de endereço --//
	        app.handle(latlng.lat, latlng.lng, endMap);
	        
	      } else {
	        window.alert('No results found');
	      }
	    } else {
	      window.alert('Geocoder failed due to: ' + status);
	    }
  		});
	}
    

	  // Create the search box and link it to the UI element.
	  var input = document.getElementById('pac-input');
	  var searchBox = new google.maps.places.SearchBox(input);
	  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	
	  // Bias the SearchBox results towards current map's viewport.
	  map.addListener('bounds_changed', function() {
	    searchBox.setBounds(map.getBounds());
	  });
	
	  var markers = [];
	  // Listen for the event fired when the user selects a prediction and retrieve
	  // more details for that place.
	  searchBox.addListener('places_changed', function() {
	    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }

    // Clear out the old markers.
    markers.forEach(function(marker) {
      marker.setMap(null);
    });
    markers = [];

    // For each place, get the icon, name and location.
    var bounds = new google.maps.LatLngBounds();
    places.forEach(function(place) {
      if (!place.geometry) {
        console.log("Returned place contains no geometry");
        return;
      }
      var icon = {
        url: place.icon,
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(25, 25)
      };

      // Create a marker for each place.
      markers.push(new google.maps.Marker({
        map: map,
        //icon: icon,
        title: place.name,
        position: place.geometry.location
      }));

      if (place.geometry.viewport) {
        // Only geocodes have viewport.
        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });

}