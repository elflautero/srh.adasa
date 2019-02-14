package controladores.principal;

import java.io.IOException;

import controladores.atendimento.ControladorAtendimento;
import controladores.fiscalizacao.ControladorFiscalizacao;
import controladores.modelosHTML.ControladorModelosHTML;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import mapas.GoogleMap;

public class ControladorPrincipal {
	
	@FXML AnchorPane apMain;
	
	// para chamar a parte da fiscalizacao //
	ControladorAtendimento controladorAtendimento;
	ControladorFiscalizacao controladorFiscalizacao;
	
	ControladorModelosHTML controladorModelosHTML;
	
	@FXML Pane pMainTop;
	@FXML BorderPane bpFiscalizacao;
	@FXML Pane pConversor;
	@FXML Pane pConversorCentro;
	
	
	TabPane tpPrincLatDir = new TabPane ();
	TabPane tpPrinclatEsq = new TabPane ();
	
	Button btnTerrain;
	Button btnRoadMap;
	Button btnSattelite;
	Button btnHybrid;
	
	Button btnZoomOut;
	Button btnZoomIn;
	
	Button btnNightMap;
	Button btnBlueMap;
	Button btnGreenMap;
	Button btnRetroMap;
	
	CheckBox checkBacia;
	CheckBox checkRiodDF;
	CheckBox checkRiosUniao;
	CheckBox checkFraturado;
	CheckBox checkPoroso;
	CheckBox checkUTM;
	CheckBox checkTrafego;
	
	
	Pane pBrowserSEI;
	Pane pFis;
	Pane pFiscalizacao;
	
	Pane pAten;
	Pane pAtendimento;
	
	
	
	@FXML ScrollPane spWebMap;
	@FXML ScrollPane spWebBrowser;
	
	@FXML AnchorPane apWebMap;
	
	@FXML Button btnConverteCoord;
	
	Button btnHome;
	Button btnMapa;
	Button btnConversor;
	Button btnAtendimento;
	Button btnFiscalizacao;
	Button btnSEI;
	Button btnEditorHTML;
	
	@FXML StackPane stackPMainSearch;
	@FXML StackPane stackPaneTop;
	
	@FXML BorderPane bpCenter;

	WebView wMaps;
	WebView wBrowser;
	
	static GoogleMap googleMaps;
	
	@FXML ComboBox <String> cbConverterCoord;
	ObservableList<String> olcbMainConverteCoord;
	
	@FXML VBox vbLateralEsq;
	@FXML VBox vbLateralDir;
		
	Double dblSearch;
	Double dblBrowser;
	Double dblFiscal;
	Double dblAtend;
	
	TranslateTransition upFiscal;
	TranslateTransition downFiscal;
	
	TranslateTransition upAtend;
	TranslateTransition downAtend;
	
	TranslateTransition downSearch;
	TranslateTransition upSearch;
	
	TranslateTransition downBrowser;
	TranslateTransition upBrowser;
	
	public static GoogleMap capturarGoogleMaps () {
		return googleMaps;
    }
	
	TextField tfLatDD;
	TextField tfLonDD;
	TextField tfZonaUTM;
	
	public static Label lblCoord1;
	public static Label lblCoord2;
	
	public static Label lblDD;
	public static Label lblDMS;
	public static Label lblUTM;
	
	
	ComboBox<String> cbNorteSul;
	ObservableList<String> cbNorteSulOpcoes;
	
	ComboBox<String> cbLesteOeste;
	ObservableList<String> cbLesteOesteOpcoes;
	
	
	
	@FXML 
	private void initialize() {
		
		olcbMainConverteCoord = FXCollections.observableArrayList(
    	        " DD ",
    	        " DMS ",
    	        " UTM "
    	    ); 
		
		cbConverterCoord.setItems(olcbMainConverteCoord);
		
		cbConverterCoord.setValue(" DD ");
		
		cbConverterCoord.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {  
            	
                if (newValue == " DD ") {
                	
                	tfLatDD = new TextField("-15"); // <TextField fx:id="tfLat1" layoutX="28.0" layoutY="12.0" prefHeight="40.0" prefWidth="185.0" />
                	tfLatDD.setPrefSize(193.0, 30.0);
                	tfLatDD.setLayoutX(11.0); 
                	tfLatDD.setLayoutY(6.0);
                	
                	tfLonDD = new TextField("-47"); // <TextField fx:id="tfLon1" layoutX="224.0" layoutY="12.0" prefHeight="40.0" prefWidth="185.0" promptText="..." />
                	tfLonDD.setPrefSize(193.0, 30.0);
                	tfLonDD.setLayoutX(216.0); 
                	tfLonDD.setLayoutY(6.0);
                	
                	lblCoord1 = new Label();
                	lblCoord1.setAlignment(Pos.CENTER); // <Label fx:id="lblCoord11" layoutX="420.0" layoutY="12.0" prefHeight="40.0" prefWidth="185.0" style="-fx-background-color: white;" />
                	lblCoord1.setPrefSize(193.0, 30.0);
                	lblCoord1.setLayoutX(421.0);
                	lblCoord1.setLayoutY(6.0);
                	lblCoord1.setStyle("-fx-background-color: white;");
                	
                	lblCoord2 = new Label(); // <Label fx:id="lblCoord21" layoutX="616.0" layoutY="12.0" prefHeight="40.0" prefWidth="185.0" style="-fx-background-color: white;" />
                	lblCoord2.setAlignment(Pos.CENTER);
                	lblCoord2.setPrefSize(193.0, 30.0);
                	lblCoord2.setLayoutX(626.0); 
                	lblCoord2.setLayoutY(6.0);
                	lblCoord2.setStyle("-fx-background-color: white;");
                	
                	pConversorCentro.getChildren().clear();
                	pConversorCentro.getChildren().addAll(tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
                	
                	btnConverteCoord.setOnAction((ActionEvent evt)->{
                    	
                		String typeCoordinate = cbConverterCoord.getValue();
                		
            			googleMaps.convDD (typeCoordinate, tfLatDD.getText(), tfLonDD.getText());
            			
            			copiarCoord (lblCoord1);
            			copiarCoord (lblCoord2);
                        
                    });
                	
                }
                
                if (newValue == " UTM ") {
                	
                	cbNorteSulOpcoes = FXCollections.observableArrayList(
                	        "N",
                	        "S"
                	    );

                	cbNorteSul = new ComboBox<>(cbNorteSulOpcoes);
                	cbNorteSul.setValue("S");
                	
                	cbNorteSul.setPrefSize(65.0, 30.0); // <ComboBox fx:id="cbNorteSul" layoutX="11.0" layoutY="11.0" prefHeight="40.0" prefWidth="65.0" />
                	cbNorteSul.setLayoutX(11.0);
                	cbNorteSul.setLayoutY(6.0);
                	
                	tfZonaUTM = new TextField("23"); // <TextField fx:id="tfUTM" layoutX="88.0" layoutY="12.0" prefHeight="40.0" prefWidth="65.0" />
                	tfZonaUTM.setPrefSize(65.0, 30.0);
                	tfZonaUTM.setLayoutX(88.0); 
                	tfZonaUTM.setLayoutY(6.0);
                	
                	
                	tfLatDD = new TextField("284947"); // <TextField fx:id="tfLat" layoutX="165.0" layoutY="12.0" prefHeight="40.0" prefWidth="150.0" />
                	tfLatDD.setPrefSize(150.0, 30.0);
                	tfLatDD.setLayoutX(165.0); 
                	tfLatDD.setLayoutY(6.0);
                	
                	tfLonDD = new TextField("8340702"); // <TextField fx:id="tfLon" layoutX="326.0" layoutY="12.0" prefHeight="40.0" prefWidth="150.0" promptText="..." />
                	tfLonDD.setPrefSize(150, 30);
                	tfLonDD.setLayoutX(326.0); 
                	tfLonDD.setLayoutY(6.0);
                	
                	lblCoord1 = new Label(); // <Label fx:id="lblCoord1" layoutX="487.0" layoutY="13.0" prefHeight="40.0" prefWidth="160.0" style="-fx-background-color: white;" />
                	lblCoord1.setAlignment(Pos.CENTER);
                	lblCoord1.setPrefSize(160, 30);
                	lblCoord1.setLayoutX(487.0);
                	lblCoord1.setLayoutY(6.0);
                	lblCoord1.setStyle("-fx-background-color: white;");
                	
                	lblCoord2 = new Label(); // <Label fx:id="lblCoord2" layoutX="658.0" layoutY="13.0" prefHeight="40.0" prefWidth="160.0" style="-fx-background-color: white;" />
                	lblCoord2.setAlignment(Pos.CENTER);
                	lblCoord2.setPrefSize(160, 30);
                	lblCoord2.setLayoutX(658.0); 
                	lblCoord2.setLayoutY(6.0);
                	lblCoord2.setStyle("-fx-background-color: white;");
                	
                	pConversorCentro.getChildren().clear();
                	pConversorCentro.getChildren().addAll(cbNorteSul, tfZonaUTM, tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
                	
                	btnConverteCoord.setOnAction((ActionEvent evt)->{
                		
                		String typeCoordinate = cbConverterCoord.getValue();
                		String utmLatLon = tfZonaUTM.getText() + " " + cbNorteSul.getValue() + " " + tfLatDD.getText() + " " + tfLonDD.getText();
                		
                		googleMaps.convUTM(typeCoordinate, utmLatLon);
                		copiarCoord(lblCoord1);
            			copiarCoord(lblCoord2);
                		
                    });
                }
                
                if (newValue == " DMS ") {
                	
                	cbNorteSulOpcoes = FXCollections.observableArrayList(
                	        "N",
                	        "S"
                	    );
                	

                	cbNorteSul = new ComboBox<>(cbNorteSulOpcoes);
                	cbNorteSul.setValue("S");
                	
                	cbNorteSul.setPrefSize(65.0, 30.0); // <ComboBox fx:id="cbNorteSul" layoutX="173.0" layoutY="12.0" prefHeight="40.0" prefWidth="65.0" />
                	cbNorteSul.setLayoutX(173.0);
                	cbNorteSul.setLayoutY(6.0);
                	
                	
                	cbLesteOesteOpcoes = FXCollections.observableArrayList(
                	        "E",
                	        "W"
                	    );

                	cbLesteOeste = new ComboBox<>(cbLesteOesteOpcoes);
                	cbLesteOeste.setValue("W");
                	
                	cbLesteOeste.setPrefSize(65.0, 30.0); //  <ComboBox fx:id="cbLesteOeste" layoutX="410.0" layoutY="12.0" prefHeight="40.0" prefWidth="65.0" />
                	cbLesteOeste.setLayoutX(410.0);
                	cbLesteOeste.setLayoutY(6.0);
                	
                	
                	tfLatDD = new TextField("15 00 00"); //  <TextField fx:id="tfLat11" layoutX="12.0" layoutY="12.0" prefHeight="40.0" prefWidth="150.0" />
                	tfLatDD.setPrefSize(150.0, 30.0);
                	tfLatDD.setLayoutX(12.0); 
                	tfLatDD.setLayoutY(6.0);
                	
                	tfLonDD = new TextField("47 00 00"); // <TextField fx:id="tfLon11" layoutX="249.0" layoutY="12.0" prefHeight="40.0" prefWidth="150.0" promptText="..." />
                	tfLonDD.setPrefSize(150, 30);
                	tfLonDD.setLayoutX(249.0); 
                	tfLonDD.setLayoutY(6.0);
                	
                	lblCoord1 = new Label(); // <Label fx:id="lblCoord111" layoutX="487.0" layoutY="12.0" prefHeight="40.0" prefWidth="160.0" style="-fx-background-color: white;" />
                	lblCoord1.setAlignment(Pos.CENTER);
                	lblCoord1.setPrefSize(160, 30);
                	lblCoord1.setLayoutX(487.0);
                	lblCoord1.setLayoutY(6.0);
                	lblCoord1.setStyle("-fx-background-color: white;");
                	
                	lblCoord2 = new Label(); //  <Label fx:id="lblCoord211" layoutX="657.0" layoutY="12.0" prefHeight="40.0" prefWidth="160.0" style="-fx-background-color: white;" />
                	lblCoord2.setAlignment(Pos.CENTER);
                	lblCoord2.setPrefSize(160, 30);
                	lblCoord2.setLayoutX(657.0); 
                	lblCoord2.setLayoutY(6.0);
                	lblCoord2.setStyle("-fx-background-color: white;");
                	
                	pConversorCentro.getChildren().clear();
                	pConversorCentro.getChildren().addAll(tfLatDD, cbNorteSul,tfLonDD, cbLesteOeste, lblCoord1, lblCoord2 );
                	
                	btnConverteCoord.setOnAction((ActionEvent evt)->{
                		
                		
                		String typeCoordinate = cbConverterCoord.getValue();
                		
                		String lat = tfLatDD.getText() + " " + cbNorteSul.getValue();
                		String lon = tfLonDD.getText() + " " + cbLesteOeste.getValue();
                		
                		googleMaps.convDMS (typeCoordinate, lat, lon);
                		copiarCoord(lblCoord1);
            			copiarCoord(lblCoord2);
                		
                    });
                }
                
             
            }    
        });
		

    	tfLatDD = new TextField("-15");
    	tfLatDD.setPrefSize(193.0, 30.0);
    	tfLatDD.setLayoutX(11.0); 
    	tfLatDD.setLayoutY(6.0);
    	
    	tfLonDD = new TextField("-47");
    	tfLonDD.setPrefSize(193.0, 30.0);
    	tfLonDD.setLayoutX(216.0); 
    	tfLonDD.setLayoutY(6.0);
    	
    	lblCoord1 = new Label();
    	lblCoord1.setAlignment(Pos.CENTER);
    	lblCoord1.setPrefSize(193.0, 30.0);
    	lblCoord1.setLayoutX(421.0);
    	lblCoord1.setLayoutY(6.0);
    	lblCoord1.setStyle("-fx-background-color: white;");
    	
    	lblCoord2 = new Label();
    	lblCoord2.setAlignment(Pos.CENTER);
    	lblCoord2.setPrefSize(193.0, 30.0);
    	lblCoord2.setLayoutX(626.0); 
    	lblCoord2.setLayoutY(6.0);
    	lblCoord2.setStyle("-fx-background-color: white;");
    	
    	pConversorCentro.getChildren().clear();
    	pConversorCentro.getChildren().addAll(tfLatDD, tfLonDD, lblCoord1, lblCoord2 );
    	
    	
    	lblDD = new Label();
		lblDD.setPrefSize(246.0, 30.0);
		lblDD.setLayoutX(126.0); 
		lblDD.setLayoutY(13.0);
		lblDD.setAlignment(Pos.CENTER); 
		lblDD.setStyle("-fx-background-color: white;");
    	
		lblDMS = new Label();
		lblDMS.setPrefSize(246.0, 30.0);
		lblDMS.setLayoutX(401.0); 
		lblDMS.setLayoutY(13.0);
		lblDMS.setAlignment(Pos.CENTER); 
		lblDMS.setStyle("-fx-background-color: white;");
		
		lblUTM = new Label();
		lblUTM.setPrefSize(246.0, 30.0);
		lblUTM.setLayoutX(676.0); 
		lblUTM.setLayoutY(13.0);
		lblUTM.setAlignment(Pos.CENTER); 
		lblUTM.setStyle("-fx-background-color: white;");
		
		copiarCoord (lblDD);
		copiarCoord (lblDMS);
		copiarCoord (lblUTM);
		
		pConversor.getChildren().addAll(lblDD, lblDMS, lblUTM);
    	
		btnConverteCoord.setOnAction((ActionEvent evt)->{
        	
    		String typeCoordinate = cbConverterCoord.getValue();
    		
			googleMaps.convDD (typeCoordinate, tfLatDD.getText(), tfLonDD.getText());
			
			copiarCoord (lblCoord1);
			copiarCoord (lblCoord2);
            
        });
		
    	// vb lado direito e esquerdo
    	AnchorPane.setRightAnchor(vbLateralDir, 5.0);
    	AnchorPane.setLeftAnchor(vbLateralEsq, 5.0);
    	
		// Pane Main Top
		AnchorPane.setTopAnchor(stackPaneTop, 0.0);
	    AnchorPane.setLeftAnchor(stackPaneTop, 0.0);
	    AnchorPane.setRightAnchor(stackPaneTop, 0.0);
	    
	    // Pane Main Bottom
	    AnchorPane.setLeftAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setRightAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setBottomAnchor(stackPMainSearch, 15.0);
	    
	    // BorderPane - Center
	    AnchorPane.setTopAnchor(bpCenter, 150.0);
	    AnchorPane.setLeftAnchor(bpCenter, 0.0);
	    AnchorPane.setRightAnchor(bpCenter, 0.0);
	    AnchorPane.setBottomAnchor(bpCenter, 115.0);
	    
	    bpCenter.setDisable(true);
	   
	   
	    StackPane.setAlignment(pConversor,Pos.CENTER);
	    StackPane.setAlignment(pMainTop,Pos.CENTER);
	  
	    Platform.runLater(new Runnable(){

			public void run() {
				
				googleMaps = new GoogleMap();
				
				apWebMap.getChildren().add(googleMaps);  //.setContent(googleMaps);
				
				
				apMain.widthProperty().addListener(
			    		(observable, oldValue, newValue) -> {
			    			googleMaps.resizeWidthMap ((Double)newValue);
			    			
				    	    }
			    		);
				
				apMain.heightProperty().addListener(
			    		(observable, oldValue, newValue) -> {
			    			googleMaps.resizeHeightMap((Double)newValue);
			    			
				    	    }
			    		);
				
				AnchorPane.setTopAnchor(spWebMap, 30.0);
				AnchorPane.setLeftAnchor(spWebMap, 0.0);
			    AnchorPane.setRightAnchor(spWebMap, 0.0);
			    AnchorPane.setBottomAnchor(spWebMap, 0.0);
			    
			}
	   
			
    	});
	    
        btnHome = GlyphsDude.createIconButton(
        		FontAwesomeIcon.HOME,
        		"HOME", 
        		"20px", 
                "11px",  
                ContentDisplay.LEFT);
        
	        btnHome.getStyleClass().add("clBotoesLateral");
	        btnHome.setLayoutX(10.0);
	        btnHome.setLayoutY(16.0);
	        
        btnSEI = GlyphsDude.createIconButton(
        		FontAwesomeIcon.CHROME,
        		"SEI - GDF", 
        		"15px", 
                "11px",  
                ContentDisplay.LEFT);
        
	        btnSEI.getStyleClass().add("clBotoesLateral");
	        btnSEI.setLayoutX(10.0);
	        btnSEI.setLayoutY(55.0);
        
        btnAtendimento = GlyphsDude.createIconButton(
        		FontAwesomeIcon.PHONE_SQUARE,
        		"ATENDIMENTO", 
        		"20px", 
                "11px",   
                ContentDisplay.LEFT);
        
	        btnAtendimento.getStyleClass().add("clBotoesLateral");
	        btnAtendimento.setLayoutX(10.0);
	        btnAtendimento.setLayoutY(94.0);
        
        btnFiscalizacao = GlyphsDude.createIconButton(
        		FontAwesomeIcon.TICKET,
        		"FISCALIZAÇÃO", 
        		"20px", 
                "11px",  
                ContentDisplay.LEFT);
        
	        btnFiscalizacao.getStyleClass().add("clBotoesLateral");
	        btnFiscalizacao.setLayoutX(10.0);
	        btnFiscalizacao.setLayoutY(133.0);
        
        btnEditorHTML = GlyphsDude.createIconButton(
           		FontAwesomeIcon.HTML5,
           		"EDITOR HTML", 
           		"20px", 
                   "11px",  
                   ContentDisplay.LEFT);
           
           btnEditorHTML.getStyleClass().add("clBotoesLateral");
           btnEditorHTML.setLayoutX(10.0);
           btnEditorHTML.setLayoutY(172.0);
           
        
        btnConversor = GlyphsDude.createIconButton(
        		FontAwesomeIcon.GLOBE,
        		"CONVERSOR", 
        		"20px", 
                "11px",   
                ContentDisplay.LEFT);
        
	        btnConversor.getStyleClass().add("clBotoesLateral");
	        btnConversor.setLayoutX(10.0);
	        btnConversor.setLayoutY(211.0);
        
        
        // botao de aumentar o zoom //
        btnZoomIn = GlyphsDude.createIconButton(
        		FontAwesomeIcon.PLUS,
        		"", 
        		"10px", 
                "9px",  
                ContentDisplay.TOP);
        	btnZoomIn.getStyleClass().add("clBotaoZoom");
        
	        btnZoomIn.setOnAction((ActionEvent evt)->{
	        	googleMaps.setZoomIn();
	        });
        
	        btnZoomIn.setLayoutX(10.0);
	        btnZoomIn.setLayoutY(290.0);
	        
        // botao de diminuir o zoom //
        btnZoomOut = GlyphsDude.createIconButton(
        		FontAwesomeIcon.MINUS,
        		"", 
        		"10px", 
                "9px",  
                ContentDisplay.TOP);
        	btnZoomOut.getStyleClass().add("clBotaoZoom");
        
	        btnZoomOut.setOnAction((ActionEvent evt)->{
	        	googleMaps.setZoomOut();
	        });
	        
	        btnZoomOut.setLayoutX(10.0);
	        btnZoomOut.setLayoutY(327.0);
        
        Label lblTipoMapa = new Label("Tipo de Mapa");
        	lblTipoMapa.setLayoutX(28.0);
        		lblTipoMapa.setLayoutY(11.0);
        		lblTipoMapa.getStyleClass().add("clLabelsEstiloTipoMapa");
        		
        		Label lblEstiloMapa = new Label("Estilo de Mapa");
        			lblEstiloMapa.setLayoutX(26.0);
        				lblEstiloMapa.setLayoutY(189.0);
        				lblEstiloMapa.getStyleClass().add("clLabelsEstiloTipoMapa");
    	
       btnTerrain = new Button("TERRENO");
       btnRoadMap = new Button("RODOVIAS");
       btnSattelite = new Button("SATÉLITE");
       btnHybrid = new Button("HÍBRIDO");
    	
    	btnNightMap = new Button();
    		btnBlueMap = new Button();
    			btnGreenMap = new Button();
    				btnRetroMap = new Button();
    	
    	btnNightMap.setId("btnNight");
	    	btnBlueMap.setId("btnBlueMap");
		    	btnGreenMap.setId("btnGreenMap");
		    		btnRetroMap.setId("btnRetroMap");
		    		
		    		btnNightMap.getStyleClass().add("clBotaoMapaEstilo");
			    	btnBlueMap.getStyleClass().add("clBotaoMapaEstilo");
				    	btnGreenMap.getStyleClass().add("clBotaoMapaEstilo");
				    		btnRetroMap.getStyleClass().add("clBotaoMapaEstilo");
				    		
    	
    	btnNightMap.setLayoutX(10.0);
    	btnNightMap.setLayoutY(216.0);
	    	btnBlueMap.setLayoutX(41.0);
	    	btnBlueMap.setLayoutY(216.0);
		    	btnGreenMap.setLayoutX(72.0);
		    	btnGreenMap.setLayoutY(216.0);
			    	btnRetroMap.setLayoutX(103.0);
			    	btnRetroMap.setLayoutY(216.0);
			    	
			    	btnNightMap.setOnAction((ActionEvent evt)->{
			        	googleMaps.mudarEstiloMapa(1);
			        });	
			    	
				    	btnBlueMap.setOnAction((ActionEvent evt)->{
				        	googleMaps.mudarEstiloMapa(2);
				        });	 
			    	
				    		btnGreenMap.setOnAction((ActionEvent evt)->{
					        	googleMaps.mudarEstiloMapa(3);
					        });	    
				    			btnRetroMap.setOnAction((ActionEvent evt)->{
						        	googleMaps.mudarEstiloMapa(4);
						        });	    	
				    			 
    	
    	btnTerrain.setLayoutX(10.0);
    		btnTerrain.setLayoutY(40.0);
    	
    			btnTerrain.getStyleClass().add("clBotoesLateral");
        
    	btnRoadMap.setLayoutX(10.0);
    		btnRoadMap.setLayoutY(75.0);
    	
    			btnRoadMap.getStyleClass().add("clBotoesLateral");
        
    	btnSattelite.setLayoutX(10.0);
    		btnSattelite.setLayoutY(110.0);
    	
    			btnSattelite.getStyleClass().add("clBotoesLateral");
        
    	btnHybrid.setLayoutX(10.0);
    		btnHybrid.setLayoutY(145.0);
    	
    			btnHybrid.getStyleClass().add("clBotoesLateral");
    	
    	checkBacia = new CheckBox("Bacias");
    		checkRiodDF  = new CheckBox("Rios do DF");
    			checkRiosUniao  = new CheckBox("Rios da União");
    				checkFraturado  = new CheckBox("Fraturado");
				    	checkPoroso  = new CheckBox("Poroso");
					    	checkUTM  = new CheckBox("UTM");
					    		checkTrafego  = new CheckBox("Tráfego");
    	
    	Pane pCheck = new Pane ();
	    	pCheck.setPrefSize(120, 345);
	    	pCheck.setLayoutX(10.0);
	    	pCheck.setLayoutY(10.0);
    	
		    	pCheck.setStyle("-fx-background-color: white;");
		    	pCheck.getChildren().addAll(checkBacia, checkRiodDF, checkRiosUniao,  checkFraturado, checkPoroso,checkUTM, checkTrafego);
		    	
    	checkBacia.setLayoutX(5.0);
    	checkBacia.setLayoutY(15.0);
    	
	    	checkRiodDF.setLayoutX(5.0);
	    	checkRiodDF.setLayoutY(40.0);
    	
		    	checkRiosUniao.setLayoutX(5.0);
		    	checkRiosUniao.setLayoutY(65.0);
    	
			    	checkFraturado.setLayoutX(5.0);
			    	checkFraturado.setLayoutY(90.0);
			    	
				    	checkPoroso.setLayoutX(5.0);
				    	checkPoroso.setLayoutY(115.0);
				    	
					    	checkUTM.setLayoutX(5.0);
					    	checkUTM.setLayoutY(140.0);
					    	
						    	checkTrafego.setLayoutX(5.0);
						    	checkTrafego.setLayoutY(165.0);
    	
        tpPrincLatDir = new TabPane();
        tpPrinclatEsq = new TabPane();
        
       
        Text iconTabHome = GlyphsDude.createIcon(FontAwesomeIcon.HOME, "20px");
        iconTabHome.setFill(Color.WHITE);
        
	        Tab tab1 = new Tab();
	        tab1.setGraphic(iconTabHome);
	        tab1.setClosable(false);
        
        Text iconTabHome2 = GlyphsDude.createIcon(FontAwesomeIcon.CROP, "20px");
        iconTabHome2.setFill(Color.WHITE);
        
	        Tab tab2 = new Tab();
	        tab2.setGraphic(iconTabHome2);
	        tab2.setClosable(false);
        
        Pane pOrgaos = new Pane ();
	        pOrgaos.setPrefSize(130, 370);
	        pOrgaos.getStyleClass().add("panesLaterais");
        
        Pane pOrgaos2 = new Pane ();
	        pOrgaos2.setPrefSize(130, 370);
	        pOrgaos2.getStyleClass().add("panesLaterais");
	        

        pOrgaos.getChildren().addAll(btnHome, btnSEI, btnAtendimento, btnFiscalizacao, btnConversor, btnEditorHTML);
        
        tab1.setContent(pOrgaos);
        tab2.setContent(pOrgaos2);
        
        tpPrinclatEsq.getTabs().addAll(tab1, tab2);
        tpPrinclatEsq.getStyleClass().add("tbPrincLat");
        
        Text iconTabMap1 = GlyphsDude.createIcon(FontAwesomeIcon.MAP, "20px");
        iconTabMap1.setFill(Color.WHITE);
        
        Tab tab3 = new Tab();
        tab3.setGraphic(iconTabMap1);
        
        tab3.setClosable(false);
        
        
        Text iconTabMap2 = GlyphsDude.createIcon(FontAwesomeIcon.OBJECT_GROUP, "20px");
        iconTabMap2.setFill(Color.WHITE);
        
        Tab tab4 = new Tab();
        tab4.setGraphic(iconTabMap2);
        tab4.setClosable(false);
       
        
        Pane pOpcoesMapa = new Pane ();
	        pOpcoesMapa.setPrefSize(130, 370);
	        pOpcoesMapa.getStyleClass().add("panesLaterais");
        
        
        Pane pShapes = new Pane ();
	        pShapes.setPrefSize(130, 370);
	        pShapes.getStyleClass().add("panesLaterais");
        
	        pShapes.getChildren().addAll(pCheck);
        
        pOpcoesMapa.getChildren().addAll(
        		lblTipoMapa, 
        		btnTerrain, btnRoadMap, btnSattelite, btnHybrid, 
        		lblEstiloMapa, btnNightMap, btnBlueMap, btnGreenMap, btnRetroMap,
        		btnZoomIn, btnZoomOut);
       
       
        tab3.setContent(pOpcoesMapa);
        tab4.setContent(pShapes);
        
        tpPrincLatDir.getStyleClass().add("tbPrincLat");
        tpPrincLatDir.getTabs().addAll(tab3, tab4);
      
        vbLateralEsq.getChildren().addAll(tpPrinclatEsq);
        vbLateralDir.getChildren().addAll(tpPrincLatDir);
        
        
        vbLateralEsq.setAlignment(Pos.TOP_CENTER);
        vbLateralDir.setAlignment(Pos.TOP_CENTER);
        
       
        downSearch = new TranslateTransition(new Duration(350), (stackPMainSearch));
        downSearch.setToY(130.0);
        upSearch = new TranslateTransition(new Duration(350), stackPMainSearch);
        upSearch.setToY(2.0);
        
       
        checkBacia.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(6);
        });
        
        checkRiodDF.setOnAction((ActionEvent evt)->{ 
        	googleMaps.openShape(1);
        });
        
        checkRiosUniao.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(4);
        });
        
        checkFraturado.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(2);
        });
        
        checkPoroso.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(3);
        });
        
        checkUTM.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(5);
        });
        
        checkTrafego.setOnAction((ActionEvent evt)->{
        	googleMaps.openShape(7);
        });
        
        
        btnTerrain.setOnAction((ActionEvent evt)->{
        	googleMaps.switchTerrain();
        });
        
        btnRoadMap.setOnAction((ActionEvent evt)->{
        	googleMaps.switchRoadmap();
        });
        
        btnSattelite.setOnAction((ActionEvent evt)->{
        	googleMaps.switchSatellite();
        });
        
        btnHybrid.setOnAction((ActionEvent evt)->{
        	googleMaps.switchHybrid();
        });
        
        btnAtendimento.setOnAction((ActionEvent evt)->{
        	
        	if (pAtendimento == null) {
        		
        		pAtendimento = new Pane();
        		
	        		downAtend = new TranslateTransition(new Duration(350), pAtendimento);
	        		downAtend.setToY(880.0);
	                upAtend = new TranslateTransition(new Duration(350), pAtendimento);
	                upAtend.setToY(0.0);
        
	        	    AnchorPane.setTopAnchor(pAtendimento, 150.0);
	        	    AnchorPane.setLeftAnchor(pAtendimento, 150.0);
	        	    AnchorPane.setRightAnchor(pAtendimento, 150.0);
	        	    AnchorPane.setBottomAnchor(pAtendimento, 115.0);
	        	    
	        	    // Para abrir o pane fora do campo de vis�o
	        	    pAtendimento.setTranslateY(880.0);

	        	    apMain.getChildren().add(pAtendimento);
        	    
        		
        	}
        	
        	dblAtend =  pAtendimento.getTranslateY();
        	
	        	if(dblAtend.equals(0.0)){
	            	
	            	downAtend.play(); 
	            	if (pBrowserSEI != null)
	            	pBrowserSEI.setTranslateY(880.0);
	            	if (pFiscalizacao != null)
            			pFiscalizacao.setTranslateY(880.0);
	        		} 
	            	
	            	else {
	            			
	            		upAtend.play();
	            		if (pBrowserSEI != null)
	            		pBrowserSEI.setTranslateY(880.0);
	            		if (pFiscalizacao != null)
	            			pFiscalizacao.setTranslateY(880.0);
	            		}
	        	
	        			
        	
				        	if (pAten == null) {
				
				        		pAten = new Pane();
					        	
					        	controladorAtendimento = new ControladorAtendimento();
					        	
					        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/atendimento/Atendimento.fxml"));
								
								loader.setRoot(pAten);
								loader.setController(controladorAtendimento);
								try {
									loader.load();
								} catch (IOException e) {
									System.out.println("erro na abertura do pane atendimento");
									e.printStackTrace();
								}
								
								pAten.minWidthProperty().bind(pAtendimento.widthProperty());
								pAten.minHeightProperty().bind(pAtendimento.heightProperty());
								
								pAten.maxWidthProperty().bind(pAtendimento.widthProperty());
								pAten.maxHeightProperty().bind(pAtendimento.heightProperty());
								
								pAten.setStyle("-fx-background-color: transparent;");
								
								pAtendimento.getChildren().add(pAten);
								
				        		}
        	});
        
        btnFiscalizacao.setOnAction((ActionEvent evt)->{
        	
        	if (pFiscalizacao == null) {
        		
        		pFiscalizacao = new Pane();
        		
	        		downFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
	                downFiscal.setToY(880.0);
	                upFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
	                upFiscal.setToY(0.0);
        
	        	    AnchorPane.setTopAnchor(pFiscalizacao, 150.0);
	        	    AnchorPane.setLeftAnchor(pFiscalizacao, 150.0);
	        	    AnchorPane.setRightAnchor(pFiscalizacao, 150.0);
	        	    AnchorPane.setBottomAnchor(pFiscalizacao, 115.0);
	        	    
	        	    // Para abrir o pane fora do campo de vis�o
	        	    pFiscalizacao.setTranslateY(880.0);

	        	    apMain.getChildren().add(pFiscalizacao);
        	    
        		
        	}
        	
        	dblFiscal =  pFiscalizacao.getTranslateY();
        	
	        	if(dblFiscal.equals(0.0)){
	            	
	            	downFiscal.play(); 
	            	if (pBrowserSEI != null)
	            	pBrowserSEI.setTranslateY(880.0);
	            	if (pAtendimento != null)
	            		pAtendimento.setTranslateY(880.0);
	        		} 
	            	
	            	else {
	            			
	            		upFiscal.play();
	            		if (pBrowserSEI != null)
	            		pBrowserSEI.setTranslateY(880.0);
	            		if (pAtendimento != null)
	            			pAtendimento.setTranslateY(880.0);
		        		
	            		}
        	
				        	if (pFis == null) {
				
				        		pFis = new Pane();
					        	
					        	controladorFiscalizacao = new ControladorFiscalizacao();
					        	
					        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/Fiscalizacao.fxml"));
								
								loader.setRoot(pFis);
								loader.setController(controladorFiscalizacao);
								try {
									loader.load();
								} catch (IOException e) {
									System.out.println("erro na abertura do pane fiscalizacao");
									e.printStackTrace();
								}
								
								pFis.minWidthProperty().bind(pFiscalizacao.widthProperty());
								pFis.minHeightProperty().bind(pFiscalizacao.heightProperty());
								
								pFis.maxWidthProperty().bind(pFiscalizacao.widthProperty());
								pFis.maxHeightProperty().bind(pFiscalizacao.heightProperty());
								
								pFis.setStyle("-fx-background-color: transparent;");
								
								pFiscalizacao.getChildren().add(pFis);
								
				        		}
        	});
				    
        btnConversor.setOnAction((ActionEvent evt)->{
        	
        	dblSearch =  stackPMainSearch.getTranslateY();
        	
            if(dblSearch.equals(0.0) || dblSearch.equals(2.0)){
            	
                downSearch.play();
	            
            	} else {
	            	
	            upSearch.play();
	            	  
	            }
            
        });
        
        btnSEI.setOnAction((ActionEvent evt)->{
        	
        	
        	if (wBrowser == null ) {
        		
        		spWebBrowser = new ScrollPane();
        		pBrowserSEI = new Pane();
        		
        		downBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
	            downBrowser.setToY(880.0);
	            upBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
	            upBrowser.setToY(0.0);
	        	
	        	AnchorPane.setTopAnchor(pBrowserSEI, 150.0);
	    	    AnchorPane.setLeftAnchor(pBrowserSEI, 150.0);
	    	    AnchorPane.setRightAnchor(pBrowserSEI, 150.0);
	    	    AnchorPane.setBottomAnchor(pBrowserSEI, 115.0);

	    	    pBrowserSEI.setTranslateY(880.0);
	    	    
	    	    apMain.getChildren().add(pBrowserSEI);

	    	    
	        	
	        	wBrowser = new WebView();
	        	WebEngine weBrowser = wBrowser.getEngine();
	        	weBrowser.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");
	        	
	        	spWebBrowser.setContent(wBrowser);
	        	
	        	spWebBrowser.prefWidthProperty().bind((pBrowserSEI.widthProperty()));
	        	spWebBrowser.prefHeightProperty().bind(pBrowserSEI.heightProperty());
	        	
	        	wBrowser.prefWidthProperty().bind(spWebBrowser.widthProperty().subtract(10.0));
	        	wBrowser.prefHeightProperty().bind(spWebBrowser.heightProperty().subtract(10.0));
	
	        	wBrowser.maxWidthProperty().bind(spWebBrowser.widthProperty());
	        	wBrowser.maxHeightProperty().bind(spWebBrowser.heightProperty());
	        	
	        	spWebBrowser.maxWidthProperty().bind(pBrowserSEI.widthProperty());
	        	spWebBrowser.maxHeightProperty().bind(pBrowserSEI.heightProperty());
	        	
	        	pBrowserSEI.getChildren().add(spWebBrowser);
        	
        	}
        	
        	
        	dblBrowser =  pBrowserSEI.getTranslateY();
        	
        	
        	if(dblBrowser.equals(0.0)){
            	
            	downBrowser.play();
            	
            	if (pFiscalizacao != null)
            	pFiscalizacao.setTranslateY(880.0);//downFiscal.play();
            	if (pAtendimento != null)
            		pAtendimento.setTranslateY(880.0);
        		
	            } else {
	            
	            	upBrowser.play();
	            	if (pFiscalizacao != null)
	            	pFiscalizacao.setTranslateY(880.0);
	            	if (pAtendimento != null)
	            		pAtendimento.setTranslateY(880.0);
	            
	            }
	            
        });
        
        btnEditorHTML.setOnAction((ActionEvent evt)->{
        	
        	Pane pEndereco = new Pane();
        	controladorModelosHTML = new ControladorModelosHTML();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modelosHTML/ModelosHTML.fxml"));
			loader.setRoot(pEndereco);
			loader.setController(controladorModelosHTML);
			
			try {
				loader.load();
			} catch (IOException e) {
				System.out.println("erro leitura do pane - chamada legislação");
				e.printStackTrace();
			}
			
			Scene scene = new Scene(pEndereco);
			Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
			stage.setWidth(1177);
			stage.setHeight(800);
	        stage.setScene(scene);
	        stage.setMaximized(false);
	        stage.setResizable(false);
	        stage.setAlwaysOnTop(true); 
	        stage.show();
        	
        });
        
        	
	}
	
	// ao clicar copiar o valor da coordenada para utilizar em navegadores //
	public void copiarCoord (Label lbl) {
		
		lbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent mouseEvent) {
	            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	                if(mouseEvent.getClickCount() == 1){
	                    
	                	Clipboard clip = Clipboard.getSystemClipboard();
	                    ClipboardContent conteudo = new ClipboardContent();
	                    conteudo.putString(lbl.getText());
	                    clip.setContent(conteudo);
	                }
	            }
	        }
	    });
		
	}
	
	
}


/*
 if (wBrowser == null ) {
        		
	        	spWebBrowser = new ScrollPane();
	        	
	        	wBrowser = new WebView();
	        	WebEngine weBrowser = wBrowser.getEngine();
	        	weBrowser.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");
	        	
	        	spWebBrowser.setContent(wBrowser);
	        	
	        	pBrowserSEI.prefWidthProperty().bind(pCenter.widthProperty());
	        	
	        	wBrowser.prefWidthProperty().bind(pBrowserSEI.widthProperty().subtract(20.0));
	        	wBrowser.prefHeightProperty().bind(pBrowserSEI.heightProperty().subtract(10.0));
	        	
	        	spWebBrowser.prefWidthProperty().bind((pBrowserSEI.widthProperty()));
	        	spWebBrowser.prefHeightProperty().bind(pBrowserSEI.heightProperty());
	
	        	wBrowser.maxWidthProperty().bind(pBrowserSEI.widthProperty().subtract(20.0));
	        	wBrowser.maxHeightProperty().bind(pBrowserSEI.heightProperty());
	        	
	        	spWebBrowser.maxWidthProperty().bind(pBrowserSEI.widthProperty());
	        	spWebBrowser.maxHeightProperty().bind(pBrowserSEI.heightProperty());
	        	
	        	pBrowserSEI.getChildren().add(spWebBrowser);
        	
        	}
 */



/*
 pRegisterExtern.setPrefSize(1155, 806);
	    
	    AnchorPane.setTopAnchor(pRegisterExtern, 150.0);
	    AnchorPane.setBottomAnchor(pRegisterExtern, 45.0);
	    
	    
	    pRegisterExtern.setStyle("-fx-background-color: orange;"); //
	    
	    // Para come�ar fora da vis�o da tela
	    //bpCenter.setTranslateY(880);
	    pRegisterExtern.setTranslateY(880);
	    
	    
	    apMain.getChildren().add(pRegisterExtern);
	    
	   
	    pCenter.layoutXProperty().addListener(
	    		(observable, oldValue, newValue) -> {
	    			pRegisterExtern.layoutXProperty().setValue(newValue);
		    	     
		    	    }
	    		);
	   	
	  
	    bpCenter.layoutYProperty().addListener(
	    		(obs, oldVal, newVal) -> {
	    			pRegisterExtern.setLayoutY(((double) newVal));
		    	     
		    	    }
	    		);
 */


/*
 
    	    Pane pBrowser = new Pane ();
    	    
    	    AnchorPane.setTopAnchor(pBrowser, 150.0);
    	    AnchorPane.setBottomAnchor(pBrowser, 45.0);
    	    
    	    pBrowser.getChildren().add(wBrowser);
    	    
    	    
    	    pBrowser.setLayoutX(pCenter.getLayoutX());
    		pBrowser.setLayoutY((bpCenter.getLayoutY()));
    		    	
    	    
    		pRegisterExtern.getChildren().add(pBrowser);
    	    
    	    TranslateTransition downBrowser = new TranslateTransition(new Duration(350), pBrowser);
        	downBrowser.setToY(222.0);
            TranslateTransition upBrowser = new TranslateTransition(new Duration(350), pBrowser);
            upBrowser.setToY(2.0);
 */

/* a��o btnSEI para mudar o fundo para map ou site sei
 
 btnSEI.setOnAction((ActionEvent evt)->{
        	
        	System.out.println(" translagte y"  + spWebMap.getTranslateY() + " e " + spWebMap.getTranslateX());
       
        	Double d =  spWebMap.getTranslateY();
        	
            if(d.equals(0.0) || d.equals(2.0)){
        	
            		downSPMap.play();
            	
	            } else {
	            	
	            	upSPMap.play();
	           
	            }
        	
        	System.out.println("largura altura wMaps " + wMaps.getWidth() + " e " + wMaps.getHeight());
        	
            
        });
 
 
 */ // tentar mudar a classe css de um bot�o



/*
btnRegister.getStyleClass().clear();
btnRegister.getStyleClass().add("btnMainClassTransRegister");

stackPaneTop.getStyleClass().clear();
stackPaneTop.getStyleClass().add("stackPaneClassRegister");
*/


/*  browser para colocar o sei
 
wBrowser = new WebView();
WebEngine weBrowser = wBrowser.getEngine();
weBrowser.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");


spWebBrowser.setContent(wBrowser);

wBrowser.minWidthProperty().bind(apMain.widthProperty());
wBrowser.minHeightProperty().bind(apMain.heightProperty());

AnchorPane.setTopAnchor(spWebBrowser, 120.0);
AnchorPane.setLeftAnchor(spWebBrowser, 0.0);
AnchorPane.setRightAnchor(spWebBrowser, 0.0);
AnchorPane.setBottomAnchor(spWebBrowser, 0.0);

*/

/*
 btnMapa = GlyphsDude.createIconButton(
        		FontAwesomeIcon.MAP,
        		"MAPA", 
        		"11px", 
                "11px",  
                ContentDisplay.LEFT);
        btnMapa.getStyleClass().add("clBotoesLateral");
        btnMapa.setLayoutX(25.0);
        btnMapa.setLayoutY(96.0);



        btnMapa.setOnAction((ActionEvent evt)->{
        	
        	googleMaps.setMarkerPosition(-15.0,-47.0);
	    	googleMaps.setMapCenter(-15.0,-47.0);
	    	System.out.println("bnt map clicado");
        }
        );
        */



/*
public void copiarCoordenadas () {
	
	lblCoord1.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 1){
                    
                	Clipboard clip = Clipboard.getSystemClipboard();
                    ClipboardContent conteudo = new ClipboardContent();
                    conteudo.putString(lblCoord1.getText());
                    clip.setContent(conteudo);
                }
            }
        }
    });
	
	lblCoord2.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 1){
                    
                	Clipboard clip = Clipboard.getSystemClipboard();
                    ClipboardContent conteudo = new ClipboardContent();
                    conteudo.putString(lblCoord2.getText());
                    clip.setContent(conteudo);
                }
            }
        }
    });
}
*/

