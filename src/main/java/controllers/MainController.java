package controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class MainController {
	
	
	@FXML AnchorPane apMain;
	@FXML Pane pMainTop;
	
	@FXML ScrollPane spRegister = new ScrollPane();
	@FXML ScrollPane spWebMap;
	
	@FXML TextField txtMainSearch = new TextField();
	@FXML Button btnMainSearch = new Button();
	
	
	@FXML StackPane stackPMainSearch;
	@FXML StackPane stackPRegister;
	
	
	@FXML Pane pMainSearch;
	
	@FXML Button btnMenu;
	@FXML Button btnRegister;
	
	public void btnMenuHab (ActionEvent a) {
		
		
	}
	
	
	WebView wMaps;
	
	@FXML 
	private void initialize() {
		
		
		
		
		AnchorPane.setTopAnchor(pMainTop, 0.0);
	    AnchorPane.setLeftAnchor(pMainTop, 0.0);
	    AnchorPane.setRightAnchor(pMainTop, 0.0);
	    
	    AnchorPane.setLeftAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setRightAnchor(stackPMainSearch, 0.0);
	    AnchorPane.setBottomAnchor(stackPMainSearch, 45.0);
	    
	    AnchorPane.setLeftAnchor(stackPRegister, 0.0);
	    AnchorPane.setRightAnchor(stackPRegister, 0.0);
	    AnchorPane.setBottomAnchor(stackPRegister, -632.0);
	    
	    
	    StackPane.setAlignment(pMainSearch,Pos.CENTER);
	    StackPane.setAlignment(spRegister,Pos.CENTER);
	    
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
	    
	    
	    TranslateTransition downSP = new TranslateTransition(new Duration(350), stackPMainSearch);
	    downSP.setToY(120.0);
        TranslateTransition upSP = new TranslateTransition(new Duration(350), stackPMainSearch);
        upSP.setToY(2.0);
        
        
        btnMenu.setOnAction((ActionEvent evt)->{
        	
        	Double d =  stackPMainSearch.getTranslateY();
        	
        	
            if(d.equals(0.0) || d.equals(2.0)){
            	
                downSP.play();
                
	            } else {
	            	
	            upSP.play();
	            	  
	            }
            
        });
        
        
        TranslateTransition downRegister = new TranslateTransition(new Duration(350), stackPRegister);
        downRegister.setToY(-800.0);
        TranslateTransition upRegister = new TranslateTransition(new Duration(350), stackPRegister);
        upRegister.setToY(8.0);
        
        btnRegister.setOnAction((ActionEvent evt)->{
        	
        	Double d =  stackPRegister.getTranslateY();
        	 
        	//-620.0  = 1331
        	
        	// 200.0 = 332
        	
        	System.out.println(stackPRegister.getLayoutY() + " e translate " + stackPRegister.getTranslateY());
        	
            if(d.equals(0.0) || d.equals(8.0)){
            	
            	downRegister.play();
                
	            } else {
	            	
	            upRegister.play();
	            	  
	            }
        	
            
        });
        
	    
	}

}
