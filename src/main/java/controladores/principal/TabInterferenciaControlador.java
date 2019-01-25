package controladores.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import dao.InterferenciaDao;
import entidades.BaciasHidrograficas;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.SituacaoProcesso;
import entidades.Subterranea;
import entidades.Superficial;
import entidades.TipoAto;
import entidades.TipoInterferencia;
import entidades.TipoOutorga;
import entidades.UnidadeHidrografica;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import principal.Alerta;
import principal.FormatoData;


public class TabInterferenciaControlador  implements Initializable{
	
 	TabSubterraneaController tabSubCon;
	TabSuperficialController tabSupCon;
 	
 	static Endereco endereco = new Endereco ();
 	
 	static Label lblEndereco = new Label();

	public void setEndereco (Endereco end) {
		
		TabInterferenciaControlador.endereco = end;
		// preencher o label com a demanda selecionada //
		
		TabInterferenciaControlador.lblEndereco.setText(
				
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
	
	Button btnNovo = new Button("Novo");
	Button btnSalvar = new Button("Salvar");
	Button btnEditar = new Button("Editar");
	Button btnExcluir = new Button("Excluir");
	Button btnCancelar = new Button("Cancelar");
	Button btnPesquisar = new Button("Pesquisar");
	TextField tfPesquisar = new TextField();
	
	
	//Button btnEndCoord;
	//Image imgEndCoord = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));

	//Button btnEndCoordMap = new Button();
	//Image imgEndCoordMap = new Image(TabVistoriaController.class.getResourceAsStream("/images/mapCoord.png"));
	
	//Image imgGetCoord = new Image(TabEnderecoController.class.getResourceAsStream("/images/getCoord.png")); 
	Button btnBuscarEnd = new Button ();

	//-- TableView Endereco --//
	private TableView <Interferencia> tvLista = new TableView<>();
	
	TableColumn<Interferencia, String> tcLogadouro  = new TableColumn<>("Endereço da Interferência");
	TableColumn<Interferencia, String> tcCorpoHidrico  = new TableColumn<>("Corpo Hídrico");
	TableColumn<Interferencia, String> tcUH  = new TableColumn<>("UH");
	
	Label lblDataAtualizacao = new Label();
												
	int tipoInterferenciaID = 1;
	String tipoInterferenciaDescricao = "Superficial";
	final int [] listaTipoInterID = new int [] { 1,2,3,4,5,6,7 };
	
	int baciaID = 1;
	final int [] listaBaciasID = new int [] { 1,2,3,4,5,6,7,8 };
	
	int unidHidID = 1;
	final int [] listaUHID = new int [] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
			22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42 };
	
	int tipoOutorgaID = 1;
	final int [] listaTipoOutorgaID = new int [] { 1,2,3,4,5,6,7 };
	
	int tipoAtoID = 1;
	final int [] listaTipoAtoID = new int [] { 1,2,3,4 };
	
	int situacaoProcessoID = 1;
	final int [] listaSituacaoProcessoID = new int [] { 1,2,3,4,5,6,7 };

	public void btnNovoHab () {
					
			cbTipoInterferencia.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			cbTipoOutorga.setDisable(false);
			cbTipoAto.setDisable(false);
			cbSituacaoProcesso.setDisable(false);
			
			dpDataPublicacao.setDisable(false);
			dpDataVencimento.setDisable(false);
			tfNumeroAto.setDisable(false);
			
			tfLat.setDisable(false);
			tfLon.setDisable(false);
			
			tfLat.setText("");
			tfLon.setText("");
			
			btnNovo.setDisable(true);
			btnSalvar.setDisable(false);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			tfPesquisar.setDisable(false);
			
			btnPesquisar.setDisable(false);
			
			//-- choice box --//
			cbTipoInterferencia.setItems(olTipoInterferencia);
			cbBacia.setItems(olBacia);
					
	}

	
	public void btnSalvarHab () {
		
		TipoInterferencia tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterferenciaID);

		BaciasHidrograficas baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UnidadeHidrografica UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		TipoOutorga tipoOutorga = new TipoOutorga();
		tipoOutorga.setTipoOutorgaID(tipoOutorgaID);
		
		TipoAto tipoAto = new TipoAto();
		tipoAto.setTipoAtoID(tipoAtoID);
		
		SituacaoProcesso situacaoProcesso = new  SituacaoProcesso();
		situacaoProcesso.setSituacaoProcessoID(situacaoProcessoID);
		
		
		if (tfLat.getText().isEmpty() || tfLon.getText().isEmpty()
				) { // ver de aceitar somente número 
			
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR,"Coordenadas inválidas!!!", ButtonType.OK));
			
		}
		
			else if (endereco == null) { // colocar na tabela que não pode ser nulo o id do endereco
				
				Alerta a = new Alerta ();
				a.alertar(new Alert(Alert.AlertType.ERROR,"Endereço relacionado não selecionado!!!", ButtonType.OK));
				
			
				} else {
				
						if (tipoInterferenciaID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoPocoFK() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null ||
												tabSubCon.obterSubterranea().getSubSubSistemaFK() == null
											) {
										
										Alerta a = new Alerta ();
										a.alertar(new Alert(Alert.AlertType.ERROR,"Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!", ButtonType.OK));
										
										} else {
										
										Interferencia inter = new Interferencia();
										
										inter.setInterTipoInterferenciaFK(tipoInterferencia);
										inter.setInterBaciaFK(baciaHid);
										inter.setInterUHFK(UniHid);
										inter.setInterTipoOutorgaFK(tipoOutorga);
										inter.setInterTipoAtoFK(tipoAto);
											
										inter.setInterLogadouro(endereco.getEndLogadouro());
										
										inter.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
										inter.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
											
										inter.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
										
										inter.setInterSituacaoProcessoFK(situacaoProcesso);
										
										inter.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
										inter.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
										
										inter.setInterNumeroAto(tfNumeroAto.getText());
										
										inter.setInterEnderecoFK(endereco);
											
											GeometryFactory geoFac = new GeometryFactory();
											
											Point p = geoFac.createPoint(new Coordinate(
													Double.parseDouble(tfLon.getText()),
													Double.parseDouble(tfLat.getText()
													)));
											
											p.setSRID(4674);
												
											inter.setInterGeom(p);
											
											
										Subterranea sub = new Subterranea ();
										
											sub.setSubInterSubFK(inter);
											
											sub.setSubTipoPocoFK(tabSubCon.obterSubterranea().getSubTipoPocoFK());
											sub.setSubSubSistemaFK(tabSubCon.obterSubterranea().getSubSubSistemaFK());
											
											sub.setSubVazao(tabSubCon.obterSubterranea().getSubVazao());
											sub.setSubEstatico(tabSubCon.obterSubterranea().getSubEstatico());
											sub.setSubDinamico(tabSubCon.obterSubterranea().getSubDinamico());
											sub.setSubProfundidade(tabSubCon.obterSubterranea().getSubProfundidade());
											
											sub.setSubCaesb(tabSubCon.obterSubterranea().getSubCaesb());
											sub.setSubDataOperacao(tabSubCon.obterSubterranea().getSubDataOperacao());
											
											inter.setIntSubFK(sub);
											
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											interferenciaDao.salvaInterferencia(inter);	
								
											obsList.remove(inter);
											obsList.add(inter);
											
											tvLista.setItems(obsList); 
										
										modularBotoes ();
										
										//-- Alerta de endereco salvo --//
										Alert a = new Alert (Alert.AlertType.INFORMATION);
										a.setTitle("Parabéns!");
										a.setContentText("Interferência salva com sucesso!");
										a.setHeaderText(null);
										a.show();
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterferenciaID == 1 || tipoInterferenciaID == 3) {
							
								if (
										tabSupCon.obterSuperficial() == null  //|| .getSupLocal()
										//tabSupCon.obterSuperficial().getSupArea() == null
										
										) {
									
									Alert aLat = new Alert (Alert.AlertType.ERROR);
									aLat.setTitle("Alerta!!!");
									aLat.setContentText("Informe o Local de Captação e se há Caesb!!!");
									aLat.setHeaderText(null);
									aLat.show();
									
									} else {
									
											Interferencia inter = new Interferencia();
											
												inter.setInterTipoInterferenciaFK(tipoInterferencia);
												inter.setInterBaciaFK(baciaHid);
												inter.setInterUHFK(UniHid);
												inter.setInterTipoOutorgaFK(tipoOutorga);
												inter.setInterTipoAtoFK(tipoAto);
													
												inter.setInterLogadouro(endereco.getEndLogadouro());
												
												inter.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
												inter.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
													
												inter.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
												
												inter.setInterSituacaoProcessoFK(situacaoProcesso);
												
												inter.setInterDataPublicacao(Date.valueOf(dpDataPublicacao.getValue()));
												inter.setInterDataVencimento(Date.valueOf(dpDataVencimento.getValue()));
												
												inter.setInterNumeroAto(tfNumeroAto.getText());
												
												inter.setInterEnderecoFK(endereco);
													
													GeometryFactory geoFac = new GeometryFactory();
													
													Point p = geoFac.createPoint(new Coordinate(
															Double.parseDouble(tfLon.getText()),
															Double.parseDouble(tfLat.getText()
															)));
													
													p.setSRID(4674);
														
													inter.setInterGeom(p);
												
												/*
												GeometryFactory geoFac = new GeometryFactory();
												
												interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfLon.getText()),
														Double.parseDouble(tfLat.getText())
														)));
														*/
												
											Superficial sup = new Superficial ();
											
												sup.setSupInterFK(inter);
												
												sup.setSupLocalCaptacaoFK(tabSupCon.obterSuperficial().getSupLocalCaptacaoFK());
												sup.setSupFormaCaptacaoFK(tabSupCon.obterSuperficial().getSupFormaCaptacaoFK());
												sup.setSupMetodoIrrigacaoFK(tabSupCon.obterSuperficial().getSupMetodoIrrigacaoFK());
												sup.setSupBarramento(tabSupCon.obterSuperficial().getSupBarramento());
												
												sup.setSupDataOperacao(tabSupCon.obterSuperficial().getSupDataOperacao());
												
												sup.setSupMarcaBomba(tabSupCon.obterSuperficial().getSupMarcaBomba());
												sup.setSupPotenciaBomba(tabSupCon.obterSuperficial().getSupPotenciaBomba());
												
												sup.setSupCorpoHidrico(tabSupCon.obterSuperficial().getSupCorpoHidrico());
												sup.setSupAreaIrrigada(tabSupCon.obterSuperficial().getSupAreaIrrigada());
												sup.setSupAreaContribuicao(tabSupCon.obterSuperficial().getSupAreaContribuicao());
												sup.setSupAreaPropriedade(tabSupCon.obterSuperficial().getSupAreaPropriedade());
												sup.setSupCaesb(tabSupCon.obterSuperficial().getSupCaesb());
												
												inter.setIntSupFK(sup);
												
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											interferenciaDao.salvaInterferencia(inter);
											
											obsList.remove(inter);
											obsList.add(inter);
											
											tvLista.setItems(obsList); 
											
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
							
							interferencia.setInterLogadouro(endereco.getEndLogadouro());
							interferencia.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
							interferencia.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
							
							interferencia.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
						
							interferencia.setInterEnderecoFK(endereco);
							
							GeometryFactory geoFac = new GeometryFactory();
							
							Point p = geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfLon.getText()),
									Double.parseDouble(tfLat.getText()
									)));
							
							p.setSRID(4674);
								
							interferencia.setInterGeom(p);
							
							/*
							GeometryFactory geoFac = new GeometryFactory();
							
							
							
							interferencia.setInterGeoLatLon(geoFac.createPoint(
									new Coordinate(
									Double.parseDouble(tfLon.getText()),
									Double.parseDouble(tfLat.getText())
									)));
							*/
							
							InterferenciaDao interferenciaDao = new InterferenciaDao ();
							interferenciaDao.salvaInterferencia(interferencia);
							
								obsList.remove(interferencia);
								obsList.add(interferencia);
								
									
								tvLista.setItems(obsList); 
							
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
	public void btnEditarHab () {
		
		TipoInterferencia tipoInterferencia = new TipoInterferencia();
		tipoInterferencia.setTipoInterID(tipoInterferenciaID);

		BaciasHidrograficas baciaHid = new BaciasHidrograficas();
		baciaHid.setBaciaID(baciaID);
		
		UnidadeHidrografica UniHid = new UnidadeHidrografica();
		UniHid.setUhID(unidHidID);
		
		TipoOutorga tipoOutorga = new TipoOutorga();
		tipoOutorga.setTipoOutorgaID(tipoOutorgaID);
		
		TipoAto tipoAto = new TipoAto();
		tipoAto.setTipoAtoID(tipoAtoID);
		
		SituacaoProcesso situacaoProcesso = new  SituacaoProcesso();
		situacaoProcesso.setSituacaoProcessoID(situacaoProcessoID);
		
		// ver excecao de querer editar sem esconlher o endereco da interferencia...
		
		// habilitar os campos para edição //
		if (cbTipoInterferencia.isDisable()) {
					
			cbTipoInterferencia.setDisable(false);
			cbBacia.setDisable(false);
			cbUnidHid.setDisable(false);
			
			tfLat.setDisable(false);
			tfLon.setDisable(false);
			
					
		}
		
		else if (tfLat.getText().isEmpty()|| tfLon.getText().isEmpty()) { // ver de aceitar somente número 
			
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Coordenadas inválidas!!!");
			a.setHeaderText(null);
			a.show();
			
			} 
		
					else {
				
						if (tipoInterferenciaID == 2) {
							
										if (tabSubCon.obterSubterranea().getSubTipoPocoFK() == null ||
											tabSubCon.obterSubterranea().getSubCaesb() == null ||
												tabSubCon.obterSubterranea().getSubSubSistemaFK() == null
											) {
										
										Alert aLat = new Alert (Alert.AlertType.ERROR);
										aLat.setTitle("Alerta!!!");
										aLat.setContentText("Informe: Tipo de Captação (), Área é atendida pela Caesb() e Subsistema()!!!");
										aLat.setHeaderText(null);
										aLat.show();
										
										} else {
										
										Interferencia interferencia = tvLista.getSelectionModel().getSelectedItem();
										
											interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
											interferencia.setInterBaciaFK(baciaHid);
											interferencia.setInterUHFK(UniHid);
										
											interferencia.setInterLogadouro(endereco.getEndLogadouro());
											interferencia.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
											interferencia.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
											
											interferencia.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
										
											interferencia.setInterEnderecoFK(endereco);
											
											GeometryFactory geoFac = new GeometryFactory();
											
											Point p = geoFac.createPoint(new Coordinate(
													Double.parseDouble(tfLon.getText()),
													Double.parseDouble(tfLat.getText()
													)));
											
											p.setSRID(4674);
												
											interferencia.setInterGeom(p);
											
										Subterranea sub = new Subterranea ();
										
											// fk interferencia //
											sub.setSubInterSubFK(interferencia);
											
											sub.setSubTipoPocoFK(tabSubCon.obterSubterranea().getSubTipoPocoFK());
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
										
										modularBotoes ();
										
										//-- Alerta de endereco salvo --//
										Alert a = new Alert (Alert.AlertType.INFORMATION);
										a.setTitle("Parabéns!");
										a.setContentText("Interferência editada com sucesso!");
										a.setHeaderText(null);
										a.show();
										
										}
									
								} // fim subterranea
						
						
						else if (tipoInterferenciaID == 1 || tipoInterferenciaID == 3) {
							
								if (tabSupCon.obterSuperficial().getSupLocalCaptacaoFK() == null // || 
									//	tabSupCon.obterSuperficial().getSupArea() == null
										
										) {
									
									Alert aLat = new Alert (Alert.AlertType.ERROR);
									aLat.setTitle("Alerta!!!");
									aLat.setContentText("Informe o Local de Captação e se há Caesb!!!");
									aLat.setHeaderText(null);
									aLat.show();
									
									} else {
									
											Interferencia interferencia = tvLista.getSelectionModel().getSelectedItem();
											
												interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
												interferencia.setInterBaciaFK(baciaHid);
												interferencia.setInterUHFK(UniHid);
												
												interferencia.setInterLogadouro(endereco.getEndLogadouro());
												interferencia.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
												interferencia.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
												
												interferencia.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
											
												interferencia.setInterEnderecoFK(endereco);
												
												GeometryFactory geoFac = new GeometryFactory();
												
												Point p = geoFac.createPoint(new Coordinate(
														Double.parseDouble(tfLon.getText()),
														Double.parseDouble(tfLat.getText()
														)));
												
												p.setSRID(4674);
												
												interferencia.setInterGeom(p);
												
											Superficial sup = new Superficial ();
											
												// fk interferencia //
												sup.setSupInterFK(interferencia);
												
												// id superficial de edicao //
												sup.setSup_ID(interferencia.getIntSupFK().getSup_ID());
												
												sup.setSupLocalCaptacaoFK(tabSupCon.obterSuperficial().getSupLocalCaptacaoFK());
												sup.setSupFormaCaptacaoFK(tabSupCon.obterSuperficial().getSupFormaCaptacaoFK());
												sup.setSupMetodoIrrigacaoFK(tabSupCon.obterSuperficial().getSupMetodoIrrigacaoFK());
												sup.setSupBarramento(tabSupCon.obterSuperficial().getSupBarramento());
												
												sup.setSupDataOperacao(tabSupCon.obterSuperficial().getSupDataOperacao());
												
												sup.setSupMarcaBomba(tabSupCon.obterSuperficial().getSupMarcaBomba());
												sup.setSupPotenciaBomba(tabSupCon.obterSuperficial().getSupPotenciaBomba());
												
												sup.setSupCorpoHidrico(tabSupCon.obterSuperficial().getSupCorpoHidrico());
												sup.setSupAreaIrrigada(tabSupCon.obterSuperficial().getSupAreaIrrigada());
												sup.setSupAreaContribuicao(tabSupCon.obterSuperficial().getSupAreaContribuicao());
												sup.setSupAreaPropriedade(tabSupCon.obterSuperficial().getSupAreaPropriedade());
												sup.setSupCaesb(tabSupCon.obterSuperficial().getSupCaesb());
												
												
												interferencia.setIntSupFK(sup);
												
											InterferenciaDao interferenciaDao = new InterferenciaDao ();
											
											// merge superficial e canal //
											interferenciaDao.mergeInterferencia(interferencia);
											
											//interferencia.setIntSupFK(sup);
												
											obsList.remove(interferencia);
											obsList.add(interferencia);
											
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
							
							Interferencia interferencia = tvLista.getSelectionModel().getSelectedItem();
							
							interferencia.setInterTipoInterferenciaFK(tipoInterferencia);
							interferencia.setInterBaciaFK(baciaHid);
							interferencia.setInterUHFK(UniHid);
						
							interferencia.setInterLogadouro(endereco.getEndLogadouro());
							interferencia.setInterDDLatitude(Double.parseDouble(tfLat.getText()));
							interferencia.setInterDDLongitude(Double.parseDouble(tfLon.getText()));
							
							interferencia.setIntAtualizacao(Timestamp.valueOf((LocalDateTime.now())));
						
							interferencia.setInterEnderecoFK(endereco);
							
							GeometryFactory geoFac = new GeometryFactory();
							
							Point p = geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfLon.getText()),
									Double.parseDouble(tfLat.getText()
									)));
							
							p.setSRID(4674);
							
							interferencia.setInterGeom(p);
							
							/*
							GeometryFactory geoFac = new GeometryFactory();
							
							interferencia.setInterGeoLatLon(geoFac.createPoint(new Coordinate(
									Double.parseDouble(tfLon.getText()),
									Double.parseDouble(tfLat.getText())
									)));
									*/
							
							InterferenciaDao interferenciaDao = new InterferenciaDao ();
							
							//merge outras interferencias //
							interferenciaDao.mergeInterferencia(interferencia);
							
								obsList.remove(interferencia);
								obsList.add(interferencia);
								
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
		
	public void btnExcluirHab () {
		
		try {
			
			Interferencia inter = tvLista.getSelectionModel().getSelectedItem();
			
			InterferenciaDao interferenciaDao = new InterferenciaDao ();
			
			interferenciaDao.removeInterferencia(inter.getInterID());
			
			// remover a interferencia da lista //
			obsList.remove(inter);
			
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
	
	Pane pInterTipo = new Pane();
	
	@FXML Pane pInterferencia;
	AnchorPane apPrincipal = new AnchorPane();
	BorderPane bpPrincipal = new BorderPane();
	ScrollPane spPrincipal = new ScrollPane();
	Pane p1 = new Pane ();
	
	Pane p_lblEndereco = new Pane();

	Pane pPersistencia = new Pane();
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		pInterferencia.getChildren().add(apPrincipal);
		
		apPrincipal.minWidthProperty().bind(pInterferencia.widthProperty());
		apPrincipal.minHeightProperty().bind(pInterferencia.heightProperty());
		
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
	    
	    tcLogadouro.setPrefWidth(409);
	    tcCorpoHidrico.setPrefWidth(232);
	    tcUH.setPrefWidth(232);
		
	    tcLogadouro.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("interLogadouro")); 
		tcCorpoHidrico.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("inter_Corpo_Hidrico")); 
		//tcUH.setCellValueFactory(new PropertyValueFactory<Interferencia, String>("inter_UH"));
	    
	    tvLista.getColumns().addAll(tcLogadouro, tcCorpoHidrico, tcUH);
	    tvLista.setItems(obsList);
	    
	    tvLista.setPrefSize(900, 185);
		tvLista.setLayoutX(120);
		tvLista.setLayoutY(352);
	    
		lblDataAtualizacao.setPrefSize(247, 22);
	    lblDataAtualizacao.setLayoutX(772);
	    lblDataAtualizacao.setLayoutY(547);
	 
	    obterEndereco ();
	    obterDadosBasicos();
	    obterPersistencia();
	    
	    // para trazer os dados do tipo de interferencia especifico //
	    pInterTipo.setPrefSize(960, 410);
	    pInterTipo.setLayoutX(90);
	    pInterTipo.setLayoutY(580);
	    
		p1.getChildren().addAll(p_lblEndereco, pDadosBasicos, pPersistencia, lblDataAtualizacao, tvLista, pInterTipo);
		
		olTipoInterferencia= FXCollections.observableArrayList(
				
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
		
		olTipoOutorga = FXCollections
				.observableArrayList(
						
						"Suspensão/Revogação",
						"Transferência",
						"Renovação",
						"Modificação",
						"Registro de Uso",
						"Outorga Prévia",
						"Outorga de Direito de Uso"
						
						); 
		
		olTipoAto = FXCollections
				.observableArrayList(
						
						"Despacho"	,
						"Portaria"	,
						"Registro"	,
						"Resolução"
						
						); 
		
		olSituacaoProcesso = FXCollections
				.observableArrayList(
						
						"Arquivado",
						"Em Análise",
						"Outorgado",
						"Vencida",
						"Arquivado (CNRH 16)",
						"Pendência"	,
						"Indeferido"	
		); 
		
		cbTipoInterferencia.setItems(olTipoInterferencia);
		cbBacia.setItems(olBacia);
		cbUnidHid.setItems(olUniHid);
		cbTipoOutorga.setItems(olTipoOutorga);
		cbTipoAto.setItems(olTipoAto);
		cbSituacaoProcesso.setItems(olSituacaoProcesso);
		
		// capturar o id do tipo de interferencia //
		cbTipoInterferencia.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoInterferenciaID = listaTipoInterID [(int) new_value];
	    			
	    		try {
					abrirTabs(tipoInterferenciaID);
				} catch (IOException e) {
					System.out.println("erro ao abrirTabs" + e);
					
				}
	    		
            }
	    });
	    
		cbTipoInterferencia.getSelectionModel().selectedItemProperty().addListener( 
				
	    	(ObservableValue<? extends String> observable, String oldValue, String newValue) ->
	    	
	    	tipoInterferenciaDescricao = (String) newValue
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
		
		cbTipoOutorga.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoOutorgaID = listaTipoOutorgaID [(int) new_value];
	    			System.out.println("tipo outorga " + tipoOutorgaID);
	    			
            }
	    });
		
		cbTipoAto.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			tipoAtoID = listaTipoAtoID [(int) new_value];
	    			System.out.println("situação " + tipoAtoID);
	    			
            }
	    });
		
		cbSituacaoProcesso.getSelectionModel().selectedIndexProperty().addListener(new
	            ChangeListener<Number>() {
	    	public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
	    		Number value, Number new_value) {
	    		
	    		if ( (Integer) new_value !=  -1)
	    			situacaoProcessoID = listaSituacaoProcessoID [(int) new_value];
	    			System.out.println("situação " + situacaoProcessoID);
	    			
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
		selecionarInterferencia();
		
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
		    
		    btnCapturarCoord.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	 btnIntMapsHab(); 
		        }
		    });
		    
		    btnExcluir.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		        	btnExcluirHab();
		        }
		    });
	}
	
	/*
	 
	OUTORGA SUBTERRÂNEA
	OUTORGA SUBTERRÂNEA TRANSFERÊNCIA
	OUTORGA SUBTERRÂNEA INDEFERIMENTO
	OUTORGA SUBTERRÂNEA MODIFICAÇÃO
	OUTORGA SUBTERRÂNEA RENOVAÇÃO
	REGISTRO SUBTERRÂNEA
	REGISTRO SUBTERRÂNEA MODIFICAÇÃO
	REGISTRO SUBTERRÂNEA TRANSFERÊNCIA
	OUTORGA SUBTERRÂNEA PRÉVIA 
	OUTORGA SUBTERRÂNEA PRÉVIA INDEFERIMENTO

	 */
	
	public void obterEndereco () {
		
		p_lblEndereco.setPrefSize(900, 50);
		p_lblEndereco.setLayoutX(120);
		p_lblEndereco.setLayoutY(20);
		p_lblEndereco.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblEnd = new Label ("Endereco: ");
		lblEnd.setLayoutX(25);
		lblEnd.setLayoutY(16);
		
		 // Label para preencher com o endereco a ser trabalhada //
	    lblEndereco.setStyle("-fx-font-weight: bold;");
	    lblEndereco.setPrefSize(750, 25);	
	    lblEndereco.setLayoutX(89);
	    lblEndereco.setLayoutY(13);
	    
	    btnBuscarEnd.setPrefSize(25, 25);
	    btnBuscarEnd.setLayoutX(851);
	    btnBuscarEnd.setLayoutY(13);
		
		p_lblEndereco.getChildren().addAll(lblEnd, lblEndereco, btnBuscarEnd);
	}
	
		Pane pDadosBasicos = new Pane();
		Button btnCapturarCoord = new Button();
		
		//-- capturar latitude e longitude --//
		TextField tfLat = new TextField();
		TextField tfLon = new TextField();
		
		ChoiceBox<String> cbTipoInterferencia  = new ChoiceBox<String>();
		ObservableList<String> olTipoInterferencia; 
		
			ChoiceBox<String> cbBacia  = new ChoiceBox<String>();
			ObservableList<String> olBacia;
			
				ChoiceBox<String> cbUnidHid  = new ChoiceBox<String>();
				ObservableList<String> olUniHid; 
				
					ChoiceBox<String> cbTipoOutorga  = new ChoiceBox<String>();
					ObservableList<String> olTipoOutorga; 
					
						ChoiceBox<String> cbTipoAto  = new ChoiceBox<String>();
						ObservableList<String> olTipoAto; 
						
							ChoiceBox<String> cbSituacaoProcesso  = new ChoiceBox<String>();
							ObservableList<String> olSituacaoProcesso; 
							
							DatePicker dpDataPublicacao = new DatePicker();
							DatePicker dpDataVencimento = new DatePicker();
							TextField tfNumeroAto = new TextField();
							
		
			
	public void obterDadosBasicos () {
		
		pDadosBasicos.setPrefSize(900, 197);
		pDadosBasicos.setLayoutX(120);
		pDadosBasicos.setLayoutY(83);
		pDadosBasicos.setStyle("-fx-background-color: #E9E9E9;");
		
		Label lblInter = new Label ("Tipo de Interferência: ");
		lblInter.setLayoutX(15);
		lblInter.setLayoutY(6);
		
			cbTipoInterferencia.setPrefSize(215	, 25);
			cbTipoInterferencia.setLayoutX(14);
			cbTipoInterferencia.setLayoutY(32);

		Label lblBacia = new Label ("Bacia: ");
		lblBacia.setLayoutX(241);
		lblBacia.setLayoutY(6);
		
			cbBacia.setPrefSize(160	, 25);
			cbBacia.setLayoutX(240);
			cbBacia.setLayoutY(32);
		
		Label lblUH = new Label ("UH: ");
		lblUH.setLayoutX(411);
		lblUH.setLayoutY(6);
		
			cbUnidHid.setPrefSize(55, 25);
			cbUnidHid.setLayoutX(411);
			cbUnidHid.setLayoutY(32);
			
				Label lblTipoOutorga = new Label ("Tipo de Outorga: ");
				lblTipoOutorga.setLayoutX(478);
				lblTipoOutorga.setLayoutY(6);
				
					cbTipoOutorga.setPrefSize(238, 25);
					cbTipoOutorga.setLayoutX(477);
					cbTipoOutorga.setLayoutY(32);
					
						Label lblTipoAto = new Label ("Tipo de Ato: ");
						lblTipoAto.setLayoutX(726);
						lblTipoAto.setLayoutY(6);
						
							cbTipoAto.setPrefSize(149, 25);
							cbTipoAto.setLayoutX(725);
							cbTipoAto.setLayoutY(32);
		
		Label lblLat = new Label ("Latitude (Y): ");
		lblLat.setLayoutX(219);
		lblLat.setLayoutY(86);
		
			tfLat.setPrefSize(140, 25);
			tfLat.setPromptText("-15.7754084");
			tfLat.setLayoutX(296);
			tfLat.setLayoutY(82);
		
		Label lblLon = new Label ("Longitude (X): ");
		lblLon.setLayoutX(447);
		lblLon.setLayoutY(86);
			
			tfLon.setPrefSize(140, 25);
			tfLon.setPromptText("-47.9411395");
			tfLon.setLayoutX(535);
			tfLon.setLayoutY(82);
			
			btnCapturarCoord.setPrefSize(25, 25);	
			btnCapturarCoord.setLayoutX(686);
			btnCapturarCoord.setLayoutY(82);
			
			
			Label lblSituacaoProcesso = new Label ("Situação: ");
			lblSituacaoProcesso.setLayoutX(223);
			lblSituacaoProcesso.setLayoutY(134);
			
				cbSituacaoProcesso.setPrefSize(149, 25);
				cbSituacaoProcesso.setLayoutX(223);
				cbSituacaoProcesso.setLayoutY(160);
				
				Label lblDataPublicacao = new Label ("Data de Publicação: ");
				lblDataPublicacao.setLayoutX(383);
				lblDataPublicacao.setLayoutY(134);
				
					dpDataPublicacao.setPrefSize(120, 25);
					dpDataPublicacao.setLayoutX(382);
					dpDataPublicacao.setLayoutY(160);
			
						Label lblDataVencimento = new Label ("Data de Vencimento: ");
						lblDataVencimento.setLayoutX(512);
						lblDataVencimento.setLayoutY(134);
						
							dpDataVencimento.setPrefSize(120, 25);
							dpDataVencimento.setLayoutX(511);
							dpDataVencimento.setLayoutY(160);
							
								Label lblNumeroAto = new Label ("Número do Ato: ");
								lblNumeroAto.setLayoutX(642);
								lblNumeroAto.setLayoutY(134);
								
									tfNumeroAto.setPrefSize(89, 25);
									tfNumeroAto.setLayoutX(642);
									tfNumeroAto.setLayoutY(160);
		
		pDadosBasicos.getChildren().addAll(
				
				lblInter, cbTipoInterferencia, lblBacia, cbBacia, lblUH, cbUnidHid, 
				lblTipoOutorga, cbTipoOutorga, lblTipoAto, cbTipoAto,
				lblLat, tfLat, lblLon, tfLon, btnCapturarCoord,
				lblSituacaoProcesso, cbSituacaoProcesso,  lblDataPublicacao, dpDataPublicacao, 
				lblDataVencimento, dpDataVencimento, lblNumeroAto, tfNumeroAto
				);
	}
	
    public void obterPersistencia () {
    	
   	    pPersistencia.setPrefSize(900, 50);
   	    pPersistencia.setLayoutX(110);
   	    pPersistencia.setLayoutY(291);
   
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
	
	
	public void btnCancelarHab () {
		
			modularBotoes ();
			
			cbTipoInterferencia.getSelectionModel().clearSelection();
			cbBacia.setValue(null);

			tfLat.setText("");
			tfLon.setText("");
			
			pInterTipo.getChildren().clear();
		
		
	}
	
	//-- String de pesquisa de enderecos --//
		String strPesquisa = "";
		
	public void btnPesquisarHab () {
		
		strPesquisa = tfPesquisar.getText();
		
		listarInterferencias(strPesquisa);
		
		modularBotoes ();
		
		cbTipoInterferencia.getSelectionModel().clearSelection();
		cbBacia.setValue(null);

		tfLat.setText("");
		tfLon.setText("");
		
	}
	
	public void btnCapturarCroqui (ActionEvent event) {
		
		
	}
	
	public void btnLatLngHab (ActionEvent event) {
			
	}
	
	public void setLatLng (double lat, double lng) {
		
		
	}
	
	public void btnIntMapsHab () {
		
		tfLat.setText( ControladorPrincipal.capturarGoogleMaps().getLat() );
		tfLon.setText( ControladorPrincipal.capturarGoogleMaps().getLon());
		
	}
	
	public void abrirTabs (int ti) throws IOException {
		
		if (ti == 1 || ti == 3) {
			
			pInterTipo.getChildren().clear();
			Pane tabSupPane = new Pane();
			tabSupCon = new TabSuperficialController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TabSuperficial.fxml")); 
			loader.setRoot(tabSupPane);
			loader.setController(tabSupCon);
			loader.load();
			
			pInterTipo.getChildren().add(tabSupPane);
			
		}
		
		else if (ti == 2) {
			
			pInterTipo.getChildren().clear();
			Pane tabSubPane = new Pane();
			tabSubCon = new TabSubterraneaController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal/TabSubterranea.fxml")); 
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
				i.getInterTipoOutorgaFK();
				i.getInterTipoAtoFK();
				
				i.getInterDDLatitude();
				i.getInterDDLongitude();
				
				i.getInterSituacaoProcessoFK();
				i.getInterDataPublicacao();
				i.getInterDataVencimento();
				i.getInterNumeroAto();
				
				obsList.add(i);
				
			}
			
			tvLista.setItems(obsList); 
			
	 }
	 	
	 	// metodo selecionar interferencia -- //
	 	public void selecionarInterferencia () {
		
	 		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Interferencia inter = (Interferencia) newValue;
				
				if (inter == null) {
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {
					
					cbTipoInterferencia.setValue(inter.getInterTipoInterferenciaFK().getTipoInterDescricao());
					cbBacia.setValue(inter.getInterBaciaFK().getBaciaNome());
					cbUnidHid.setValue(String.valueOf(inter.getInterUHFK().getObjectID()));
					cbTipoOutorga.setValue(inter.getInterTipoOutorgaFK().getTipoOutorgaDescricao());
					cbTipoAto.setValue(inter.getInterTipoAtoFK().getTipoAtoDescricao());
					
					// latitude e longitude
					tfLat.setText(inter.getInterDDLatitude().toString());
					tfLon.setText(inter.getInterDDLongitude().toString());
					
					cbSituacaoProcesso.setValue(inter.getInterSituacaoProcessoFK().getSituacaoProcessoDescricao());
					
					Date dPub = inter.getInterDataPublicacao();
					dpDataPublicacao.setValue(dPub.toLocalDate());
					
					Date dVen = inter.getInterDataVencimento();
					dpDataVencimento.setValue(dVen.toLocalDate());
					
					tfNumeroAto.setText(inter.getInterNumeroAto());
					
					
					// mostrar data de atualizacao //
					FormatoData d = new FormatoData();
					try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(inter.getIntAtualizacao()));
							lblDataAtualizacao.setTextFill(Color.BLACK);
							System.out.println("teste data atualizacao black");
					}catch (Exception e) {
							lblDataAtualizacao.setText("Não há data de atualização!");
							lblDataAtualizacao.setTextFill(Color.RED);
							System.out.println("teste data atualizacao red");
					}
				
					setEndereco(inter.getInterEnderecoFK());
					
					
					// estudando geometry
					System.out.println(
							
							/*
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
							*/
							//"srid xxxxxxxxxxxxxx  " + inter.getInterBaciaFK().getBaciaShape().getSRID()
							
							 "\n srid do ponto  " + inter.getInterGeom().getSRID()
							
							);
					
					
					tipoInterferenciaID = inter.getInterTipoInterferenciaFK().getTipoInterID();
					
					if (tipoInterferenciaID == 2) {
						
						try {
							abrirTabs (tipoInterferenciaID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSubCon.imprimirSubterranea(inter.getIntSubFK());
						
					}
					
					if (tipoInterferenciaID == 1 || tipoInterferenciaID == 3) {
						
						try {
							abrirTabs (tipoInterferenciaID);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						tabSupCon.imprimirSuperficial(inter.getIntSupFK());
						
					}
					
					
					
					//System.out.println("FK " + intTab.getEnderecoInterferenciaObjetoTabelaFK());
					
					//Double lat = Double.parseDouble(tfLat.getText());
					//Double  lng = Double.parseDouble(tfLon.getText() );
					
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
			});
		}
	 	
	 	public void modularBotoes () {
		
	 		cbTipoInterferencia.setDisable(true);
			cbBacia.setDisable(true);
			cbUnidHid.setDisable(true);
			cbTipoOutorga.setDisable(true);
			cbTipoAto.setDisable(true);
			cbSituacaoProcesso.setDisable(true);
			
			dpDataPublicacao.setDisable(true);
			dpDataVencimento.setDisable(true);
			tfNumeroAto.setDisable(true);
			
			tfLat.setDisable(true);
			tfLon.setDisable(true);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			
			btnNovo.setDisable(false);
		}
	

}
