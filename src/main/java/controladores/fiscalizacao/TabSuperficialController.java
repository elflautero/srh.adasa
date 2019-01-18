package controladores.fiscalizacao;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import entidades.Superficial;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TabSuperficialController implements Initializable{
	
	@FXML TextField tfMarcaBomba = new TextField();
	@FXML TextField tfPotenciaBomba = new TextField();
	@FXML TextField tfTempoBomba = new TextField();
	@FXML TextField tfArea = new  TextField();
	
	@FXML DatePicker dpDataOperacao;
	
	@FXML Pane tabSuperficial;
	
	@FXML
	ChoiceBox<String> cbCaesb = new ChoiceBox<String>();
		ObservableList<String> olCaesb = FXCollections
			.observableArrayList(
					"Sim" , 
					"Não"
					); 
		
		@FXML
		ChoiceBox<String> cbCaptacao = new ChoiceBox<String>();
			ObservableList<String> olCaptacao = FXCollections
				.observableArrayList(
						"Canal" , 
						"Rio",
						"Reservatório",
						"Lago Natural",
						"Nascente",
						"Outro"
						
						); 

			@FXML
			ChoiceBox<String> cbFormaCaptacao = new ChoiceBox<String>();
				ObservableList<String> olFormaCaptacao = FXCollections
					.observableArrayList(
							"Gravidade" , 
							"Bombeamento",
							"Outro"
							); 
			
	@FXML ImageView	iVewSuper = new ImageView();
	//Image imgSuper = new Image(TabSuperficialController.class.getResourceAsStream("/images/superficial.png"));
	
	Superficial sup = new Superficial();
	
	public Superficial obterSuperficial () {
		
		sup.setSupLocal(cbCaptacao.getValue());		// sup_Local; //-- () canal () rio () reservatório () lago natural () nascente
		sup.setSupCaptacao(cbFormaCaptacao.getValue()); // sup_Captacao; // gravidade, bombeamento, outro
		sup.setSupBomba(tfMarcaBomba.getText()); // marca da bomba
		sup.setSupPotencia(tfPotenciaBomba.getText()); // potência da bomba
		sup.setSupTempo(tfTempoBomba.getText()); // tempo de captação
		sup.setSupArea(tfArea.getText()); // área da propriedade
		sup.setSupCaesb(cbCaesb.getValue()); // caesb
		
		if (dpDataOperacao.getValue() == null) {
			
			sup.setSupDataOperacao(null);}
		else {
			sup.setSupDataOperacao(Date.valueOf(dpDataOperacao.getValue()));
			
			}
		
		
		
	return sup;
	
	};
	
	public void imprimirSuperficial (Superficial sup) {
		
		 try {cbCaptacao.setValue(sup.getSupLocal());} catch (Exception e) {cbCaptacao.setValue("");}
		 try {cbFormaCaptacao.setValue(sup.getSupCaptacao());} catch (Exception e) {cbFormaCaptacao.setValue("");};
		 
		 tfMarcaBomba.setText(sup.getSupBomba());
		 tfPotenciaBomba.setText(sup.getSupPotencia());
		 tfTempoBomba.setText(sup.getSupTempo());
		 
		 tfArea.setText(sup.getSupArea());
		 
		 cbCaesb.setValue(sup.getSupCaesb());
		
		Date dataOper = sup.getSupDataOperacao();
		dpDataOperacao.setValue(dataOper.toLocalDate());
		 
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		System.out.println("initialize superficial iniciado!");
		
		cbCaesb.setItems(olCaesb);
		cbCaptacao.setItems(olCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
		
		//iVewSuper.setImage(imgSuper);
		
		
		/*
		dpDataOperacao.setConverter(new StringConverter<LocalDate>() {
			
			@Override
			public String toString(LocalDate t) {
				if (t != null) {
					return formatter.format(t);
				}
				return null;
			}
			
			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.trim().isEmpty()) {
					return LocalDate.parse(string, formatter);
				}
				return null;
			}

		});
		*/
		
		tfPotenciaBomba.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfPotenciaBomba.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfPotenciaBomba.setText(tfPotenciaBomba.getText().substring(0, 5));
                    }
                }
            }
        });
		
		tfTempoBomba.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfTempoBomba.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfTempoBomba.setText(tfTempoBomba.getText().substring(0, 5));
                    }
                }
            }
        });
		
		tfArea.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfArea.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfArea.setText(tfArea.getText().substring(0, 5));
                    }
                }
            }
        });
		
		
	}

}
