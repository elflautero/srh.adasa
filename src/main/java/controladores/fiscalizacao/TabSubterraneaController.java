package controladores.fiscalizacao;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;

import entidades.SubSistema;
import entidades.Subterranea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class TabSubterraneaController implements Initializable {
	
	//Image imgSub = new Image(TabSubterraneaController.class.getResourceAsStream("/images/subterranea.png"));
	//@FXML ImageView	iVewSubt = new ImageView();
	
	@FXML
	TextField tfVazao = new TextField();
	@FXML
	TextField tfEstatico = new TextField();
	@FXML
	TextField tfDinamico = new TextField();
	@FXML
	TextField tfProfundidade = new TextField();
	
	
	@FXML public DatePicker dpDataSubterranea = new DatePicker();
	
	public Subterranea sub = new Subterranea();
	
	public Subterranea obterSubterranea () {
		
		// adicionar o id escolhido no combobox
		subSistema.setSubID(subSistemaID);;
		
		sub.setSubPoco(cbTipoCaptacao.getValue());
		
		sub.setSubSubSistemaFK(subSistema);
		
		sub.setSubVazao(tfVazao.getText());
		sub.setSubEstatico(tfEstatico.getText());
		sub.setSubDinamico(tfDinamico.getText());
		sub.setSubProfundidade(tfProfundidade.getText());
		sub.setSubCaesb(cbSubCaesb.getValue());
		
		sub.setSubDataOperacao(dpDataSubterranea.getValue());
		
		return sub;
	
	}
	
	public void imprimirSubterranea (Subterranea sub) {
		
		cbTipoCaptacao.setValue(sub.getSubPoco());
		
		// mostrar a descricao do subsistema //
		cbSubSis.setValue(sub.getSubSubSistemaFK().getSubDescricao());
		tfVazao.setText(sub.getSubVazao());
		tfEstatico.setText(sub.getSubEstatico());
		tfDinamico.setText(sub.getSubDinamico());
		tfProfundidade.setText(sub.getSubProfundidade());
		
		cbSubCaesb.setValue(sub.getSubCaesb());
		
		dpDataSubterranea.setValue(sub.getSubDataOperacao());
		
	}
	
	@FXML Pane tabSubterranea = new Pane ();
	
	@FXML
	ChoiceBox<String> cbSubSis = new ChoiceBox<String>();
		ObservableList<String> olSubSis = FXCollections
			.observableArrayList(
					
					"S/A",
					"A ",
					"R3/Q3",
					"R4",
					"F",
					"PPC",
					"F/Q/M",
					"P1",
					"P2",
					"P3",
					"P4",
					"Bambuí",
					"Araxá"
					
					); 
		
	
		@FXML
		ChoiceBox<String> cbTipoCaptacao = new ChoiceBox<String>();
			ObservableList<String> olTipoCaptacao = FXCollections
				.observableArrayList(
						"Tubular", 
						"Manual"
						
						); 
			
			@FXML
			ChoiceBox<String> cbSubCaesb = new ChoiceBox<String>();
				ObservableList<String> olSubCaesb = FXCollections
					.observableArrayList(
							"Sim", 
							"Não"
							); 
		
	
	int subSistemaID = 1;
	final int [] listaSubsistema = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13 };
	
	SubSistema subSistema = new SubSistema ();
	
	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		cbTipoCaptacao.setItems(olTipoCaptacao);
		cbSubCaesb.setItems(olSubCaesb);
	
		cbSubSis.setItems(olSubSis);
		
		cbSubSis.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			subSistemaID = listaSubsistema [(int) new_value];
	    		System.out.println("susbsistema ID tabSubterranea " + subSistemaID);
	    		
            }
	    });
		
		
		//iVewSubt.setImage(imgSub);
		
		/*
		dpDataSubterranea.setConverter(new StringConverter<LocalDate>() {
			
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
		
		System.out.println("TabSubterranea inicializada!");
		
		// listeners para envitar valor maior que cinco caracteres
		tfVazao.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfVazao.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfVazao.setText(tfVazao.getText().substring(0, 5));
                    }
                }
            }
        });
		
		tfEstatico.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfEstatico.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfEstatico.setText(tfEstatico.getText().substring(0, 5));
                    }
                }
            }
        });
		
		tfDinamico.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfDinamico.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfDinamico.setText(tfDinamico.getText().substring(0, 5));
                    }
                }
            }
        });
		
		tfProfundidade.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfProfundidade.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfProfundidade.setText(tfProfundidade.getText().substring(0, 5));
                    }
                }
            }
        });
		
	}

}
