package controladores.fiscalizacao;

import java.net.URL;
import java.util.ResourceBundle;

import controladores.principal.ControladorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TabDemandaControlador implements Initializable {
	
	@FXML AnchorPane apDemanda; //  = new AnchorPane();
	@FXML ScrollPane spDemanda;
	@FXML AnchorPane apDemandaSP;
	@FXML BorderPane bpDemanda;
	
	@FXML Label lblTeste;
	@FXML Button btnTeste;
	
	@FXML TextField tfLat;
	@FXML TextField tfLng;
	@FXML Button btnLatLng;
	
	
	ControladorPrincipal controladorPrincipal;
	
	public void initialize(URL url, ResourceBundle rb) {
		
		
		AnchorPane.setTopAnchor(spDemanda,0.0);
	    AnchorPane.setLeftAnchor(spDemanda, 0.0);
		AnchorPane.setRightAnchor(spDemanda, 0.0);
	    AnchorPane.setBottomAnchor(spDemanda, 0.0);
	    
		apDemandaSP.minWidthProperty().bind(spDemanda.widthProperty().subtract(20));
		apDemandaSP.maxWidthProperty().bind(spDemanda.widthProperty().subtract(20));
		
		apDemandaSP.minHeightProperty().bind(spDemanda.heightProperty().add(300));
		apDemandaSP.maxHeightProperty().bind(spDemanda.heightProperty().add(300));
		
		bpDemanda.minWidthProperty().bind(spDemanda.widthProperty());
		bpDemanda.maxWidthProperty().bind(spDemanda.widthProperty());
		
		
		bpDemanda.minHeightProperty().bind(spDemanda.heightProperty().add(300));
		bpDemanda.maxHeightProperty().bind(spDemanda.heightProperty().add(300));
		
		btnTeste.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        lblTeste.setText("correto");
		    }
		});
		
		
		bpDemanda.widthProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println("border pane " + newVal);
	    	
			
	    });
		
		apDemanda.widthProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println(" ancor pane demanda" + newVal);
	    	
			
	    });
		
		
		btnLatLng.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    	//googleMaps = new GoogleMap();
		    	//googleMaps.setMarkerPosition(Double.parseDouble(tfLat.getText()), Double.parseDouble(tfLng.getText()));
		    	//googleMaps.setMapCenter(Double.parseDouble(tfLat.getText()), Double.parseDouble(tfLng.getText()));
		    	
		    	ControladorPrincipal.capturarGoogleMaps().setMarkerPosition(-15.0,-47.0);
		    	ControladorPrincipal.capturarGoogleMaps().setMapCenter(-15.0,-47.0);
		    	
		    	
		    }
		});
		
	  
	}
	
	
	
}




/*
// métodos de remimensionar as tabs //
public void redimensionarLargura (Number newValue) {
		apDemanda.setMinWidth((double) newValue);
		System.out.println("método redim. largura chamado " + newValue);
		lblTeste.setText(  newValue.toString());
}
public void redimensionarAltura (Number newValue) {
		apDemanda.setMinHeight((double) newValue);
		System.out.println("método redim. altura chamado " + newValue);
}
*/


/*
 
  
		apDemanda.widthProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println("ap demanda " + newVal);
	    	
			
	    });
		
		apDemanda.heightProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println("ap demanda altura" + newVal);
			 
			
	    });
	    
	    
		*/
  

/*
 @FXML private ControladorFiscalizacao conFisMain;

	public void init(ControladorFiscalizacao controladorFiscalizacao) {
		conFisMain = controladorFiscalizacao;
	}
	
	
	
	*/
