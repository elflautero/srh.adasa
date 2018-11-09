// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

var map;
var marker;
var markers = [];
var layers = [];


function initAutocomplete() {

	var defLatLng = new google.maps.LatLng(-15.790631073109617, -47.74939032660592); // centralizar o mapa no DF
	var adasa = new google.maps.LatLng(-15.775073004902042, -47.940351677729836); // coordenada adasa

	var mapOptions = {
	        center: defLatLng,
	        zoom: 4,
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        scaleControl: true,
	        disableDefaultUI: true // desabilitar controles
	    	//streetViewControl: true
	    };
	map = new google.maps.Map(document.getElementById("map"), mapOptions);
	
	marker = new google.maps.Marker({
	    position: adasa,
	    map: map,
	    title: 'Adasa'
	 });
	
	google.maps.event.addListener(map, 'click', getCoordClick);

	  // Create the search box and link it to the UI element.
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
	
	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
	   searchBox.setBounds(map.getBounds());
  });

 
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
        //icon: ,
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