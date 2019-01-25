package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import entidades.FormaCaptacao;
import entidades.LocalCaptacao;
import entidades.MetodoIrrigacao;
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
	@FXML TextField tfCorpoHidrico = new TextField();
	@FXML TextField tfAreaIrrigada = new  TextField();
	@FXML TextField tfAreaContribuicao = new TextField();
	@FXML TextField tfAreaPropriedade = new TextField();
	
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
		ChoiceBox<String> cbLocalCaptacao = new ChoiceBox<String>();
			ObservableList<String> olLocalCaptacao = FXCollections
				.observableArrayList(
						"Canal" , 
						"Rio",
						"Reservatório",
						"Lago Natural",
						"Nascente"
						
						); 

			@FXML
			ChoiceBox<String> cbFormaCaptacao = new ChoiceBox<String>();
				ObservableList<String> olFormaCaptacao = FXCollections
					.observableArrayList(
							"Bombeamento" , 
							"Gravidade"
							); 
				
				@FXML
				ChoiceBox<String> cbMetodoIrrigacao = new ChoiceBox<String>();
					ObservableList<String> olMetodoIrrigacao = FXCollections
						.observableArrayList(
								"Aspersão"	,
								"Gotejamento",
								"Pivô",
								"Manual",
								"Aspersão/gotejamento"	

								); 
					
					@FXML
					ChoiceBox<String> cbBarramento = new ChoiceBox<String>();
						ObservableList<String> olBarramento = FXCollections
							.observableArrayList(
									"Sim",
									"Não"
									); 
					
	int localCaptacaoID = 1;
	String strLocalCaptacao = "Nascente";
	final int [] listaLocalCaptacao = new int [] { 1,2,3,4,5 };
	
	int formaCaptacaoID = 1;
	String strFormaCaptacao = "Bombeamento";
	final int [] listaFormaCaptacao = new int [] { 1,2};
	
	int metodoIrrigacaoID = 1;
	String strMetodoIrrigacao = "Aspersão";
	final int [] listaMetodoIrrigacao = new int [] { 1,2,3,4,5 };
	
				
	@FXML ImageView	iVewSuper = new ImageView();
	//Image imgSuper = new Image(TabSuperficialController.class.getResourceAsStream("/images/superficial.png"));
	
	Superficial sup = new Superficial();
	
	public Superficial obterSuperficial () {
		
		LocalCaptacao lc = new LocalCaptacao();
		lc.setLocalCatacaoID(localCaptacaoID);
		
		FormaCaptacao fc = new FormaCaptacao();
		fc.setFormaCaptacaoID(formaCaptacaoID);
		
		MetodoIrrigacao mi = new MetodoIrrigacao();
		mi.setMetodoIrrigacaoID(metodoIrrigacaoID);
		
		sup.setSupLocalCaptacaoFK(lc);		// sup_Local; //-- () canal () rio () reservatório () lago natural () nascente
		sup.setSupFormaCaptacaoFK(fc);
		sup.setSupMetodoIrrigacaoFK(mi);
		
		sup.setSupBarramento(cbBarramento.getValue());
		
		sup.setSupPotenciaBomba(tfPotenciaBomba.getText()); // potência da bomba
		sup.setSupMarcaBomba(tfMarcaBomba.getText()); // marca da bomba
		
		sup.setSupCorpoHidrico(tfCorpoHidrico.getText());
		sup.setSupAreaIrrigada(tfAreaIrrigada.getText());
		sup.setSupAreaContribuicao(tfAreaContribuicao.getText());
		sup.setSupAreaPropriedade(tfAreaPropriedade.getText());
		
		sup.setSupCaesb(cbCaesb.getValue());
		
		if (dpDataOperacao.getValue() == null) {
			
			sup.setSupDataOperacao(null);}
		else {
			sup.setSupDataOperacao(Date.valueOf(dpDataOperacao.getValue()));
			
			}
		
	return sup;
	
	};
	
	public void imprimirSuperficial (Superficial sup) {
		
		cbLocalCaptacao.setValue(sup.getSupLocalCaptacaoFK().getLocalCaptacaoDescricao());
		cbFormaCaptacao.setValue(sup.getSupFormaCaptacaoFK().getFormaCaptacaoDescricao());
		cbMetodoIrrigacao.setValue(sup.getSupMetodoIrrigacaoFK().getMetodoIrrigacaoDescricao());
		cbBarramento.setValue(sup.getSupBarramento());
		
		Date d = sup.getSupDataOperacao();
		dpDataOperacao.setValue(d.toLocalDate());
		
		tfPotenciaBomba.setText(sup.getSupPotenciaBomba());
		tfMarcaBomba.setText(sup.getSupMarcaBomba());
		 
		tfCorpoHidrico.setText(sup.getSupCorpoHidrico());
		tfAreaIrrigada.setText(sup.getSupAreaIrrigada());
		tfAreaContribuicao.setText(sup.getSupAreaContribuicao());
		tfAreaPropriedade.setText(sup.getSupAreaPropriedade());
		 
		cbCaesb.setValue(sup.getSupCaesb());
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		System.out.println("initialize superficial iniciado!");
		
		cbLocalCaptacao.setItems(olLocalCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
		cbMetodoIrrigacao.setItems(olMetodoIrrigacao);
		cbBarramento.setItems(olBarramento);
		
		cbCaesb.setItems(olCaesb);
		
		cbLocalCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		localCaptacaoID = listaLocalCaptacao [(int) new_value];
	    		
	    		System.out.println("+++ local captacao id " + localCaptacaoID);
	    		
            }
	    });

		cbFormaCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		formaCaptacaoID = listaFormaCaptacao [(int) new_value];
	    		
	    		System.out.println("---------------------------- forma captacao id " + formaCaptacaoID);
	    		
            }
	    });
		
		cbMetodoIrrigacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		metodoIrrigacaoID = listaMetodoIrrigacao [(int) new_value];
	    		
	    		System.out.println("/////////////////////////////////////////////// metodo irrigacao id " + metodoIrrigacaoID);
	    		
            }
	    });
		
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
		/*
		tf.lengthProperty().addListener(new ChangeListener<Number>() {

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
        */
		
		
	}

}
