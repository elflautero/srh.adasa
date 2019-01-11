package controladores.fiscalizacao;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import dao.AtoDao;
import entidades.Ato;
import entidades.Vistoria;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import principal.FormatoData;


public class TabAtoControlador implements Initializable {

	static Vistoria vistoria = new Vistoria ();

	public void setVistoria (Vistoria vistoria) {
	
	TabAtoControlador.vistoria = vistoria;
	
	
		TabAtoControlador.lblVistoria2.setText(
			"Vistoria n°: " + vistoria.getVisSEI()
			+ ", Data da fiscalização: " + vistoria.getVisDataFiscalizacao()
		);
			
	}

	public static Vistoria getVistoria () {
		
		return vistoria;
	}
	
	@FXML Pane p_lbl_Vistoria;
	@FXML Label lblVistoria1;
	static Label lblVistoria2;
	
	@FXML Label lblDataAtualizacao;
	
String strPesquisa = "";
	
	@FXML	Pane tabAto = new Pane ();
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	@FXML TextField tfAto;
	@FXML TextField tfAtoSEI;
	
	@FXML DatePicker dpDataFiscalizacao;
	@FXML DatePicker dpDataCriacaoAto;
	
	@FXML Button bntCaracterizacao;
	@FXML Button btnGerarAto;
	
	//@FXML Image imgAto = new Image(TabAtoController.class.getResourceAsStream("/images/ato32.png"));
	
	
	Ato atoGeral;
	
	// TableView Endereço //
	@FXML private TableView <Ato> tvLista;
	ObservableList<Ato> obsList = FXCollections.observableArrayList();
	
	@FXML TableColumn<Ato, String> tcTipo;
	@FXML TableColumn<Ato, String> tcNumero;
	@FXML TableColumn<Ato, String> tcSEI;
		
		
	@FXML TextField tfPesquisar;
	
	@FXML
	ChoiceBox<String> cbAtoTipo = new ChoiceBox<String>();
		ObservableList<String> olAtoTipo = FXCollections
			.observableArrayList(
					"Termo de Notificação", 
					"Auto de Infração",
					"Auto de Infração de Multa");
	
	@FXML public Label lblVisAto; // público para receber valor do MainController, método pegarEnd()
	
	//  objeto para passar os valor pelo MainControoler para outro controller //
	public Vistoria visGeral;
	
	HTMLEditor htmlCaracterizar = new HTMLEditor();
  	@FXML Pane paneCaracterizar;
  	
  	int u = 0;
  	@FXML
	ChoiceBox<String> cbUsuario = new ChoiceBox<String>();
		ObservableList<String> olUsuario = FXCollections
			.observableArrayList("0" , "1", "2", "3", "4");
	
		

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
	    
	    
	 // Label para preencher com a demanda a ser trabalhada //
	    lblVistoria2 = new Label();
	    lblVistoria2.setStyle("-fx-font-weight: bold;");
	    lblVistoria2.setPrefSize(727, 25);	
	    lblVistoria2.setLayoutX(109);
	    lblVistoria2.setLayoutY(12);
		
		p_lbl_Vistoria.getChildren().add(lblVistoria2); 
		
		tcTipo.setCellValueFactory(new PropertyValueFactory<Ato, String>("atoTipo")); 
		tcNumero.setCellValueFactory(new PropertyValueFactory<Ato, String>("atoIdentificacao")); 
		tcSEI.setCellValueFactory(new PropertyValueFactory<Ato, String>("atoSEI")); 
		
		modularBotoes();
		
		// -- inicitalizar a caracterizacao -- //
		Platform.runLater(() ->{
		
			caracterizarHTML();
			fecharEditorHTML();
		
		});
		
		cbAtoTipo.setItems(olAtoTipo);
		
		
		
		tvLista.setItems(obsList);
	    
		
	}
	
	public void bntCaracterizacaoHab (ActionEvent event) {
		
	}
	
	
	
public void btnNovoHab (ActionEvent event) {
		
		cbAtoTipo.setDisable(false);
		tfAto.setDisable(false);
		tfAtoSEI.setDisable(false);
		dpDataFiscalizacao.setDisable(false);
		dpDataCriacaoAto.setDisable(false);
		
		cbAtoTipo.setValue(null);
		tfAto.setText("");
		tfAtoSEI.setText("");
		dpDataFiscalizacao.setValue(null);
		dpDataCriacaoAto.setValue(null);
		
		htmlCaracterizar.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		
		
		abrirEditorHTML();
		
		btnNovo.setDisable(true);
		btnSalvar.setDisable(false);
		
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		
	}

	public void btnSalvarHab (ActionEvent event) {
		
		if (vistoria == null) {
			
			Alert a = new Alert (Alert.AlertType.ERROR);
			a.setTitle("Alerta!!!");
			a.setContentText("Vistoria não selecionada!!!");
			a.setHeaderText(null);
			a.show();
			
		} else {
			
				if (cbAtoTipo.getValue() == null  ||
						tfAto.getText().isEmpty() ||
						tfAtoSEI.getText().isEmpty() 
						) 
				{
					
					Alert a = new Alert (Alert.AlertType.ERROR);
					a.setTitle("Alerta!!!");
					a.setContentText("Informe: Tipo de Ato, Número do Ato!!!");
					a.setHeaderText(null);
					a.show();
					
				} else {
				
				Ato ato = new Ato();
				
				ato.setAtoTipo(cbAtoTipo.getValue());
				ato.setAtoIdentificacao(tfAto.getText());
				ato.setAtoSEI(tfAtoSEI.getText());
				
				ato.setAtoAtualizacao(LocalDateTime.now());
				
				if (dpDataFiscalizacao.getValue() == null) {
					ato.setAtoDataFiscalizacao(null);}
					else {
						ato.setAtoDataFiscalizacao(dpDataFiscalizacao.getValue()); // DATA
					}
				
				if (dpDataCriacaoAto.getValue() == null) {
					ato.setAtoDataCriacao(null);}
					else {
						ato.setAtoDataCriacao(dpDataCriacaoAto.getValue()); // DATA
					}
				
				ato.setAtoCaracterizacao(htmlCaracterizar.getHtmlText());
				
				ato.setAtoVistoriaFK(vistoria);
				
				AtoDao atoDao = new  AtoDao();
				
				atoDao.mergeAto(ato);
				
				selecionarAto();
				
				
				modularBotoes();
				fecharEditorHTML();
				
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Parabéns!!!");
				a.setContentText("Cadastro salvo com sucesso!!!");
				a.setHeaderText(null);
				a.show();
		}
		}
	}
	
	public void btnEditarHab (ActionEvent event) {
		
		if (cbAtoTipo.isDisable()) {
			
			cbAtoTipo.setDisable(false);
			tfAto.setDisable(false);
			tfAtoSEI.setDisable(false);
			
			dpDataFiscalizacao.setDisable(false);
			dpDataCriacaoAto.setDisable(false);
			
			abrirEditorHTML();
			
			
		} else {
			
			if (cbAtoTipo.getValue() == null  ||
					tfAto.getText().isEmpty() ) 
			{
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Informe: Tipo de Ato, Número do Ato!!!");
				a.setHeaderText(null);
				a.show();
				
			} else {
			
			Ato ato = tvLista.getSelectionModel().getSelectedItem();
			
			ato.setAtoTipo(cbAtoTipo.getValue());
			ato.setAtoIdentificacao(tfAto.getText());
			ato.setAtoSEI(tfAtoSEI.getText());
			ato.setAtoAtualizacao(LocalDateTime.now());
			
			if (dpDataFiscalizacao.getValue() == null) {
				ato.setAtoDataFiscalizacao(null);}
				else {
					ato.setAtoDataFiscalizacao(dpDataFiscalizacao.getValue()); // DATA
				}
			
			if (dpDataCriacaoAto.getValue() == null) {
				ato.setAtoDataCriacao(null);}
				else {
					ato.setAtoDataCriacao(dpDataCriacaoAto.getValue()); // DATA
				}

			ato.setAtoCaracterizacao(htmlCaracterizar.getHtmlText());
			
			AtoDao atoDao = new AtoDao();
			
				atoDao.mergeAto(ato);
			
			obsList.remove(ato);
			
			obsList.add(ato);
			
			selecionarAto();
			
			modularBotoes();
			fecharEditorHTML();
			
			Alert a = new Alert (Alert.AlertType.INFORMATION);
			a.setTitle("Parabéns!!!");
			a.setContentText("Cadastro editado com sucesso!!!");
			a.setHeaderText(null);
			a.show();
			
			}
			
		}
		
	}

	public void btnExcluirHab (ActionEvent event) {
		
		try {
	
			Ato ato = tvLista.getSelectionModel().getSelectedItem();
			
			AtoDao atoDao = new AtoDao();
			
			atoDao.removerAto(ato.getAtoID());
			
			obsList.remove(ato);
			
			selecionarAto();
			
			modularBotoes();
			
			fecharEditorHTML();
			
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
	
	public void btnCancelarHab (ActionEvent event) {
		
		modularBotoes();
		fecharEditorHTML();
	}
	
	
public void btnPesquisarHab (ActionEvent event) {
		
		strPesquisa = tfPesquisar.getText();
		
		listarAtos(strPesquisa);
		selecionarAto ();
		
		modularBotoes();
		fecharEditorHTML();
	
	}
	
	public void modularBotoes () {
		
		cbAtoTipo.setDisable(true);
		tfAto.setDisable(true);
		tfAtoSEI.setDisable(true);
		
		dpDataFiscalizacao.setDisable(true);
		dpDataCriacaoAto.setDisable(true);
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		 
		btnNovo.setDisable(false);
	}
	
	@FXML Pane pAto;
	@FXML AnchorPane apAtoInt;
	@FXML BorderPane bpAto;
	
//  metodo para listar interferencias  //
 	public void listarAtos (String strPesquisaAto) {
 	
	 	// --- conexão - listar endereços --- //
		AtoDao atoDao = new AtoDao();
		List<Ato> atoList = atoDao.listAto(strPesquisaAto);
		
		if (!obsList.isEmpty()) {
			obsList.clear();
		}
		
			for (Ato ato : atoList) {
				
					
				ato.getAtoID();
				ato.getAtoVistoriaFK();
				ato.getAtoTipo();
				ato.getAtoIdentificacao();
				ato.getAtoSEI();
				ato.getAtoCaracterizacao();
				ato.getAtoDataFiscalizacao();
				ato.getAtoDataCriacao();
				
				obsList.add(ato);
			
		}
		
 	}
		
 	// método selecionar interferência //
  	public void selecionarAto () {
 	
 		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
 			
 			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
 			
 			Ato ato = (Ato) newValue;
 			
 			if (ato == null) {
 				
 				btnNovo.setDisable(true);
 				btnSalvar.setDisable(true);
 				btnEditar.setDisable(false);
 				btnExcluir.setDisable(false);
 				btnCancelar.setDisable(false);
 				
 			} else {

 				// -- preencher os campos -- //
 				cbAtoTipo.setValue(ato.getAtoTipo());
 				tfAto.setText(ato.getAtoIdentificacao());
 				tfAtoSEI.setText(ato.getAtoSEI());
 				
 				
 				if (ato.getAtoDataFiscalizacao() == null) {
 					dpDataFiscalizacao.getEditor().clear();
	 				} else {
	 					dpDataFiscalizacao.setValue(ato.getAtoDataFiscalizacao());
	 				}
 				
 				if (ato.getAtoDataCriacao() == null) {
 					dpDataCriacaoAto.getEditor().clear();
	 				} else {
	 					dpDataCriacaoAto.setValue(ato.getAtoDataCriacao());
	 				}
 				
 				htmlCaracterizar.setHtmlText(ato.getAtoCaracterizacao());
 				
 				FormatoData d = new FormatoData();
				
				// mostrar data de atualizacao //
				try {lblDataAtualizacao.setText("Data de Atualização: " + d.formatarData(ato.getAtoAtualizacao()));
						lblDataAtualizacao.setTextFill(Color.BLACK);
				}catch (Exception e) {lblDataAtualizacao.setText("Não há data de atualização!");
						lblDataAtualizacao.setTextFill(Color.RED);}
 					
 				//-- mudar a vistoria de acordo com a seleçao --//
				//visGeral = ato.getAtoVistoriaFK();
 				
 				vistoria = ato.getAtoVistoriaFK();
				
 				
 				lblVistoria2.setText(
 						"Vistoria n°: " + vistoria.getVisSEI()
 						+ ", Data da fiscalização: " + vistoria.getVisDataFiscalizacao()
 				);
				
				// copiar número do ato  sei ao selecionar //
				Clipboard clip = Clipboard.getSystemClipboard();
                ClipboardContent conteudo = new ClipboardContent();
                conteudo.putString(ato.getAtoSEI());
                clip.setContent(conteudo);
                
				//-- modular botoes --//
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
				
 				}
 			}
 		});
  	}
  	
  	public void caracterizarHTML () {
  		
  		
		
		htmlCaracterizar.setPrefSize(800, 200);
		htmlCaracterizar.setLayoutX(165);
		htmlCaracterizar.setLayoutY(467);
		
		
		/*
		htmlCaracterizar.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.SPACE  
			            || event.getCode() == KeyCode.TAB ) {
			        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
			        event.consume();
			    }
			});
			*/
		
		//htmlCaracterizar.setHtmlText("<p><font face='Times New Roman'> </font></p>");
		
		htmlCaracterizar.setHtmlText( 
	    		"<p><b>This text is bold</b></p>"
	    		+ "<p><i>This text is italic</i></p>"
	    		+ "<p>This is<sub> subscript</sub> and <sup>superscript</sup></p>"
	    		
	    		);
	    
			
			//StackPane rootCaracterizacao = new StackPane();
			//rootCaracterizacao.getChildren().add(htmlCaracterizar);
			pAto.getChildren().add(htmlCaracterizar);
  	}
  	
  	public void btnGerarAtoHab (ActionEvent event) {
  		
  	}
	
  	
  	public void fecharEditorHTML (){
		htmlCaracterizar.setDisable(true);
	}
	public void abrirEditorHTML (){
		htmlCaracterizar.setDisable(false);
	}

	

}
