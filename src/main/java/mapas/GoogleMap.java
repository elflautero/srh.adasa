package mapas;


import controladores.principal.ControladorPrincipal;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
			
			webView.setMaxHeight(hMap);
			webView.setMinHeight(hMap);
	       
		}
		
	    // inicializacao do webview e mapa html javascript //
	    void initMap()
	    {
	        webView = new WebView();
	        webEngine = webView.getEngine();
	        webView.setPrefWidth(1900);
	        webView.setPrefHeight(710);
	        webEngine.load(getClass().getResource("/html/mapas/Principal/index.html").toExternalForm()); // originalMap
	        
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
	    
	    public void handle(String ddLatLon, String utmLatLon) {
	    	
	    	
	    	/*
	    	Alert a = new Alert (Alert.AlertType.INFORMATION);
			a.setTitle("Alerta!!!");
			a.setContentText(ddLatLon + " e " + utmLatLon);
			a.setHeaderText(null);
			a.show();
			*/
			
			ControladorPrincipal.lblCoord1.setText(ddLatLon);
			ControladorPrincipal.lblCoord2.setText(utmLatLon);
			
	    	//System.out.println("metodo handle chamado: " + lat + " e " + lng + "e endereco: " + endMap);
	    	
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
	    
	    public void setAllCoords(String dd, String dms, String utm) {
	    	
	    	ControladorPrincipal.lblDD.setText(dd);
	    	ControladorPrincipal.lblDMS.setText(dms);
	    	ControladorPrincipal.lblUTM.setText(utm);
	    	
	    	System.out.println(dd + " e " + dms + " e " + utm);
	    }

	    public void convDD (String lat, String lon) {
	    	
	    	invokeJS("obterUTMDMS(" + lat + ", " + lon + ")");
	    }
	    
	    public void convDMS (String lat, String lon) {
	    	
	    	invokeJS("obterDDUTM(\'" + ""+ lat + ""  + "\', \'" + ""+ lon + "" + "\');"); 
	    	
	    }
	    
	    public void convUTM(String latLon) {
	    	
	    	invokeJS("obterDDDMS(\'" + ""+ latLon + ""  + "\');"); 
	    	
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
