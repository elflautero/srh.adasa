package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import dao.DemandaDao;
import entidades.Demanda;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import principal.Alerta;
import principal.FormatoData;


public class TabDemandaControlador implements Initializable {
	
	// transmitir para os outros controladores o objeto do crud //
	TabEnderecoControlador tabEndCon = new TabEnderecoControlador();
	EditarEnderecoControlador enditarEnderecoControlador = new EditarEnderecoControlador();
	
	// --- String para primeira pesquisa --- //
	String strPesquisa = "";
	
	TextField tfDocumento = new TextField();
	TextField tfDocSei  = new TextField();
	TextField tfProcSei  = new TextField();
	TextField tfResDemanda  = new TextField();

	 Button btnNovo = new Button("Novo");
	 Button btnSalvar = new Button("Salvar");
	 Button btnEditar = new Button("Editar");
	 Button btnExcluir = new Button("Excluir");
	 Button btnCancelar = new Button("Cancelar");
	 Button btnPesquisar = new Button("Pesquisar");
	 TextField tfPesquisar = new TextField();
	
	 Button btnEndDetalhes = new Button("Detalhes");
	 
	 Label lblDemEnd = new Label();

	 DatePicker dpDataDistribuicao = new DatePicker();
	 DatePicker dpDataRecebimento  = new DatePicker();
	
	// -- Tabela --  //
	 private TableView <Demanda> tvLista = new TableView<>();
	
	// -- Colunas -- //
	 private TableColumn<Demanda, String> tcDocumento  = new TableColumn<>("Número da Demanda");
	 private TableColumn<Demanda, String> tcDocSEI  = new TableColumn<>("SEI");
	 private TableColumn<Demanda, String> tcProcSEI  = new TableColumn<>("Número do Processo");
	 
	public void btnNovoHab () {
		
		tfDocumento.setText("");
		tfDocSei.setText("");
		tfProcSei.setText("");
		
		dpDataDistribuicao.getEditor().clear();
		dpDataRecebimento.getEditor().clear();
		
		tfResDemanda.setText("");
		
		dpDataDistribuicao.setDisable(false);
		dpDataRecebimento.setDisable(false);
		
		tfDocumento.setDisable(false);
		tfDocSei.setDisable(false);
		tfProcSei.setDisable(false);
		tfResDemanda.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(true);
		
	}
	
	// -- botão salvar -- //
	public void btnSalvarHab() {
		
        // filtro para não salvar documento sem numero de documento ou processo
        
		try { 
			
			if (tfDocSei.getText().isEmpty()  ||
				tfProcSei.getText().isEmpty()	) 
			{
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Documento, Processo SEI!!!", ButtonType.OK));
		
			} else {
			
					Demanda demanda = new Demanda();
					
					demanda.setDemDocumento(tfDocumento.getText()); 
					
					demanda.setDemDocumentoSEI(tfDocSei.getText()); 
					demanda.setDemProcessoSEI(tfProcSei.getText());
					
					if (dpDataDistribuicao.getValue() == null) {
						
						demanda.setDemDistribuicao(null);}
					else {
						demanda.setDemDistribuicao(Date.valueOf(dpDataDistribuicao.getValue()));
						
						}
						
						if (dpDataRecebimento.getValue() == null) {
							
						demanda.setDemRecebimento(null);}
						
							else {
								demanda.setDemRecebimento(Date.valueOf(dpDataRecebimento.getValue()));
								}
					
					demanda.setDemDescricao(tfResDemanda.getText());
					
					demanda.setDemAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
					
					// salvar a demanda //
					DemandaDao dao = new DemandaDao();
					
					dao.salvarDemanda(demanda);
					
					// enviar o objeto Demanda para a tabEndereco //
					tabEndCon.setDemanda(demanda);
					enditarEnderecoControlador.setObjetoDeEdicao(demanda);
					
					// adicionar a lista //
					obsList.add(demanda);
					
					modularBotoesInicial ();
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", ButtonType.OK));
			
					}
			
		} catch (Exception ex) {
			
			System.out.println("Erro: " + ex);
			ex.printStackTrace();
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "erro na conexão, tente novamente!", ButtonType.OK));
	
		}
		
	}
	
	// -- botão editar -- //
	public void btnEditarHab () {
		
		if (tfDocumento.isDisable()) { // filtro para abrir caixas para edição
			
			tfDocumento.setDisable(false);
			tfDocumento.setDisable(false);
			tfDocSei.setDisable(false);
			tfProcSei.setDisable(false);
			
			dpDataDistribuicao.setDisable(false);
			dpDataRecebimento.setDisable(false);
			
			tfResDemanda.setDisable(false);
			
		} else {
			
			if (tfDocSei.getText().isEmpty() ||  // filtro para não editar sem informacoes nas caixas
					tfProcSei.getText().isEmpty()) 
				{
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.ERROR, "Informe: Documento, Processo SEI!!!", ButtonType.OK));
			
					
				} else {
			
						Demanda demanda = tvLista.getSelectionModel().getSelectedItem();
						
						demanda.setDemDocumento(tfDocumento.getText());
						demanda.setDemDocumentoSEI(tfDocSei.getText());
						demanda.setDemProcessoSEI(tfProcSei.getText());
						
						
						if (dpDataDistribuicao.getValue() == null) {
							demanda.setDemDistribuicao(null);}
							else {
								
								demanda.setDemDistribuicao(Date.valueOf(dpDataDistribuicao.getValue()));
								
								}
						
						
											
						if (dpDataRecebimento.getValue() == null) {
							demanda.setDemRecebimento(null);}
							else {
								demanda.setDemRecebimento(Date.valueOf(dpDataRecebimento.getValue()));
								}
						
						demanda.setDemAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
								
						demanda.setDemDescricao(tfResDemanda.getText());
						 
						DemandaDao dDao = new DemandaDao();
						
						dDao.mergeDemanda(demanda);
						
						// atualizar os dados na tabela
						obsList.remove(demanda);
						obsList.add(demanda);
						
						// pegar o valor, levar para o MainController  e depois para o label lblDoc no EnderecoController
						
						tabEndCon.setDemanda(demanda);
						enditarEnderecoControlador.setObjetoDeEdicao(demanda);
						
						
						// para trazer o resultado por id (do maior para o menor) //
							//Comparator<Demanda> comparar = Comparator.comparing(Demanda::getDemID); //getDemID
							//obsList.sort(comparar.reversed());
						
						modularBotoesInicial ();
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Cadastro editado com sucesso!!!", ButtonType.OK));
				
				}
				
			}
	}
	
	// -- botão excluir -- //
	public void btnExcluirHab () {
	
		try {
			
			Demanda dem = tvLista.getSelectionModel().getSelectedItem();
			
			int id = dem.getDemID(); // buscar id para deletar
			
			DemandaDao dDao = new DemandaDao();
			
			dDao.removerDemanda(id);
			
			obsList.remove(dem);
			
			modularBotoesInicial (); 
			
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro excluído com sucesso!!!", ButtonType.OK));
		
			}
		
			catch (Exception e) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Erro ao excluir o cadastro!!!", ButtonType.OK));
			}
				
	}
	
	// -- botão cancelar -- //
	public void btnCancelarHab () {
			
		modularBotoesInicial();
	}
	
	// -- botão pesquisar demanda -- //
	public void btnPesquisarHab () {
		
		strPesquisa = (String) tfPesquisar.getText();
		
		listarDemandas(strPesquisa);
	
		modularBotoesInicial (); 
		
	}
	
	Label lblDataAtualizacao = new Label();
	
	@FXML Pane pDemanda;
	AnchorPane apPrincipal = new AnchorPane();
	BorderPane bpPrincipal = new BorderPane();
	ScrollPane spPrincipal = new ScrollPane();
	Pane p1 = new Pane ();
	
	Pane pDadosBasicos = new Pane();
	Pane pPersistencia = new Pane();
	Pane pEndereco = new Pane();
	
	@SuppressWarnings("unchecked")
	public void initialize(URL url, ResourceBundle rb) {
		
		pDemanda.getChildren().add(apPrincipal);
		
		apPrincipal.minWidthProperty().bind(pDemanda.widthProperty());
		apPrincipal.minHeightProperty().bind(pDemanda.heightProperty());
		
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
	    
	    obterDadosBasicos ();
	    obterPersistencia ();
	    obterEndereco ();
	    
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(779);
	    lblDataAtualizacao.setLayoutY(441);
	    
	    p1.getChildren().addAll(
	    		
	    		pDadosBasicos, pPersistencia, tvLista, lblDataAtualizacao, pEndereco
	    		);
		
		// --- habilitar e desabilitar botões ---- //
		modularBotoesInicial();
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumento"));
		tcDocSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumentoSEI"));
		tcProcSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demProcessoSEI")); 
		
		tcDocumento.setPrefWidth(409);
		tcDocSEI.setPrefWidth(232);
		tcProcSEI.setPrefWidth(232);
		
		tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(126);
		tvLista.setLayoutY(248);
		
		tvLista.getColumns().addAll(tcDocumento, tcDocSEI, tcProcSEI);
		
		tvLista.setItems(obsList);
		
		bpPrincipal.setTop(p1);
	    BorderPane.setAlignment(p1, Pos.CENTER);
		
		selecionarDemanda ();
		
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
	    
	    btnEndDetalhes.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	editarEnderecoEmpreendimento();
	        }
	    });
	    
	    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	        	btnExcluirHab();
	        }
	    });
	    
	   
		    
	}
	
	public void obterDadosBasicos () {
		
	 	pDadosBasicos.setStyle("-fx-background-color: #E9E9E9;");
	    pDadosBasicos.setPrefSize(900, 160);
	    pDadosBasicos.setLayoutX(126);
	    pDadosBasicos.setLayoutY(35);
	   
	    Label lblDoc = new Label("Documento: ");
	    lblDoc.setLayoutX(19);
	    lblDoc.setLayoutY(3);
	    
	    tfDocumento.setPrefSize(520, 25);
	    tfDocumento.setLayoutX(19);
	    tfDocumento.setLayoutY(27);
	    
	    Label lblDocSEI = new Label("Número SEI: ");

	    lblDocSEI.setLayoutX(551);
	    lblDocSEI.setLayoutY(3);
	    
	    tfDocSei.setPrefSize(128, 25);
	    tfDocSei.setLayoutX(550);
	    tfDocSei.setLayoutY(27);
	    
	    Label lblProcSEI = new Label("Número do Processo: ");

	    lblProcSEI.setLayoutX(690);
	    lblProcSEI.setLayoutY(3);
	    
	    tfProcSei.setPrefSize(197, 25);
	    tfProcSei.setLayoutX(689);
	    tfProcSei.setLayoutY(27);
	    
	    Label lblDistribuicao = new Label("Data de Distribuição: ");
	    lblDistribuicao.setLayoutX(308);
	    lblDistribuicao.setLayoutY(64);

	    dpDataDistribuicao.setPrefSize(125, 25);
	    dpDataDistribuicao.setLayoutX(308); //504
	    dpDataDistribuicao.setLayoutY(87);

	    Label lblRecebimento = new Label("Data de Recebimento: ");
	    lblRecebimento.setLayoutX(469);
	    lblRecebimento.setLayoutY(64);

	    dpDataRecebimento.setPrefSize(125, 25);
	    dpDataRecebimento.setLayoutX(469); //504
	    dpDataRecebimento.setLayoutY(87);
	    
	    Label lblResDemanda = new Label("Resumo da Demanda: ");
	    lblResDemanda.setLayoutX(19);
	    lblResDemanda.setLayoutY(100);
	    
	    tfResDemanda.setPrefSize(865, 25);
	    tfResDemanda.setLayoutX(19);
	    tfResDemanda.setLayoutY(124);
	   
	    pDadosBasicos.getChildren().addAll( 
	    		
	    		lblDoc, tfDocumento, lblDocSEI, tfDocSei, lblProcSEI, tfProcSei,
	    		lblDistribuicao, dpDataDistribuicao, lblRecebimento, dpDataRecebimento,
	    		lblResDemanda, tfResDemanda
				
				);
	    
	}
	
    public void obterPersistencia () {
    	
    	pPersistencia  = new Pane();
   	    pPersistencia.setPrefSize(900, 50);
   	    pPersistencia.setLayoutX(126);
   	    pPersistencia.setLayoutY(195);
   
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
    
    public void obterEndereco () {
    	
   	    pEndereco.setPrefSize(900, 50);
   	    pEndereco.setLayoutX(126);
   	    pEndereco.setLayoutY(474);
   	    
   	    pEndereco.setStyle("-fx-background-color: #E9E9E9;");
   	    
    	Label lblEnd = new Label("Endereço: ");
    	lblEnd.setLayoutX(15);
    	lblEnd.setLayoutY(15);
    	
    	lblDemEnd.setPrefSize(728, 25);
    	lblDemEnd.setLayoutX(85);
    	lblDemEnd.setLayoutY(13);
   
    	btnEndDetalhes.setLayoutX(824);
    	btnEndDetalhes.setLayoutY(13);
	
    	pEndereco.getChildren().addAll( 
	    		lblEnd, lblDemEnd, btnEndDetalhes
	    		);
	    
    }
	 
	ObservableList<Demanda> obsList = FXCollections.observableArrayList();

	public void listarDemandas(String strPesquisa) {
		
		// -- Conexão e pesquisa de demandas -- //
		DemandaDao demandaDao = new DemandaDao();	//passar classe
		List<Demanda> demandaList = demandaDao.listarDemandas(strPesquisa); //passar string de pesquisar
	
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
		
		// funcionando
    	List<Demanda> iList = demandaList;
    	
    	
    	for (Demanda d : iList) {
    		
    		d.getDemID();
    		d.getDemDocumento();
    		d.getDemDocumentoSEI();
    		d.getDemProcessoSEI();
    		
    		obsList.add(d);
    		
    		
    	} // fim loop for
		
		
		// para trazer o resultado por id (do maior para o menor) //
		//Comparator<DemandaTabela> comparar = Comparator.comparing(DemandaTabela::getDemID);
		//obsList.sort(comparar.reversed());
		
        tvLista.setItems(obsList); 
        
	}
	
	// -- selecionar demandas -- //
	public void selecionarDemanda () {
		
		// TableView - selecionar demandas ao clicar //
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			Demanda demanda = (Demanda) newValue;
			
			if (demanda == null) {
				
				tfDocumento.setText("");
				tfDocSei.setText("");
				tfProcSei.setText("");
				
				dpDataRecebimento.getEditor().clear();
				dpDataDistribuicao.getEditor().clear();
				
				tfResDemanda.setText("");
				
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} else {
	
				// preencher os campos //
				tfDocumento.setText(demanda.getDemDocumento());
				tfDocSei.setText(demanda.getDemDocumentoSEI());
				tfProcSei.setText(demanda.getDemProcessoSEI());
				
				if (demanda.getDemDistribuicao() == null) {
					dpDataDistribuicao.setValue(null);
					
	 				} else {
	 					Date dataDis = demanda.getDemDistribuicao();
	 					dpDataDistribuicao.setValue(dataDis.toLocalDate());
	 				}
				
				if (demanda.getDemRecebimento() == null) {
					dpDataRecebimento.setValue(null);
	 				} else {
	 					
	 					Date dataRec = demanda.getDemRecebimento();
	 					dpDataRecebimento.setValue(dataRec.toLocalDate());
	 				}
				
				tfResDemanda.setText(demanda.getDemDescricao());
				
				
				// endereço relacionado //
				if (demanda.getDemEnderecoFK() != null) {
					lblDemEnd.setText(demanda.getDemEnderecoFK().getEndLogadouro() 
							+ ",  Região Administrativa: " + demanda.getDemEnderecoFK().getEndRAFK().getRaNome()
							+ ",  Cep: " + demanda.getDemEnderecoFK().getEndCEP()
							+ ",  Cidade: " + demanda.getDemEnderecoFK().getEndCidade()
							+ ",  UF: " + demanda.getDemEnderecoFK().getEndUF()
							);
					 		
					lblDemEnd.setTextFill(Color.BLACK);
				} else {
					lblDemEnd.setText("Sem endereço cadastrado!");
					lblDemEnd.setTextFill(Color.RED);	
				}
				
				// mostrar data de atualizacao //
				FormatoData d = new FormatoData();
				try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(demanda.getDemAtualizacao()));  // d.formatarData(demanda.getDemAtualizacao())
						lblDataAtualizacao.setTextFill(Color.BLACK);
				}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
						lblDataAtualizacao.setTextFill(Color.RED);}
				
				//Levar a demanda para cadastrar o endereco //
				tabEndCon.setDemanda(demanda);
				enditarEnderecoControlador.setObjetoDeEdicao(demanda);
				
				// copiar número sei da demanda ao selecionar //
				Clipboard clip = Clipboard.getSystemClipboard();
	            ClipboardContent conteudo = new ClipboardContent();
	            conteudo.putString(demanda.getDemDocumentoSEI());
	            clip.setContent(conteudo);
				
				// habilitar e desabilitar botões //
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} // fim do else
			
		} // fim do metodo changed
			
			
	}); // fim do selection model
		
	}
	
	public void editarEnderecoEmpreendimento () {
		
			Pane pEndereco = new Pane();
			enditarEnderecoControlador = new EditarEnderecoControlador();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/EditarEndereco.fxml"));
			loader.setRoot(pEndereco);
			loader.setController(enditarEnderecoControlador);
			
			try {
				loader.load();
			} catch (IOException e) {
				System.out.println("erro leitura do pane - chamada legislação");
				e.printStackTrace();
			}
			
			Scene scene = new Scene(pEndereco);
			Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
			stage.setWidth(964);
			stage.setHeight(600);
	        stage.setScene(scene);
	        stage.setMaximized(false);
	        stage.setResizable(false);
	        stage.setAlwaysOnTop(true); 
	        stage.show();
		}

	// -- método habilitar e desabilitar botões -- //
	private void modularBotoesInicial () {
			
			tfDocumento.setDisable(true);
			tfDocSei.setDisable(true);
			tfProcSei.setDisable(true);
			
			dpDataDistribuicao.setDisable(true);
			dpDataRecebimento.setDisable(true);
			
			tfResDemanda.setDisable(true);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			btnNovo.setDisable(false);
			
	}
	
}

