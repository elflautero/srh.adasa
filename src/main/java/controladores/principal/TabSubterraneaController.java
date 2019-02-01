package controladores.principal;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

import entidades.SubSistema;
import entidades.Subterranea;
import entidades.TipoPoco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TabSubterraneaController implements Initializable {
	
	//Image imgSub = new Image(TabSubterraneaController.class.getResourceAsStream("/images/subterranea.png"));
	//@FXML ImageView	iVewSubt = new ImageView();
	
	
	@FXML TextField tfVazaoPoco;
	@FXML TextField tfEstatico;
	@FXML TextField tfDinamico;
	@FXML TextField tfProfundidade;
	
	ObservableList<String> olFinalidades = FXCollections.observableArrayList(
			
			"Abastecimento Humano"	,
			"Criação De Animais"	,
			"Irrigação"				,
			
			"Uso Comercial"			,
			"Uso Industrial"		,
			
			"Piscicultura"			,
			"Lazer"					,
			
			"Outras Finalidades"	

			);
	
	ObservableList<String> olSubFinalidades = FXCollections.observableArrayList(
			
			"Área Rural"	,
			"Área Urbana"	,
			""	,
			"Aves"	,
			"Bovinos"	,
			"Bubalino"	,
			"Caprinos"	,
			"Equinos"	,
			"Ovinos"	,
			"Piscicultura"	,
			"Suínos"	,
			""	,
			"Abacaxi"	,
			"Abóbora"	,
			"Agrião"	,
			"Agrofloresta"	,
			"Agroindústria"	,
			"Agroturismo"	,
			""	,
			"Alface"	,
			"Alho"	,
			"Bambu"	,
			"Banana"	,
			"Café"	,
			"Feijão"	,
			"Flores"	,
			"Flores"	,
			"Frutífera"	,
			"Frutífera"	,
			"Gramínea"	,
			"Grãos"	,
			"Hortaliças"	,
			"Mandioca"	,
			"Milho"	,
			"Mogno"	,
			"Paisagismo"	,
			"Reflorestamento"	,
			"Tomate",
			""	,
			"Lavagem De Veículo"	,
			"Concreto"	,
			"Construção Civil"	,
			"Fabricação De Gelo"	,
			"Farmacêutica"	,
			""	,
			"Tanque Escavado Não Revestido"	,
			"Tanque Escavado Revestido"	,
			
			
			"Água Mineral"
				


			);
	
	
	@FXML public DatePicker dpDataSubterranea;
	
	public Subterranea sub = new Subterranea();
	
	public Double mudaStringDouble (String s) {
		
		Double d;
		try {d = Double.parseDouble(s); } 
			catch (Exception e) {d = 0.0;};
		return d;
		
	}
	
	public int mudaStringInterger(String s) {
		
		int i;
		try {i = Integer.parseInt(s); } 
			catch (Exception e) {i = 0;};
		return i;
		
	}
	

	String strVariaveisFinalidades [] = {"subFinalidade1", "subFinalidade2", "subFinalidade3", "subFinalidade4", "subFinalidade5"};
	String strVariaveisSubfinaldades [] = {"subSubfinalidade1", "subSubfinalidade2", "subSubfinalidade3", "subSubfinalidade4", "subSubfinalidade5"};
	String strVariaveisQuantidades [] = {"subQuantidade1", "subQuantidade2", "subQuantidade3", "subQuantidade4", "subQuantidade5"};
	String strVariaveisConsumo [] = {"subConsumo1", "subConsumo2", "subConsumo3", "subConsumo4", "subConsumo5"};
	String strVariaveisVazao [] = {"subVazao1", "subVazao2", "subVazao3", "subVazao4", "subVazao5"};
	
	// Vazao Mensal //
			String strVariaveisVazaoMes [] = {
					
					"subQDiaJan","subQDiaFev","subQDiaMar","subQDiaAbr","subQDiaMai","subQDiaJun",
					"subQDiaJul","subQDiaAgo","subQDiaSet","subQDiaOut","subQDiaNov","subQDiaDez",	
					
			};
			
			String strVariaveisVazaoHora [] = {
					"subQHoraJan","subQHoraFev","subQHoraMar","subQHoraAbr","subQHoraMai","subQHoraJun",
					"subQHoraJul","subQHoraAgo","subQHoraSet","subQHoraOut","subQHoraNov","subQHoraDez",

			};
			
			String strVariaveisTempo  [] = {
				
					"subTempoCapJan","subTempoCapFev","subTempoCapMar","subTempoCapAbr","subTempoCapMai","subTempoCapJun",
					"subTempoCapJul","subTempoCapAgo","subTempoCapSet","subTempoCapOut","subTempoCapNov","subTempoCapDez",

			};
	
	public Subterranea obterSubterranea () {
		
		// adicionar o id escolhido no combobox
		subSistema.setSubID(subSistemaID);
		tipoPoco.setTipoPocoID(tipoPocoID);
		
		sub.setSubTipoPocoFK(tipoPoco);
		sub.setSubSubSistemaFK(subSistema);
		
		sub.setSubVazao(tfVazaoPoco.getText());
		sub.setSubEstatico(tfEstatico.getText());
		sub.setSubDinamico(tfDinamico.getText());
		sub.setSubProfundidade(tfProfundidade.getText());
		sub.setSubCaesb(cbSubCaesb.getValue());
		
		if (dpDataSubterranea.getValue() == null) {
			
			sub.setSubDataOperacao(null);}
		else {
			sub.setSubDataOperacao(Date.valueOf(dpDataSubterranea.getValue()));
			
			}
		
		// Finalidades Sub Quan Cons e Vazao //
		entidades.GetterAndSetter gs  = new entidades.GetterAndSetter();
		
		for (int i = 0; i< strVariaveisFinalidades.length; i++) {
			 gs.callSetter(sub, strVariaveisFinalidades[i], tfListFinalidades[i].getText());
			 gs.callSetter(sub, strVariaveisSubfinaldades[i], tfListSubfinalidades[i].getText());
			 gs.callSetter(sub, strVariaveisQuantidades[i],mudaStringDouble(tfListQuantidades[i].getText()));
			 gs.callSetter(sub, strVariaveisConsumo[i], mudaStringDouble(tfListConsumo[i].getText()));
			 gs.callSetter(sub, strVariaveisVazao[i], mudaStringDouble(tfListFinVazoes[i].getText()));
		}
		
		// Buscar Vazao Total //
		try {sub.setSubVazaoTotal(Double.parseDouble(lblCalTotal.getText())); } catch (Exception e) {sub.setSubVazaoTotal(0.0);};
		
		for (int i = 0; i< strVariaveisVazaoMes.length; i++) {
			
			String vazao = null;
			
			 try {
				
				 vazao = String.valueOf(tfVazoesLD[0].getText()).replace('.', ','); System.out.println("de boa ");} 
			
						catch (Exception e) {vazao = "0,0"; System.out.println("exceção da string ");};
					
					//try { String.valueOf(tfVazoesLD[0].getText()).replace('.', ',');} cacth (Exception e) {};
			
			 try {
				gs.callSetter(sub, strVariaveisVazaoMes[0], NumberFormat.getInstance().parse(vazao).doubleValue());
				
				System.out.println(NumberFormat.getInstance().parse(vazao).doubleValue());
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 
			 //gs.callSetter(sub, strVariaveisVazaoHora[i], mudaStringInterger(tfVazoesHD[i].getText()));
			// gs.callSetter(sub, strVariaveisTempo[i],mudaStringInterger(tfPeriodoDM[i].getText()));
			 
				// mudaStringDouble(tfVazoesLD[i].getText()
				// NumberFormat.getInstance().parse(tf.getText()).doubleValue()
				 
			
		}

		return sub;
	
	}
	
	public void imprimirSubterranea (Subterranea sub) {
		
		cbTipoPoco.setValue(sub.getSubTipoPocoFK().getTipoPocoDescricao());
		cbSubSis.setValue(sub.getSubSubSistemaFK().getSubDescricao());
		
		tfVazaoPoco.setText(sub.getSubVazao());
		tfEstatico.setText(sub.getSubEstatico());
		tfDinamico.setText(sub.getSubDinamico());
		tfProfundidade.setText(sub.getSubProfundidade());
		
		cbSubCaesb.setValue(sub.getSubCaesb());
		
		Date d = sub.getSubDataOperacao();
		dpDataSubterranea.setValue(d.toLocalDate());
		
		// tabela de finalidades e consumo  //
		
		// Finalidades Sub Quan Cons e Vazao //
		entidades.GetterAndSetter gs  = new entidades.GetterAndSetter();
				
		System.out.println(gs.callGetter(sub, "subFinalidade1"));
		
		for (int i = 0; i< strVariaveisFinalidades.length; i++) {
			
			tfListFinalidades[i].setText(gs.callGetter(sub, strVariaveisFinalidades[i]));
			tfListSubfinalidades[i].setText(gs.callGetter(sub, strVariaveisSubfinaldades[i]));
			tfListQuantidades[i].setText(gs.callGetter(sub, strVariaveisQuantidades[i]));
			tfListConsumo[i].setText(gs.callGetter(sub, strVariaveisConsumo[i]));
			tfListFinVazoes[i].setText(gs.callGetter(sub, strVariaveisVazao[i]));
		}
		
		try {lblCalTotal.setText(String.valueOf(sub.getSubVazaoTotal()));} catch (Exception e) {lblCalTotal.setText("");};
		
		for (int i = 0; i< strVariaveisVazaoMes.length; i++) {
			
			tfVazoesLD[i].setText( String.valueOf(gs.callGetter(sub, strVariaveisVazaoMes[i]).replace('.', ',')));
			tfVazoesHD[i].setText(gs.callGetter(sub, strVariaveisVazaoHora[i]));
			tfPeriodoDM[i].setText(gs.callGetter(sub, strVariaveisTempo[i]));
			
		}
		
		
			
	}
	
	@FXML Pane tabSubterranea = new Pane ();
	
	@FXML
	ChoiceBox<String> cbSubSis = new ChoiceBox<String>();
		ObservableList<String> olSubSis = FXCollections
			.observableArrayList(
					
					"S/A       ",
					"A         ",
					"R3/Q3     ",
					"R4        ",
					"F         ",
					"PPC       ",
					"F/Q/M     ",
					"P1        ",
					"P2        ",
					"P3        ",
					"P4        ",
					"BAMBUÍ    ",
					"ARAXÁ     "
					
					); 
		
		int subSistemaID = 1;
		final int [] listaSubsistema = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13 };
		
		int tipoPocoID = 1;
		final int [] listaTipoPoco = new int [] { 1,2 };
		
	
		@FXML
		ChoiceBox<String> cbTipoPoco = new ChoiceBox<String>();
			ObservableList<String> olTipoPoco = FXCollections
				.observableArrayList(
						"Manual", 
						"Tubular"
						
						); 
			
			@FXML
			ChoiceBox<String> cbSubCaesb = new ChoiceBox<String>();
				ObservableList<String> olSubCaesb = FXCollections
					.observableArrayList(
							"Sim", 
							"Não"
							); 
		
	
	
	SubSistema subSistema = new SubSistema ();
	TipoPoco tipoPoco = new TipoPoco();
	
	@FXML GridPane gpFinalidades;
	@FXML GridPane gpVazoes;
	
	TextField[] tfListFinalidades = new TextField[5];
	TextField[] tfListSubfinalidades = new TextField[5];
	TextField[] tfListQuantidades = new TextField[5];
	TextField[] tfListConsumo = new TextField[5];
	TextField[] tfListFinVazoes = new TextField[5];
	
	Button [] btnListCalcular = new Button[6];
	Label lblCalTotal = new Label();
	
	ChoiceBox<String>[] listCbFinalidade = new ChoiceBox[5];
	ChoiceBox<String>[] listCbSubfinalidades = new ChoiceBox[5];
	
	TextField[] tfVazoesLD = new TextField[12];
	TextField[] tfVazoesHD = new TextField[12]; //  
	TextField[] tfPeriodoDM = new TextField[12];
	Button [] btnListCalMeses = new Button[3];
	
	
	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		iniciarDadosFinalidade ();
		
		cbTipoPoco.setItems(olTipoPoco);
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
		
		cbTipoPoco.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoPocoID = listaTipoPoco [(int) new_value];
	    		System.out.println(" tipo poço id" + tipoPocoID);
	    		
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
		tfVazaoPoco.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tfVazaoPoco.getText().length() >= 5) {

                        // if it's 11th character then just setText to previous
                        // one
                    	tfVazaoPoco.setText(tfVazaoPoco.getText().substring(0, 5));
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
	
	public void iniciarDadosFinalidade () {
		
		
		for (int i = 0; i<5; i++ ) {
			
			TextField tfFin = tfListFinalidades [i] = new TextField();
			TextField tfSub = tfListSubfinalidades [i] = new TextField();
			TextField tfQuant = tfListQuantidades [i] = new TextField();
			TextField tfCon = tfListConsumo [i] = new TextField();
			TextField tfVaz = tfListFinVazoes [i] = new TextField();
			
			Button btnCal = btnListCalcular[i] = new Button();
			
			btnCal.setPrefSize(25, 25);
			
			ChoiceBox<String> cbFin =  listCbFinalidade [i] = new ChoiceBox<String>();
			cbFin.setItems(olFinalidades);
			cbFin.setPrefSize(50, 20);
			
			ChoiceBox<String> cbSub =  listCbSubfinalidades [i] = new ChoiceBox<String>();
			cbSub.setItems(olSubFinalidades);
			cbSub.setPrefSize(50, 20);
			
			gpFinalidades.add(tfFin, 0, i+1); // child, columnIndex, rowIndex
			gpFinalidades.add(cbFin, 1, i+1);
			
			
			gpFinalidades.add(tfSub, 2, i+1);
			gpFinalidades.add(cbSub, 3, i+1);
			
			gpFinalidades.add(tfQuant, 4, i+1); 
			gpFinalidades.add(tfCon, 5, i+1); 
			
			gpFinalidades.add(tfVaz, 6, i+1);
			
			gpFinalidades.add(btnCal, 7, i+1);
			
			cbFin.getSelectionModel().selectedItemProperty().addListener( 
					
			    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
			    	
			    	tfFin.setText(newValue)
			     );
			
			cbSub.getSelectionModel().selectedItemProperty().addListener( 
					
			    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
			    	
			    	tfSub.setText(newValue)
			     );
			
			 btnCal.setOnAction(new EventHandler<ActionEvent>() {

			        @Override
			        public void handle(ActionEvent event) {
			        	Double result = Double.parseDouble(tfQuant.getText()) * Double.parseDouble(tfCon.getText());
			            tfVaz.setText(String.valueOf(result));
			        }
			    });
			
		}
		
		
		for (int i = 0; i<12; i++ ) {
			
			TextField tfVazLD = tfVazoesLD [i] = new TextField();
			gpVazoes.add(tfVazLD, i+1, 1); // child, columnIndex, rowIndex
		
		}
		
		for (int i = 0; i<12; i++ ) {
			
			TextField tfVazHD = tfVazoesHD [i] = new TextField();
			gpVazoes.add(tfVazHD, i+1, 2);
			
		}

		for (int i = 0; i<12; i++ ) {
	
			TextField tfPerDM = tfPeriodoDM [i] = new TextField();
			gpVazoes.add(tfPerDM, i+1, 3);
	
		}
		
		for (int i = 0; i<3; i++ ) {
			
			Button btnCalMes = btnListCalMeses[i] = new Button();
			btnCalMes.setPrefSize(25, 25);
			gpVazoes.add(btnCalMes, 13, i+1);
			
		} // fim loop for
		
		// botao para calcular o valor total de TODAS as finalidades
		Button btnCal6 = btnListCalcular[5] = new Button();
		btnCal6.setPrefSize(25, 25);
		gpFinalidades.add(btnCal6, 7, 6);
		
		// adicionar label com resultado total no gridpane e centralizar o texto
		lblCalTotal.setText("0.0");
		lblCalTotal.setMaxWidth(Double.MAX_VALUE);
		lblCalTotal.setAlignment(Pos.CENTER);
		gpFinalidades.add(lblCalTotal, 6, 6);
		
		// calcular o valor total das finalidades
		btnCal6.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	Double result = 0.0;
	        	
	        	for (int i = 0; i<5;i++) {
	        		if (! tfListFinVazoes[i].getText().isEmpty())
	        		result += Double.parseDouble(
		        					tfListFinVazoes[i].getText());
	        		
	        		
	        	}
	        	lblCalTotal.setText(String.valueOf(result));
	        	
	        }
	    });
	    
		// facilitar o cadastro dos meses 
		btnListCalMeses[2].setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	int meses [] =  {31,28,31,30,31,30,31,31,30,31,30,31};
	        	for (int i = 0; i<12;i++) {
	        		tfPeriodoDM [i].setText(String.valueOf(meses[i]));
	        	}
	        	
	        }
	    });
		
	}

}
