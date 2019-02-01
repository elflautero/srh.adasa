package controladores.principal;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import controladores.fiscalizacao.TabAtoControlador;
import dao.ModelosDao;
import dao.UsuarioDao;
import entidades.Endereco;
import entidades.ModelosHTML;
import entidades.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principal.FormatoData;
import principal.MalaDireta;

public class TabUsuarioControlador implements Initializable {
	
	TabAtoControlador tabAtoControlador = new TabAtoControlador ();
	
	static Endereco endereco = new Endereco ();
	
	public void setEndereco (Endereco endereco) {
		
		TabUsuarioControlador.endereco = endereco;
		
		// preencher o label com a demanda selecionada //
		TabUsuarioControlador.lblEndereco.setText(
				endereco.getEndLogadouro() 
				+ ", Cidade: " + endereco.getEndCidade()
				+ ", CEP: " + endereco.getEndCEP()
				);
	
	}
	
	public static Endereco getEndereco () {
		return endereco;
	}
	
	
	Pane p_lbl_Endereco = new Pane();
	static Label lblEndereco = new Label();

	//-- Strings --//
	String strPesquisa = "";
		
		Pane tabUsuario = new Pane();
		
		TextField tfNome = new TextField();
		TextField tfCPFCNPJ = new TextField();
		TextField tfLogadouro = new TextField();
		TextField tfCEP = new TextField();
		TextField tfCidade = new TextField();
		TextField tfTelefone = new TextField();
		TextField tfCelular = new TextField();
		TextField tfEmail = new TextField();
		
		
		//-- Persistência --//
		
		Button btnNovo = new Button("Novo");
		Button btnSalvar = new Button("Salvar");
		Button btnEditar = new Button("Editar");
		Button btnExcluir = new Button("Excluir");
		Button btnCancelar = new Button("Cancelar");
		Button btnPesquisar = new Button("Pesquisar");
		TextField tfPesquisar = new TextField();

		
		CheckBox cbEndEmp = new CheckBox();
		
		//-- TableView Endereço --//
		private TableView <Usuario> tvLista = new TableView<>();
		
		TableColumn<Usuario, String> tcNome = new TableColumn<>("Nome");
		TableColumn<Usuario, String> tcCPFCNPJ = new TableColumn<>("CPF/CNPJ");
		TableColumn<Usuario, String> tcEndereco = new TableColumn<>("Endereço");
		
		ObservableList<Usuario> obsList = FXCollections.observableArrayList();

		//Pane pUsuario;
		//Label lblEndUsuario;
		
		//-- combobox - tipo de pessoa --//

		ChoiceBox<String> cbTipoPessoa = new ChoiceBox<String>();
			ObservableList<String> olTipoPessoa = FXCollections
				.observableArrayList("Física" , "Jurídica"); // box - seleção pessoa físcia ou jurídica
			
		//-- combobox - Região Administrativa --//	

		ChoiceBox<String> cbRA = new ChoiceBox<String>();
			ObservableList<String> olRA = FXCollections
				.observableArrayList(
						
						"Águas Claras",
						"Brasília",
						"Brazlândia",
						"Candangolândia",
						"Ceilândia",
						"Cruzeiro",
						"Fercal",
						"Gama",
						"Guará",
						"Itapoã",
						"Jardim Botânico",
						"Lago Norte",
						"Lago Sul",
						"Núcleo Bandeirante",
						"Paranoá",
						"Park Way",
						"Planaltina",
						"Recanto das Emas",
						"Riacho Fundo II",
						"Riacho Fundo",
						"Samambaia",
						"Santa Maria",
						"São Sebastião",
						"SCIA",
						"SIA",
						"Sobradinho II",
						"Sobradinho	",
						"Sudoeste/Octogonal",
						"Taguatinga	",
						"Varjão	",
						"Vicente Pires"
						
						); 	
			
			//-- combobox - unidade federal --//

			ChoiceBox<String> cbUF = new ChoiceBox<String>();
				ObservableList<String> olDF = FXCollections
					.observableArrayList("DF" , "GO", "Outro"); // box - seleção pessoa físcia ou jurídica
				
				
	
	public void btnNovoHab () {
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText(null);
		tfCPFCNPJ.setText(null);
		tfLogadouro.setText(null);
		
		cbRA.setValue(null);
		
		tfCEP.setText(null);
		tfCidade.setText("Brasília");
		
		cbUF.setValue("DF");
		
		tfTelefone.setText(null);
		tfCelular.setText(null);
		tfEmail.setText(null);
		
		cbTipoPessoa.setDisable(false);
		
		tfNome.setDisable(false);
		tfCPFCNPJ.setDisable(false);  //tfEndUF.setDisable(false);
		
		cbEndEmp.setDisable(false);
		
		tfLogadouro.setDisable(false);
		cbRA.setDisable(false);
		tfCEP.setDisable(false);
		tfCidade.setDisable(false);
		cbUF.setDisable(false);
		tfTelefone.setDisable(false);
		tfCelular.setDisable(false);
		tfEmail.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
		
	}
	
	public void btnSalvarHab () {
		
		
		if (endereco.getEndLogadouro() == null) {
			
			Alert aLat = new Alert (Alert.AlertType.ERROR);
			aLat.setTitle("Alerta!!!");
			aLat.setContentText("Endereço relacionado ao usuário não selecionado!!!");
			aLat.setHeaderText(null);
			aLat.show();
			
		} else {
			
				if (cbTipoPessoa.getValue() == null  ||
						tfNome.getText().isEmpty()
						
						) {
					
					Alert a = new Alert (Alert.AlertType.ERROR);
					a.setTitle("Alerta!!!");
					a.setContentText("Informe: Tipo e Nome do Usuário!!!");
					a.setHeaderText(null);
					a.show();
					
				} else {
		
						Usuario us = new  Usuario ();
						
							us.setUsTipo(cbTipoPessoa.getValue());
							us.setUsNome(tfNome.getText());
							us.setUsCPFCNPJ(tfCPFCNPJ.getText()); 
							us.setUsLogadouro(tfLogadouro.getText());
							us.setUsRA(cbRA.getValue());
							us.setUsCidade(tfCidade.getText());
							us.setUsEstado(cbUF.getValue());
							us.setUsCEP(tfCEP.getText());
							us.setUsTelefone(tfTelefone.getText());
							us.setUsCelular(tfCelular.getText());
							us.setUsEmail(tfEmail.getText());
							
							us.setUsDataAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
							
							
						
						Endereco end = new Endereco();
						
							end = endereco;
							//end.getUsuarios().add(usuario);
							us.setUsEnderecoFK(end);
							
						UsuarioDao  usDao = new UsuarioDao();
						
							usDao.salvarUsuario(us);
							usDao.mergeUsuario(us);
						
						obsList.add(us);
						
						modularBotoesInicial();
						
						Alert a = new Alert (Alert.AlertType.INFORMATION);
						a.setTitle("Parabéns!!!");
						a.setContentText("Informe: Cadastro salvo com sucesso!!!");
						a.setHeaderText(null);
						a.show();
				
						
				}
		}
			
	}
	
	public void btnEditarHab () {
		
		if (cbTipoPessoa.isDisable()) {
			
			cbTipoPessoa.setDisable(false);
			tfNome.setDisable(false);
			
			cbEndEmp.setDisable(false);
			
			tfCPFCNPJ.setDisable(false);
			tfLogadouro.setDisable(false);
			cbRA.setDisable(false);
			tfCEP.setDisable(false);
			tfCidade.setDisable(false);
			cbUF.setDisable(false);
			tfTelefone.setDisable(false);
			tfCelular.setDisable(false);
			tfEmail.setDisable(false);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(false);
			btnExcluir.setDisable(true);
			btnCancelar.setDisable(false);
			
		}
		
		else {
			
			if (cbTipoPessoa.getValue() == null  ||
					tfNome.getText().isEmpty()
					
					) {
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Informe: Tipo e Nome do Usuário!!!");
				a.setHeaderText(null);
				a.show();
				
			} else {
	
			//*  //eGeralUs
			Usuario us = tvLista.getSelectionModel().getSelectedItem(); 
			
			// -- preencher os campos -- //
			us.setUsTipo(cbTipoPessoa.getValue()); 
			us.setUsNome(tfNome.getText());
			us.setUsCPFCNPJ(tfCPFCNPJ.getText());
			us.setUsLogadouro(tfLogadouro.getText()); 
			
			us.setUsRA(cbRA.getValue()); 
			
			us.setUsCEP(tfCEP.getText()); 
			us.setUsCidade(tfCidade.getText()); 
			
			us.setUsEstado(cbUF.getValue()); 
			
			us.setUsTelefone(tfTelefone.getText());
			us.setUsCelular(tfCelular.getText());
			us.setUsEmail(tfEmail.getText());
			
			us.setUsEnderecoFK(endereco);
			
			us.setUsDataAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.mergeUsuario(us);
			
			obsList.remove(us);
			obsList.add(us);
			
			modularBotoesInicial();
			
			Alert a = new Alert (Alert.AlertType.INFORMATION);
			a.setTitle("Parabéns!!!");
			a.setContentText("Informe: Cadastro editado com sucesso!!!");
			a.setHeaderText(null);
			a.show();
			
			
			}
			
		}
		
	}
	
	public void btnExcluirHab () {
		
		try {
			//-- capturar usuário selecionado --//
			Usuario usuario = tvLista.getSelectionModel().getSelectedItem(); 
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.removerUsuario(usuario.getUsID());
			
			obsList.remove(usuario);
			
			modularBotoesInicial();
		
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Parabéns!!!");
				a.setContentText("Cadastro excluído com sucesso!!!");
				a.setHeaderText(null);
				a.show();
	
		}	catch (Exception e) {
		
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText(e.toString());
				a.setHeaderText("Erro ao escluir o cadastro!!!");
				a.show();
				};
		
	}
	
	public void btnCancelarHab () {
		
		modularBotoesInicial ();
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText("");
		tfCPFCNPJ.setText("");
		tfLogadouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		tfCidade.setText("");
		
		cbUF.setValue(null);
		
		tfTelefone.setText("");
		tfCelular.setText("");
		tfEmail.setText("");
		
	}
	
	//-- botão pesquisar usuário --//
	public void btnPesquisarHab () {
		
		strPesquisa = tfPesquisar.getText();
		
		listarUsuarios (strPesquisa);
		
		modularBotoesInicial();
		
	}

	public void cbEndEmpHab () {
		
		int count = 0;
		
		if (cbEndEmp.isSelected()) {
			count ++;
			try{tfLogadouro.setText(endereco.getEndLogadouro());}catch (Exception e) {tfLogadouro.setText(null);};
			try{cbRA.setValue(endereco.getEndRAFK().getRaNome());}catch (Exception e) {cbRA.setValue(null);};
			try{tfCEP.setText(endereco.getEndCEP());}catch (Exception e) {tfCEP.setText(null);};
			try{tfCidade.setText(endereco.getEndCidade());}catch (Exception e) {tfCidade.setText(null);};
			try{cbUF.setValue(endereco.getEndUF());}catch (Exception e) {cbUF.setValue(null);};
			
		}
		System.out.println("check box usuario - endereço: " + count);
	}
	
	
	@FXML Pane pUsuario;
	AnchorPane apPrincipal = new AnchorPane();
	BorderPane bpPrincipal = new BorderPane();
	ScrollPane spPrincipal = new ScrollPane();
	Pane p1 = new Pane ();
	
	Pane p_lblEndereco = new Pane();
	Pane pDadosBasicos = new Pane();
	Pane pPersistencia = new Pane();
	
	Label lblDataAtualizacao = new Label();
	
	@SuppressWarnings("unchecked")
	public void initialize(URL url, ResourceBundle rb) {
		
		pUsuario.getChildren().add(apPrincipal);
		
		apPrincipal.minWidthProperty().bind(pUsuario.widthProperty());
		apPrincipal.minHeightProperty().bind(pUsuario.heightProperty());
		
		apPrincipal.getChildren().add(spPrincipal);
		
		spPrincipal.setHbarPolicy(ScrollBarPolicy.NEVER);
		spPrincipal.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
	    AnchorPane.setLeftAnchor(spPrincipal, 0.0);
		AnchorPane.setRightAnchor(spPrincipal, 0.0);
		AnchorPane.setTopAnchor(spPrincipal, 0.0);
		AnchorPane.setBottomAnchor(spPrincipal, 47.0);
		
		spPrincipal.setPrefSize(200, 200);
		
	    bpPrincipal.minWidthProperty().bind(spPrincipal.widthProperty());
	    bpPrincipal.setPrefHeight(1200);

	    spPrincipal.setContent(bpPrincipal);
	    
	    p1.setMaxSize(1140, 680);
	    p1.setMinSize(1140, 680);
	    
		bpPrincipal.setTop(p1);
	    BorderPane.setAlignment(p1, Pos.CENTER);
		
	    obterEndereco();
	    obterDadosBasicos();
	    obterPersistencia();
	    
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(772);
	    lblDataAtualizacao.setLayoutY(567);
	    
	    btnGerarRequerimento.setLayoutX(559);
	    btnGerarRequerimento.setLayoutY(577);
	   
		cbTipoPessoa.setValue("Física");
		cbTipoPessoa.setItems(olTipoPessoa);
		
		cbRA.setItems(olRA);
		
		cbUF.setValue("DF");
		cbUF.setItems(olDF);
		
		tcNome.setPrefWidth(409);
	    tcCPFCNPJ.setPrefWidth(232);
	    tcEndereco.setPrefWidth(232);
		
		tcNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usNome"));
		tcCPFCNPJ.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usCPFCNPJ"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usLogadouro"));
		
		tvLista.getColumns().addAll(tcNome, tcCPFCNPJ, tcEndereco);
		tvLista.setItems(obsList);
		
		tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(120);
		tvLista.setLayoutY(372);
		
		p1.getChildren().addAll(p_lblEndereco, pDadosBasicos, pPersistencia, tvLista, lblDataAtualizacao, btnGerarRequerimento);
		
		modularBotoesInicial();
		
		 btnNovo.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            btnNovoHab();
		        }
		    });
			    
		    btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            btnSalvarHab();
		        }
		    });
		    
		    btnEditar.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            btnEditarHab();
		        }
		    });
		    
		    btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            btnCancelarHab();
		        }
		    });
		    
		    btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            btnPesquisarHab();
		        }
		    });
		    
		    
		    cbEndEmp.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> ov,
		            Boolean old_val, Boolean new_val) {
		                if(new_val == true) {
		                	cbEndEmpHab();
		                }
		        }
		    });
		    /*
		    btnGerarRequerimento.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	btnGerarRequerimentoHab (); //btnGerarRequerimentoHab (Usuario us)
		        }
		    });
		    */
		    
		    selecionarUsuario();
		   
	}
	
	Button btnGerarRequerimento = new Button("Gerar Requerimento");
	
	WebView webTermo;
	WebEngine engTermo;
	
	
	
	public void btnGerarRequerimentoHab (Usuario us) {
		
		HTMLEditor htmlEditor = new HTMLEditor();
		
		ModelosDao modDao = new ModelosDao();
		
		List<ModelosHTML> listRequerimento = modDao.listarModelo("Requerimento de Outorga Subterrânea");
		
		htmlEditor.setHtmlText(listRequerimento.get(0).getModConteudo());
		
		MalaDireta ml = new MalaDireta();
		ml.setHtmlRel(listRequerimento.get(0).getModConteudo());
		ml.setUs(us);
		ml.setEnd(us.getUsEnderecoFK());
		ml.setInter(us.getUsEnderecoFK().getInterferencias().get(0));
		
		htmlEditor.setHtmlText(ml.criarDocumento());
		
		// adicionar um novo botao ao htmlEditor //
		final String TOP_TOOLBAR = ".top-toolbar";
	  
		ToolBar tooImprimir = new ToolBar();
		Button btnImprimir = new Button("Imprimir");
		
        Node nod;
        
        nod = htmlEditor.lookup(TOP_TOOLBAR);
        if (nod instanceof ToolBar) {
        	tooImprimir = (ToolBar) nod;
        }
        
        tooImprimir.getItems().add(btnImprimir);
        tooImprimir.getItems().add(new Separator(Orientation.VERTICAL));
		
        // imprimir o requerimento //
        btnImprimir.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	
	        	ChoiceDialog<Printer> dialog = new ChoiceDialog<Printer>(Printer.getDefaultPrinter(), Printer.getAllPrinters());
	        	dialog.setHeaderText("Escolha a impressora!");
	        	dialog.setContentText("Impressoras disponíveis");
	        	dialog.setTitle("Printer Choice");
	        	Optional<Printer> opt = dialog.showAndWait();
	        	if (opt.isPresent()) {
	        	    //Printer printer = opt.get();
	        	    
	        	    PrinterJob job = PrinterJob.createPrinterJob();
					 if (job != null) {
					    boolean success = true;
					    if (success) {
					    	htmlEditor.print(job);
					        job.endJob();
					    }
					 }
	        	}
	        	
				 
				 
	        }
	    });
	    
 		Scene scene = new Scene(htmlEditor);
		
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1150);
		stage.setHeight(750);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        
        stage.show();
        
        
      //WebView webRequerimento = new WebView();
      		//WebEngine engReq = webRequerimento.getEngine();
        
      //engReq.load(getClass().getResource("/html/termoNotificacao.html").toExternalForm()); 
		
      		/*
      		//engReq.load(getClass().getResource(listRequerimento.get(0).getModConteudo()); 
      		engReq.loadContent(listRequerimento.get(0).getModConteudo());
      		
      		engReq.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>(){ 

                   public void changed(final ObservableValue<? extends Worker.State> observableValue, 

                                       final Worker.State oldState, 
                                       final Worker.State newState) 

                   { 
                   	if (newState == Worker.State.SUCCEEDED){  
                   		String strRequerimento = (String) engReq.executeScript("document.documentElement.outerHTML"); 
                   		
                   		System.out.println("string \n " + strRequerimento);
                   		
                   		editor.setHtmlText(strRequerimento);
                   		
                   		Scene scene = new Scene(editor);
              			
              			Stage stage = new Stage(StageStyle.UTILITY);
              			stage.setWidth(1150);
              			stage.setHeight(750);
              	        stage.setScene(scene);
              	        stage.setMaximized(false);
              	        stage.setResizable(false);
              	        
              	        stage.show();
                   		
                   	} 
                   	
                  } 
      		}); 
      		
      		*/
	
	
	}
	
	
	Button btnBuscarEnd = new Button();
	
	public void obterEndereco () {
		
		p_lblEndereco.setPrefSize(900, 50);
		p_lblEndereco.setLayoutX(120);
		p_lblEndereco.setLayoutY(20);
		p_lblEndereco.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblEnd = new Label ("Endereco: ");
		lblEnd.setLayoutX(20);
		lblEnd.setLayoutY(16);
		
		 // Label para preencher com o endereco a ser trabalhada //
	    lblEndereco.setStyle("-fx-font-weight: bold;");
	    lblEndereco.setPrefSize(750, 25);	
	    lblEndereco.setLayoutX(90);
	    lblEndereco.setLayoutY(13);
	    
	    btnBuscarEnd.setPrefSize(25, 25);
	    btnBuscarEnd.setLayoutX(850);
	    btnBuscarEnd.setLayoutY(13);
		
		p_lblEndereco.getChildren().addAll(lblEnd, lblEndereco, btnBuscarEnd);
	}
	
	public void obterDadosBasicos () {
		
		pDadosBasicos.setPrefSize(900, 227);
		pDadosBasicos.setLayoutX(120);
		pDadosBasicos.setLayoutY(80);
		pDadosBasicos.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblTipo = new Label ("Tipo: ");
		lblTipo.setLayoutX(30);
		lblTipo.setLayoutY(13);
		
			cbTipoPessoa.setPrefSize(106, 25);
			cbTipoPessoa.setLayoutX(29);
			cbTipoPessoa.setLayoutY(38);
			
				Label lblNome = new Label ("Nome/Razão Social: ");
				lblNome.setLayoutX(148);
				lblNome.setLayoutY(13);
					
					tfNome.setPrefSize(509, 25);
					tfNome.setLayoutX(147);
					tfNome.setLayoutY(38);
					
						Label lblCPFCNPJ = new Label ("CPF/CNPJ: ");
						lblCPFCNPJ.setLayoutX(670);
						lblCPFCNPJ.setLayoutY(13);
			
							tfCPFCNPJ.setPrefSize(203, 25);
							tfCPFCNPJ.setLayoutX(668);
							tfCPFCNPJ.setLayoutY(38);
							
			cbEndEmp.setText("importar endereço do empreendimento. ");				
			cbEndEmp.setLayoutX(29);
			cbEndEmp.setLayoutY(73);
							
							
			Label lblLog = new Label ("Endereço: ");
			lblLog.setLayoutX(31);
			lblLog.setLayoutY(103);	
			
				tfLogadouro.setPrefSize(390, 25);
				tfLogadouro.setLayoutX(31);
				tfLogadouro.setLayoutY(128);
		
			Label lblRA = new Label ("RA: ");
			lblRA.setLayoutX(435);
			lblRA.setLayoutY(103);	
			
				cbRA.setPrefSize(150, 25);
				cbRA.setLayoutX(433);
				cbRA.setLayoutY(128);
			
			Label lblCEP = new Label ("CEP:");
			lblCEP.setLayoutX(597);
			lblCEP.setLayoutY(103);	
			
				tfCEP.setPrefSize(83, 25);
				tfCEP.setLayoutX(596);
				tfCEP.setLayoutY(128);
			
			Label lblCidade = new Label ("Cidade: ");
			lblCidade.setLayoutX(692);
			lblCidade.setLayoutY(103);
			

				tfCidade.setPrefSize(112, 25);
				tfCidade.setLayoutX(691);
				tfCidade.setLayoutY(128);
			
			Label lblUF = new Label ("UF: ");
			lblUF.setLayoutX(816);
			lblUF.setLayoutY(103);	
			
				cbUF.setPrefSize(55, 25);
				cbUF.setLayoutX(815);
				cbUF.setLayoutY(128);
				
			Label lblTel = new Label ("Telefone: ");
			lblTel.setLayoutX(31);
			lblTel.setLayoutY(164);	
			
				tfTelefone.setPrefSize(140, 25);
				tfTelefone.setLayoutX(30);
				tfTelefone.setLayoutY(189);
			
			Label lblCel = new Label ("Celular: ");
			lblCel.setLayoutX(182);
			lblCel.setLayoutY(164);	
			
				tfCelular.setPrefSize(140, 25);
				tfCelular.setLayoutX(181);
				tfCelular.setLayoutY(189);
			
			Label lblEmail = new Label ("Email: ");
			lblEmail.setLayoutX(333);
			lblEmail.setLayoutY(164);	
											
				tfEmail.setPrefSize(535	, 25);
				tfEmail.setLayoutX(332);
				tfEmail.setLayoutY(189);

		pDadosBasicos.getChildren().addAll(
				
				lblTipo, cbTipoPessoa, lblNome, tfNome, lblCPFCNPJ, tfCPFCNPJ, 
				cbEndEmp,  lblLog, tfLogadouro, lblRA, cbRA, lblCEP, tfCEP, lblCidade, tfCidade, lblUF, cbUF,
				lblTel, tfTelefone, lblCel, tfCelular, lblEmail, tfEmail
				);
		
	}
	
    public void obterPersistencia () {
    	
   	    pPersistencia.setPrefSize(900, 50);
   	    pPersistencia.setLayoutX(120);
   	    pPersistencia.setLayoutY(313);
   
		btnNovo.setPrefSize(76, 25);
		btnNovo.setLayoutX(42);
		btnNovo.setLayoutY(12);
	
	    btnSalvar.setPrefSize(76, 25);
	    btnSalvar.setLayoutX(129);
	    btnSalvar.setLayoutY(12);
	
	    btnEditar.setPrefSize(76, 25);
	    btnEditar.setLayoutX(216);
	    btnEditar.setLayoutY(12);
	
	    btnExcluir.setPrefSize(76, 25);
	    btnExcluir.setLayoutX(303);
	    btnExcluir.setLayoutY(12);
	    
	    btnCancelar.setPrefSize(76, 25);
	    btnCancelar.setLayoutX(390);
	    btnCancelar.setLayoutY(12);
	    
	    btnPesquisar.setPrefSize(76, 25);
	    btnPesquisar.setLayoutX(783);
	    btnPesquisar.setLayoutY(12);
	    
	    tfPesquisar.setPrefSize(295, 25);
	    tfPesquisar.setLayoutX(477);
	    tfPesquisar.setLayoutY(12);
	    
	    pPersistencia.getChildren().addAll( 
	    		btnNovo, btnSalvar, btnEditar, btnExcluir,
	    		btnCancelar, tfPesquisar, btnPesquisar
	    		
	    		);
	    
	    
    }
	
	private void modularBotoesInicial () {
		
		cbTipoPessoa.setDisable(true);
		tfNome.setDisable(true); 
		tfCPFCNPJ.setDisable(true);
		
		cbEndEmp.setDisable(true);
		
		tfLogadouro.setDisable(true);
		
		cbRA.setDisable(true); 
		
		tfCEP.setDisable(true);
		tfCidade.setDisable(true);
		
		cbUF.setDisable(true);
		
		tfTelefone.setDisable(true);
		tfCelular.setDisable(true);
		tfEmail.setDisable(true);
		
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		
	}
	
	//-- método listar usuários --//
		public void listarUsuarios (String strPesquisa) {
			
			UsuarioDao usDao = new UsuarioDao();
			List<Usuario> usuarioList = usDao.listarUsuario(strPesquisa);
			obsList = FXCollections.observableArrayList();
			
			
			if (!obsList.isEmpty()) {
				obsList.clear();
			}
			
				for (Usuario usuario : usuarioList) {
					
					
							usuario.getUsID();
							usuario.getUsTipo();
							usuario.getUsNome();
							usuario.getUsCPFCNPJ();
							usuario.getUsLogadouro();
							usuario.getUsRA();
							usuario.getUsCidade();
							usuario.getUsEstado();
							usuario.getUsCEP();
							usuario.getUsTelefone();
							usuario.getUsCelular();
							usuario.getUsEmail();
							
							usuario.getUsDataAtualizacao();
							
							usuario.getUsEnderecoFK();
							
					obsList.add(usuario);
						
					}
					
					tvLista.setItems(obsList);
		}
			
			
	public void selecionarUsuario () {
			
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Usuario us = (Usuario) newValue;
				
				if (us == null) {
					
					cbTipoPessoa.setValue(null);
					
					tfNome.setText(null);
					tfCPFCNPJ.setText(null);
					tfLogadouro.setText(null);
					
					cbRA.setValue(null);
					
					tfCEP.setText(null);
					tfCidade.setText(null);
					
					cbUF.setValue(null);
					
					tfTelefone.setText(null);
					tfCelular.setText(null);
					tfEmail.setText(null);
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					cbTipoPessoa.setValue(us.getUsTipo());
					
					tfNome.setText(us.getUsNome());
					tfCPFCNPJ.setText(us.getUsCPFCNPJ());
					tfLogadouro.setText(us.getUsLogadouro());
					
					cbRA.setValue(us.getUsRA());
					
					tfCEP.setText(us.getUsCEP());
					tfCidade.setText(us.getUsCidade());
					
					cbUF.setValue(us.getUsEstado());
					
					tfTelefone.setText(us.getUsTelefone());
					tfCelular.setText(us.getUsCelular());
					tfEmail.setText(us.getUsEmail());
					
					// mudar o endereço relacionado //
					setEndereco(us.getUsEnderecoFK());
					
					// mostrar data de atualizacao //
					FormatoData d = new FormatoData();
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(us.getUsDataAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
					
					btnGerarRequerimentoHab (us);
					
					// copiar cpf do usuario ao selecionar //
					Clipboard clip = Clipboard.getSystemClipboard();
	                ClipboardContent conteudo = new ClipboardContent();
	                conteudo.putString(us.getUsCPFCNPJ());
	                clip.setContent(conteudo);
	                
					// -- habilitar e desabilitar botões -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					
				}
				}
			});
	}

}
