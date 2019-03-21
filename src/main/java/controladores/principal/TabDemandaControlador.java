package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.xslf.model.PropertyFetcher;

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
import javafx.scene.Node;
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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapas.GoogleMap;
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
	
	/////////////////
	
	BorderPane bp1 = new BorderPane();
	BorderPane bp2 = new BorderPane();
	ScrollPane sp = new ScrollPane();
	Pane pMapa = new Pane ();
	
	GoogleMap googleMaps = new GoogleMap();
	
	@SuppressWarnings("unchecked")
	public void initialize(URL url, ResourceBundle rb) {
		
		bp1.minWidthProperty().bind(pDemanda.widthProperty());
		bp1.maxHeightProperty().bind(pDemanda.heightProperty().subtract(60));
		
			bp1.getStyleClass().add("border-pane");
		
			bp2.setPrefHeight(800);
			bp2.minWidthProperty().bind(pDemanda.widthProperty());
			
				sp.setHbarPolicy(ScrollBarPolicy.NEVER);
			
				sp.setContent(bp2);
				
					bp1.setCenter(sp);
				
		
		pDemanda.getChildren().add(bp1);
		
		p1.setMaxSize(980, 800);
		p1.setMinSize(980, 800);
		
		bp2.setTop(p1);
		BorderPane.setAlignment(p1, Pos.CENTER);
		
		inicializarCadastroDeDados();
		inicializarEnderecoRelacionado ();
		inicializarPersistencia ();
		
	    lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(637);
	    lblDataAtualizacao.setLayoutY(457);
	    
	   
		// --- habilitar e desabilitar botões ---- //
		modularBotoesInicial();
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumento"));
		tcDocSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumentoSEI"));
		tcProcSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demProcessoSEI")); 
		
		tcDocumento.setPrefWidth(405);
		tcDocSEI.setPrefWidth(180);
		tcProcSEI.setPrefWidth(185);
		
		tvLista.setPrefSize(790, 185);
		tvLista.setLayoutX(95);
		tvLista.setLayoutY(260);
		
		tvLista.getColumns().addAll(tcDocumento, tcDocSEI, tcProcSEI);
		
		tvLista.setItems(obsList);
		
		pMapa.setPrefSize(790, 250);
		pMapa.setLayoutX(95);
		pMapa.setLayoutY(501);
		pMapa.getStyleClass().add("panes");
	
			pMapa.getChildren().add(googleMaps);
				googleMaps.setWidth(790);
				googleMaps.setHeight(240);
				googleMaps.switchHybrid();
		    
		p1.getChildren().addAll(
		    		
		    		tvLista, lblDataAtualizacao, pMapa
		    		);
			
		 
		//bpPrincipal.setTop(p1);
	    //BorderPane.setAlignment(p1, Pos.CENTER);
		
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
	 
	} // fim initialize //////////////////////////////////////////////////////
	
	
	ArrayList<Node> listNodesDados= new ArrayList<Node>();
	
	Pane pCadastroDeDados = new Pane();
	
	public void inicializarCadastroDeDados(){
		
		pCadastroDeDados.setPrefSize(586, 148);
		pCadastroDeDados.setLayoutX(20);
		pCadastroDeDados.setLayoutY(12);
		
		pCadastroDeDados.getStyleClass().add("panes");
		
		 	listNodesDados.add(new Label("DOCUMENTO:")); // 0 a 4 - Labels
		 	listNodesDados.add(new Label("NÚMERO SEI:"));
		 	listNodesDados.add(new Label("PROCESSO:"));
		 	listNodesDados.add(new Label("DATA DE RECEBIMENTO:"));
		 	listNodesDados.add(new Label("DATA DE DISTRIBUIÇÃO:"));
		 	
		 	listNodesDados.add(new TextField()); // 5 a 7
		 	listNodesDados.add( new TextField());
		 	listNodesDados.add(new TextField());
			
		 	listNodesDados.add(new DatePicker()); // 8 a 9 - DatePicker
		 	listNodesDados.add(new DatePicker());	
		 	
			 	Double[][] prefSizeWHeLayXY  = {
				
						/* labels */	{85.0, 17.0, 10.0, 10.0}	, 
										{85.0, 17.0, 410.0, 10.0}	,
										{85.0, 17.0, 10.0, 75.0}	,
										{145.0, 17.0, 234.0, 75.0}	,
										{145.0, 17.0, 409.0, 75.0}	, 
										
							/* textfields */ 	{388.0, 17.0, 10.0, 35.0}	,
												{165.0, 25.0, 408.0, 35.0}	,
												{213.0, 25.0, 10.0, 100.0}	,
												
								/* datepickers */	{165.0, 25.0, 233.0, 100.0}	,
													{165.0, 25.0, 409.0, 100.0}	,
								
					
				};
			 	
			 	for (int i1 = 0; i1< prefSizeWHeLayXY .length ; i1++) {
					
			 			Double dblWHXY [][] = new Double[11][4];
					
						for (int i2 = 0; i2 < prefSizeWHeLayXY [i1].length ; i2++) {
							
							dblWHXY[i1][i2] = prefSizeWHeLayXY [i1][i2];
							
						} // fim loop i2

						((Region)listNodesDados.get(i1)).setPrefSize(dblWHXY[i1][0], dblWHXY[i1][1]);
						((Region)listNodesDados.get(i1)).setLayoutX(dblWHXY[i1][2]);
						((Region)listNodesDados.get(i1)).setLayoutY(dblWHXY[i1][3]);
						
						
						
			 		} // fim loop i1 for
			 	
			 	pCadastroDeDados.getChildren().addAll(listNodesDados);
			 	
				p1.getChildren().addAll(
						pCadastroDeDados
						 );
		
		
	}
	
	ArrayList<Node> listNodesEndereco = new ArrayList<Node>();
	
	Pane pEnderecoDaDemanda = new Pane();
	
	public void inicializarEnderecoRelacionado (){ // inicializar a mostra de endereco relacionado com a demanda
		
		pEnderecoDaDemanda.setPrefSize(342, 83);
		pEnderecoDaDemanda.setLayoutX(618);
		pEnderecoDaDemanda.setLayoutY(44);
		
		pEnderecoDaDemanda.getStyleClass().add("panes");
		
						listNodesEndereco.add(new Label("ENDERECO:")); // 0 a 4 - Labels
			/* 1 */		listNodesEndereco.add(new Label("Rua dos Jardins Floridos 10/13"));
						listNodesEndereco.add(new Label("REGIÃO ADMINISTRATIVA:"));
			/* 2 */		listNodesEndereco.add(new Label("São Sebastião"));
						listNodesEndereco.add(new Label("LAT:"));
			/* 5 */		listNodesEndereco.add(new Label("-15.123456789"));
						listNodesEndereco.add(new Label("LON:"));
			/* 7 */		listNodesEndereco.add(new Label("-47.123456789"));	
	 	
			 	
			 	Double[][] prefSizeWHeLayXY  = {
				
						/* labels */	{75.0, 17.0, 11.0, 10.0}	, 
										{235.0, 17.0, 96.0, 10.0}	,
										{155.0, 17.0, 11.0, 31.0}	,
										{155.0, 17.0, 176.0, 31.0}	,
										
										{35.0, 17.0, 11.0, 55.0}	, 
										{120.0, 17.0, 48.0, 55.0}	,
										{35.0, 17.0, 173.0, 55.0}	,
										{120.0, 17.0, 211.0, 55.0}	,
								
				};
			 	
			 	for (int i1 = 0; i1< prefSizeWHeLayXY .length ; i1++) {
					
			 			Double dblWHXY [][] = new Double[11][4];
					
						for (int i2 = 0; i2 < prefSizeWHeLayXY [i1].length ; i2++) {
							
							dblWHXY[i1][i2] = prefSizeWHeLayXY [i1][i2];
							
						} // fim loop i2

						((Region)listNodesEndereco.get(i1)).setPrefSize(dblWHXY[i1][0], dblWHXY[i1][1]);
						((Region)listNodesEndereco.get(i1)).setLayoutX(dblWHXY[i1][2]);
						((Region)listNodesEndereco.get(i1)).setLayoutY(dblWHXY[i1][3]);
						
						
						
			 		} // fim loop i1 for
			 	
			 	pEnderecoDaDemanda.getChildren().addAll(listNodesEndereco);
			 	
				p1.getChildren().addAll(
						pEnderecoDaDemanda
						 );
		
		
	}

	ArrayList<Node> listNodesPersistencia = new ArrayList<Node>();
	
	Pane pPersisten = new Pane();
	
	public void inicializarPersistencia (){
		
		pPersisten.setPrefSize(790, 60);
		pPersisten.setLayoutX(95);
		pPersisten.setLayoutY(175);
		
		pPersisten.getStyleClass().add("panes");
		
		 	listNodesPersistencia.add(new Button("NOVO")); 
		 	listNodesPersistencia.add(new Button("SALVAR"));
		 	listNodesPersistencia.add(new Button("EDITAR"));
		 	listNodesPersistencia.add(new Button("EXCLUIR"));
		 	listNodesPersistencia.add(new Button("CANCELAR"));
		 	
		 	listNodesPersistencia.add(new TextField());
		 	
		 	listNodesPersistencia.add(new Button("PESQUISAR"));
		 
		 	
			 	Double[][] prefSizeWHeLayXY  = {

						{85.0, 25.0, 17.0, 18.0}	, 
						{85.0, 25.0, 111.0, 18.0}	,
						{85.0, 25.0, 207.0, 18.0}	,
						{85.0, 25.0, 302.0, 18.0}	,
						{85.0, 25.0, 397.0, 18.0}	,
						
						{195.0, 25.0, 493.0, 17.0}	,
						
						{85.0, 25.0, 699.0, 18.0}	,
					
				};
			 	
			 	for (int i1 = 0; i1< prefSizeWHeLayXY .length ; i1++) {
					
			 			Double dblWHXY [][] = new Double[11][4];
					
						for (int i2 = 0; i2 < prefSizeWHeLayXY [i1].length ; i2++) {
							
							dblWHXY[i1][i2] = prefSizeWHeLayXY [i1][i2];
							
						} // fim loop i2

						((Region)listNodesPersistencia.get(i1)).setPrefSize(dblWHXY[i1][0], dblWHXY[i1][1]);
						((Region)listNodesPersistencia.get(i1)).setLayoutX(dblWHXY[i1][2]);
						((Region)listNodesPersistencia.get(i1)).setLayoutY(dblWHXY[i1][3]);
						
						
						
			 		} // fim loop i1 for
			 	
			 	pPersisten.getChildren().addAll(listNodesPersistencia);
			 	
				p1.getChildren().addAll(
						pPersisten
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

