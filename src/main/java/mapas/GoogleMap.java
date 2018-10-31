package mapas;


import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;


public class GoogleMap extends Parent {
	
	
	//TabEnderecoController tabEnd = new TabEnderecoController();
	
		// metodo  de chamada initMap com o mapa e webview //
		public GoogleMap() {
	        initMap();
	        initCommunication();
	        getChildren().add(webView);
	        
	       // setMarkerPosition(0,0);
	       // setMapCenter(0, 0);
	       // switchHybrid();
	        
		}
		
		public void resizeWidthMap (Double wMap) {
			
	        webView.setMaxWidth(wMap);
	        webView.setMinWidth(wMap);
	       
	        
		}
		
		public void resizeHeightMap (Double hMap) {
			
			webView.setMaxHeight(hMap + 10);
			webView.setMinHeight(hMap + 10);
	       
	        
		}
		
		 

	    // inicializacao do webview e mapa html javascript //
	    void initMap()
	    {
	        webView = new WebView();
	        webEngine = webView.getEngine();
	        webView.setPrefWidth(1900);
	        webView.setPrefHeight(710);
	        webEngine.load(getClass().getResource("/html/mapas/map.html").toExternalForm()); // originalMap
	        
	        ready = false;
	        
	        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
	        {
	            public void changed(final ObservableValue<? extends Worker.State> observableValue,
	                                final Worker.State oldState,
	                                final Worker.State newState)
	            {
	            	
	                if (newState == Worker.State.SUCCEEDED)
	                {
	                    ready = true;
	                }
	                System.out.println(" initMap funcionando");
	            }
	        });
	    }

	    // metodo de comunicacao com o webEngine //
	    private void initCommunication() {
	        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
	        {
	            public void changed(final ObservableValue<? extends Worker.State> observableValue,
	                                final Worker.State oldState,
	                                final Worker.State newState)
	            {
	                if (newState == Worker.State.SUCCEEDED)
	                {
	                    doc = (JSObject) webEngine.executeScript("window");
	                    
	                    doc.setMember("app", GoogleMap.this);
	                }
	               
	                System.out.println(" initComunicantion funcionando");
	            }
	        });
	    } 
	   
	    private void invokeJS(final String str) {
	    	/*
	    	if (ready == true) {
	    		System.out.println("ready true");
	    	}
	    	else { 
	    		System.out.println("ready false");
	    	}
	    	*/
	    	
	        if(ready) {
	        	try {
	            doc.eval(str);
	             }
	        	catch (JSException js){ 
	            System.out.println("nao ready execao de leitura javascript " + js);
	        	}
	        }
	        else {
	        	
	            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
	            {
	                @Override
	                public void changed(final ObservableValue<? extends Worker.State> observableValue,
	                                    final Worker.State oldState,
	                                    final Worker.State newState)
	                {
	                    if (newState == Worker.State.SUCCEEDED)
	                    {
	                        doc.eval(str);
	                       
	                    }
	                   
		                System.out.println(" invokeJS funcionando");
	                }
	            });
	            
	        }
	    }
	
	    public void setOnMapLatLngChanged(EventHandler<MapEvent> eventHandler) {
	        onMapLatLngChanged = eventHandler;
	    }
	    
	    
	    public void handle(double lat, double lng, String endMap) {
	    	
	    	System.out.println("metodo handle chamado: " + lat + " e " + lng + "e endereco: " + endMap);
	    	
	    	/*
	    	TabEnderecoController.latDec = Double.toString(lat);
			TabEnderecoController.lngDec = Double.toString(lng);
			TabEnderecoController.endMap = endMap;
			
		    TabInterfController.latDec = Double.toString(lat);
		    TabInterfController.lngDec = Double.toString(lng);
		    
		    
	        if(onMapLatLngChanged != null) {
	            MapEvent event = new MapEvent(this, lat, lng);
	            onMapLatLngChanged.handle(event);
	           
	        }
	        */
	        
	    }

	    
	    public void setMarkerPosition(double lat, double lng) {
	    	
	        String sLat = Double.toString(lat);
	        String sLng = Double.toString(lng);
	  
	        invokeJS("setMarkerPosition(" + sLat + ", " + sLng + ")");
	       
	    }

	    public void setMapCenter(double lat, double lng) {
	        String sLat = Double.toString(lat);
	        String sLng = Double.toString(lng);
	        
	        invokeJS("setMapCenter(" + sLat + ", " + sLng + ")");
	    }

	    public void switchSatellite() {
	        invokeJS("switchSatellite()");
	    }

	    public void switchRoadmap() {
	        invokeJS("switchRoadmap()");
	    }

	    public void switchHybrid() {
	        invokeJS("switchHybrid()");
	    }

	    public void switchTerrain() {
	        invokeJS("switchTerrain()");
	    }

	    public void startJumping() {
	        invokeJS("startJumping()");
	    }

	    public void stopJumping() {
	        invokeJS("stopJumping()");
	    }
	    
	    public void setHeight(double h) {
	        webView.setPrefHeight(h);
	    }

	    public void setWidth(double w) {
	        webView.setPrefWidth(w);
	    }
	  
	    public ReadOnlyDoubleProperty widthProperty() {
	        return webView.widthProperty();
	    }
	    
	    private JSObject doc;
	    private EventHandler<MapEvent> onMapLatLngChanged;
	    private WebView webView;
	    private WebEngine webEngine;
	    public boolean ready;
	    
}
