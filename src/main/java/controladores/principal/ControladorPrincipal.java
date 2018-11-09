package controladores.principal;

import java.io.IOException;

import controladores.fiscalizacao.ControladorFiscalizacao;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import mapas.GoogleMap;

public class ControladorPrincipal {
	
	@FXML AnchorPane apMain;
	
	// para chamar a parte da fiscalizacao //
	ControladorFiscalizacao controladorFiscalizacao;
	
	@FXML Pane pMainTop;
	@FXML Pane pCenter;
	@FXML Pane pConversor;
	@FXML Pane pConversorCentro;
	
	Pane pBrowserSEI = new Pane();
	
	Pane pFiscalizacao  = new Pane();
	Pane p;
	
	@FXML ScrollPane spWebMap;
	@FXML ScrollPane spWebBrowser;
	
	@FXML AnchorPane apWebMap;
	
	@FXML TextField txtMainSearch = new TextField();
	
	
	@FXML Button btnMainSearch = new Button();
	@FXML Button btnMenu;
	@FXML Button btnRegister;
	@FXML Button btnSEI;
	
	Button btnHome;
	Button btnMapa;
	Button btnSearch;
	Button btnFiscal;
	Button btnBrowserSEI;
	
	@FXML StackPane stackPMainSearch;
	@FXML StackPane stackPaneTop;
	
	@FXML BorderPane bpCenter;
	
	WebView wMaps;
	WebView wBrowser;
	
	static GoogleMap googleMaps;
	
	
	@FXML ComboBox <String> cbMainSearch;
	ObservableList<String> olCBMainSearch;
	
	@FXML VBox vbLateralEsq;
	@FXML VBox vbLateralDir;
	
	
	Double dblSearch;
	Double dblBrowser;
	Double dblFiscal;
	
	TranslateTransition upFiscal;
	TranslateTransition downFiscal;
	
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
	
	
	@FXML 
	private void initialize() {
		
		olCBMainSearch = FXCollections.observableArrayList(
    	        " DD ",
    	        " DMS ",
    	        " UTM "
    	    ); 
		
		cbMainSearch.setItems(olCBMainSearch);
		
		cbMainSearch.setValue(" DD ");
		
		cbMainSearch.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
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
                	
                	btnMainSearch.setOnAction((ActionEvent evt)->{
                    	
                		String typeCoordinate = cbMainSearch.getValue();
                		
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
                	
                	btnMainSearch.setOnAction((ActionEvent evt)->{
                		
                		String typeCoordinate = cbMainSearch.getValue();
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
                	
                	btnMainSearch.setOnAction((ActionEvent evt)->{
                		
                		
                		String typeCoordinate = cbMainSearch.getValue();
                		
                		String lat = tfLatDD.getText() + " " + cbNorteSul.getValue();
                		String lon = tfLonDD.getText() + " " + cbLesteOeste.getValue();
                		
                		googleMaps.convDMS (typeCoordinate, lat, lon);
                		copiarCoord(lblCoord1);
            			copiarCoord(lblCoord2);
                		
                    });
                }
                
             
            }    
        });
		

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
    	
    	
    	lblDD = new Label(); // <Label fx:id="lblDD" layoutX="126.0" layoutY="13.0" prefHeight="30.0" prefWidth="246.0" style="-fx-background-color: white;" />
		lblDD.setPrefSize(246.0, 30.0);
		lblDD.setLayoutX(126.0); 
		lblDD.setLayoutY(13.0);
		lblDD.setAlignment(Pos.CENTER); 
		lblDD.setStyle("-fx-background-color: white;");
    	
		lblDMS = new Label(); // <Label fx:id="lblDMS" layoutX="401.0" layoutY="13.0" prefHeight="30.0" prefWidth="246.0" style="-fx-background-color: white;" />
		lblDMS.setPrefSize(246.0, 30.0);
		lblDMS.setLayoutX(401.0); 
		lblDMS.setLayoutY(13.0);
		lblDMS.setAlignment(Pos.CENTER); 
		lblDMS.setStyle("-fx-background-color: white;");
		
		lblUTM = new Label(); // <Label fx:id="lblUTM" layoutX="676.0" layoutY="13.0" prefHeight="30.0" prefWidth="246.0" style="-fx-background-color: white;" />
		lblUTM.setPrefSize(246.0, 30.0);
		lblUTM.setLayoutX(676.0); 
		lblUTM.setLayoutY(13.0);
		lblUTM.setAlignment(Pos.CENTER); 
		lblUTM.setStyle("-fx-background-color: white;");
		
		copiarCoord (lblDD);
		copiarCoord (lblDMS);
		copiarCoord (lblUTM);
		
		pConversor.getChildren().addAll(lblDD, lblDMS, lblUTM);
    	
    	btnMainSearch.setOnAction((ActionEvent evt)->{
        	
    		String typeCoordinate = cbMainSearch.getValue();
    		
			googleMaps.convDD (typeCoordinate, tfLatDD.getText(), tfLonDD.getText());
			
			copiarCoord (lblCoord1);
			copiarCoord (lblCoord2);
            
        });
		
    	// vb lado direito //
    	AnchorPane.setRightAnchor(vbLateralDir, 10.0);
    	
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
	    AnchorPane.setBottomAnchor(bpCenter, 45.0); //-632.0
	    
	    
	    pCenter.minWidthProperty().bind(bpCenter.widthProperty().subtract(300.0));
	    pCenter.maxWidthProperty().bind(bpCenter.widthProperty().subtract(300.0));
	    
	    
	    bpCenter.setDisable(true);
	    
        // -- Pane Fiscalizacao -- //
	    pFiscalizacao.minWidthProperty().bind(pCenter.widthProperty());
	    pFiscalizacao.maxWidthProperty().bind(pCenter.widthProperty());
	    
	    
	    pFiscalizacao.setStyle("-fx-background-color: transparent;"); //
	    
	    AnchorPane.setTopAnchor(pFiscalizacao, 150.0);
	    AnchorPane.setBottomAnchor(pFiscalizacao, 105.0);
	    
	    
	    // Para abrir o pane fora do campo de visão
	    pFiscalizacao.setTranslateY(880.0);
	    
	    //pFiscalizacao.getChildren().add(ControladorPaneFiscalizacao.pFiscalizacao);
	    
	    apMain.getChildren().add(pFiscalizacao);
	    
	    // Pane Navegador SEI //
		
	    AnchorPane.setTopAnchor(pBrowserSEI, 150.0);
	    AnchorPane.setBottomAnchor(pBrowserSEI, 105.0);
	    
	    pBrowserSEI.setTranslateY(880.0);
	    
	    apMain.getChildren().add(pBrowserSEI);
	    
	    pCenter.layoutXProperty().addListener(
	    		(observable, oldValue, newValue) -> {
	    			pFiscalizacao.layoutXProperty().setValue(newValue);
	    			pBrowserSEI.layoutXProperty().setValue(newValue);
	    		
		    	    }
	    		);
	   	
	    bpCenter.layoutYProperty().addListener(
	    		(obs, oldVal, newVal) -> {
	    			pFiscalizacao.setLayoutY(((double) newVal));
	    			pBrowserSEI.setLayoutY(((double) newVal));
		    	    }
	    		);
	    
	    
	    StackPane.setAlignment(pConversor,Pos.CENTER);
	    StackPane.setAlignment(pMainTop,Pos.CENTER);
	  
	    Platform.runLater(new Runnable(){

			public void run() {
				
				/*
				wMaps = new WebView();
				WebEngine weMaps = wMaps.getEngine();
				weMaps.load("https://www.google.com.br/maps");
				
				wMaps.minWidthProperty().bind(apMain.widthProperty());
				wMaps.minHeightProperty().bind(apMain.heightProperty());
				
				
				spWebMap.setContent(wMaps);
				
				*/
				//spWebMap.setContent(wMaps);
				
				googleMaps = new GoogleMap();
				
				//spWebMap.minWidthProperty().bind(apMain.widthProperty());
				//spWebMap.minHeightProperty().bind(apMain.heightProperty());
				
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
				
				
				AnchorPane.setTopAnchor(spWebMap, 63.0);
				AnchorPane.setLeftAnchor(spWebMap, 0.0);
			    AnchorPane.setRightAnchor(spWebMap, 0.0);
			    AnchorPane.setBottomAnchor(spWebMap, 0.0);
			    
			    
			}
	   
			
    	});
	    
	    
        btnHome = GlyphsDude.createIconButton(
        		FontAwesomeIcon.HOME,
        		"HOME", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        
        btnHome.getStyleClass().add("clBotoesLateral");
        
        
        btnBrowserSEI = GlyphsDude.createIconButton(
        		FontAwesomeIcon.CHROME,
        		"   SEI   ", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        btnBrowserSEI.getStyleClass().add("clBotoesLateral");
        
        btnMapa = GlyphsDude.createIconButton(
        		FontAwesomeIcon.MAP,
        		"MAPA", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        btnMapa.getStyleClass().add("clBotoesLateral");
        
        btnSearch = GlyphsDude.createIconButton(
        		FontAwesomeIcon.GLOBE,
        		"COORD", 
        		"11px", 
                "11px",   
                ContentDisplay.TOP);
        btnSearch.getStyleClass().add("clBotoesLateral");
        
        btnFiscal = GlyphsDude.createIconButton(
        		FontAwesomeIcon.TICKET,
        		"FISCAL", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        btnFiscal.getStyleClass().add("clBotoesLateral");
        
        
        
        vbLateralEsq.getChildren().addAll(btnHome,btnMapa,btnBrowserSEI, btnSearch,btnFiscal);
        
        vbLateralEsq.setAlignment(Pos.TOP_CENTER);
        vbLateralDir.setAlignment(Pos.TOP_CENTER);
        
       
        downSearch = new TranslateTransition(new Duration(350), (stackPMainSearch));
        downSearch.setToY(130.0);
        upSearch = new TranslateTransition(new Duration(350), stackPMainSearch);
        upSearch.setToY(2.0);
        
        downFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
        downFiscal.setToY(880.0);
        upFiscal = new TranslateTransition(new Duration(350), pFiscalizacao);
        upFiscal.setToY(0.0);
        
        
        downBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
        downBrowser.setToY(880.0);
        upBrowser = new TranslateTransition(new Duration(350), pBrowserSEI);
        upBrowser.setToY(0.0);
        
        
        btnFiscal.setOnAction((ActionEvent evt)->{
        	
        	dblFiscal =  pFiscalizacao.getTranslateY();
        	
        	System.out.println(pFiscalizacao.getWidth());
        	
        	if(dblFiscal.equals(0.0)){
            	
            	downFiscal.play(); 
            	pBrowserSEI.setTranslateY(880.0);//downBrowser.play();
        		} 
            	
            	else {
            			
            		upFiscal.play();
            		pBrowserSEI.setTranslateY(880.0); //downBrowser.play();
            		}
        	
        	if (p == null) {
        		
	        	p = new Pane();
	        	
	        	controladorFiscalizacao = new ControladorFiscalizacao();
	        	
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/Fiscalizacao.fxml"));
				
				loader.setRoot(p);
				loader.setController(controladorFiscalizacao);
				try {
					loader.load();
				} catch (IOException e) {
					System.out.println("erro na abertura do pane fiscalizacao");
					e.printStackTrace();
				}
				
				p.minWidthProperty().bind(pFiscalizacao.widthProperty());
				p.minHeightProperty().bind(pFiscalizacao.heightProperty());
				
				p.maxWidthProperty().bind(pFiscalizacao.widthProperty());
				p.maxHeightProperty().bind(pFiscalizacao.heightProperty());
				
				p.setStyle("-fx-background-color: transparent;");
				
				pFiscalizacao.getChildren().add(p);
        	}
        	
        	
        });
        
        
        btnSearch.setOnAction((ActionEvent evt)->{
        	
        	dblSearch =  stackPMainSearch.getTranslateY();
        	
            if(dblSearch.equals(0.0) || dblSearch.equals(2.0)){
            	
                downSearch.play();
	            
            	} else {
	            	
	            upSearch.play();
	            	  
	            }
            
        });
        
        btnBrowserSEI.setOnAction((ActionEvent evt)->{
        	
        	
        	if (wBrowser == null ) {
        		
	        	spWebBrowser = new ScrollPane();
	        	
	        	wBrowser = new WebView();
	        	WebEngine weBrowser = wBrowser.getEngine();
	        	weBrowser.load("https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI");
	        	
	        	spWebBrowser.setContent(wBrowser);
	        	
	        	pBrowserSEI.prefWidthProperty().bind(pCenter.widthProperty());
	        	
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
            	pFiscalizacao.setTranslateY(880.0);//downFiscal.play();
            	
	            } else {
	            
	            	upBrowser.play();
	            	pFiscalizacao.setTranslateY(880.0);//downFiscal.play();
	            
	            }
	            
        });
        
        btnMapa.setOnAction((ActionEvent evt)->{
        	
        	googleMaps.setMarkerPosition(-15.0,-47.0);
	    	googleMaps.setMapCenter(-15.0,-47.0);
	    	System.out.println("bnt map clicado");
        }
        );
        
        	
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
	    
	    // Para começar fora da visão da tela
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

/* ação btnSEI para mudar o fundo para map ou site sei
 
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
 
 
 */ // tentar mudar a classe css de um botão



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
