package controladores.fiscalizacao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import principal.Alerta;


public class EditarEnderecoControlador implements Initializable {
	
	static Demanda demanda = new Demanda ();
	
	public void setDemanda (Demanda demanda) {
		
		EditarEnderecoControlador.demanda = demanda;
		//lblDoc.setText(demanda.getDemDocumento().toString()); // não está funcionando
		
	System.out.println("setDemanda  "
			+ "\n demanda ID: " + demanda.getDemID()
			);
	if (demanda.getDemEnderecoFK() == null) {
		System.out.println("setDemanda - endereço nulo");
	} else {
		System.out.println(" setDemanda endereco id " + demanda.getDemEnderecoFK().getEndID());
	}
		
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
		
		ObservableList<Endereco> obsList = FXCollections.observableArrayList();
		
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
		
		if (tfEndLat.getText().isEmpty() || 
				tfEndLon.getText().isEmpty()) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
			
			
		} 
		
		else if (demanda == null) {
			
			Alerta a = new Alerta ();
			a.alertar(new Alert(Alert.AlertType.ERROR, "Não há demanda selecionada!!!", ButtonType.OK));
			
		} 
		
			else {
			
			
				if (tfEnd.getText().isEmpty()) {
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.ERROR, "Informe o logadouro do empreendimento!!!", ButtonType.OK));
					
				} else {
					
						RA ra = new RA ();
						ra.setRaID(intRA);
						ra.setRaNome(strRA);
						
						Endereco end = new Endereco();
							
							end.setEndLogadouro(tfEnd.getText());
							end.setEndRA(ra);
								
							end.setEndCEP(tfEndCep.getText());
							end.setEndCidade(tfEndCid.getText());
							end.setEndUF(cbEndUF.getValue());
							
							try {
								
							
								end.setEndLatitude(Double.parseDouble(tfEndLat.getText()));
								end.setEndLongitude(Double.parseDouble(tfEndLon.getText()));
										
										
										Demanda dem = new Demanda ();
										
											dem = EditarEnderecoControlador.getDemanda();
											dem.setDemEnderecoFK(end);
											end.getDemandas().add(dem);
										
										EnderecoDao endDao = new EnderecoDao();
										
											endDao.salvarEndereco(end); //solução para recuperar o id do endereço
											endDao.mergeEndereco(end); // assim adiciona o id end na demanda dem
										
										System.out.println(
												
												"bntSalvar "
												+ "\n demanda salva id "  + dem.getDemID()
												+ "\n endereco salvo id " + end.getEndID()
												+ "\n endereco salvo na demanda - id " + demanda.getDemEnderecoFK().getEndID()
												);
										
										
										//-- modular botoes--//
										modularBotoesInicial ();
										
										obsList.remove(end);
										obsList.add(end);
										
										selecionarEndereco();
										
										modularBotoesInicial();
										

										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro salvo com sucesso!!!", ButtonType.OK));
										
								} 
							
								catch (Exception e) {
									
									Alerta a = new Alerta ();
									a.alertar(new Alert(Alert.AlertType.ERROR, "erro ao salvar!!!", ButtonType.OK));
									
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
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Coordenadas inválidas!!!", ButtonType.OK));
				// colocar para não aceitar texto e somente número
				
			} 
			
			else if (demanda == null) {
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR, "Não foi selecionado uma demanda!!!", ButtonType.OK));
			}
			
			else {
					RA ra = new RA ();
					ra.setRaID(intRA);
					ra.setRaNome(strRA);
	
					Endereco end = new Endereco ();
					
					end = tvLista.getSelectionModel().getSelectedItem();
						
					end.setEndLogadouro(tfEnd.getText());
					end.setEndRA(ra);
					end.setEndCEP(tfEndCep.getText());
					end.setEndCidade(tfEndCid.getText());
					end.setEndUF(cbEndUF.getValue());
					end.setEndLatitude(Double.parseDouble(tfEndLat.getText()));
					end.setEndLongitude(Double.parseDouble(tfEndLon.getText()));
					
					Demanda dem = new Demanda();
					
					
					if (demanda.equals(null) ) {
						dem = end.getDemandas().get(0);
						System.out.println(
								"bnt editar - demanda nula id " + dem.getDemID()
								);
						 
						
										}
					else {
						dem = demanda;
						System.out.println(
								"btn Editar demanda do método getDemanda" + dem.getDemID()
								);
					}
					
					dem.setDemEnderecoFK(end);
					end.getDemandas().remove(dem);
					
					for (int i = 0 ; i < end.getDemandas().size(); i++) {
						System.out.println("demanda size "+ end.getDemandas().size() + " removendo uma demanda " + end.getDemandas().get(i).getDemID());
					}
					
					end.getDemandas().add(dem);
					
					EnderecoDao enderecoDao = new EnderecoDao();
					
					for (int i = 0 ; i < end.getDemandas().size(); i++) {
						System.out.println("demandas listadas para editar" + end.getDemandas().get(i).getDemID());
					}
				
					enderecoDao.mergeEndereco(end);
					
					// atualizar dados na tabela
					if (obsList != null) {
						obsList.remove(end);
					}
					
					obsList.add(end);
					
					modularBotoesInicial (); 
					
					
					Alerta a = new Alerta ();
					a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!!!", ButtonType.OK));
					
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
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.INFORMATION, "Cadastro deletado com sucesso!!!", ButtonType.OK));
				
		
					}
		
					catch (Exception e) {
						
						// não entendi o porquê deste alerta
						
						/*
						Alert a = new Alert (Alert.AlertType.ERROR);
						a.setTitle("Alerta!!!");
						a.setContentText("Há denúncia associada a este endereço!" + "[ " + e + " ]");
						a.setHeaderText(null);
						a.show();
						*/
						
						Alerta a = new Alerta ();
						a.alertar(new Alert(Alert.AlertType.ERROR, "Há denúncia associada a este endereço!", ButtonType.OK));
						
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
			
	}
	
	public void btnEndCoordHab (ActionEvent event) {
		
	}
	
	@FXML AnchorPane apPrincipal = new AnchorPane();
	@FXML AnchorPane apPrin1 = new AnchorPane();
	@FXML AnchorPane apPrin2 = new AnchorPane();
	@FXML BorderPane bpPrincipal = new BorderPane();
	@FXML ScrollBar sbPrincipal = new ScrollBar();
	
	/*
	// métodos de remimensionar as tabs //
			public void redimWid (Number newValue) {
					apPrincipal.setMinWidth((double) newValue);
						
					}
			public void redimHei (Number newValue) {
					apPrincipal.setMinHeight((double) newValue);;
					}
					*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
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
	    
	    // capturar o numero da RA (Regiao Administrativa) selecionada
	    cbEndRA.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		intRA = listaRA[(int) new_value];
	    	
            }
	    });
	    
	    // capturar o nome da RA (Regiao Administrativa) selecionada
	    cbEndRA.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    		
	    		strRA = (String) newValue );
	    
	    // Se houver endereco, abrir ja preenchendo os campos //
	    try {
	    	
		    tfEnd.setText(demanda.getDemEnderecoFK().getEndLogadouro());
		    cbEndRA.setValue(demanda.getDemEnderecoFK().getEndRA().getRaNome());
		    tfEndCep.setText(demanda.getDemEnderecoFK().getEndCEP());
		    tfEndCid.setText(demanda.getDemEnderecoFK().getEndCidade());
		    cbEndUF.setValue(demanda.getDemEnderecoFK().getEndUF());
		    tfEndLat.setText(demanda.getDemEnderecoFK().getEndLatitude().toString());
		    tfEndLon.setText(demanda.getDemEnderecoFK().getEndLongitude().toString());
	    
	    } catch (Exception e ) {
	    	// Alert a = new Alert(Alert.AlertType.CONFIRMATION, title, ButtonType.OK, ButtonType.CANCEL); //
	    	Alerta a = new Alerta ();
	    	a.alertar(new Alert(Alert.AlertType.CONFIRMATION, "Cadastre um endereço para esta demanda!!!", ButtonType.OK));
	    }
	    
	    // ao abrir fechar os campos para edicao //
	    modularBotoesInicial();
	    			
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
		//obsList = FXCollections.observableArrayList();
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
			
		// funcionando
    	List<Endereco> iList = enderecoList;
    	
    	
    	for (Endereco e : iList) {
    		
    		e.getEndID();
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
					
					
					/*
					if (end.getDemandas().size() != 0) { // colocar regra de só pode enditar escolhendo uma demanda...
						demanda = end.getDemandas().get(0);
					}
					*/
					
					
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