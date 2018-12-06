package controladores.fiscalizacao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controladores.principal.ControladorPrincipal;
import dao.EnderecoDao;
import entidades.Demanda;
import entidades.Endereco;
import entidades.RA;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import mapas.MapEvent;
import principal.Alerta;
import principal.FormatoData;


public class TabEnderecoControlador implements Initializable {
	
	static Demanda demanda = new Demanda ();
	
	public void setDemanda (Demanda demanda) {
		
		TabEnderecoControlador.demanda = demanda;
		
	}
	
	public static Demanda getDemanda () {
		
		return demanda;
		
	}
	
	@FXML public Label lblDoc = new Label(); // publico para receber valor do MainController, metodo pegarDoc()
	@FXML Pane tabEndereco;
	@FXML Button btnBuscarDoc;
	@FXML TextField tfEnd;
	
	
	@FXML TextField tfEndCep;
	@FXML TextField  tfEndCid;
	
	
	@FXML TextField tfLinkEnd;
	@FXML TextField tfEndLat;
	@FXML TextField tfEndLon;
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	@FXML Button btnEndCoord;
	@FXML Pane pEndMap;
	@FXML Pane pEndereco;
	
	@FXML Button btnEndMaps;
	
	@FXML Label lblDataAtualizacao;
	
	
	@FXML Button btnDenAtualizar;
	@FXML TextField tfEndPesq;
	
	@FXML Button btnEndLatLon;
	
	@FXML Pane pEndCoord;
	
	//-- TableView endereco --//
	@FXML private TableView <Endereco> tvLista;
	
	@FXML TableColumn<Endereco, String> tcDesEnd;
	@FXML TableColumn<Endereco, String> tcEndRA;
	@FXML TableColumn<Endereco, String> tcEndCid;
	
	@FXML TextField tfPesquisar;
	
	//-- combobox -Regiao Administrativa --//	
	
	//List <String> listaRA = new ArrayList<> ();
	
	int intRA = 1;
	String strRA = "Plano Piloto";
	
	final int [] listaRA = new int [] {
			
			20	,
			4	,
			19	,
			9	,
			11	,
			31	,
			2	,
			10	,
			28	,
			27	,
			18	,
			16	,
			8	,
			7	,
			24	,
			6	,
			1	,
			15	,
			17	,
			21	,
			12	,
			13	,
			14	,
			25	,
			29	,
			5	,
			26	,
			22	,
			3	,
			23	,
			30	,

	};
	
	@FXML
	ChoiceBox<String> cbListaDemandas = new ChoiceBox<String>();
	
	ObservableList<String> olListaDemandas;
	
	@FXML
	ChoiceBox<String> cbEndRA = new ChoiceBox<String>();
	
	ObservableList<String> olEndRA = FXCollections
	.observableArrayList(
			
			"Águas Claras"	,
			"Brazlândia"	,
			"Candangolândia"	,
			"Ceilândia"	,
			"Cruzeiro"	,
			"Fercal"	,
			"Gama"	,
			"Guará"	,
			"Itapoã"	,
			"Jardim Botânico"	,
			"Lago Norte"	,
			"Lago Sul"	,
			"Núcleo Bandeirante"	,
			"Paranoá"	,
			"Park Way"	,
			"Planaltina"	,
			"Plano Piloto"	,
			"Recanto das Emas"	,
			"Riacho Fundo"	,
			"Riacho Fundo II"	,
			"Samambaia"	,
			"Santa Maria"	,
			"São Sebastião"	,
			"SCIA/Estrutural"	,
			"SIA"	,
			"Sobradinho"	,
			"Sobradinho II"	,
			"Sudoeste/Octogonal"	,
			"Taguatinga"	,
			"Varjão"	,
			"Vicente Pires"	


			); 	
		
		//-- combobox - unidade federal --//
		@FXML
		ChoiceBox<String> cbEndUF = new ChoiceBox<String>();
			ObservableList<String> olEndUF = FXCollections
				.observableArrayList("DF" , "GO", "Outro");

		
		@FXML Label lblEndereco = new Label();
		
		//-- string para chamar as coordenadas corretas do mapa --//
		String strHTMLMap;
		
		
		//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
		ObservableList<Endereco> obsList;
		
	public void btnNovoHab (ActionEvent event) {
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfEndCep.setText("");
		tfEndCid.setText("Brasília");
		
		cbEndUF.setValue("DF");
		
		tfLinkEnd.setText("");
		tfEndLat.setText("");
		tfEndLon.setText("");
		
		
		tfEnd.setDisable(false);
		cbEndRA.setDisable(false);
		
		
		tfEndCep.setDisable(false);
		tfEndCid.setDisable(false);
		cbEndUF.setDisable(false);
		tfEndLat.setDisable(false);
		tfEndLon.setDisable(false);
		tfLinkEnd.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
		obsList = FXCollections.observableArrayList();
		
		if (tfEndLat.getText().isEmpty() || 
				tfEndLon.getText().isEmpty()) {
			
			/*
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Coordenadas inválidas!!!");
			a.setHeaderText(null);
			a.show();
			*/
			
			Alerta a = new Alerta();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
		} 
		
		/*
		else if (dGeralEnd == null) {
			
			Alert aLat = new Alert (Alert.AlertType.ERROR);
			aLat.setTitle("Alerta!!!");
			aLat.setContentText("Documento relacionado não selecionado!!!");
			aLat.setHeaderText(null);
			aLat.show();
		}*/
		
		
			else {
			
			
				if (tfEnd.getText().isEmpty()) {
					
					Alert a = new Alert (Alert.AlertType.ERROR);
					a.setTitle("Alerta!!!");
					a.setContentText("Informe: Endereço do Empreendimento!!!");
					a.setHeaderText(null);
					a.show();
					
				} else {
					
						RA ra = new RA ();
						ra.setRaID(intRA);
						ra.setRaNome(strRA);
						
						
						System.out.println("id da ra a salvar " + ra.getRaID());
						
						Endereco endereco = new Endereco();
							
							endereco.setEndLogadouro(tfEnd.getText());
							endereco.setEndRA(ra);
							
							endereco.setEndCEP(tfEndCep.getText());
							endereco.setEndCidade(tfEndCid.getText());
							endereco.setEndUF(cbEndUF.getValue());
							
							try {
								
							
							endereco.setEndLatitude(Double.parseDouble(tfEndLat.getText()));
							endereco.setEndLongitude(Double.parseDouble(tfEndLon.getText()));
										
										
										Demanda dem = new Demanda ();
										
											dem = TabEnderecoControlador.getDemanda();
											dem.setDemEnderecoFK(endereco);
											endereco.getDemandas().add(dem);
										
										EnderecoDao enderecoDao = new EnderecoDao();
									
											//enderecoDao.salvarEndereco(endereco); 
											enderecoDao.mergeEndereco(endereco);
										
										
										//-- modular botoes--//
										modularBotoesInicial ();
										
										obsList.remove(endereco);
										obsList.add(endereco);
										
										selecionarEndereco();
										
										modularBotoesInicial();
										
										Alert a = new Alert (Alert.AlertType.INFORMATION);
										a.setTitle("Parabéns!!!");
										a.setContentText("Cadastro salvo com sucesso!!!");
										a.setHeaderText(null);
										a.show();
								} 
							
								catch (Exception e) {
									
									Alert a = new Alert (Alert.AlertType.ERROR);
									a.setTitle("Atenção!!!");
									a.setContentText("erro ao salvar!!!" + "[ " + e + " ]");
									a.setHeaderText(null);
									a.show();
									
									e.printStackTrace();
								}
				}
			
		}
			
	}
	
	public void btnEditarHab (ActionEvent event) {
		
		if (tfEnd.isDisable()) {
			
			tfEnd.setDisable(false);
			cbEndRA.setDisable(false);
			tfEndCep.setDisable(false);
			tfEndCid.setDisable(false);
			cbEndUF.setDisable(false);
			tfEndLat.setDisable(false);
			tfEndLon.setDisable(false);
			tfLinkEnd.setDisable(false);
				
		} else {
			
			if (tfEndLat.getText().isEmpty()|| tfEndLon.getText().isEmpty() ) {
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Coordenadas inválidas!!!");
				a.setHeaderText(null);
				a.show();
				
			} 
			
			else if (demanda == null) {
				
				Alert aLat = new Alert (Alert.AlertType.ERROR);
				aLat.setTitle("Alerta!!!");
				aLat.setContentText("Documento relacionado não selecionado!!!");
				aLat.setHeaderText(null);
				aLat.show();
			}
			
			else {
					RA ra = new RA ();
					ra.setRaID(intRA);
					ra.setRaNome(strRA);
	
					
					Endereco end = tvLista.getSelectionModel().getSelectedItem();
					
					end.setEndLogadouro(tfEnd.getText());
					end.setEndRA(ra);
					end.setEndCEP(tfEndCep.getText());
					end.setEndCidade(tfEndCid.getText());
					end.setEndUF(cbEndUF.getValue());
					end.setEndLatitude(Double.parseDouble(tfEndLat.getText()));
					end.setEndLongitude(Double.parseDouble(tfEndLon.getText()));
					
					Demanda dem = new Demanda();
					
					dem = TabEnderecoControlador.getDemanda();
					dem.setDemEnderecoFK(end);
					end.getDemandas().add(TabEnderecoControlador.getDemanda()); /// ?????????não entendi
					
					EnderecoDao enderecoDao = new EnderecoDao();
				
					enderecoDao.mergeEndereco(end);
					
					// pegar o valor, levar para o MainController  e depois para o label lblEnd no InterfController
					//eGeral = endereco;
					//main.pegarEnd(eGeral);
					
					// atualizar dados na tabela
					obsList.remove(end);
						
					obsList.add(end);
					
					modularBotoesInicial (); 
					
					Alert a = new Alert (Alert.AlertType.INFORMATION);
					a.setTitle("Parabéns!!!");
					a.setContentText("Cadastro editado com sucesso!!!");
					a.setHeaderText(null);
					a.show();
					
			}
		
		}	
		
	}
	public void btnExcluirHab (ActionEvent event) {
		
		Endereco end = tvLista.getSelectionModel().getSelectedItem();
		
		int id = end.getEndID();
		
		EnderecoDao endDao = new EnderecoDao();
		
			try {
				
				endDao.removerEndereco(id);
				
				obsList.remove(end);
				
				modularBotoesInicial();
				
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Parabéns!!!");
				a.setContentText("Cadastro deletado com sucesso!!!");
				a.setHeaderText(null);
				a.show();
		
					}
		
					catch (Exception e) {
						//-- Alerta de demandas salva --//
						Alert a = new Alert (Alert.AlertType.ERROR);
						a.setTitle("Alerta!!!");
						a.setContentText("Há denúncia associada a este endereço!" + "[ " + e + " ]");
						a.setHeaderText(null);
						a.show();
						
					}
			
	}
	
	public void btnCancelarHab (ActionEvent event) {
		
		modularBotoesInicial ();
		
		tfEnd.setText("");
		
		cbEndRA.setValue(null);
		
		tfEndCep.setText("");
		
		cbEndUF.setValue(null);
		
		tfLinkEnd.setText("");
		tfEndLat.setText("");
		tfEndLon.setText("");
		
	}

	public void btnPesquisarHab (ActionEvent event) {
		
		
		strPesquisa = tfPesquisar.getText();
		
		listarEnderecos (strPesquisa);
		
		selecionarEndereco () ;
		
		modularBotoesInicial (); 
		
	}
	
	public void btnEndLatLonHab (ActionEvent event) {
		
	}
	
	public void btnEndMapsHab (ActionEvent event) {
			//ControladorPrincipal.capturarGoogleMaps ()
		
		
		
	}
	
	public void btnEndCoordHab (ActionEvent event) {
		
	}
	
	@FXML AnchorPane apPrincipal = new AnchorPane();
	@FXML AnchorPane apPrin1 = new AnchorPane();
	@FXML AnchorPane apPrin2 = new AnchorPane();
	@FXML BorderPane bpPrincipal = new BorderPane();
	@FXML ScrollBar sbPrincipal = new ScrollBar();
	
	// métodos de remimensionar as tabs //
			public void redimWid (Number newValue) {
					apPrincipal.setMinWidth((double) newValue);
						
					}
			public void redimHei (Number newValue) {
					apPrincipal.setMinHeight((double) newValue);;
					}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

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
	    
	    // para trazer o valor da entidade principal, no caso Endereco
	    tcDesEnd.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endLogadouro")); // endLogadouro
		// para trazer valor de outra entidade, no caso RA
	    
		tcEndRA.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Endereco, String>, ObservableValue<String>>() {
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Endereco, String> e) {
		    	return new SimpleStringProperty(e.getValue().getEndRA().getRaNome());
		       
		    }
		});
		
		// para trazer o valor da entidade principal, no caso Endereco
		tcEndCid.setCellValueFactory(new PropertyValueFactory<Endereco, String>("endCEP")); // endCEP
	    
	    cbEndRA.setItems(olEndRA);
	    cbEndRA.setValue("Plano Piloto");
	    cbEndUF.setItems(olEndUF);
	    
	    cbEndRA.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		intRA = listaRA[(int) new_value];
	    		
            }
	    });
	    
	    cbEndRA.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    		
	    		strRA = (String) newValue );
	    
	    olListaDemandas = FXCollections.observableArrayList();
	    cbListaDemandas.setItems(olListaDemandas);
	    			
	}
	
	//-- Modular os botoes na inicializacao do programa --//
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		cbEndRA.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		cbEndUF.setDisable(true);
		tfEndLat.setDisable(true);
		tfEndLon.setDisable(true);
		tfLinkEnd.setDisable(true);
		tfLinkEnd.setText("");
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		
	}
	
	// --- metodo para listar endereco --- //
	 	public void listarEnderecos (String strPesquisa) {
	 		
	 	// --- conexao - listar enderecos --- //
		EnderecoDao enderecoDao = new EnderecoDao();
		List<Endereco> enderecoList = enderecoDao.listarEndereco(strPesquisa);
		obsList = FXCollections.observableArrayList();
		
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
		
				
		// funcionando
    	List<Endereco> iList = enderecoList;
    	
    	
    	for (Endereco e : iList) {
    		
    		e.getEndLogadouro();
    		e.getEndRA();
    		e.getEndCEP();
    		e.getEndCidade();
    		e.getEndUF();
    		e.getEndLatitude();
    		e.getEndLongitude();
    		
    		obsList.add(e);
    		
		}
			
		tvLista.setItems(obsList); 
		
	}
	
	// método selecionar endereço -- //
	 	public void selecionarEndereco () {
		
			tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Endereco end = (Endereco) newValue;
				
				if (end == null) {
					
					tfEnd.setText("");
					
					tfEndCep.setText("");
					tfEndCid.setText("");
					tfEndLat.setText("");
					tfEndLon.setText("");
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					tfEnd.setText(end.getEndLogadouro());
					
					cbEndRA.setValue(end.getEndRA().getRaNome()); 
					
					tfEndCep.setText(end.getEndCEP());
					tfEndCid.setText(end.getEndCidade());
					
					cbEndUF.setValue(end.getEndUF());
					
					tfEndLat.setText(end.getEndLatitude().toString());
					tfEndLon.setText(end.getEndLongitude().toString());
					
					// -- habilitar e desabilitar botoes -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					if (end.getDemandas().size() != 0) { // colocar regra de só pode enditar escolhendo uma demanda...
						demanda = end.getDemandas().get(0);
					}
					
					FormatoData d = new FormatoData();
					
					// mostrar data de atualizacao //
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(end.getEndAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
					
					olListaDemandas.clear();
					
					for (int i = 0; i<end.getDemandas().size(); i++) {
						
						if (end.getDemandas().get(i).getDemID() != 0) {
							
							olListaDemandas.add(end.getDemandas().get(i).getDemDocumento() 
									+ "     Sei nº " + end.getDemandas().get(i).getDemDocumentoSEI()
									+ "     Processo nº " + end.getDemandas().get(i).getDemProcessoSEI()
									
									);
						}
						
					}
					
					try {
						
					cbListaDemandas.setValue(
							end.getDemandas().get(0).getDemDocumento() 
							+ "     Sei nº " + end.getDemandas().get(0).getDemDocumentoSEI()
							+ "     Processo nº " + end.getDemandas().get(0).getDemProcessoSEI()
							);
					}
					catch (Exception e) {
						// não está funcionando. mostrar na choicebox essa mensagem
						cbListaDemandas.setValue("não há demanda relacionada");
						System.out.println("catch - não há demanda relacionada com o endereço");
					}
					
					//eGeral = new Endereco(endTab);
					
					//main.pegarEnd(eGeral);
					
					
					Double lat = Double.parseDouble(tfEndLat.getText());
					Double  lng = Double.parseDouble(tfEndLon.getText() );
					
					/*
					if (wv1 == null) {
						
						String strMarcador = "" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);";
						
						abrirMapa(strMarcador);
						
					} else
					{
						webEng.executeScript("" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);"
		                    );
				
					}
					*/
					//System.out.println("demanda selecionada " + demanda.getDemDocumento());
					
				}
				
				}
				
			});
			
			
		}
	 	
	 	

}
