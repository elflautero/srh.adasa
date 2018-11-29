package controladores.fiscalizacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.EnderecoDao;
import entidades.Demanda;
import entidades.Endereco;
import entidades.RA;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class TabEnderecoControlador implements Initializable {
	
	static Demanda demanda = new Demanda ();
	
	public void setDemanda (Demanda demanda) {
		
		TabEnderecoControlador.demanda = demanda;
		
		lblDoc.setText(demanda.getDemDocumento().toString()); // não está funcionando

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
	
	List <String> listaRA = new ArrayList<> ();
	
	
	@FXML
	ChoiceBox<String> cbEndRA = new ChoiceBox<String>();
		ObservableList<String> olEndRA = FXCollections
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
			
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Coordenadas inválidas!!!");
			a.setHeaderText(null);
			a.show();
			
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
					
						RA objetoRA = new RA ();
						objetoRA.setRaID(1);
						
						Endereco endereco = new Endereco();
							
							endereco.setEndLogadouro(tfEnd.getText());
							endereco.setEndRA(objetoRA);
							
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
											enderecoDao.mergeEnd(endereco);
										
										
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
		
	}
	public void btnExcluirHab (ActionEvent event) {
		
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
	    
	    
	}
	
	//-- Modular os botoes na inicializacao do programa --//
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		cbEndRA.setDisable(true);  //tfEndRA.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		cbEndUF.setDisable(true);  //tfEndUF.setDisable(true);
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
					//tfEndUF.setText("");
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
					cbEndRA.setValue(end.getEndRA().toString()); 
					tfEndCep.setText(end.getEndCEP());
					tfEndCid.setText(end.getEndCidade());
					cbEndUF.setValue(end.getEndUF());  //tfEndUF.setText(endTab.getUF_Endereco());
					tfEndLat.setText(end.getEndLatitude().toString());
					tfEndLon.setText(end.getEndLongitude().toString());
					
					// -- habilitar e desabilitar botoes -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
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
				}
				}
			});
		}
	 	
	 	

}
