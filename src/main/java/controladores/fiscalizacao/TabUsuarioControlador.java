package controladores.fiscalizacao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.UsuarioDao;
import entidades.Endereco;
import entidades.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TabUsuarioControlador implements Initializable {
	
	TabAtoControlador tabAtoControlador = new TabAtoControlador ();
	
	static Endereco endereco = new Endereco ();
	
	public void setEndereco (Endereco endereco) {
		
		TabUsuarioControlador.endereco = endereco;
		
		// preencher o label com a demanda selecionada //
		TabUsuarioControlador.lblEndereco2.setText(
				endereco.getEndLogadouro() 
				+ ", Cidade: " + endereco.getEndCidade()
				+ ", CEP: " + endereco.getEndCEP()
				);
			
	}
	
	public static Endereco getEndereco () {
		return endereco;
	}
	
	
	@FXML Pane p_lbl_Endereco;
	@FXML Label lblEndereco1; // captura layout x y, tamanho e largura para lblDemanda2
	static Label lblEndereco2;

	//-- Strings --//
		String strPesquisa = "";
		
		ObservableList<Usuario> obsList = FXCollections.observableArrayList();
		
		@FXML Pane tabUsuario = new Pane();
		
		@FXML TextField tfNome;
		@FXML TextField tfCPFCNPJ;
		@FXML TextField tfLogadouro;
		@FXML TextField tfCEP;
		@FXML TextField tfCidade;
		@FXML TextField tfTelefone;
		@FXML TextField tfCelular;
		@FXML TextField tfEmail;
		
		
		//-- Persistência --//
		
		@FXML Button btnNovo;
		@FXML Button btnSalvar;
		@FXML Button btnEditar;
		@FXML Button btnExcluir;
		@FXML Button btnCancelar;
		@FXML TextField tfPesquisar;
		@FXML Button btnPesquisar;

		
		@FXML CheckBox cbEndEmp;
		
		//-- TableView Endereço --//
		@FXML private TableView <Usuario> tvLista;
		
		@FXML TableColumn<Usuario, String> tcNome;
		@FXML TableColumn<Usuario, String> tcCPFCNPJ;
		@FXML TableColumn<Usuario, String> tcEndereco;

		@FXML Pane pUsuario;
		@FXML Label lblEndUsuario;
		
	
	@FXML AnchorPane apPrincipal = new AnchorPane();
	@FXML AnchorPane apPrin1 = new AnchorPane();
	@FXML AnchorPane apPrin2 = new AnchorPane();
	@FXML BorderPane bpPrincipal = new BorderPane();
	@FXML ScrollBar sbPrincipal = new ScrollBar();
	
	//-- combobox - tipo de pessoa --//
		@FXML
		ChoiceBox<String> cbTipoPessoa = new ChoiceBox<String>();
			ObservableList<String> olTipoPessoa = FXCollections
				.observableArrayList("Física" , "Jurídica"); // box - seleção pessoa físcia ou jurídica
			
		//-- combobox - Região Administrativa --//	
		@FXML
		ChoiceBox<String> cbRA = new ChoiceBox<String>();
			ObservableList<String> olRA = FXCollections
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
			ChoiceBox<String> cbUF = new ChoiceBox<String>();
				ObservableList<String> olDF = FXCollections
					.observableArrayList("DF" , "GO", "Outro"); // box - seleção pessoa físcia ou jurídica
				
				
	
	public void btnNovoHab (ActionEvent event) {
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText(null);
		tfCPFCNPJ.setText(null);
		tfLogadouro.setText(null);
		
		cbRA.setValue(null);
		
		tfCEP.setText(null);
		tfCidade.setText("Brasília");
		
		cbUF.setValue("DF");
		
		tfTelefone.setText(null);
		tfCelular.setText(null);
		tfEmail.setText(null);
		
		cbTipoPessoa.setDisable(false);
		
		tfNome.setDisable(false);
		tfCPFCNPJ.setDisable(false);  //tfEndUF.setDisable(false);
		
		cbEndEmp.setDisable(false);
		
		tfLogadouro.setDisable(false);
		cbRA.setDisable(false);
		tfCEP.setDisable(false);
		tfCidade.setDisable(false);
		cbUF.setDisable(false);
		tfTelefone.setDisable(false);
		tfCelular.setDisable(false);
		tfEmail.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
		
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
		
		if (endereco.getEndLogadouro() == null) {
			
			Alert aLat = new Alert (Alert.AlertType.ERROR);
			aLat.setTitle("Alerta!!!");
			aLat.setContentText("Endereço relacionado ao usuário não selecionado!!!");
			aLat.setHeaderText(null);
			aLat.show();
			
		} else {
			
				if (cbTipoPessoa.getValue() == null  ||
						tfNome.getText().isEmpty()
						
						) {
					
					Alert a = new Alert (Alert.AlertType.ERROR);
					a.setTitle("Alerta!!!");
					a.setContentText("Informe: Tipo e Nome do Usuário!!!");
					a.setHeaderText(null);
					a.show();
					
				} else {
		
						Usuario usuario = new  Usuario ();
						
							usuario.setUsTipo(cbTipoPessoa.getValue());
							usuario.setUsNome(tfNome.getText());
							usuario.setUsCPFCNPJ(tfCPFCNPJ.getText()); 
							usuario.setUsLogadouro(tfLogadouro.getText());
							usuario.setUsRA(cbRA.getValue());
							usuario.setUsCidade(tfCidade.getText());
							usuario.setUsEstado(cbUF.getValue());
							usuario.setUsCEP(tfCEP.getText());
							usuario.setUsTelefone(tfTelefone.getText());
							usuario.setUsCelular(tfCelular.getText());
							usuario.setUsEmail(tfEmail.getText());
							
						
						Endereco end = new Endereco();
						
							end = endereco;
							//end.getUsuarios().add(usuario);
							usuario.setUsEnderecoFK(end);
							
						UsuarioDao  usDao = new UsuarioDao();
						
							usDao.salvarUsuario(usuario);
							usDao.mergeUsuario(usuario);
						
						obsList.add(usuario);
						
						
						//-- selecionar --//
						selecionarUsuario();
						
						modularBotoesInicial();
						
						Alert a = new Alert (Alert.AlertType.INFORMATION);
						a.setTitle("Parabéns!!!");
						a.setContentText("Informe: Cadastro salvo com sucesso!!!");
						a.setHeaderText(null);
						a.show();
				
						
				}
		}
			
	}
	
	public void btnEditarHab (ActionEvent event) {
		
		if (cbTipoPessoa.isDisable()) {
			
			cbTipoPessoa.setDisable(false);
			tfNome.setDisable(false);
			
			cbEndEmp.setDisable(false);
			
			tfCPFCNPJ.setDisable(false);
			tfLogadouro.setDisable(false);
			cbRA.setDisable(false);
			tfCEP.setDisable(false);
			tfCidade.setDisable(false);
			cbUF.setDisable(false);
			tfTelefone.setDisable(false);
			tfCelular.setDisable(false);
			tfEmail.setDisable(false);
			
			btnSalvar.setDisable(true);
			btnEditar.setDisable(false);
			btnExcluir.setDisable(true);
			btnCancelar.setDisable(false);
			
		}
		
		else {
			
			if (cbTipoPessoa.getValue() == null  ||
					tfNome.getText().isEmpty()
					
					) {
				
				Alert a = new Alert (Alert.AlertType.ERROR);
				a.setTitle("Alerta!!!");
				a.setContentText("Informe: Tipo e Nome do Usuário!!!");
				a.setHeaderText(null);
				a.show();
				
			} else {
	
			//*  //eGeralUs
			Usuario usuario = tvLista.getSelectionModel().getSelectedItem(); 
			
			// -- preencher os campos -- //
			usuario.setUsTipo(cbTipoPessoa.getValue()); 
			usuario.setUsNome(tfNome.getText());
			usuario.setUsCPFCNPJ(tfCPFCNPJ.getText());
			usuario.setUsLogadouro(tfLogadouro.getText()); 
			
			usuario.setUsRA(cbRA.getValue()); 
			
			usuario.setUsCEP(tfCEP.getText()); 
			usuario.setUsCidade(tfCidade.getText()); 
			
			usuario.setUsEstado(cbUF.getValue()); 
			
			usuario.setUsTelefone(tfTelefone.getText());
			usuario.setUsCelular(tfCelular.getText());
			usuario.setUsEmail(tfEmail.getText());
			
			usuario.setUsEnderecoFK(endereco);
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.mergeUsuario(usuario);
			
			obsList.remove(usuario);
			obsList.add(usuario);
			
			//-- selecionar --//
			selecionarUsuario();
			
			modularBotoesInicial();
			
			Alert a = new Alert (Alert.AlertType.INFORMATION);
			a.setTitle("Parabéns!!!");
			a.setContentText("Informe: Cadastro editado com sucesso!!!");
			a.setHeaderText(null);
			a.show();
			
			
			}
			
		}
		
	}
	
	public void btnExcluirHab (ActionEvent event) {
		
		try {
			//-- capturar usuário selecionado --//
			Usuario usuario = tvLista.getSelectionModel().getSelectedItem(); 
			
			UsuarioDao usDao = new UsuarioDao();
			
			usDao.removerUsuario(usuario.getUsID());
			
			obsList.remove(usuario);
			
			//-- selecionar --//
			selecionarUsuario();
			modularBotoesInicial();
		
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
		
		modularBotoesInicial ();
		
		cbTipoPessoa.setValue(null);
		
		tfNome.setText("");
		tfCPFCNPJ.setText("");
		tfLogadouro.setText("");
		
		cbRA.setValue(null);
		
		tfCEP.setText("");
		tfCidade.setText("");
		
		cbUF.setValue(null);
		
		tfTelefone.setText("");
		tfCelular.setText("");
		tfEmail.setText("");
		
	}
	
	//-- botão pesquisar usuário --//
	public void btnPesquisarHab (ActionEvent event) {
		
		strPesquisa = tfPesquisar.getText();
		
		listarUsuarios (strPesquisa);
		
		selecionarUsuario ();
		
		modularBotoesInicial();
		
	}

	public void cbEndEmpHab (ActionEvent event) {
		
		int count = 0;
		
		if (cbEndEmp.isSelected()) {
			count ++;
			try{tfLogadouro.setText(endereco.getEndLogadouro());}catch (Exception e) {tfLogadouro.setText(null);};
			try{cbRA.setValue(endereco.getEndRAFK().getRaNome());}catch (Exception e) {cbRA.setValue(null);};
			try{tfCEP.setText(endereco.getEndCEP());}catch (Exception e) {tfCEP.setText(null);};
			try{tfCidade.setText(endereco.getEndCidade());}catch (Exception e) {tfCidade.setText(null);};
			try{cbUF.setValue(endereco.getEndUF());}catch (Exception e) {cbUF.setValue(null);};
			
		}
		System.out.println("check box usuario - endereço: " + count);
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
	    
	    
	 // Label para preencher com a demanda a ser trabalhada //
	    lblEndereco2 = new Label();
	    lblEndereco2.setStyle("-fx-font-weight: bold;");
	    lblEndereco2.setPrefSize(641, 26);	
	    lblEndereco2.setLayoutX(206);
	    lblEndereco2.setLayoutY(12);
		
		p_lbl_Endereco.getChildren().add(lblEndereco2);
		
		cbTipoPessoa.setValue("Física");
		cbTipoPessoa.setItems(olTipoPessoa);
		
		cbRA.setItems(olRA);
		
		cbUF.setValue("DF");
		cbUF.setItems(olDF);
		
		tcNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usNome"));
		tcCPFCNPJ.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usCPFCNPJ"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usLogadouro"));
		
		tvLista.setItems(obsList);
		
		modularBotoesInicial();
	}
	
	private void modularBotoesInicial () {
		
		cbTipoPessoa.setDisable(true);
		tfNome.setDisable(true); 
		tfCPFCNPJ.setDisable(true);
		
		cbEndEmp.setDisable(true);
		
		tfLogadouro.setDisable(true);
		
		cbRA.setDisable(true); 
		
		tfCEP.setDisable(true);
		tfCidade.setDisable(true);
		
		cbUF.setDisable(true);
		
		tfTelefone.setDisable(true);
		tfCelular.setDisable(true);
		tfEmail.setDisable(true);
		
		
		btnSalvar.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		
	}
	
	//-- método listar usuários --//
		public void listarUsuarios (String strPesquisa) {
			
			UsuarioDao usDao = new UsuarioDao();
			List<Usuario> usuarioList = usDao.listarUsuario(strPesquisa);
			obsList = FXCollections.observableArrayList();
			
			
			if (!obsList.isEmpty()) {
				obsList.clear();
			}
			
				for (Usuario usuario : usuarioList) {
					
					
							usuario.getUsID();
							usuario.getUsTipo();
							usuario.getUsNome();
							usuario.getUsCPFCNPJ();
							usuario.getUsLogadouro();
							usuario.getUsRA();
							usuario.getUsCidade();
							usuario.getUsEstado();
							usuario.getUsCEP();
							usuario.getUsTelefone();
							usuario.getUsCelular();
							usuario.getUsEmail();
							
							usuario.getUsEnderecoFK();
							
					obsList.add(usuario);
						
					}
					
					tvLista.setItems(obsList);
		}
			
			
	public void selecionarUsuario () {
			
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				Usuario us = (Usuario) newValue;
				
				if (us == null) {
					
					cbTipoPessoa.setValue(null);
					
					tfNome.setText(null);
					tfCPFCNPJ.setText(null);
					tfLogadouro.setText(null);
					
					cbRA.setValue(null);
					
					tfCEP.setText(null);
					tfCidade.setText(null);
					
					cbUF.setValue(null);
					
					tfTelefone.setText(null);
					tfCelular.setText(null);
					tfEmail.setText(null);
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					// -- preencher os campos -- //
					cbTipoPessoa.setValue(us.getUsTipo());
					
					tfNome.setText(us.getUsNome());
					tfCPFCNPJ.setText(us.getUsCPFCNPJ());
					tfLogadouro.setText(us.getUsLogadouro());
					
					cbRA.setValue(us.getUsRA());
					
					tfCEP.setText(us.getUsCEP());
					tfCidade.setText(us.getUsCidade());
					
					cbUF.setValue(us.getUsEstado());
					
					tfTelefone.setText(us.getUsTelefone());
					tfCelular.setText(us.getUsCelular());
					tfEmail.setText(us.getUsEmail());
					
					// mudar o endereço relacionado //
					setEndereco(us.getUsEnderecoFK());
					
					//eGeralUs = usTab.getEnderecoUsObjetoTabelaFK();
					//lblEndUsuario.setText(eGeralUs.getDesc_Endereco()  + " |  RA: "  +  eGeralUs.getRA_Endereco());
					
					// copiar cpf do usuario ao selecionar //
					Clipboard clip = Clipboard.getSystemClipboard();
	                ClipboardContent conteudo = new ClipboardContent();
	                conteudo.putString(us.getUsCPFCNPJ());
	                clip.setContent(conteudo);
	                
					// -- habilitar e desabilitar botões -- //
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					
				}
				}
			});
	}

}
