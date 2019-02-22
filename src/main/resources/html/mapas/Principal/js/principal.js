// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

var map;
var marker;6
var markers = [];
var layers = [];


function initAutocomplete() {

	var defLatLng = new google.maps.LatLng(-15.790631073109617, -47.74939032660592); // centralizar o mapa no DF
	var adasa = new google.maps.LatLng(-15.775073004902042, -47.940351677729836); // coordenada adasa
	
	// opcoes do mapa //
	var mapOptions = {
	        center: defLatLng,
	        zoom: 3,
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        scaleControl: true,
	        disableDefaultUI: true, // desabilitar controles
	        styles: styles.darkBlue
	    };
	
	// mapa //
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	
	// primeiro marcador //
	marker = new google.maps.Marker({
	    position: adasa,
	    map: map,
	    title: 'Adasa'
	 });
	
	// ouvinte para dar zoom ao clicar no primeiro marcador //
	marker.addListener('click', function() {
	    map.setZoom(12);
	    map.setCenter(marker.getPosition());
	    
	  });
	
	// ouvinte para  obter  as coordenadas do local clicado no  mapa //
	google.maps.event.addListener(map, 'click', getCoordClick);

	
  //-- inicializar as variaveis de shapes --//
  layers[0] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/Unidades_Hidrograficas_70.kmz',{ preserveViewport: true });

  layers[1] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/riosDF110000.kmz',{ preserveViewport: true });

  layers[2] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/fraturadoDF.kmz',{ preserveViewport: true });

  layers[3] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/PorosoDF.kmz',{ preserveViewport: true });

  layers[4] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/riosUniao.kmz',{ preserveViewport: true });

  layers[5] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/zonasUTM.kmz',{ preserveViewport: true });

  layers[6] = new google.maps.KmlLayer('https://sites.google.com/site/shapeskmlkmz/baciasHidrograficas.kmz',{ preserveViewport: true });
   
  layers[7] = new google.maps.TrafficLayer();
  
}

