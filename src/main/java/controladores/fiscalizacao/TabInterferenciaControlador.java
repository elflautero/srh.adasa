package controladores.fiscalizacao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

import dao.InterferenciaDao;
import entidades.BaciasHidrograficas;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.TipoInterferencia;
import entidades.UnidadeHidrografica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import principal.FormatoData;


public class TabInterferenciaControlador  implements Initializable{
	
 	@FXML private Pane tabInterferencia;
 	
 	TabSubterraneaController tabSubCon;
	TabSuperficialController tabSupCon;
 	
 	static Endereco endereco = new Endereco ();
 	
 	@FXML Pane p_lblEndereco;
 	@FXML Label lblEndereco1;
 	static Label lblEndereco2;
	
	public void setEndereco (Endereco end) {
		
		TabInterferenciaControlador.endereco = end;
		// preencher o label com a demanda selecionada //
		
		TabInterferenciaControlador.lblEndereco2.setText(
				
				endereco.getEndLogadouro()
				+ ", CEP n°: " + endereco.getEndCEP()
				+ ", Cidade: " + endereco.getEndCidade()
				
				);
			
	}
	
	public static Endereco getEndereco () {
		return endereco;
	}
	
	ObservableList<Interferencia> obsList = FXCollections.observableArrayList();
	
	int tipoCaptacao = 3;
	

	@FXML Button btnCapturar;
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	@FXML Button btnBuscarInt;
	
	//@FXML Label lblEnd = new  Label();
	@FXML public Label lblEnd; // publico para receber valor do MainController, metodo pegarEnd()
	
	//-- Tab interferencia latitude e longitude --//
	@FXML TextField tfIntLat;
	@FXML TextField tfIntLon;
	@FXML TextField tfPesquisar;
	@FXML TextField tfCorpoHid;
	//@FXML Image imgMap = new Image(TabEnderecoController.class.getResourceAsStream("/images/map.png")); 
	
	
	//-- chamar mapa --//
	@FXML Button btnIntMaps;
	
	
	@FXML Button btnEndCoord;
	//@FXML Image imgEndCoord = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));

	@FXML Button btnEndCoordMap = new Button();
	//@FXML Image imgEndCoordMap = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));
	
	//@FXML Image imgGetCoord = new Image(TabEnderecoController.class.getResourceAsStream("/images/getCoord.png")); 
	@FXML Button btnCoord = new Button ();

	//-- TableView Endereco --//
	@FXML private TableView <Interferencia> tvListaInt;
	
	@FXML TableColumn<Interferencia, String> tcDescEndInt;
	@FXML TableColumn<Interferencia, String> tcIntCorpoHidrico;
	@FXML TableColumn<Interferencia, String> tcIntUH;
	
	//-- combobox - tipo interferencia --//
	@FXML ChoiceBox<String> cbTipoCaptacao  = new ChoiceBox<String>();
			ObservableList<String> olTipoCaptacao; 
		
	@FXML ChoiceBox<String> cbBacia  = new ChoiceBox<String>();
			ObservableList<String> olBacia;
			
	@FXML ChoiceBox<String> cbUnidHid  = new ChoiceBox<String>();
	ObservableList<String> olUniHid; 
	
	@FXML Label lblDataAtualizacao;
												
	int tipoInterID = 1;
	String tipoInterDescricao = "Superficial";
	final int [] listaTipoInterID = new int [] { 1,2,3,4,5,6,7 };
	
	int baciaID = 1;
	//String baciaNome = "Rio Corumbá";
	final int [] listaBaciasID = new int [] { 1,2,3,4,5,6,7,8 };
	
	int unidHidID = 1;
	final int [] listaUHID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40,41,42 };
	
	TipoInterferencia tipoInterferencia;
	BaciasHidrograficas baciaHid;
	UnidadeHidrografica UniHid;												
		
	public void btnNovoHab (ActionEvent event) {
					
			cbTipoCaptacao.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			
			tfCorpoHid.setDisable(false);
			
			tfIntLat.setDisable(false);
			tfIntLon.setDisable(false);
			
			tfCorpoHid.setText(null);
			tfIntLat.setText("");
			tfIntLon.setText("");
			
			btnNovo.setDisable(true);
			btnSalvar.setDisable(false);
			
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			
			tfPesquisar.setDisable(false);
			
			btnPesquisar.setDisable(false);
			
			//-- choice box --//
			cbTipoCaptacao.setItems(olTipoCaptacao);
			cbBacia.setItems(olBacia);
					
	}

	
	public void btnSalvarHab (ActionEvent event) {
		
		tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterID);

		baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		if (tfIntLat.getText().isEmpty() || tfIntLon.getText().isEmpty()
				) { // ver de aceitar somente número 
			
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Coordenadas inválidas!!!");
			a.setHeaderText(null);
			a.show();
		}
		
			else if (endereco == null) { // colocar na tabela que não pode ser nulo o id do endereco
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Endereço relacionado não selecionado!!!");
				a.setHeaderText(null);
				a.show();
				
			
				} else {
				
						if (tipoInterID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoCaptacao() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null // ||
												//	tabSubCon.obterSubterranea().getSub_Sistema() == null
											) {
										
										Alert aLat = new Alert (Alert.AlertType.ERROR);
										aLat.setTitle("Alerta!!!");
										aLat.setContentText("Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!");
										aLat.setHeaderText(null);
										aLat.show();
										
										} else {
										
										Interferencia interferencia = new Interferencia();
										
											interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
											interferencia.setInterBaciaFK(baciaHid);
											interferencia.setInterUHFK(UniHid);
											
											interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
											
											interferencia.setInterLogadouro(endereco.getEndLogadouro());
											interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
											interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
											
											interferencia.setIntAtualizacao((LocalDateTime.now()));
										
											interferencia.setInterEnderecoFK(endereco);
											
											GeometryFactory geoFac = new GeometryFactory();
											
											interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
													Double.parseDouble(tfIntLon.getText()),
													Double.parseDouble(tfIntLat.getText())
													)));
											
										Subterranea sub = new Subterranea ();
										
											sub.setSubInterSubFK(interferencia);
											
											sub.setSubTipoCaptacao(tabSubCon.obterSubterranea().getSubTipoCaptacao());
											sub.setSubSubSistemaFK(tabSubCon.obterSubterranea().getSubSubSistemaFK());
											
											sub.setSubVazao(tabSubCon.obterSubterranea().getSubVazao());
											sub.setSubEstatico(tabSubCon.obterSubterranea().getSubEstatico());
											sub.setSubDinamico(tabSubCon.obterSubterranea().getSubDinamico());
											sub.setSubProfundidade(tabSubCon.obterSubterranea().getSubProfundidade());
											
											sub.setSubCaesb(tabSubCon.obterSubterranea().getSubCaesb());
											sub.setSubDataOperacao(tabSubCon.obterSubterranea().getSubDataOperacao());
											
											interferencia.setIntSubFK(sub);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											interferenciaDao.salvaInterferencia(interferencia);	
								
											obsList.remove(interferencia);
											obsList.add(interferencia);
											
											tvListaInt.setItems(obsList); 
											
										
										selecionarInterferencia ();
										
										modularBotoes ();
										
										//-- Alerta de endereco salvo --//
										Alert a = new Alert (Alert.AlertType.INFORMATION);
										a.setTitle("Parabéns!");
										a.setContentText("Interferência salva com sucesso!");
										a.setHeaderText(null);
										a.show();
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterID == 1 || tipoInterID == 3) {
							
								if (tabSupCon.obterSuperficial().getSupLocal() == null  ||
										tabSupCon.obterSuperficial().getSupArea() == null
										
										) {
									
									Alert aLat = new Alert (Alert.AlertType.ERROR);
									aLat.setTitle("Alerta!!!");
									aLat.setContentText("Informe o Local de Captação e se há Caesb!!!");
									aLat.setHeaderText(null);
									aLat.show();
									
									} else {
									
											Interferencia interferencia = new Interferencia();
											
												interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
												interferencia.setInterBaciaFK(baciaHid);
												interferencia.setInterUHFK(UniHid);
												
												interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
												
												interferencia.setInterLogadouro(endereco.getEndLogadouro());
												interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
												interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
												
												interferencia.setIntAtualizacao((LocalDateTime.now()));
											
												interferencia.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfIntLon.getText()),
														Double.parseDouble(tfIntLat.getText())
														)));
												
											Superficial sup = new Superficial ();
											
												sup.setSupInterFK(interferencia);
												
												sup.setSupLocal(tabSupCon.obterSuperficial().getSupLocal());
												sup.setSupCaptacao(tabSupCon.obterSuperficial().getSupCaptacao());
												sup.setSupBomba(tabSupCon.obterSuperficial().getSupBomba());
												sup.setSupPotencia(tabSupCon.obterSuperficial().getSupPotencia());
												sup.setSupTempo(tabSupCon.obterSuperficial().getSupTempo());
												sup.setSupArea(tabSupCon.obterSuperficial().getSupArea());
												sup.setSupCaesb(tabSupCon.obterSuperficial().getSupArea());
												
												sup.setSupDataOperacao(tabSupCon.obterSuperficial().getSupDataOperacao());
												
												interferencia.setIntSupFK(sup);
												
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											interferenciaDao.salvaInterferencia(interferencia);
											
											//interferencia.setIntSupFK(sup);
												
											obsList.remove(interferencia);
											obsList.add(interferencia);
											
												
											tvListaInt.setItems(obsList); 
											
											selecionarInterferencia ();
											
											modularBotoes ();
											
											//-- Alerta de endereco salvo --//
											Alert a = new Alert (Alert.AlertType.INFORMATION);
											a.setTitle("Parabéns!");
											a.setContentText("Interferência salva com sucesso!");
											a.setHeaderText(null);
											a.show();
											
											}
											
												
									} // fim superficial //
						
						else {
							
							Interferencia interferencia = new Interferencia();
							
							interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
							interferencia.setInterBaciaFK(baciaHid);
							interferencia.setInterUHFK(UniHid);
							
							interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
							
							interferencia.setInterLogadouro(endereco.getEndLogadouro());
							interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
							interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
							
							interferencia.setIntAtualizacao((LocalDateTime.now()));
						
							interferencia.setInterEnderecoFK(endereco);
							
							GeometryFactory geoFac = new GeometryFactory();
							
							interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfIntLon.getText()),
									Double.parseDouble(tfIntLat.getText())
									)));
							
						
							
							InterferenciaDao interferenciaDao = new InterferenciaDao ();
							interferenciaDao.salvaInterferencia(interferencia);
							
								obsList.remove(interferencia);
								obsList.add(interferencia);
								
									
								tvListaInt.setItems(obsList); 
								
								selecionarInterferencia ();
								
								modularBotoes ();
								
									//-- Alerta de endereco salvo --//
									Alert a = new Alert (Alert.AlertType.INFORMATION);
									a.setTitle("Parabéns!");
									a.setContentText("Interferência salva com sucesso!");
									a.setHeaderText(null);
									a.show();
							
							
						} // fim outras interferencias
						
				}
		
		}
	
	//-- botao editar --//
	public void btnEditarHab (ActionEvent event) {
		
		tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterID);

		baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		// ver excecao de querer editar sem esconlher o endereco da interferencia...
		
		
		// habilitar os campos para edição //
		if (cbTipoCaptacao.isDisable()) {
					
			cbTipoCaptacao.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			
			tfCorpoHid.setDisable(false);
			
			tfIntLat.setDisable(false);
			tfIntLon.setDisable(false);
			
					
		}
		
		else if (tfIntLat.getText().isEmpty()|| tfIntLon.getText().isEmpty()) { // ver de aceitar somente número 
			
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Coordenadas inválidas!!!");
			a.setHeaderText(null);
			a.show();
			
			} 
		
					else {
				
						if (tipoInterID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoCaptacao() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null // ||
												//	tabSubCon.obterSubterranea().getSub_Sistema() == null
											) {
										
										Alert aLat = new Alert (Alert.AlertType.ERROR);
										aLat.setTitle("Alerta!!!");
										aLat.setContentText("Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!");
										aLat.setHeaderText(null);
										aLat.show();
										
										} else {
										
										Interferencia interferencia = tvListaInt.getSelectionModel().getSelectedItem();
										
											interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
											interferencia.setInterBaciaFK(baciaHid);
											interferencia.setInterUHFK(UniHid);
											
											interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
											
											interferencia.setInterLogadouro(endereco.getEndLogadouro());
											interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
											interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
											
											interferencia.setIntAtualizacao((LocalDateTime.now()));
										
											interferencia.setInterEnderecoFK(endereco);
											
											GeometryFactory geoFac = new GeometryFactory();
											
											interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
													Double.parseDouble(tfIntLon.getText()),
													Double.parseDouble(tfIntLat.getText())
													)));
											
										Subterranea sub = new Subterranea ();
										
											// fk interferencia //
											sub.setSubInterSubFK(interferencia);
											
											sub.setSubTipoCaptacao(tabSubCon.obterSubterranea().getSubTipoCaptacao());
											sub.setSubSubSistemaFK(tabSubCon.obterSubterranea().getSubSubSistemaFK());
											
											sub.setSubVazao(tabSubCon.obterSubterranea().getSubVazao());
											
											sub.setSubEstatico(tabSubCon.obterSubterranea().getSubEstatico());
											sub.setSubDinamico(tabSubCon.obterSubterranea().getSubDinamico());
											sub.setSubProfundidade(tabSubCon.obterSubterranea().getSubProfundidade());
											
											sub.setSubCaesb(tabSubCon.obterSubterranea().getSubCaesb());
											sub.setSubDataOperacao(tabSubCon.obterSubterranea().getSubDataOperacao());
											
											// id subterranea de edicao //
											sub.setSubID(interferencia.getIntSubFK().getSubID());
											
											interferencia.setIntSubFK(sub);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
													
											// merge subterranea //
											interferenciaDao.mergeInterferencia(interferencia);
								
											obsList.remove(interferencia);
											obsList.add(interferencia);
											
											//tvListaInt.setItems(obsList); 
											
										
										selecionarInterferencia ();
										
										modularBotoes ();
										
										//-- Alerta de endereco salvo --//
										Alert a = new Alert (Alert.AlertType.INFORMATION);
										a.setTitle("Parabéns!");
										a.setContentText("Interferência editada com sucesso!");
										a.setHeaderText(null);
										a.show();
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterID == 1 || tipoInterID == 3) {
							
								if (tabSupCon.obterSuperficial().getSupLocal() == null  ||
										tabSupCon.obterSuperficial().getSupArea() == null
										
										) {
									
									Alert aLat = new Alert (Alert.AlertType.ERROR);
									aLat.setTitle("Alerta!!!");
									aLat.setContentText("Informe o Local de Captação e se há Caesb!!!");
									aLat.setHeaderText(null);
									aLat.show();
									
									} else {
									
											Interferencia interferencia = tvListaInt.getSelectionModel().getSelectedItem();
											
												interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
												interferencia.setInterBaciaFK(baciaHid);
												interferencia.setInterUHFK(UniHid);
												
												interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
												
												interferencia.setInterLogadouro(endereco.getEndLogadouro());
												interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
												interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
												
												interferencia.setIntAtualizacao((LocalDateTime.now()));
											
												interferencia.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfIntLon.getText()),
														Double.parseDouble(tfIntLat.getText())
														)));
												
											Superficial sup = new Superficial ();
											
												// fk interferencia //
												sup.setSupInterFK(interferencia);
												
												// id superficial de edicao //
												sup.setSup_ID(interferencia.getIntSupFK().getSup_ID());
												
												sup.setSupLocal(tabSupCon.obterSuperficial().getSupLocal());
												sup.setSupCaptacao(tabSupCon.obterSuperficial().getSupCaptacao());
												sup.setSupBomba(tabSupCon.obterSuperficial().getSupBomba());
												sup.setSupPotencia(tabSupCon.obterSuperficial().getSupPotencia());
												sup.setSupTempo(tabSupCon.obterSuperficial().getSupTempo());
												sup.setSupArea(tabSupCon.obterSuperficial().getSupArea());
												sup.setSupCaesb(tabSupCon.obterSuperficial().getSupArea());
												
												sup.setSupDataOperacao(tabSupCon.obterSuperficial().getSupDataOperacao());
												
												interferencia.setIntSupFK(sup);
												
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											
											// merge superficial e canal //
											interferenciaDao.mergeInterferencia(interferencia);
											
											//interferencia.setIntSupFK(sup);
												
											obsList.remove(interferencia);
											obsList.add(interferencia);
											
												
											//tvListaInt.setItems(obsList); 
											
											selecionarInterferencia ();
											
											modularBotoes ();
											
											//-- Alerta de endereco salvo --//
											Alert a = new Alert (Alert.AlertType.INFORMATION);
											a.setTitle("Parabéns!");
											a.setContentText("Interferência editada com sucesso!");
											a.setHeaderText(null);
											a.show();
											
											}
											
												
									} // fim superficial //
						
						else {
							
							Interferencia interferencia = tvListaInt.getSelectionModel().getSelectedItem();
							
							interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
							interferencia.setInterBaciaFK(baciaHid);
							interferencia.setInterUHFK(UniHid);
							
							interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
							
							interferencia.setInterLogadouro(endereco.getEndLogadouro());
							interferencia.setInterDDLatitude(Double.parseDouble(tfIntLat.getText()));
							interferencia.setInterDDLongitude(Double.parseDouble(tfIntLon.getText()));
							
							interferencia.setIntAtualizacao((LocalDateTime.now()));
						
							interferencia.setInterEnderecoFK(endereco);
							
							GeometryFactory geoFac = new GeometryFactory();
							
							interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfIntLon.getText()),
									Double.parseDouble(tfIntLat.getText())
									)));
							
							InterferenciaDao interferenciaDao = new InterferenciaDao ();
							
							//merge outras interferencias //
							interferenciaDao.mergeInterferencia(interferencia);
							
								obsList.remove(interferencia);
								obsList.add(interferencia);
								
									
								//tvListaInt.setItems(obsList); 
								
								selecionarInterferencia ();
								
								modularBotoes ();
								
									//-- Alerta de endereco salvo --//
									Alert a = new Alert (Alert.AlertType.INFORMATION);
									a.setTitle("Parabéns!");
									a.setContentText("Interferência editada com sucesso!");
									a.setHeaderText(null);
									a.show();
							
							
						} // fim outras interferencias
						
				}
		
		}
		
	public void btnExcluirHab (ActionEvent event) {
		
		try {
			
			Interferencia inter = tvListaInt.getSelectionModel().getSelectedItem();
			
			InterferenciaDao interferenciaDao = new InterferenciaDao ();
			
			interferenciaDao.removeInterferencia(inter.getInterID());
			
			// remover a interferencia da lista //
			obsList.remove(inter);
			
			selecionarInterferencia ();
			
			modularBotoes ();
			
			
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Parabéns!");
				a.setContentText("Cadastro excluído!");
				a.setHeaderText(null);
				a.show();
				
		}
		
		catch (Exception e) {
			
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Atenção!!!");
				a.setContentText("Erro ao excluir o cadastro!!!");
				a.setHeaderText(e.toString());
				a.show();
		}
		
	}
	
	
	
	public void btnEndCoordHab (ActionEvent event) {
		  //adicMarcador();
		  
	}
	
	public void checkMapHab (ActionEvent event) {
		
	}
	
	public void btnAtualizarHab (ActionEvent event) {
		
	}
	
	
	
	
	@FXML ScrollPane spInter;
	@FXML AnchorPane apInter;
	
	@FXML Pane pInterForm;
	@FXML Pane pInterTipo;
	@FXML Pane pInterMap;
	@FXML Pane pInterferencia;
	@FXML BorderPane bpInterferencia;
	
		@FXML AnchorPane apPrincipal = new AnchorPane();
		@FXML AnchorPane apPrin1 = new AnchorPane();
		@FXML AnchorPane apPrin2 = new AnchorPane();
		@FXML BorderPane bpPrincipal = new BorderPane();
		@FXML ScrollBar sbPrincipal = new ScrollBar();
		
		
	@Override
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
	    
	    tcDescEndInt.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("interLogadouro")); 
		tcIntCorpoHidrico.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("inter_Corpo_Hidrico")); 
		//tcIntUH.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("inter_UH"));
	    
	    // Label para preencher com o endereco a ser trabalhada //
	    lblEndereco2 = new Label();
	    lblEndereco2.setStyle("-fx-font-weight: bold;");
	    lblEndereco2.setPrefSize(632, 25);	
	    lblEndereco2.setLayoutX(208);
	    lblEndereco2.setLayoutY(13);
		
		p_lblEndereco.getChildren().add(lblEndereco2);
		
		olTipoCaptacao = FXCollections.observableArrayList(
				
				"Superficial",
				"Subterrânea" ,
				"Canal",
				"Caminhão Pipa",
				"Lançamento de Águas Pluviais",
				"Lançamento de Efluentes",
				"Barragem"
				
				);
		
		olBacia = FXCollections
				.observableArrayList(
						
						"Rio Corumbá"	,
						"Rio Descoberto"	,
						"Rio Paranã"	,
						"Rio São Bartolomeu"	,
						"Rio São Marcos"	,
						"Rio Maranhão"	,
						"Rio Paranoá"	,
						"Rio Preto"	

						); 
		
		olUniHid = FXCollections
				.observableArrayList(
						
						"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22",
						"23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42"
						); 
		
		
		cbTipoCaptacao.setItems(olTipoCaptacao);
		cbBacia.setItems(olBacia);
		cbUnidHid.setItems(olUniHid);
		
		
		cbTipoCaptacao.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoInterID = listaTipoInterID [(int) new_value];
	    		try {
					abrirTabs(tipoInterID);
				} catch (IOException e) {
					System.out.println("erro ao abrirTabs" + e);
					
				}
	    		
            }
	    });
	    
		cbTipoCaptacao.getSelectionModel().selectedItemProperty().addListener( 
				
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	tipoInterDescricao = (String) newValue
	     );
		
		cbBacia.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			
	    		baciaID = listaBaciasID [(int) new_value];
	    		
	    		System.out.println(" bacia id " + baciaID);
	    		
            }
	    });
	    
		/*
		cbBacia.getSelectionModel()
	    	.selectedItemProperty()
	    	.addListener( 
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	baciaNome = (String) newValue
	    	
	    );
	    */
		
		cbUnidHid.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    		unidHidID = listaUHID [(int) new_value];
	    		
	    		System.out.println("unidade hidr selecionada " + unidHidID);
	    		
            }
	    });
		
		modularBotoes ();
	}
	
	public void btnCancelarHab (ActionEvent event) {
		
			modularBotoes ();
			
			cbTipoCaptacao.getSelectionModel().clearSelection();
			cbBacia.setValue(null);

			tfCorpoHid.setText("");

			tfIntLat.setText("");
			tfIntLon.setText("");
			
			pInterTipo.getChildren().clear();
		
		
	}
	
	//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
	public void btnPesquisarHab (ActionEvent event) {
		
		strPesquisa = tfPesquisar.getText();
		
		listarInterferencias(strPesquisa);
		
		selecionarInterferencia ();
		
		modularBotoes ();
		
		cbTipoCaptacao.getSelectionModel().clearSelection();
		cbBacia.setValue(null);
		tfCorpoHid.setText("");
		
		
		tfIntLat.setText("");
		tfIntLon.setText("");
		
	}
	
	public void btnCapturarCroqui (ActionEvent event) {
		
		
	}
	
	public void btnLatLngHab (ActionEvent event) {
			
	}
	
	public void setLatLng (double lat, double lng) {
		
		
	}
	
	public void btnIntMapsHab (ActionEvent event) throws IOException {
		
	}
	
	public void abrirTabs (int ti) throws IOException {
		
		if (ti == 1 || ti == 3) {
			
			pInterTipo.getChildren().clear();
			Pane tabSupPane = new Pane();
			tabSupCon = new TabSuperficialController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/TabSuperficial.fxml")); 
			loader.setRoot(tabSupPane);
			loader.setController(tabSupCon);
			loader.load();
			
			pInterTipo.getChildren().add(tabSupPane);
			
		}
		
		else if (ti == 2) {
			
			pInterTipo.getChildren().clear();
			Pane tabSubPane = new Pane();
			tabSubCon = new TabSubterraneaController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fiscalizacao/TabSubterranea.fxml")); 
			loader.setRoot(tabSubPane);
			loader.setController(tabSubCon);
			loader.load();
			pInterTipo.getChildren().add(tabSubPane);
			
			}
		
			else {
				
				pInterTipo.getChildren().clear();
				
				}
		
	}
	
	// --- método para listar interferencias --- //
	 	public void listarInterferencias (String strPesquisa) {
	 	
		 	// --- conexao - listar enderecos --- //
			InterferenciaDao interferenciaDao = new InterferenciaDao();
			List<Interferencia> interferenciaList = null;
			
			try {
				interferenciaList = interferenciaDao.listInterferencia(strPesquisa);
			} catch (Exception e) {
				System.out.println("erro ao listar as interferências!");
				e.printStackTrace();
			}
					
			if (!obsList.isEmpty()) {
				obsList.clear();
			}
			
			// preencher a observable lista para a table view //
			for (Interferencia i : interferenciaList) {
				
				i.getInterID();
				
				i.getInterTipoInterferenciaFK();
				i.getInterBaciaFK();
				
				i.getInterUHFK();
				i.getInter_Corpo_Hidrico();
				
				i.getInterDDLatitude();
				i.getInterDDLongitude();
				
				obsList.add(i);
				
			}
			
			tvListaInt.setItems(obsList); 
			
	 }
	 	
	 	// metodo selecionar interferencia -- //
	 	public void selecionarInterferencia () {
		
	 		tvListaInt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Interferencia inter = (Interferencia) newValue;
				
				if (inter == null) {
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					//cbTipoInteferencia.setValue(intTab.getInter_Tipo());
					//cbBacia.setValue(intTab.getInter_Bacia());
					//tfUH.setText(intTab.getInter_UH());
					tfCorpoHid.setText(inter.getInter_Corpo_Hidrico());
					
					
					// latitude e longitude
					tfIntLat.setText(inter.getInterDDLatitude().toString());
					tfIntLon.setText(inter.getInterDDLongitude().toString());
					
					FormatoData d = new FormatoData();
					
					// mostrar data de atualizacao //
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(inter.getIntAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
					}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);}
					
					setEndereco(inter.getInterEnderecoFK());
					
					/*
					// estudando geometry
					System.out.println(
							
							inter.getInterBaciaFK().getBaciaShape() 
							+ " \n vamos ver"
							+ "\n num point - qunatidade de pontos " + inter.getInterBaciaFK().getBaciaShape().getNumPoints()
							+ "\n gemetry type " + inter.getInterBaciaFK().getBaciaShape().getGeometryType()
							+ "\n get coordinate ordinate 0 lon " + inter.getInterBaciaFK().getBaciaShape().getCoordinate().getOrdinate(0)
							+ "\n get coordinate ordinate 1 lat " + inter.getInterBaciaFK().getBaciaShape().getCoordinate().getOrdinate(1)
							+ "\n get coordinate          2 orbit height " + inter.getInterBaciaFK().getBaciaShape().getCoordinate().getOrdinate(2)
							+ "\n get coordinate          getArea " + inter.getInterBaciaFK().getBaciaShape().getArea()
							
							+ "\n get coordinate          geometry type " + inter.getInterBaciaFK().getBaciaShape().getGeometryType()
							
							
							+ "\n GeoLatLon Lon x " + inter.getInterGeoLatLon().getCoordinate().x
							
							+ "\n GeoLatLon Lat y" + inter.getInterGeoLatLon().getCoordinate().y
							
							);
					
					
					*/
					/*
					// mudar o endereco da interferencia de acordo com a selecao do usuario
					eGeralInt = intTab.getEnderecoInterferenciaObjetoTabelaFK();
					
					lblEnd.setText(eGeralInt.getDesc_Endereco()  + " |  RA: "  + eGeralInt.getRA_Endereco());
					*/
					
					tipoInterID = inter.getInterTipoInterferenciaFK().getTipoInterID();
					
					if (tipoInterID == 2) {
						
						try {
							abrirTabs (tipoInterID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSubCon.imprimirSubterranea(inter.getIntSubFK());
						
					}
					
					if (tipoInterID == 1 || tipoInterID == 3) {
						
						try {
							abrirTabs (tipoInterID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSupCon.imprimirSuperficial(inter.getIntSupFK());
						
					}
					
					
					
					//System.out.println("FK " + intTab.getEnderecoInterferenciaObjetoTabelaFK());
					
					//Double lat = Double.parseDouble(tfIntLat.getText());
					//Double  lng = Double.parseDouble(tfIntLon.getText() );
					
					/*
					if (wv1 != null) {
						
						String endCoord = "" + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLat_Endereco() + ","
										+ intTab.getEnderecoInterferenciaObjetoTabelaFK().getLon_Endereco();
						
						String intCoord = "" + intTab.getInter_Lat() +  "," + intTab.getInter_Lng();
						
						//System.out.println(endCoord);
						//System.out.println(intCoord);
						
						webEng.executeScript("" +
		                        "window.coordenadas = [" 
		                        + 	"'" + endCoord + "'," 
		                        +	"'" + intCoord + "'" 
		                        + "];"
		                        + "document.buscarCoordenadas(coordenadas);"
		                        + " map.setCenter({lat: " 
		                        + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLat_Endereco()
		                        + ", lng: "  
		                        + intTab.getEnderecoInterferenciaObjetoTabelaFK().getLon_Endereco()
		                        + "});"
		                    );
		                    */
		                    
						
						/*
						webEng.executeScript("" +
		                        "window.lat = " + lat + ";" +
		                        "window.lon = " + lng + ";" +
		                        "document.goToLocation(window.lat, window.lon);"
		                    );
		                    */
						
					}
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					}
				//}
			});
		}
	 	
	 	public void modularBotoes () {
			
	 		cbTipoCaptacao.setDisable(true);
			cbBacia.setDisable(true);
			cbUnidHid.setDisable(true);
			
			tfCorpoHid.setDisable(true);
			tfIntLat.setDisable(true);
			tfIntLon.setDisable(true);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			
			
			btnNovo.setDisable(false);
		}
	

}
