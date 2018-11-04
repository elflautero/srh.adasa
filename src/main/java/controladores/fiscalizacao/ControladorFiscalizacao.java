package controladores.fiscalizacao;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorFiscalizacao {
	
	@FXML Pane pFiscalizacao;
	@FXML TabPane tpFiscalizacao;
	
	
	@FXML 
    private void initialize() {
		
		tpFiscalizacao.setStyle("-fx-background-color: transparent;");
		
		tpFiscalizacao.prefWidthProperty().bind(pFiscalizacao.widthProperty());
		tpFiscalizacao.prefHeightProperty().bind(pFiscalizacao.heightProperty());
	}

}


/*
p = new Pane();

tabDemandaControlador = new TabDemandaControlador();

FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/TabDemanda.fxml"));

//loader.setRoot(p);
loader.setController(tabDemandaControlador);

try {
	loader.load();
} catch (IOException e) {
	e.printStackTrace();
}
*/


/*
tabDemandaControlador = new TabDemandaControlador();

pFiscalizacao.widthProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	// Demanda //
    	tabDemandaControlador.redimensionarLargura(newValue);
    	
    }
});



pFiscalizacao.heightProperty().addListener(new ChangeListener<Number>() {
	   
    @Override 
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
    	
    	// Demanda //
    	tabDemandaControlador.redimensionarAltura(newValue);
    	
    }
});
*/