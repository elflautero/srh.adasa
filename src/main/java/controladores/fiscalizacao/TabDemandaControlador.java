package controladores.fiscalizacao;

import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import principal.FormatoData;


public class TabDemandaControlador implements Initializable {
	
	// transmitir para os outros controladores o objeto do crud //
	TabEnderecoControlador tabEndCon = new TabEnderecoControlador();
	EditarEnderecoControlador edEndCon = new EditarEnderecoControlador();
	
	// --- String para primeira pesquisa --- //
	String strPesquisa = "";
	
	@FXML TextField tfDocumento;
	@FXML TextField tfDocSei;
	@FXML TextField tfProcSei;
	@FXML TextField tfResDen;
	@FXML TextField tfPesquisar;
	
	// ----- Label - endereco da demanda ------ //
	@FXML Label lblDenEnd = new Label ();

	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	@FXML Button btnSair;
	
	@FXML Button btnEndDetalhes;

	@FXML DatePicker dpDataDistribuicao;
	@FXML DatePicker dpDataRecebimento;
	
	// -- Tabela --  //
	@FXML private TableView <Demanda> tvLista;
	
	// -- Colunas -- //
	@FXML private TableColumn<Demanda, String> tcDocumento;
	@FXML private TableColumn<Demanda, String> tcDocSEI;
	@FXML private TableColumn<Demanda, String> tcProcSEI;
	
	@FXML DatePicker dpDoc;
	
	public void btnNovoHabilitar (ActionEvent event) {
		
		tfDocumento.setText("");
		tfDocSei.setText("");
		tfProcSei.setText("");
		
		dpDataDistribuicao.getEditor().clear();
		dpDataRecebimento.getEditor().clear();
		
		tfResDen.setText("");
		
		dpDataDistribuicao.setDisable(false);
		dpDataRecebimento.setDisable(false);
		
		tfDocumento.setDisable(false);
		tfDocSei.setDisable(false);
		tfProcSei.setDisable(false);
		tfResDen.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(true);
		
	}
	
	// -- botão salvar -- //
	public void btnSalvarHabilitar (ActionEvent event) {
		
        obsList = FXCollections.observableArrayList();
        
		try { // filtro para não salvar documento sem numero de documento ou processo
			
			if (tfDocSei.getText().isEmpty()  ||
				tfProcSei.getText().isEmpty()	) 
			{
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Informe: Documento, Processo SEI!!!");
				a.setHeaderText(null);
				a.show();
				
			} else {
			
					Demanda demanda = new Demanda();
					
					demanda.setDemDocumento(tfDocumento.getText()); 
					
					demanda.setDemDocumentoSEI(tfDocSei.getText()); 
					demanda.setDemProcessoSEI(tfProcSei.getText());
					
					if (dpDataDistribuicao.getValue() == null) {
						
						demanda.setDemDistribuicao(null);}
					else {
						demanda.setDemDistribuicao(dpDataDistribuicao.getValue());
						
						}
						
						if (dpDataRecebimento.getValue() == null) {
							
						demanda.setDemRecebimento(null);}
						
							else {
								demanda.setDemRecebimento(dpDataRecebimento.getValue());
								}
					
					demanda.setDemDescricao(tfResDen.getText());
					
					demanda.setDemAtualizacao((LocalDateTime.now()));
					
					DemandaDao dao = new DemandaDao();
					
					dao.salvarDemanda(demanda);
					
					tabEndCon.setDemanda(demanda);
					edEndCon.setDemanda(demanda);
					
					// pegar o valor, levar para o MainController  e depois para o label lblDoc no EnderecoController
					//dGeral = demanda;
					//main.pegarDoc(dGeral);
						
					obsList.add(demanda);
					
					tvLista.setItems(obsList);
						
					selecionarDemanda();
				
					modularBotoesInicial ();
					
					//-- Alerta de denúncia salva --//
					Alert a = new Alert (Alert.AlertType.INFORMATION);
					a.setTitle("Parabéns!!!");
					a.setContentText("Cadastro salvo com sucesso!!!");
					a.setHeaderText(null);
					a.show();
					
					}
			
		} catch (Exception ex) {
			
			System.out.println("Erro: " + ex);
			ex.printStackTrace();
			
			//-- Alerta de denúncia salva --//
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("erro na conexão, tente novamente!");
			a.setHeaderText(null);
			a.show();
		}
		
	}
	
	// -- botão editar -- //
	public void btnEditarHabilitar (ActionEvent event) {
		
		if (tfDocumento.isDisable()) { // filtro para abrir caixas para edição
			
			tfDocumento.setDisable(false);
			tfDocumento.setDisable(false);
			tfDocSei.setDisable(false);
			tfProcSei.setDisable(false);
			
			dpDataDistribuicao.setDisable(false);
			dpDataRecebimento.setDisable(false);
			
			tfResDen.setDisable(false);
			
		} else {
			
			if (tfDocSei.getText().isEmpty() ||  // filtro para não editar sem informacoes nas caixas
					tfProcSei.getText().isEmpty()) 
				{
					
					Alert a = new Alert (Alert.AlertType.ERROR);
					a.setTitle("Alerta!!!");
					a.setContentText("Informe: Documento, Processo SEI!!!");
					a.setHeaderText(null);
					a.show();
					
				} else {
			
						Demanda demanda = tvLista.getSelectionModel().getSelectedItem();
						
						demanda.setDemDocumento(tfDocumento.getText());
						demanda.setDemDocumentoSEI(tfDocSei.getText());
						demanda.setDemProcessoSEI(tfProcSei.getText());
						
						
						if (dpDataDistribuicao.getValue() == null) {
							demanda.setDemDistribuicao(null);}
							else {
								
								demanda.setDemDistribuicao(dpDataDistribuicao.getValue());
								
								}
											
						if (dpDataRecebimento.getValue() == null) {
							demanda.setDemRecebimento(null);}
							else {
								demanda.setDemRecebimento(dpDataRecebimento.getValue());
								}
						
						demanda.setDemAtualizacao((LocalDateTime.now()));
								
						demanda.setDemDescricao(tfResDen.getText());
						 
						DemandaDao dDao = new DemandaDao();
						
						dDao.mergeDemanda(demanda);
						
						// atualizar os dados na tabela
						obsList.remove(demanda);
						
						obsList.add(demanda);
						
						// pegar o valor, levar para o MainController  e depois para o label lblDoc no EnderecoController
						
						tabEndCon.setDemanda(demanda);
						edEndCon.setDemanda(demanda);
						
							//dGeral = demanda;
							//main.pegarDoc(dGeral);
						
						// para trazer o resultado por id (do maior para o menor) //
							//Comparator<Demanda> comparar = Comparator.comparing(Demanda::getDemID); //getDemID
							//obsList.sort(comparar.reversed());
							
						selecionarDemanda();
						
						modularBotoesInicial ();
						
						Alert a = new Alert (Alert.AlertType.INFORMATION);
						a.setTitle("Parabéns!!!");
						a.setContentText("Cadastro editado com sucesso!!!");
						a.setHeaderText(null);
						a.show();
				}
				
			}
	}
	
	// -- botão excluir -- //
	public void btnExcluirHabilitar (ActionEvent event) {
	
		try {
			
			Demanda demanda = tvLista.getSelectionModel().getSelectedItem();
			
			int id = demanda.getDemID(); // buscar id para deletar
			
			DemandaDao dDao = new DemandaDao();
			
			dDao.removerDemanda(id);
			
			obsList.remove(demanda);
			
			selecionarDemanda();
			
			modularBotoesInicial (); 
			
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Parabéns!!!");
				a.setContentText("Cadastro excluído com sucesso!!!");
				a.setHeaderText(null);
				a.show();
		
			}
		
			catch (Exception e) {
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Erro ao excluir o cadastro!!!");
				a.setHeaderText(e.toString());
				a.show();
			}
				
	}
	
	// -- botão cancelar -- //
	public void btnCancelarHabilitar (ActionEvent event) {
			
		modularBotoesInicial();
	}
	
	// -- botão pesquisar denúncia -- //
	public void btnPesquisarHabilitar (ActionEvent event) {
		
		strPesquisa = (String) tfPesquisar.getText();
		
		listarDemandas(strPesquisa);
		selecionarDemanda();
		
		modularBotoesInicial (); 
		
	}
	
	
		@FXML ScrollPane spDemanda = new ScrollPane ();
		@FXML Pane pDemanda = new Pane ();
		@FXML AnchorPane apSBInterno = new AnchorPane();
		@FXML Pane psbInterno = new Pane();
		
		@FXML Label lblDataAtualizacao = new Label();
		
		
		@FXML AnchorPane apPrincipal = new AnchorPane();
		@FXML AnchorPane apPrin1 = new AnchorPane();
		@FXML AnchorPane apPrin2 = new AnchorPane();
		@FXML BorderPane bpPrincipal = new BorderPane();
		@FXML ScrollBar sbPrincipal = new ScrollBar();

	public void initialize(URL url, ResourceBundle rb) {
		
		AnchorPane.setTopAnchor(apPrin1, 0.0);
	    AnchorPane.setLeftAnchor(apPrin1, 0.0);
		AnchorPane.setRightAnchor(apPrin1, 0.0);
	    AnchorPane.setBottomAnchor(apPrin1, 0.0);
	
	    AnchorPane.setLeftAnchor(apPrin2, 0.0);
		AnchorPane.setRightAnchor(apPrin2, 0.0);
		
		AnchorPane.setTopAnchor(sbPrincipal, 0.0);
		AnchorPane.setBottomAnchor(sbPrincipal, 2.0);
		AnchorPane.setRightAnchor(sbPrincipal, 0.0);
		
		AnchorPane.setTopAnchor(bpPrincipal, 0.0);
	    AnchorPane.setLeftAnchor(bpPrincipal, 0.0);
		AnchorPane.setRightAnchor(bpPrincipal, 0.0);
	    AnchorPane.setBottomAnchor(bpPrincipal, 0.0);
	    
	    // para rolar a tab //
	    sbPrincipal.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	apPrin2.setLayoutY(-new_val.doubleValue());
            }
        });
		
		// para concluir pesquisa com a tecla do enter
		tfPesquisar.setOnKeyReleased(event -> {
	  		  if (event.getCode() == KeyCode.ENTER){
	  		     btnPesquisar.fire();
	  		  }
	  		});
		
		// --- habilitar e desabilitar botões ---- //
				modularBotoesInicial();
		
		
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumento"));
		tcDocSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumentoSEI"));
		tcProcSEI.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demProcessoSEI")); 
		
		selecionarDemanda ();
		
	}
	
	ObservableList<Demanda> obsList;

	public void listarDemandas(String strPesquisa) {
		
		// -- Conexão e pesquisa de den�ncias -- //
		DemandaDao demandaDao = new DemandaDao();	//passar classe
		List<Demanda> demandaList = demandaDao.listarDemandas(strPesquisa); //passar string de pesquisar
		obsList = FXCollections.observableArrayList(); //chamar observable list e outra classe

		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
		
		// funcionando
    	List<Demanda> iList = demandaList;
    	
    	
    	for (Demanda d : iList) {
    		
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
	
	// -- selecionar denúncia -- //
	public void selecionarDemanda () {
		
		// TableView - selecionar denúncia ao clicar //
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			Demanda demanda = (Demanda) newValue;
			
			if (demanda == null) {
				
				tfDocumento.setText("");
				tfDocSei.setText("");
				tfProcSei.setText("");
				
				dpDataRecebimento.getEditor().clear();
				dpDataDistribuicao.getEditor().clear();
				
				tfResDen.setText("");
				
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
	 					dpDataDistribuicao.setValue(demanda.getDemDistribuicao());
	 					
	 				}
				
			
				if (demanda.getDemRecebimento() == null) {
					dpDataRecebimento.setValue(null);
	 				} else {
	 					dpDataRecebimento.setValue(demanda.getDemRecebimento());
	 					
	 				}
	 				
				tfResDen.setText(demanda.getDemDescricao());
				
				
				// endereço relacionado //
				if (demanda.getDemEnderecoFK() != null) {
					lblDenEnd.setText(demanda.getDemEnderecoFK().getEndLogadouro() 
							+ ",  Região Administrativa: " + demanda.getDemEnderecoFK().getEndRAFK().getRaNome()
							+ ",  Cep: " + demanda.getDemEnderecoFK().getEndCEP()
							+ ",  Cidade: " + demanda.getDemEnderecoFK().getEndCidade()
							+ ",  UF: " + demanda.getDemEnderecoFK().getEndUF()
							);
					 		/*
							ControladorPrincipal.capturarGoogleMaps().setMarkerPosition(
									demanda.getDemEnderecoFK().getEndLatitude() + 1,
									demanda.getDemEnderecoFK().getEndLongitude());
							
							ControladorPrincipal.capturarGoogleMaps().setMapCenter(
									demanda.getDemEnderecoFK().getEndLatitude(),
									demanda.getDemEnderecoFK().getEndLongitude()
									);
							
							apPrincipal.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
							*/
					
					lblDenEnd.setTextFill(Color.BLACK);
				} else {
					lblDenEnd.setText("Sem endereço cadastrado!");
					lblDenEnd.setTextFill(Color.RED);	
				}
				
				FormatoData d = new FormatoData();
				
				// mostrar data de atualizacao //
				try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(demanda.getDemAtualizacao()));
						lblDataAtualizacao.setTextFill(Color.BLACK);
				}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
						lblDataAtualizacao.setTextFill(Color.RED);}
				
				//Levar a demanda para cadastrar o endereco //
				tabEndCon.setDemanda(demanda);
				edEndCon.setDemanda(demanda);
				
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
	
	public void btnEndDetalhesHabilitar (ActionEvent event) {
		
			Pane pEndereco = new Pane();
			edEndCon = new EditarEnderecoControlador();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/EditarEndereco.fxml"));
			loader.setRoot(pEndereco);
			loader.setController(edEndCon);
			
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
			
			tfResDen.setDisable(true);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			btnNovo.setDisable(false);
			
	}
	
}

	/*
		btnLatLng.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    	//googleMaps = new GoogleMap();
		    	//googleMaps.setMarkerPosition(Double.parseDouble(tfLat.getText()), Double.parseDouble(tfLng.getText()));
		    	//googleMaps.setMapCenter(Double.parseDouble(tfLat.getText()), Double.parseDouble(tfLng.getText()));
		    	
		    	ControladorPrincipal.capturarGoogleMaps().setMarkerPosition(-15.0,-47.0);
		    	ControladorPrincipal.capturarGoogleMaps().setMapCenter(-15.0,-47.0);
		    	
		    	
		    }
		});
		
*/


/*
// métodos de remimensionar as tabs //
public void redimensionarLargura (Number newValue) {
		apDemanda.setMinWidth((double) newValue);
		System.out.println("método redim. largura chamado " + newValue);
		lblTeste.setText(  newValue.toString());
}
public void redimensionarAltura (Number newValue) {
		apDemanda.setMinHeight((double) newValue);
		System.out.println("método redim. altura chamado " + newValue);
}
*/


/*
 
  
		apDemanda.widthProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println("ap demanda " + newVal);
	    	
			
	    });
		
		apDemanda.heightProperty().addListener((obs, oldVal, newVal) -> {
			  
			 System.out.println("ap demanda altura" + newVal);
			 
			
	    });
	    
	    
		*/
  

/*
 @FXML private ControladorFiscalizacao conFisMain;

	public void init(ControladorFiscalizacao controladorFiscalizacao) {
		conFisMain = controladorFiscalizacao;
	}
	
	
	
	*/



/*
LatLng latLng = new LatLng(latitude, longitude);

//latLng.toUTMRef()

OSRef osRef = latLng.toOSRef();

double eastingRef = osRef.getEasting();
double northingRef = osRef.getNorthing();

UTMRef eUTM = latLng.toUTMRef();


double utmE = eUTM.getEasting();
double utmL = eUTM.getNorthing();



System.out.println( utmE + " e " + utmL);
*/


/*
apInterno.setOnMouseClicked(new EventHandler<MouseEvent>() {

    public void handle(MouseEvent e) {
    	
        System.out.println("clicou anchor pane");
        
        
        
        
		 
    }
    
});
*/
/*
apInterno.setOnScroll(new EventHandler<ScrollEvent>() {

	@Override
	public void handle(ScrollEvent event) {
		System.out.println("scroll event");
		System.out.println(event.getY());
		
		apInterno.setTranslateY(apInterno.getTranslateY() + 20);
		
	}

    });
*/

