package controllers;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class MainController {
	
	
	@FXML AnchorPane apMain;
	
	@FXML Pane pMainTop;
	@FXML Pane pCenter;
	@FXML Pane pMainSearch;
	Pane pBrowserSEI = new Pane();
	Pane pFiscalizacao  = new Pane();
	
	@FXML ScrollPane spWebMap;
	@FXML ScrollPane spWebBrowser;
	
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
	
	
	@FXML ComboBox <String> cbMainSearch;
	ObservableList<String> olCBMainSearch;
	
	@FXML VBox vbLateral;
	
	Double dblSearch;
	Double dblBrowser;
	Double dblFiscal;
	
	
	
	TranslateTransition upFiscal;
	TranslateTransition downFiscal;
	
	TranslateTransition downSearch;
	TranslateTransition upSearch;
	
	TranslateTransition downBrowser;
	TranslateTransition upBrowser;
	
	@FXML 
	private void initialize() {
		
		olCBMainSearch = FXCollections.observableArrayList(
    	        "COORDENADAS",
    	        "DOCUMENTO"
    	    ); 
		
		cbMainSearch.setItems(olCBMainSearch);
		
		// Pane Main Top
		AnchorPane.setTopAnchor(stackPaneTop, 0.0);
	    AnchorPane.setLeftAnchor(stackPaneTop, 0.0);
	    AnchorPane.setRightAnchor(stackPaneTop, 0.0);
	    
	    
	    // Pane Main Bottom
	    AnchorPane.setLeftAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setRightAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setBottomAnchor(stackPMainSearch, 45.0);
	    
	    // BorderPane - Center
	    AnchorPane.setTopAnchor(bpCenter, 150.0);
	    AnchorPane.setLeftAnchor(bpCenter, 0.0);
	    AnchorPane.setRightAnchor(bpCenter, 0.0);
	    AnchorPane.setBottomAnchor(bpCenter, 45.0); //-632.0
	    
	    pCenter.maxWidthProperty().bind(bpCenter.widthProperty().subtract(300.0));
	    
	    bpCenter.setDisable(true);
	    
        // Pane Fiscalizacao //
	    
	    pFiscalizacao.prefWidthProperty().bind(pCenter.widthProperty());
	    
	    AnchorPane.setTopAnchor(pFiscalizacao, 150.0);
	    AnchorPane.setBottomAnchor(pFiscalizacao, 45.0);
	    
	    pFiscalizacao.setStyle("-fx-background-color: #ffff00;"); //
	    
	    // Para começar fora da visão da tela
	    pFiscalizacao.setTranslateY(880.0);
	    
	    apMain.getChildren().add(pFiscalizacao);
	    
	    // Pane Navegador SEI //
		
	    AnchorPane.setTopAnchor(pBrowserSEI, 150.0);
	    AnchorPane.setBottomAnchor(pBrowserSEI, 45.0);
	    
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
	    
	    
	    StackPane.setAlignment(pMainSearch,Pos.CENTER);
	    StackPane.setAlignment(pMainTop,Pos.CENTER);
	  
	    Platform.runLater(new Runnable(){

			public void run() {
				
				wMaps = new WebView();
				WebEngine weMaps = wMaps.getEngine();
				weMaps.load("https://www.google.com.br/maps");
				
				
				wMaps.minWidthProperty().bind(apMain.widthProperty());
				wMaps.minHeightProperty().bind(apMain.heightProperty());
				
				spWebMap.setContent(wMaps);
				
				
				AnchorPane.setTopAnchor(spWebMap, 0.0);
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
        
        btnBrowserSEI = GlyphsDude.createIconButton(
        		FontAwesomeIcon.CHROME,
        		"   SEI   ", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        
        btnMapa = GlyphsDude.createIconButton(
        		FontAwesomeIcon.MAP,
        		"MAPA", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        
        btnSearch = GlyphsDude.createIconButton(
        		FontAwesomeIcon.SEARCH,
        		"BUSCA", 
        		"11px", 
                "11px",   
                ContentDisplay.TOP);
        
        btnFiscal = GlyphsDude.createIconButton(
        		FontAwesomeIcon.TICKET,
        		"FISCAL", 
        		"11px", 
                "11px",  
                ContentDisplay.TOP);
        
        
        
        vbLateral.getChildren().addAll(btnHome,btnMapa,btnBrowserSEI, btnSearch,btnFiscal);
        vbLateral.setAlignment(Pos.TOP_CENTER);
        
       
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
        	
        	System.out.println(pFiscalizacao.getTranslateY());
        	
        	dblFiscal =  pFiscalizacao.getTranslateY();
        	
        	if(dblFiscal.equals(0.0)){
            	
            	downFiscal.play(); 
            	downBrowser.play();
        		} 
            	
            	else {
            			
            		upFiscal.play();
            		downBrowser.play();
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
            	downFiscal.play();
            	
	            } else {
	            
	            	upBrowser.play();
	            	downFiscal.play();
	            
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
