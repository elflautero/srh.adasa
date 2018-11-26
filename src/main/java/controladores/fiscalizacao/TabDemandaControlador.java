package controladores.fiscalizacao;

import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class TabDemandaControlador implements Initializable {
	
	// --- String para primeira pesquisa --- //
	String strPesquisa = "";
	
	
	@FXML TextField tfDocumento = new TextField();
	@FXML TextField tfDocSei = new TextField();
	@FXML TextField tfProcSei =  new TextField();
	@FXML TextField tfResDen = new TextField();
	@FXML TextField tfPesquisar = new TextField();
	
	// ----- Label - endereco da demanda ------ //
	@FXML Label lblDenEnd = new Label ();

	@FXML Button btnNovo = new Button();
	@FXML Button btnSalvar = new Button();
	@FXML Button btnEditar = new Button();
	@FXML Button btnExcluir = new Button();
	@FXML Button btnCancelar = new Button();
	@FXML Button btnPesquisar = new Button();
	@FXML Button btnSair = new Button();
	
	@FXML DatePicker dpDataDistribuicao = new DatePicker();
	@FXML DatePicker dpDataRecebimento = new DatePicker();
	
	// -- Tabela --  //
	@FXML private TableView <Demanda> tvLista;
	
	// -- Colunas -- //
	@FXML private TableColumn<Demanda, String> tcDocumento;
	@FXML private TableColumn<Demanda, String> tcDocSEI;
	@FXML private TableColumn<Demanda, String> tcProcSEI;
	
	@FXML DatePicker dpDoc;
	
	
	public void btnNovoHabilitar (ActionEvent event) {
		
	}
	
	public void btnSalvarSalvar (ActionEvent event) { 
		
		Demanda demanda = new Demanda();
		
		//demanda.setDemDocumento(tfDocumento.getText()); 
		
		DemandaDao demDao = new DemandaDao();
		
		demDao.salvarDemanda(demanda);
		
		
	}
	
	public void btnEditarHabilitar (ActionEvent event) {
		
	}
	
	public void btnExcluirHabilitar (ActionEvent event) {
		
	}
	
	public void btnCancelarHabilitar (ActionEvent event) {
		
	}
	
	public void btnPesquisarHabilitar (ActionEvent event) {
		
		//strPesquisa = (String) tfPesquisar.getText();
		
		listarDemandas(strPesquisa);
		//selecionarDemanda();
		
		//modularBotoesInicial (); 
		
	}
	
	
	
	//ControladorPrincipal controladorPrincipal;
	
	// métodos de remimensionar as tabs //
		public void redimWid (Number newValue) {
					apDemanda.setMinWidth((double) newValue);
					
				}
		public void redimHei (Number newValue) {
					apDemanda.setMinHeight((double) newValue);;
				}
		
		
		@FXML AnchorPane apDemanda = new AnchorPane();
		@FXML ScrollPane spDemanda;
		@FXML AnchorPane apDemInt;
		@FXML BorderPane  bpDemanda;
		@FXML Pane pDemanda;
		
		@FXML Label lblDataAtualizacao;
		
		
	public void initialize(URL url, ResourceBundle rb) {
		
		spDemanda.setCache(false);
	    spDemanda.getChildrenUnmodifiable();
	    
	    btnCancelar.setCache(false);
	    apDemInt.setCache(false);
	    bpDemanda.setCache(false);
	    pDemanda.setCache(false);
	    
		// redimensionamento da tab ato
		AnchorPane.setTopAnchor(spDemanda, 0.0);
	    AnchorPane.setLeftAnchor(spDemanda, 0.0);
		AnchorPane.setRightAnchor(spDemanda, 0.0);
	    AnchorPane.setBottomAnchor(spDemanda, 35.0);
	    
	    apDemInt.setMaxHeight(1200);
	    apDemInt.setMinHeight(1200);
	    
	    apDemanda.widthProperty().addListener((obs, oldVal, newVal) -> {
	    	
			bpDemanda.setMaxWidth((Double) newVal);
			bpDemanda.setMinWidth((Double) newVal);
			
	    });
	    
	   
		
		//tcDocumento.setCellValueFactory(new PropertyValueFactory<Demanda,String>("demDocumento"));
		
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
    		
    		obsList.add(d);
    		
    		
    		System.out.println(d.getDemDocumento());
    		
    		
    		/*
    		System.out.println("id interferencia " + i.getIdInterferencia() + " e id empreendimento " 
    						+ i.getIdEmpreendimento() + " e id uh " + i.getIdUH());
    						*/
    		
    	} // fim loop for
		
		
		/*
		for (Demanda demanda : demandaList) {
			
			DemandaTabela denTab = new DemandaTabela(
					demanda.getDemID(),
					demanda.getDemDocumento(),
					demanda.getDemDocumentoSEI(),
					demanda.getDemProcessoSEI(),
					demanda.getDemDescricao(),
					demanda.getDemDistribuicao(),
					demanda.getDemRecebimento(),
					
					demanda.getDemAtualizacao(),
					
					demanda.getDemEnderecoFK()
					);
			
				obsList.add(denTab);
				
		} // fim loop for
		*/
		
		
		// para trazer o resultado por id (do maior para o menor) //
		//Comparator<DemandaTabela> comparar = Comparator.comparing(DemandaTabela::getDemID);
		//obsList.sort(comparar.reversed());
		
       // tvLista.setItems(obsList); 
        
	}
	
	
}




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
