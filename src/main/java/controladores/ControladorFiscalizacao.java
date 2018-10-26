package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ControladorFiscalizacao implements Initializable {
	
	@FXML Pane pFiscalizacao;
	@FXML TabPane tpFiscalizacao = new TabPane ();

	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	
		tpFiscalizacao.setStyle("-fx-background-color: white;");
		
		tpFiscalizacao.prefWidthProperty().bind(pFiscalizacao.widthProperty());
		tpFiscalizacao.prefHeightProperty().bind(pFiscalizacao.heightProperty());
	}

	
}
