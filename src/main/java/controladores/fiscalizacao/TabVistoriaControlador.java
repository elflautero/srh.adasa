package controladores.fiscalizacao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.exception.JDBCConnectionException;

import dao.VistoriaDao;
import entidades.Endereco;
import entidades.Vistoria;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;


public class TabVistoriaControlador implements Initializable {
	
TabAtoControlador tabAtoControlador = new TabAtoControlador ();
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	@FXML TextField tfNumVistoria;
	@FXML TextField tfNumVisSEI;
	
	@FXML TextField tfPesquisar;
	
	@FXML DatePicker dpDataFiscalizacao;
	@FXML DatePicker dpDataCriacaoAto;
	
	@FXML Button btnIfracoes;
	@FXML Button btnPenalidades;
	@FXML Button btnAtenuantes;
	@FXML Button btnAgravantes;
	
	@FXML Button btnAjudaRelatorio;
	@FXML Button btnRecomendacoes;
	
	@FXML Button btnPesquisarObjeto;
	@FXML Button  btnPesquisarApresentacao;
	@FXML Button btnPesquisarRelato;
	@FXML Button btnRelatorio;
	
	
	@FXML CheckBox checkInfra1;
	@FXML CheckBox checkInfra2;
	@FXML CheckBox checkInfra3;
	@FXML CheckBox checkInfra4;
	@FXML CheckBox checkInfra5;
	@FXML CheckBox checkInfra6;
	@FXML CheckBox checkInfra7;
	
	@FXML CheckBox checkPena1;
	@FXML CheckBox checkPena2;
	@FXML CheckBox checkPena3;
	@FXML CheckBox checkPena4;
	@FXML CheckBox checkPena5;
	@FXML CheckBox checkPena6;
	@FXML CheckBox checkPena7;
	
	@FXML CheckBox checkAten1;
	@FXML CheckBox checkAten2;
	@FXML CheckBox checkAten3;
	@FXML CheckBox checkAten4;
	@FXML CheckBox checkAten5;
	@FXML CheckBox checkAten6;
	@FXML CheckBox checkAten7;
	@FXML CheckBox checkAten8;
	@FXML CheckBox checkAten9;
	
	@FXML CheckBox checkAgra1;
	@FXML CheckBox checkAgra2;
	@FXML CheckBox checkAgra3;
	@FXML CheckBox checkAgra4;
	@FXML CheckBox checkAgra5;
	@FXML CheckBox checkAgra6;
	@FXML CheckBox checkAgra7;
	@FXML CheckBox checkAgra8;
	@FXML CheckBox checkAgra9;
	@FXML CheckBox checkAgra10;
	@FXML CheckBox checkAgra11;
	@FXML CheckBox checkAgra12;
	
	
	String strInfracoes;
	String strPenalidades;
	String strAgravantes;
	String strAtenuantes;
	
	String strPesquisa = "";
	
	// TableView Endereço //
		@FXML private TableView <Vistoria> tvVistoria;
		
		@FXML TableColumn<Vistoria, String> tcNumero;
		@FXML TableColumn<Vistoria, String> tcSEI;
		@FXML TableColumn<Vistoria, String> tcData;

	HTMLEditor htmlObjeto;
	HTMLEditor htmlApresentacao; // = new HTMLEditor();
	HTMLEditor htmlRelato; //  = new HTMLEditor();
	HTMLEditor htmlRecomendacao; //   = new HTMLEditor();

	//-- pane para os editores html --//
	@FXML Pane paneObjeto; // = new Pane();
	@FXML Pane paneApresentacao; // = new Pane();
	@FXML Pane paneRelato; // = new Pane();
	@FXML Pane paneRecomendacao; // = new Pane();
	
	
	
	@FXML AnchorPane apPrincipal = new AnchorPane();
	@FXML AnchorPane apPrin1 = new AnchorPane();
	@FXML AnchorPane apPrin2 = new AnchorPane();
	@FXML BorderPane bpPrincipal = new BorderPane();
	@FXML ScrollBar sbPrincipal = new ScrollBar();
	

public void checkInfraHab (ActionEvent event) {
		
		int count = 0;
		String strCheckInfra = "";
		
		if (checkInfra1.isSelected()) {
			count ++;
			strCheckInfra += "1";
		
		}
		if (checkInfra2.isSelected()) {
			count ++;
			strCheckInfra += "2";
			
		}
		if (checkInfra3.isSelected()) {
			count ++;
			strCheckInfra += "3";
			
		}
		if (checkInfra4.isSelected()) {
			count ++;
			strCheckInfra += "4";
			
		}
		if (checkInfra5.isSelected()) {
			count ++;
			strCheckInfra += "5";
			
		
		}
		if (checkInfra6.isSelected()) {
			strCheckInfra += "6";
			count ++;
			
		}
		if (checkInfra7.isSelected()) {
			strCheckInfra += "7";
			count ++;
			
		}
		
		strInfracoes = strCheckInfra;
		
		System.out.println("contador de cliques infração " + count );
		System.out.println("infrações " + strInfracoes + "<<<<<<<<<<<<<");
		
	}
	
	public void checkPenaHab (ActionEvent event) {
		
		int count = 0;
		String strCheckPena = "";
		
		if (checkPena1.isSelected()) {
			count ++;
			strCheckPena += "1";
		}
		if (checkPena2.isSelected()) {
			count ++;
			strCheckPena += "2";
		}
		if (checkPena3.isSelected()) {
			count ++;
			strCheckPena += "3";
		}
		if (checkPena4.isSelected()) {
			count ++;
			strCheckPena += "4";
		}
		if (checkPena5.isSelected()) {
			count ++;
			strCheckPena += "5";
		
		}
		if (checkPena6.isSelected()) {
			strCheckPena += "6";
			count ++;
		}
		if (checkPena7.isSelected()) {
			strCheckPena += "7";
			count ++;
		}
		
		strPenalidades = strCheckPena;
		
		
		System.out.println("contador de penalidades " + count);
		System.out.println("checkbox / penalidades " + strPenalidades);
		
		
	}
	
	public void checkAtenHab (ActionEvent event) {
		int count = 0;
		String strCheckAten = "";
		
		if (checkAten1.isSelected()) {
			count ++;
			strCheckAten += "1";
		}
		if (checkAten2.isSelected()) {
			count ++;
			strCheckAten += "2";
		}
		if (checkAten3.isSelected()) {
			count ++;
			strCheckAten += "3";
		}
		if (checkAten4.isSelected()) {
			count ++;
			strCheckAten += "4";
		}
		if (checkAten5.isSelected()) {
			count ++;
			strCheckAten += "5";
		
		}
		if (checkAten6.isSelected()) {
			strCheckAten += "6";
			count ++;
		}
		if (checkAten7.isSelected()) {
			strCheckAten += "7";
			count ++;
		}
		if (checkAten8.isSelected()) {
			strCheckAten += "8";
			count ++;
		}
		if (checkAten9.isSelected()) {
			strCheckAten += "9";
			count ++;
		}
		
		strAtenuantes = strCheckAten;
		
		System.out.println("contador de atenuantes " + count);
		System.out.println("atenuantes selecionados " + strAtenuantes);
		
	}
	
	public void checkAgraHab (ActionEvent event) {
		int count = 0;
		String strCheckAgra = "";
		
		if (checkAgra1.isSelected()) {
			count ++;
			strCheckAgra += "a";
		}
		if (checkAgra2.isSelected()) {
			count ++;
			strCheckAgra += "b";
		}
		if (checkAgra3.isSelected()) {
			count ++;
			strCheckAgra += "c";
		}
		if (checkAgra4.isSelected()) {
			count ++;
			strCheckAgra += "d";
		}
		if (checkAgra5.isSelected()) {
			count ++;
			strCheckAgra += "e";
		
		}
		if (checkAgra6.isSelected()) {
			strCheckAgra += "f";
			count ++;
		}
		if (checkAgra7.isSelected()) {
			strCheckAgra += "g";
			count ++;
		}
		
		if (checkAgra8.isSelected()) {
			strCheckAgra += "h";
			count ++;
		}
		
		if (checkAgra9.isSelected()) {
			strCheckAgra += "i";
			count ++;
		}
		if (checkAgra10.isSelected()) {
			strCheckAgra += "j";
			count ++;
		}
		if (checkAgra11.isSelected()) {
			strCheckAgra += "k";
			count ++;
		}
		if (checkAgra12.isSelected()) {
			strCheckAgra += "l";
			count ++;
		}
		
		strAgravantes = strCheckAgra;
		
		System.out.println("contador de agravantes " + count);
		System.out.println("agravantes " + strAgravantes);
		
	}
	
	
	static Endereco endereco = new Endereco ();

	public void setEndereco (Endereco endereco) {
	
	TabVistoriaControlador.endereco = endereco;
	
	
	TabVistoriaControlador.lblEndereco2.setText(
			endereco.getEndLogadouro() 
			+ ", Cidade: " + endereco.getEndCidade()
			+ ", CEP: " + endereco.getEndCEP()
			);
	}

	public static Endereco getEndereco () {
		
		return endereco;
	}
	
	@FXML Pane p_lbl_Endereco;
	@FXML Label lblEndereco1;
	static Label lblEndereco2;

	ObservableList<Vistoria> obsList = FXCollections.observableArrayList();
	
	
	public void btnNovoHab (ActionEvent event) {
		
		tfNumVistoria.setText(null);
		tfNumVisSEI.setText(null);
		dpDataFiscalizacao.getEditor().clear(); // limpar datepicker
		dpDataCriacaoAto.getEditor().clear();
		
		htmlObjeto.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		htmlApresentacao.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		htmlRelato.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		htmlRecomendacao.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		
		tfNumVistoria.setDisable(false);
		tfNumVisSEI.setDisable(false);
		dpDataFiscalizacao.setDisable(false);
		dpDataCriacaoAto.setDisable(false);
		
		btnNovo.setDisable(true);
		btnSalvar.setDisable(false);
		
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		
		abrirEditorHTML();
		abrirCheckBox();
		LimparCheckBox();
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
		
		if (endereco.getEndLogadouro() == null) {
			
			Alert aLat = new Alert (Alert.AlertType.ERROR);
			aLat.setTitle("Alerta!!!");
			aLat.setContentText("Endereço NÃO selecionado!!!");
			aLat.setHeaderText(null);
			aLat.show();
			
		} else {
			
			if (dpDataFiscalizacao.getValue() == null  ||
					dpDataCriacaoAto.getValue() == null  ||
						tfNumVistoria.getText().isEmpty()  ||
						tfNumVisSEI.getText().isEmpty()
					
					) {
				
				Alert aLat = new Alert (Alert.AlertType.ERROR);
				aLat.setTitle("Alerta!!!");
				aLat.setContentText("Informe: Número do Ato, Data da fiscalização e Data de Criação!!!");
				aLat.setHeaderText(null);
				aLat.show();
				
			} else {
		
					Vistoria vis = new Vistoria();
					
						vis.setVisIdentificacao(tfNumVistoria.getText());
						vis.setVisSEI(tfNumVisSEI.getText());
						
						if (dpDataFiscalizacao.getValue() == null) {
							vis.setVisDataFiscalizacao(null);}
						else {
							vis.setVisDataFiscalizacao(dpDataFiscalizacao.getValue());
						}
						
						if (dpDataCriacaoAto.getValue() == null) {
							vis.setVisDataCriacao(null);}
						else {
							vis.setVisDataCriacao(dpDataCriacaoAto.getValue());
						}
						
						if (dpDataFiscalizacao.getValue() == null) {
							vis.setVisDataFiscalizacao(null);}
						else {
							vis.setVisDataFiscalizacao(dpDataFiscalizacao.getValue());
						}
						
						if (dpDataCriacaoAto.getValue() == null) {
							vis.setVisDataCriacao(null);}
						else {
							vis.setVisDataCriacao(dpDataCriacaoAto.getValue());
						}
						
						checkInfraHab(null);
						checkPenaHab(null);
						checkAtenHab(null);
						checkAgraHab(null);
						
						
						vis.setVisInfracoes(strInfracoes);
						vis.setVisPenalidades(strPenalidades);
						vis.setVisAgravantes(strAgravantes);
						vis.setVisAtenuantes(strAtenuantes);
						
						vis.setVisObjeto(htmlObjeto.getHtmlText());
						vis.setVisApresentacao(htmlApresentacao.getHtmlText());
						vis.setVisRelato(htmlRelato.getHtmlText());
						vis.setVisRecomendacoes(htmlRecomendacao.getHtmlText());
						
						
						vis.setVisEnderecoFK(endereco);
						
						VistoriaDao visDao = new VistoriaDao();
						
						visDao.salvarVistoria(vis);
						visDao.mergeVistoria(vis);
						
						
						obsList.add(vis);
						
						
						selecionarVistoria();
						modularBotoes();
						fecharEditorHTML();
					
						//-- Alerta de interferência editada --//
						Alert a = new Alert (Alert.AlertType.INFORMATION);
						a.setTitle("Parabéns!");
						a.setContentText("Cadastro salvo com sucesso!!!");
						a.setHeaderText(null);
						a.show();
						
						
						//-- número da vistoria para a tabela atos --//
						
						//visGeral = vis;
						//main.pegarVistoria(vis);
			}
		}
		
	}
	
	public void btnEditarHab (ActionEvent event) {
		
		if (tfNumVistoria.isDisable()) {
			
			tfNumVistoria.setDisable(false);
			tfNumVisSEI.setDisable(false);
			dpDataFiscalizacao.setDisable(false);
			dpDataCriacaoAto.setDisable(false);
			
			htmlObjeto.setDisable(false);
			htmlApresentacao.setDisable(false);
			htmlRelato.setDisable(false);
			htmlRecomendacao.setDisable(false);
			
			abrirEditorHTML();
			abrirCheckBox();
			
		} else {
			
			if (dpDataFiscalizacao == null  ||
					dpDataCriacaoAto == null
					
					) {
				
				Alert aLat = new Alert (Alert.AlertType.ERROR);
				aLat.setTitle("Alerta!!!");
				aLat.setContentText("Informe: Data da Fiscalização e Data de Criação do Ato!!!");
				aLat.setHeaderText(null);
				aLat.show();
				
			} else {
		
				try {
				
					Vistoria vis  = tvVistoria.getSelectionModel().getSelectedItem();
				
					//-- capturar endereço relacionado --//
					vis.setVisEnderecoFK(endereco);
				
					//-- capturar tela --//
					vis.setVisIdentificacao(tfNumVistoria.getText());
					vis.setVisSEI(tfNumVisSEI.getText());
						
					if (dpDataFiscalizacao.getValue() == null) {
						vis.setVisDataFiscalizacao(null);}
					else {
						vis.setVisDataFiscalizacao(dpDataFiscalizacao.getValue());
					}
					
					if (dpDataCriacaoAto.getValue() == null) {
						vis.setVisDataCriacao(null);}
					else {
						vis.setVisDataCriacao(dpDataCriacaoAto.getValue());
					}
					
					checkInfraHab(null);
					checkPenaHab(null);
					checkAtenHab(null);
					checkAgraHab(null);
					
					vis.setVisInfracoes(strInfracoes);
					vis.setVisPenalidades(strPenalidades);
					vis.setVisAgravantes(strAgravantes);
					vis.setVisAtenuantes(strAtenuantes);
					
					vis.setVisObjeto(htmlObjeto.getHtmlText());
					vis.setVisApresentacao(htmlApresentacao.getHtmlText());
					vis.setVisRelato(htmlRelato.getHtmlText());
					vis.setVisRecomendacoes(htmlRecomendacao.getHtmlText());
					
							VistoriaDao visDao = new VistoriaDao();
							
							visDao.mergeVistoria(vis);
							
							obsList.remove(vis);
							obsList.add(vis);
							
							selecionarVistoria();
							modularBotoes();
							fecharEditorHTML();
							
							//-- Alerta de interferência editada --//
							Alert a = new Alert (Alert.AlertType.INFORMATION);
							a.setTitle("Parabéns!");
							a.setContentText("Vistoria editada!");
							a.setHeaderText(null);
							a.show();
							
							//visGeral = vis;
							//main.pegarVistoria(vis);
				
				
						} catch (Exception e) {
							
							System.out.println("Erro ao editar: " + e);
							
							//-- Alerta de interferência editada --//
							Alert a = new Alert (Alert.AlertType.ERROR);
							a.setTitle("Atenção!"); 
							a.setContentText(e.toString());
							a.setHeaderText("Erro ao editar vistoria!");
							a.show();
							
						}
			
			}
			
		}
		
	}

	public void btnExcluirHab (ActionEvent event) {
		
		try {
		
			Vistoria vis = tvVistoria.getSelectionModel().getSelectedItem();
			
			VistoriaDao visDao = new VistoriaDao();
			
				try {
					
					visDao.removerVistoria(vis.getVisID());
					obsList.remove(vis);
				
					selecionarVistoria();
					modularBotoes();
					fecharEditorHTML();
					
					//-- Alerta de interferência editada --//
					Alert a = new Alert (Alert.AlertType.INFORMATION);
					a.setTitle("Parabéns!");
					a.setContentText("Cadastro excluído!");
					a.setHeaderText(null);
					a.show();
					
					}	catch (JDBCConnectionException eJDBC) {
							
							Alert a = new Alert (Alert.AlertType.ERROR);
							a.setTitle("Atenção!");
							a.setContentText("erro ao excluir o cadastro!!!");
							a.setHeaderText("jdbc" + eJDBC.toString());
							a.show();
					}
				
			}	catch (Exception e) {
					
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Atenção!");
				a.setContentText("erro ao excluir o cadastro!!!");
				a.setHeaderText(e.toString());
				a.show();
			}
			
}

	
	public void btnCancelarHab (ActionEvent event) {
		
		modularBotoes();
		fecharEditorHTML();
		
	}
	
	public void btnPesquisarHab (ActionEvent event) {
		
		try {
		strPesquisa = tfPesquisar.getText();
		listarVistorias(strPesquisa);
		selecionarVistoria();
		fecharEditorHTML();
		modularBotoes();
		
		
		}
		
			catch (Exception e) {
				Alert aLat = new Alert (Alert.AlertType.ERROR);
				aLat.setTitle("Alerta!!!");
				aLat.setContentText("Erro de conexão!!!" + "[ " + e + " ]");
				aLat.setHeaderText(null);
				aLat.show();
			}
		
	}
	
	String strData;
	String strEndereco;
	
	public void btnPesqObjHab (ActionEvent event) {
		
		/*
		try {
			strData = formatter.format(dpDataFiscalizacao.getValue());
		} catch (Exception e) {
			strData = "DATA";
		}
		*/
		
		
		try {
			strEndereco = endereco.getEndLogadouro();
		} catch (Exception e) {
			strEndereco = "ENDEREÇO";
		}
		
		//e se a vistoria for salva pela primeira vez? Ainda  não há as informaçoes visGeral
				
		String objeto = "<p>Em atendimento ao MEMORANDO... foi realizada vistoria no dia "
				+ strData 
				+ ", para verifica&ccedil;&atilde;o de DESCRIÇÃO DENÚNCIA, no endereço: " + strEndereco + ".";
				;
		
		htmlObjeto.setHtmlText(objeto);
		
	}
	
	public void btnPesApHab (ActionEvent event) {
		
		String apresentacao = "A vistoria ocorreu em " + strData + ", por volta das HORAS, "
				+ "e contou com a presen&ccedil;a do(s) t&eacute;cnico(s) "
				+ "TECNICO e do respons&aacute;vel pela propriedade USUÁRIO.";
		
		htmlApresentacao.setHtmlText(apresentacao);
		
	}
	
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
	    
	    relatarHTML ();
	    
	 // Label para preencher com a demanda a ser trabalhada //
	    lblEndereco2 = new Label();
	    lblEndereco2.setStyle("-fx-font-weight: bold;");
	    lblEndereco2.setPrefSize(728, 25);	
	    lblEndereco2.setLayoutX(131);
	    lblEndereco2.setLayoutY(13);
		
		p_lbl_Endereco.getChildren().add(lblEndereco2);
		
		tcNumero.setCellValueFactory(new PropertyValueFactory<Vistoria, String>("visIdentificacao"));  // visIdentificacao
		tcSEI.setCellValueFactory(new PropertyValueFactory<Vistoria, String>("visSEI"));  
		tcData.setCellValueFactory(new PropertyValueFactory<Vistoria, String>("visDataFiscalizacao")); 
		
		tvVistoria.setItems(obsList);
		
	}
	
	
	public void listarVistorias (String strPesquisa) {
 		
	 	// --- conexão - listar endereços --- //
		VistoriaDao visDao = new VistoriaDao();
		List<Vistoria> visList = visDao.listarVistoria(strPesquisa);
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
			
			for (Vistoria vis : visList) {
				
				vis.getVisID();
				vis.getVisEnderecoFK();
				vis.getVisObjeto();
				vis.getVisApresentacao();
				vis.getVisRelato();
				vis.getVisRecomendacoes();
				vis.getVisInfracoes();
				vis.getVisPenalidades();
				vis.getVisAtenuantes();
				vis.getVisAgravantes();
				vis.getVisIdentificacao();
				vis.getVisSEI();
				vis.getVisDataFiscalizacao();
				vis.getVisDataCriacao();
				
				obsList.add(vis);
				
			}
			
				
					
	}
		
	String infrArray [];
	String agraArray [];
	String atenArray [];
	
	// método selecionar vistoria -- //
	 	public void selecionarVistoria () {
		
			tvVistoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Vistoria vis = (Vistoria) newValue;
				
				if (vis == null) {
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					tfNumVistoria.setText(vis.getVisIdentificacao());
					tfNumVisSEI.setText(vis.getVisSEI());
					
					if (vis.getVisDataFiscalizacao() == null) {
						dpDataFiscalizacao.getEditor().clear();
		 				} else {
		 					dpDataFiscalizacao.setValue(vis.getVisDataFiscalizacao());
		 				}
	 				
	 				if (vis.getVisDataCriacao() == null) {
	 					dpDataCriacaoAto.getEditor().clear();
		 				} else {
		 					dpDataCriacaoAto.setValue(vis.getVisDataCriacao());
		 				}
	 				
	 				dpDataFiscalizacao.setValue(vis.getVisDataFiscalizacao());
					
					String infr =  vis.getVisInfracoes();
					String pena = vis.getVisPenalidades();
					String agra = vis.getVisAgravantes();
					String aten = vis.getVisAtenuantes();
					
					LimparCheckBox();
					
					
					//-- infrações --//
					
					System.out.println("infracoes do infr para preencher ifraarray " + infr);
					
					
					if (infr != null) {
						
						infrArray = infr.split("");
							
						for (int i = 0; i<infrArray.length; i++) {
							if (infrArray[i].equals("1") ) {
								checkInfra1.setSelected(true);
								
							}
							if (infrArray[i].equals("2") ) {
								checkInfra2.setSelected(true);
								
							}
							if (infrArray[i].equals("3")  ) {
								checkInfra3.setSelected(true);
								
								
							}
							if (infrArray[i].equals("4") ) {
								checkInfra4.setSelected(true);
								
								
							}
							if (infrArray[i].equals("5")  ) {
								checkInfra5.setSelected(true);
								
								
							}
							if (infrArray[i].equals("6") ) {
								checkInfra6.setSelected(true);
								
								
							}
							if (infrArray[i].equals("7")  ) {
								checkInfra7.setSelected(true);
								
							}
							
						} checkInfraHab(null);}
					

									//-- penalidades --//
									if (pena != null) {
										
										String penaArray [] = pena.split("");
										
										//System.out.println("valor string pena auto selecionadas: " + pena);
										
										
										for (int i = 0; i<penaArray.length; i++) {
											
											if (penaArray[i].equals("1") ) {
												checkPena1.setSelected(true);
												
											}
											if (penaArray[i].equals("2") ) {
												checkPena2.setSelected(true);
												
											}
											if (penaArray[i].equals("3")  ) {
												checkPena3.setSelected(true);
												
											}
											if (penaArray[i].equals("4") ) {
												checkPena4.setSelected(true);
												
											}
											if (penaArray[i].equals("5")  ) {
												checkPena5.setSelected(true);
												
											}
											if (penaArray[i].equals("6") ) {
												checkPena6.setSelected(true);
												
											}
											if (penaArray[i].equals("7")  ) {
												checkPena7.setSelected(true);
												
											}
											
											//System.out.println(i + " veja as penalidades array selecionadas" + penaArray[i]);
											
										} checkPenaHab(null);}
									
							//-- atenuantes --//
							if (aten != null) {
								
								atenArray = aten.split("");
								
								//System.out.println("valor string atenuantes auto selecionadas" + aten);
								
								for (int i = 0; i<atenArray.length; i++) {
								
									if (atenArray[i].equals("1") ) {
										checkAten1.setSelected(true);
										
									}
									if (atenArray[i].equals("2") ) {
										checkAten2.setSelected(true);
										
									}
									if (atenArray[i].equals("3")  ) {
										checkAten3.setSelected(true);
										
									}
									if (atenArray[i].equals("4") ) {
										checkAten4.setSelected(true);
										
									}
									if (atenArray[i].equals("5")  ) {
										checkAten5.setSelected(true);
										
									}
									if (atenArray[i].equals("6") ) {
										checkAten6.setSelected(true);
										
									}
									if (atenArray[i].equals("7")  ) {
										checkAten7.setSelected(true);
										
									}
									if (atenArray[i].equals("8")  ) {
										checkAten8.setSelected(true);
										
									}
									if (atenArray[i].equals("9")  ) {
										checkAten9.setSelected(true);
										
									}
									
									
								} checkAtenHab(null);}
							
											//-- agravantes --//
											if (agra != null) {
												
												agraArray = agra.split("");
													
												for (int i = 0; i<agraArray.length; i++) {
												
													if (agraArray[i].equals("a") ) {
														checkAgra1.setSelected(true);
														
													}
													if (agraArray[i].equals("b") ) {
														checkAgra2.setSelected(true);
														
													}
													if (agraArray[i].equals("c")  ) {
														checkAgra3.setSelected(true);
														
													}
													if (agraArray[i].equals("d") ) {
														checkAgra4.setSelected(true);
														
													}
													if (agraArray[i].equals("e")  ) {
														checkAgra5.setSelected(true);
														
													}
													
													if (agraArray[i].equals("f") ) {
														checkAgra6.setSelected(true);
														
													}
													if (agraArray[i].equals("g")  ) {
														checkAgra7.setSelected(true);
														
													}
													
													if (agraArray[i].equals("h")  ) {
														checkAgra8.setSelected(true);
														
													}
													
													if (agraArray[i].equals("i")  ) {
														checkAgra9.setSelected(true);
														
													}
													if (agraArray[i].equals("j")  ) {
														checkAgra10.setSelected(true);
														
													}
													if (agraArray[i].equals("k")  ) {
														checkAgra11.setSelected(true);
														
													}
													if (agraArray[i].equals("l")  ) {
														checkAgra12.setSelected(true);
														
													}
													
													
												}checkAgraHab(null);}
							
					
					htmlObjeto.setHtmlText(vis.getVisObjeto());
					htmlApresentacao.setHtmlText(vis.getVisApresentacao());
					htmlRelato.setHtmlText(vis.getVisRelato());
					htmlRecomendacao.setHtmlText(vis.getVisRecomendacoes());
					
					
					setEndereco(vis.getVisEnderecoFK());
					
					//-- pegar a vistoria selecionada --//
					//Vistoria visG = new Vistoria(vis);
					
					
					//visGeral = visG;
					//main.pegarVistoria(visGeral);
					
					//-- mudar o endereço de acordo com a seleção --//
					//eGeralVis = visTab.getVisEndCodigoFK();
					//lblVisEnd.setText(eGeralVis.getDesc_Endereco() + " |  RA: "  + eGeralVis.getRA_Endereco() );
					
					// copiar número da vistoria no sei ao selecionar //
					Clipboard clip = Clipboard.getSystemClipboard();
	                ClipboardContent conteudo = new ClipboardContent();
	                conteudo.putString(vis.getVisSEI());
	                clip.setContent(conteudo);
	                
					//-- modular botões --//
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				}
				}
			});
		}
	 	
	 	
	 public void fecharEditorHTML () {
		 htmlObjeto.setDisable(true);
		 htmlApresentacao.setDisable(true);
		 htmlRelato.setDisable(true);
		 htmlRecomendacao.setDisable(true);
	 }
	 
	 public void abrirEditorHTML () {
		 htmlObjeto.setDisable(false);
		 htmlApresentacao.setDisable(false);
		 htmlRelato.setDisable(false);
		 htmlRecomendacao.setDisable(false);
	 }
	 	
	 public void modularBotoes () {
		 
		 tfNumVistoria.setDisable(true);
		 tfNumVisSEI.setDisable(true);
		 dpDataFiscalizacao.setDisable(true);
		 dpDataCriacaoAto.setDisable(true);
		 
		 btnSalvar.setDisable(true);
		 btnEditar.setDisable(true);
		 btnExcluir.setDisable(true);
		 
		 btnNovo.setDisable(false);
		 
		 fecharCheckBox ();
	 }
	 
	 public void fecharCheckBox () {
		 
		 checkInfra1.setDisable(true);
		 checkInfra2.setDisable(true);
		 checkInfra3.setDisable(true);
		 checkInfra4.setDisable(true);
		 checkInfra5.setDisable(true);
		 checkInfra6.setDisable(true);
		 checkInfra7.setDisable(true);
		 
		 checkPena1.setDisable(true);
		 checkPena2.setDisable(true);
		 checkPena3.setDisable(true);
		 checkPena4.setDisable(true);
		 checkPena5.setDisable(true);
		 checkPena6.setDisable(true);
		 checkPena7.setDisable(true);
		 
		 checkAten1.setDisable(true);
		 checkAten2.setDisable(true);
		 checkAten3.setDisable(true);
		 checkAten4.setDisable(true);
		 checkAten5.setDisable(true);
		 checkAten6.setDisable(true);
		 checkAten7.setDisable(true);
		 checkAten8.setDisable(true);
		 checkAten9.setDisable(true);
		 
		 checkAgra1.setDisable(true);
		 checkAgra2.setDisable(true);
		 checkAgra3.setDisable(true);
		 checkAgra4.setDisable(true);
		 checkAgra5.setDisable(true);
		 checkAgra6.setDisable(true);
		 checkAgra7.setDisable(true);
		 checkAgra8.setDisable(true);
		 checkAgra9.setDisable(true);
		 checkAgra10.setDisable(true);
		 checkAgra11.setDisable(true);
		 checkAgra12.setDisable(true);
		 
	 }
	 
	 public void abrirCheckBox () {
		 
		 checkInfra1.setDisable(false);
		 checkInfra2.setDisable(false);
		 checkInfra3.setDisable(false);
		 checkInfra4.setDisable(false);
		 checkInfra5.setDisable(false);
		 checkInfra6.setDisable(false);
		 checkInfra7.setDisable(false);
		 
		 checkPena1.setDisable(false);
		 checkPena2.setDisable(false);
		 checkPena3.setDisable(false);
		 checkPena4.setDisable(false);
		 checkPena5.setDisable(false);
		 checkPena6.setDisable(false);
		 checkPena7.setDisable(false);
		 
		 checkAten1.setDisable(false);
		 checkAten2.setDisable(false);
		 checkAten3.setDisable(false);
		 checkAten4.setDisable(false);
		 checkAten5.setDisable(false);
		 checkAten6.setDisable(false);
		 checkAten7.setDisable(false);
		 checkAten8.setDisable(false);
		 checkAten9.setDisable(false);
		 
		 checkAgra1.setDisable(false);
		 checkAgra2.setDisable(false);
		 checkAgra3.setDisable(false);
		 checkAgra4.setDisable(false);
		 checkAgra5.setDisable(false);
		 checkAgra6.setDisable(false);
		 checkAgra7.setDisable(false);
		 checkAgra8.setDisable(false);
		 checkAgra9.setDisable(false);
		 checkAgra10.setDisable(false);
		 checkAgra11.setDisable(false);
		 checkAgra12.setDisable(false);
		 
	 }

	 public void LimparCheckBox () {
		 
		 checkInfra1.setSelected(false);
		 checkInfra2.setSelected(false);
		 checkInfra3.setSelected(false);
		 checkInfra4.setSelected(false);
		 checkInfra5.setSelected(false);
		 checkInfra6.setSelected(false);
		 checkInfra7.setSelected(false);
		 
		 checkPena1.setSelected(false);
		 checkPena2.setSelected(false);
		 checkPena3.setSelected(false);
		 checkPena4.setSelected(false);
		 checkPena5.setSelected(false);
		 checkPena6.setSelected(false);
		 checkPena7.setSelected(false);
		 
		 checkAten1.setSelected(false);
		 checkAten2.setSelected(false);
		 checkAten3.setSelected(false);
		 checkAten4.setSelected(false);
		 checkAten5.setSelected(false);
		 checkAten6.setSelected(false);
		 checkAten7.setSelected(false);
		 checkAten8.setSelected(false);
		 checkAten9.setSelected(false);
		 
		 checkAgra1.setSelected(false);
		 checkAgra2.setSelected(false);
		 checkAgra3.setSelected(false);
		 checkAgra4.setSelected(false);
		 checkAgra5.setSelected(false);
		 checkAgra6.setSelected(false);
		 checkAgra7.setSelected(false);
		 checkAgra8.setSelected(false);
		 checkAgra9.setSelected(false);
		 checkAgra10.setSelected(false);
		 checkAgra11.setSelected(false);
		 checkAgra12.setSelected(false);
		 
	 }
	 
	 public void btnInfracoesHab (ActionEvent event) {
		 
		 	CheckBox ci1 = new CheckBox();
			CheckBox ci2 = new CheckBox();
			CheckBox ci3 = new CheckBox();
			CheckBox ci4 = new CheckBox();
			CheckBox ci5 = new CheckBox();
			CheckBox ci6 = new CheckBox();
			CheckBox ci7 = new CheckBox();
			
			ci1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra1.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci2.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra2.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci3.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra3.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci4.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra4.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci5.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra5.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci6.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra6.setSelected(newVal);
		        checkPenaHab(null);
		    });
			ci7.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkInfra7.setSelected(newVal);
		        checkPenaHab(null);
		    });
			
			
			VBox vBoxCheck = new VBox();
			vBoxCheck.setSpacing(6);
			vBoxCheck.setLayoutY(3);
			vBoxCheck.setLayoutX(3);
			
			VBox vBoxInfra = new VBox();
			vBoxInfra.setPrefWidth(915);
			vBoxInfra.setPrefHeight(176);
			vBoxInfra.setLayoutX(25);

			vBoxCheck.getChildren().addAll(ci1, ci2,ci3,ci4,ci5,ci6,ci7);
			
			// ObservableList<String> obs = FXCollections.observableArrayList(infraIncisos);
			 //ListView<String> list = new ListView<String>(obs);
			 
			// vBoxInfra.getChildren().add(list);
			
				Group g = new Group(vBoxCheck,vBoxInfra);
				
				Scene scene = new Scene(g);
				Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
				stage.setWidth(964);
				stage.setHeight(215);
			    stage.setScene(scene);
			    stage.setMaximized(false);
			    stage.setResizable(false);
			    stage.setX(425.0);
			    stage.setY(410.0);
			   
			    stage.setAlwaysOnTop(true); 
			    stage.show();
			
			    
			    /*
			  //--  https://docs.oracle.com/javafx/2/ui_controls/ListViewSample.java.html  --// 
			  list.getSelectionModel().selectedItemProperty().addListener(
	             new ChangeListener<String>() {
	                 public void changed(ObservableValue<? extends String> 
	                 ov, String old_val, String new_val) {
	               
	                      Clipboard clip = Clipboard.getSystemClipboard();
	                      ClipboardContent conteudo = new ClipboardContent();
	                      conteudo.putHtml(new_val);
	                      String artigo = (String) conteudo.getString();
	                      conteudo.putString(artigo);
	                      clip.setContent(conteudo);
	                      
	                      
	                      
	                 }
          });
          
          */
			
	 }
	 
	 public void btnPenalidadesHab (ActionEvent event) {
		 
		 CheckBox cp1 = new CheckBox();
			CheckBox cp2 = new CheckBox();
			CheckBox cp3 = new CheckBox();
			CheckBox cp4 = new CheckBox();
			CheckBox cp5 = new CheckBox();
			CheckBox cp6 = new CheckBox();
			CheckBox cp7 = new CheckBox();
			
			cp1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkPena1.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp2.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena2.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp3.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena3.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp4.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena4.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp5.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena5.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp6.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena6.setSelected(newVal);
		        checkPenaHab(null);
		    });
			cp7.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkPena7.setSelected(newVal);
		        checkPenaHab(null);
		    });
			
			VBox vBoxCheck = new VBox();
			vBoxCheck.setSpacing(6);
			vBoxCheck.setLayoutY(3);
			vBoxCheck.setLayoutX(3);
			vBoxCheck.getChildren().addAll(cp1, cp2,cp3,cp4,cp5,cp6,cp7);
			
			VBox vBoxPena = new VBox();
			vBoxPena.setPrefWidth(915);
			vBoxPena.setPrefHeight(170);
			vBoxPena.setLayoutX(25);

			//ObservableList<String> obs = FXCollections.observableArrayList(penaIncisos);
			//ListView<String> list = new ListView<String>(obs);
			
			//vBoxPena.getChildren().add(list);
			
			Group g = new Group(vBoxCheck, vBoxPena);
			
				Scene scene = new Scene(g);
				Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
				stage.setWidth(964);
				stage.setHeight(210);
			    stage.setScene(scene);
			    stage.setMaximized(false);
			    stage.setResizable(false);
			    stage.setX(425.0);
			    stage.setY(410.0);
			   
			    stage.setAlwaysOnTop(true); 
			    stage.show();
			
			    //--  https://docs.oracle.com/javafx/2/ui_controls/ListViewSample.java.html  --// 
			    /*
			    list.getSelectionModel().selectedItemProperty().addListener(
			    		new ChangeListener<String>() {
			    			public void changed(ObservableValue<? extends String> 
			    				ov, String old_val, String new_val) {
         
                Clipboard clip = Clipboard.getSystemClipboard();
                ClipboardContent conteudo = new ClipboardContent();
                conteudo.putHtml(new_val);
                String artigo = (String) conteudo.getString();
                conteudo.putString(artigo);
                clip.setContent(conteudo);
                
           }
       });
       
       */
			    
	 }
	 
	 public void btnAtenuantesHab (ActionEvent event) {
		 
		 	CheckBox ca1 = new CheckBox();
			CheckBox ca2 = new CheckBox();
			CheckBox ca3 = new CheckBox();
			CheckBox ca4 = new CheckBox();
			CheckBox ca5 = new CheckBox();
			CheckBox ca6 = new CheckBox();
			CheckBox ca7 = new CheckBox();
			CheckBox ca8 = new CheckBox();
			CheckBox ca9 = new CheckBox();
			
			ca1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkAten1.setSelected(newVal);
		        checkAtenHab(null);
		    });
			ca2.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten2.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca3.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten3.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca4.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten4.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca5.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten5.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca6.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten6.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca7.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten7.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca8.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten8.setSelected(newVal);
				checkAtenHab(null);
		    });
			ca9.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAten9.setSelected(newVal);
				checkAtenHab(null);
		    });
			
			VBox vBoxCheck = new VBox();
			vBoxCheck.setSpacing(6);
			vBoxCheck.setLayoutY(3);
			vBoxCheck.setLayoutX(3);
			vBoxCheck.getChildren().addAll(ca1, ca2,ca3,ca4,ca5,ca6,ca7,ca8,ca9);
			
			VBox vBoxPena = new VBox();
			vBoxPena.setPrefWidth(915);
			vBoxPena.setPrefHeight(210);
			vBoxPena.setLayoutX(25);

			
			//ObservableList<String> obs = FXCollections.observableArrayList(atenIncisos);
			//ListView<String> list = new ListView<String>(obs);
			
			//vBoxPena.getChildren().add(list);
			
			
			Group g = new Group(vBoxCheck, vBoxPena);
		 
		
				Scene scene = new Scene(g);
				Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
				stage.setWidth(964);
				stage.setHeight(250);
			    stage.setScene(scene);
			    stage.setMaximized(false);
			    stage.setResizable(false);
			    stage.setX(425.0);
			    stage.setY(410.0);
			   
			    stage.setAlwaysOnTop(true); 
			    stage.show();
			
			    
			    
			    /*
			    //--  https://docs.oracle.com/javafx/2/ui_controls/ListViewSample.java.html  --// 
			    list.getSelectionModel().selectedItemProperty().addListener(
			    		new ChangeListener<String>() {
			    			public void changed(ObservableValue<? extends String> 
			    				ov, String old_val, String new_val) {
         
         	  Clipboard clip = Clipboard.getSystemClipboard();
               ClipboardContent conteudo = new ClipboardContent();
               conteudo.putHtml(new_val);
               String artigo = (String) conteudo.getString();
               conteudo.putString(artigo);
               clip.setContent(conteudo);
           }
       });
       
       */
     
	 }
	 
	 public void btnAgravantesHab (ActionEvent event) {
		 
		 	CheckBox chA = new CheckBox();
			CheckBox chB = new CheckBox();
			CheckBox chC = new CheckBox();
			CheckBox chD = new CheckBox();
			CheckBox chE = new CheckBox();
			CheckBox chF = new CheckBox();
			CheckBox chG = new CheckBox();
			CheckBox chH = new CheckBox();
			CheckBox chI = new CheckBox();
			CheckBox chJ = new CheckBox();
			CheckBox chK = new CheckBox();
			CheckBox chL = new CheckBox();
			
			chA.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
		        checkAgra1.setSelected(newVal);
		        checkAgraHab(null);
		    });
			chB.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra2.setSelected(newVal);
				checkAgraHab(null);
		    });
			chC.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra3.setSelected(newVal);
				checkAgraHab(null);
		    });
			chD.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra4.setSelected(newVal);
				checkAgraHab(null);
		    });
			chE.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra5.setSelected(newVal);
				checkAgraHab(null);
		    });
			chF.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra6.setSelected(newVal);
				checkAgraHab(null);
		    });
			chG.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra7.setSelected(newVal);
				checkAgraHab(null);
		    });
			chH.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra8.setSelected(newVal);
				checkAgraHab(null);
		    });
			chI.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra9.setSelected(newVal);
				checkAgraHab(null);
		    });
			chJ.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra10.setSelected(newVal);
				checkAgraHab(null);
		    });
			chK.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra11.setSelected(newVal);
				checkAgraHab(null);
		    });
			chL.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
				checkAgra12.setSelected(newVal);
				checkAgraHab(null);
		    });
			
			VBox vBoxCheck = new VBox();
			vBoxCheck.setSpacing(6);
			vBoxCheck.setLayoutY(3);
			vBoxCheck.setLayoutX(3);
			vBoxCheck.getChildren().addAll(
					chA, chB, chC, chD, chE, chF,
					chG, chH, chI, chJ, chK, chL);
			
			VBox vBoxPena = new VBox();
			vBoxPena.setPrefWidth(915);
			vBoxPena.setPrefHeight(278);
			vBoxPena.setLayoutX(25);

			//ObservableList<String> obs = FXCollections.observableArrayList(agraIncisos);
			//ListView<String> list = new ListView<String>(obs);
			
			//vBoxPena.getChildren().add(list);
			
			Group g = new Group(vBoxCheck, vBoxPena);
		
				
				Scene scene = new Scene(g);
				Stage stage = new Stage(); // StageStyle.UTILITY - tirei para ver como fica, se aparece o minimizar
				stage.setWidth(964);
				stage.setHeight(320);
			    stage.setScene(scene);
			    stage.setMaximized(false);
			    stage.setResizable(false);
			    stage.setX(425.0);
			    stage.setY(410.0);
			   
			    stage.setAlwaysOnTop(true); 
			    stage.show();
			
			    //--  https://docs.oracle.com/javafx/2/ui_controls/ListViewSample.java.html  --// 
			    /*
			    list.getSelectionModel().selectedItemProperty().addListener(
			    		new ChangeListener<String>() {
			    			public void changed(ObservableValue<? extends String> 
			    				ov, String old_val, String new_val) {
         
         	  
         	  Clipboard clip = Clipboard.getSystemClipboard();
               ClipboardContent conteudo = new ClipboardContent();
               conteudo.putHtml(new_val);
               String artigo = (String) conteudo.getString();
               conteudo.putString(artigo);
               clip.setContent(conteudo);
         	  
           }
       });*/
      
	 }
	 
	 public void btnAjudaRelatorioHab (ActionEvent event) {
		 
	 }
	 
	 public void btnRecomendacoesHab (ActionEvent event) {
		 
	 }
	 
	 public void btnRelatorioHab (ActionEvent event) {
		 
	 }
	 
	 public void relatarHTML () {
			
			
			htmlObjeto = new HTMLEditor();
			
				htmlObjeto.setPrefSize(800, 200);
				
				htmlObjeto.setOnKeyPressed(event -> {
				    if (event.getCode() == KeyCode.SPACE  
				            || event.getCode() == KeyCode.TAB ) {
				        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
				        event.consume();
				    }
				});
				
				htmlObjeto.setHtmlText("<p><font face='Times New Roman'> </font></p>");
				
				StackPane root = new StackPane();
				root.getChildren().add(htmlObjeto);
				paneObjeto.getChildren().add(htmlObjeto);
		    
		   
			htmlApresentacao  = new HTMLEditor();
			
				htmlApresentacao.setPrefSize(800, 200);
				
				htmlApresentacao.setOnKeyPressed(event -> {
				    if (event.getCode() == KeyCode.SPACE  
				            || event.getCode() == KeyCode.TAB ) {
				        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
				        event.consume();
				    }
				});
				htmlApresentacao.setHtmlText("<p><font face='Times New Roman'> </font></p>");
				
				StackPane rootAp = new StackPane();
				rootAp.getChildren().add(htmlApresentacao);
			    paneApresentacao.getChildren().add(htmlApresentacao);
		    
		    
			htmlRelato  = new HTMLEditor();
			
				htmlRelato.setPrefSize(800, 673);
				
				htmlRelato.setOnKeyPressed(event -> {
				    if (event.getCode() == KeyCode.SPACE  
				            || event.getCode() == KeyCode.TAB ) {
				        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
				        event.consume();
				    }
				});
				htmlRelato.setHtmlText("<p><font face='Times New Roman'> </font></p>");
				
				
				StackPane rootRel = new StackPane();
				rootRel.getChildren().add(htmlRelato);
				paneRelato.getChildren().add(htmlRelato);
				
			    
			htmlRecomendacao  = new HTMLEditor();
			
				htmlRecomendacao.setPrefSize(800, 200);
				
				htmlRecomendacao.setOnKeyPressed(event -> {
				    if (event.getCode() == KeyCode.SPACE  
				            || event.getCode() == KeyCode.TAB ) {
				        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
				        event.consume();
				    }
				});
				htmlRecomendacao.setHtmlText("<p><font face='Times New Roman'> </font></p>");
				
				StackPane rootReco = new StackPane();
				rootReco.getChildren().add(htmlRecomendacao);
				paneRecomendacao.getChildren().add(htmlRecomendacao);

		}

}
