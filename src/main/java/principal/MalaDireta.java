package principal;

import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import entidades.Endereco;
import entidades.Interferencia;
import entidades.Usuario;

public class MalaDireta {
	
	String htmlRel;
	
	Usuario us = new Usuario ();
	Endereco end = new Endereco();
	Interferencia inter = new Interferencia();
	
	public String getHtmlRel() {
		return htmlRel;
	}

	public void setHtmlRel(String htmlRel) {
		this.htmlRel = htmlRel;
	}
	
	public MalaDireta () {
		
	}
	
	public MalaDireta (Usuario us, Endereco end, Interferencia inter) {
		this.us = us;
		this.end = end;
		this.inter = inter;
	}

	public String criarDocumento () {
		
		Document docHtml = null;
		
		docHtml = Jsoup.parse(htmlRel, "UTF-8").clone();
		
		if (us.getUsNome() != null ) {
			
			String strPosicoesUsuario [] = {
					
					"usnome",
						"uscpfcnpj",
							"ustelefone",
								"usCelular",
									"usendcor",
										"uscep",
											"usemail"};
			String strUsuario [] = {
					us.getUsNome(),
						us.getUsCPFCNPJ(),						
							us.getUsTelefone()	,
								us.getUsCelular(),
									us.getUsLogadouro(),
										us.getUsCEP(),
											us.getUsEmail(),
					};
				
			for (int i  = 0; i<strPosicoesUsuario.length; i++) {
	 				
	 				try { docHtml.select(strPosicoesUsuario[i]).prepend(strUsuario[i]);} 
	 					catch (Exception e) {docHtml.select(strPosicoesUsuario[i]).prepend("");};
	 			}
			
		}
		
		if (end.getEndLogadouro() != null) {
			
			String strPosicoesEndereco [] = {
					
					"endLog",
						"endregadm",
							"endcep",
								};
			String strEndereco [] = {
				
					end.getEndLogadouro(),
						end.getEndRAFK().getRaNome(),						
							end.getEndCEP(),
								
					};
			
			for (int i  = 0; i<strPosicoesEndereco.length; i++) {
	 				
	 				try { docHtml.select(strPosicoesEndereco[i]).prepend(strEndereco[i]);} 
	 					catch (Exception e) {docHtml.select(strPosicoesEndereco[i]).prepend("");};
	 			}
		
		}
		
		if ( inter.getInterTipoInterferenciaFK().getTipoInterID() == 2) {
			
			
             String strPosicoesInterferencia  [] = {
      
            		 "intertipooutorga", 
            		 	"intertipocaptacao", 
            		 		"interCaesb", 
            		 			"interVazao", 
            		 				"interNivEst", 
            		 					"interNivDin", 
            		 						"interProf", 
            		 							"interLat", 
            		 								"interLon", 
            		 									"interDataOper",
            		 										
            		 };
            		 
            String strInterferencia [] = {
            		inter.getInterTipoOutorgaFK().getTipoOutorgaDescricao(), 
            			inter.getIntSubFK().getSubTipoPocoFK().getTipoPocoDescricao(), 
            				inter.getIntSubFK().getSubCaesb(),
            					inter.getIntSubFK().getSubVazao(),
            						inter.getIntSubFK().getSubEstatico(),
            							inter.getIntSubFK().getSubDinamico(),
            								inter.getIntSubFK().getSubProfundidade(),
            									inter.getInterDDLatitude().toString() + ",",
            										inter.getInterDDLongitude().toString(),
            											new SimpleDateFormat("dd/MM/yyyy").format(inter.getIntSubFK().getSubDataOperacao())
            												
            											
  					};
             
             for (int i  = 0; i<strPosicoesInterferencia.length; i++) {
 				
 				try { docHtml.select(strPosicoesInterferencia[i]).prepend(strInterferencia[i]);} 
 					catch (Exception e) {docHtml.select(strPosicoesInterferencia[i]).prepend("");};
 			}
			
			
		} // fim if interferencia id
		
		if (inter.getIntSubFK().getSubFinalidade1().equals("Abastecimento Humano")) {
			
			/*
			 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
			 */
			StringBuilder strAbasHum = new StringBuilder();
			
			strAbasHum
			.append("<div align='justify'><strong style='font-style: italic; font-size: 12px;'>- ABASTECIMENTO HUMANO</strong>"
					+	"<table border='1' cellspacing='0' style='width: 800px;'><tbody><tr><td colspan='1'>"
					+ 	"Popula&ccedil;&atilde;o:&nbsp;")    // populacao
			.append(String.valueOf(inter.getIntSubFK().getSubQuantidade1()))
			.append("</td><td colspan='1'>Consumo diário por habitante:&nbsp;") //  consumo
			.append(String.valueOf(inter.getIntSubFK().getSubConsumo1()))
			.append("</td><td colspan='1' width='20%'>Total:")              // total
			.append(String.valueOf(inter.getIntSubFK().getSubVazao1()));
			
			System.out.println(strAbasHum);
				 
			try { docHtml.select("abasthumtag").prepend(String.valueOf(strAbasHum));} catch (Exception e) {docHtml.select("abasthumtag").prepend("erro");};
					
		}
		
		if (inter.getIntSubFK().getSubFinalidade1().equals("Criação De Animais")) {
			
			/*
			 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
			 */
			StringBuilder strCriacaoAnimais = new StringBuilder();
			
			strCriacaoAnimais
			.append("<strong style='font-style: italic; font-size: 12px;'>- CRIA&Ccedil;&Atilde;O DE ANIMAIS</strong>"
					+ "<table border='1' cellspacing='0' style='width: 800px;'>"
					+ "<tbody><tr><td colspan='2'>Esp&eacute;cie:&nbsp;")                         // especie
			.append(String.valueOf(inter.getIntSubFK().getSubSubfinalidade1()))
			.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;")   // total
			.append(String.valueOf(inter.getIntSubFK().getSubVazao1()))
			.append("</span></td></tr><tr><td colspan='1'>Quantidade:&nbsp;")          // quantidade
			.append(String.valueOf(inter.getIntSubFK().getSubQuantidade1()))
			.append("</td><td colspan='1'>Consumo:&nbsp;")                    // consumo
			.append(String.valueOf(inter.getIntSubFK().getSubConsumo1()));
			
			System.out.println(strCriacaoAnimais);
				 
			try { docHtml.select("criacaoaniamistag").prepend(String.valueOf(strCriacaoAnimais));} catch (Exception e) {docHtml.select("criacaoaniamistag").prepend("erro");};
					
		}
		
		
		if (inter.getIntSubFK().getSubFinalidade1().equals("Irrigação")) {
			
			/*
			 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
			 */
			StringBuilder strIrrigacao = new StringBuilder();
			
			strIrrigacao
			.append("<strong style='font-style: italic; font-size: 12px;'>- IRRIGA&Ccedil;&Atilde;O</strong>" + 
					"<table border='1' cellspacing='0' style='width: 800px;'>")
			.append("<tbody><tr><td colspan='2'>Esp&eacute;cie:&nbsp;") //  cultura
			.append(String.valueOf(inter.getIntSubFK().getSubSubfinalidade1()))
			.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
			.append(String.valueOf(inter.getIntSubFK().getSubVazao1()))
			.append("</span></td></tr><tr><td colspan='1'>Quantidade:&nbsp;") // quantidade
			.append(String.valueOf(inter.getIntSubFK().getSubQuantidade1()))
			.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
			.append(String.valueOf(inter.getIntSubFK().getSubConsumo1()))
			.append("</td></tr></tbody></table>");
			
			
			try { docHtml.select("irrigacaotag").prepend(String.valueOf(strIrrigacao));} catch (Exception e) {docHtml.select("irrigacaotag").prepend("erro");};
					
		}
		
		if (inter.getIntSubFK().getSubFinalidade1().equals("Uso Industrial" )) {
			
			/*
			 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
			 */
			
			StringBuilder strIrrigacao = new StringBuilder();
			
			strIrrigacao
			.append("<strong style='font-style: italic; font-size: 12px;'>- IND&Uacute;STRIA</strong>" + 
					"<table border='1' cellspacing='0' style='width: 800px;'>")
			.append("<tbody><tr><td colspan='2'>Produto:&nbsp;") //  produto
			.append(String.valueOf(inter.getIntSubFK().getSubSubfinalidade1()))
			.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
			.append(String.valueOf(inter.getIntSubFK().getSubVazao1()))
			.append("</span></td></tr><tr><td colspan='1'>Produ&ccedil;&atilde;o:&nbsp;&nbsp;") // producao
			.append(String.valueOf(inter.getIntSubFK().getSubQuantidade1()))
			.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
			.append(String.valueOf(inter.getIntSubFK().getSubConsumo1()))
			.append("</td></tr></tbody></table>");
			
			try { docHtml.select("industriatag").prepend(String.valueOf(strIrrigacao));} catch (Exception e) {docHtml.select("industriatag").prepend("erro");};
					
		}
		
		
		if (inter.getIntSubFK().getSubFinalidade1().equals("Outras Finalidades")) {
			
			/*
			 * unir tags html e informacoes do banco para  preencher o formulario 'requerimento de outorga'
			 */
			
			StringBuilder strIrrigacao = new StringBuilder();
			
			strIrrigacao
			.append("<strong style='font-style: italic; font-size: 12px;'>- OUTRAS FINALIDADES</strong>" + 
					"<table border='1' cellspacing='0' style='width: 800px;'>")
			.append("<tbody><tr><td colspan='2'>Produto:&nbsp;") //  produto
			.append(String.valueOf(inter.getIntSubFK().getSubSubfinalidade1()))
			.append("</td><td rowspan='3' width='20%'><span style='text-align: justify;'>TOTAL:&nbsp;") // total
			.append(String.valueOf(inter.getIntSubFK().getSubVazao1()))
			.append("</span></td></tr><tr><td colspan='1'>Produ&ccedil;&atilde;o:&nbsp;&nbsp;") // producao
			.append(String.valueOf(inter.getIntSubFK().getSubQuantidade1()))
			.append("</td><td colspan='1'>Consumo:&nbsp;") // consumo
			.append(String.valueOf(inter.getIntSubFK().getSubConsumo1()))
			.append("</td></tr></tbody></table>");
			
			
			try { docHtml.select("outrasfinalidadestag").prepend(String.valueOf(strIrrigacao));} catch (Exception e) {docHtml.select("outrasfinalidadestag").prepend("erro");};
					
		}
		
		

		
		/*
		String strAbasHum [] = 
				
				"<div align='justify'><strong style='font-style: italic; font-size: 12px;'>- ABASTECIMENTO HUMANO</strong>"
						+	"<table border='1' cellspacing='0' style='width: 800px;'><tbody><tr><td colspan='1'>"
						+ 	"Popula&ccedil;&atilde;o:&nbsp;";
					
						
		String strConsTag = 
				"</td><td colspan='1'>Consumo diário por habitante:&nbsp;";
		
		String strTotTag = "</td><td colspan='1' width='20%'>Total:";
		
		String strFecTag = "</td></tr></tbody></table>";
		

		/*
		 	<ahpopulatag></abasthumtab><interpopulacao></interpopulacao>

			<ahconstag></ahcons><intercons></intercons>

			<ahtotaltag></ahtotaltag><intertotal></intertotal>
			
			<ahfechartab></ahfechartab>
			
			ahabasthumanotag
		
		 */
		
		/*
		 String strPosicoesAbasHum  [] = {
			      
        		 "ahpopulatag", 
        		 	"interpopulacao", 
        		 			"ahconstag", 
        		 				"intercons", 
        		 						"ahtotaltag", 
        		 							"intertotal", 
        		 										"ahfechartab"
        		 };
				 
				 String strAbasHum [] = {
						 strPopTag, 
		            			String.valueOf(inter.getIntSubFK().getSubSubfinQuan1())  , 
		            				strConsTag,
		            					String.valueOf(inter.getIntSubFK().getSubSubfinCon1()),
		            						strTotTag,
		            						String.valueOf(inter.getIntSubFK().getSubVazao1())
		  					};
		  					*/
		
		/*
		try { docHtml.select("tipoPoco").prepend(outorga.getTipoPoco());} catch (Exception e) {docHtml.select("tipoPoco").prepend("");};
		
		try { docHtml.select("finalidade1").prepend(outorga.getFinalidade()[0]);} catch (Exception e) {docHtml.select("finalidade1").prepend("");};
		try { docHtml.select("finalidade2").prepend(outorga.getFinalidade()[1]);} catch (Exception e) {docHtml.select("finalidade2").prepend("");};
		try { docHtml.select("finalidade3").prepend(outorga.getFinalidade()[2]);} catch (Exception e) {docHtml.select("finalidade3").prepend("");};
		try { docHtml.select("finalidade4").prepend(outorga.getFinalidade()[3]);} catch (Exception e) {docHtml.select("finalidade4").prepend("");};
		try { docHtml.select("finalidade5").prepend(outorga.getFinalidade()[4]);} catch (Exception e) {docHtml.select("finalidade5").prepend("");};
		
		try { docHtml.select("processo").prepend(outorga.getProcesso());} catch (Exception e) {docHtml.select("processo").prepend("");};
		
		
		try { docHtml.select("cpfCNPJ").prepend(outorga.getCpfCNPJ());} catch (Exception e) {docHtml.select("cpfCNPJ").prepend("");};
		try { docHtml.select("endereco").prepend(outorga.getEndereco());} catch (Exception e) {docHtml.select("endereco").prepend("");};
		
		try { docHtml.select("baciaHid").prepend(outorga.getBacia());} catch (Exception e) {docHtml.select("baciaHid").prepend("");};
		try { docHtml.select("baciaUH").prepend(outorga.getUh());} catch (Exception e) {docHtml.select("baciaUH").prepend("");};
		
		// coordenadas
		try { docHtml.select("pontoCapLat").prepend(outorga.getLat().toString());} catch (Exception e) {docHtml.select("pontoCapLat").prepend("");};
		try { docHtml.select("pontoCapLng").prepend(outorga.getLng().toString());} catch (Exception e) {docHtml.select("pontoCapLng").prepend("");};
		
		
		// VAZAO LITROS HORA
		try { docHtml.select("vazJanLH").prepend(String.valueOf(outorga.getVazaoHora()[0]));} catch (Exception e) {docHtml.select("vazJanLH").prepend("");};
		try { docHtml.select("vazFevLH").prepend(String.valueOf(outorga.getVazaoHora()[1]));} catch (Exception e) {docHtml.select("vazFevLH").prepend("");};
		try { docHtml.select("vazMarLH").prepend(String.valueOf(outorga.getVazaoHora()[2]));} catch (Exception e) {docHtml.select("vazMarLH").prepend("");};
		try { docHtml.select("varAbrLH").prepend(String.valueOf(outorga.getVazaoHora()[3]));} catch (Exception e) {docHtml.select("varAbrLH").prepend("");};
		try { docHtml.select("vazMaiLH").prepend(String.valueOf(outorga.getVazaoHora()[4]));} catch (Exception e) {docHtml.select("vazMaiLH").prepend("");};
		try { docHtml.select("vazJunLH").prepend(String.valueOf(outorga.getVazaoHora()[5]));} catch (Exception e) {docHtml.select("vazJunLH").prepend("");};
		
		try { docHtml.select("vazJulLH").prepend(String.valueOf(outorga.getVazaoHora()[6]));} catch (Exception e) {docHtml.select("vazJulLH").prepend("");};
		try { docHtml.select("vazAgoLH").prepend(String.valueOf(outorga.getVazaoHora()[7]));} catch (Exception e) {docHtml.select("vazAgoLH").prepend("");};
		try { docHtml.select("vazSetLH").prepend(String.valueOf(outorga.getVazaoHora()[8]));} catch (Exception e) {docHtml.select("vazSetLH").prepend("");};
		try { docHtml.select("vazOutLH").prepend(String.valueOf(outorga.getVazaoHora()[9]));} catch (Exception e) {docHtml.select("vazOutLH").prepend("");};
		try { docHtml.select("vazNovLH").prepend(String.valueOf(outorga.getVazaoHora()[10]));} catch (Exception e) {docHtml.select("vazNovLH").prepend("");};
		try { docHtml.select("vazDezLH").prepend(String.valueOf(outorga.getVazaoHora()[11]));} catch (Exception e) {docHtml.select("vazDezLH").prepend("");};
		
		// TEMPO DE CAPTACAO
		try { docHtml.select("tJAN").prepend(String.valueOf(outorga.getTempoCaptacao()[0]));} catch (Exception e) {docHtml.select("tJAN").prepend("");};
		try { docHtml.select("tFEV").prepend(String.valueOf(outorga.getTempoCaptacao()[1]));} catch (Exception e) {docHtml.select("tFEV").prepend("");};
		try { docHtml.select("tMAR").prepend(String.valueOf(outorga.getTempoCaptacao()[2]));} catch (Exception e) {docHtml.select("tMAR").prepend("");};
		try { docHtml.select("tABR").prepend(String.valueOf(outorga.getTempoCaptacao()[3]));} catch (Exception e) {docHtml.select("tABR").prepend("");};
		try { docHtml.select("tMAI").prepend(String.valueOf(outorga.getTempoCaptacao()[4]));} catch (Exception e) {docHtml.select("tMAI").prepend("");};
		try { docHtml.select("tJUN").prepend(String.valueOf(outorga.getTempoCaptacao()[5]));} catch (Exception e) {docHtml.select("tJUN").prepend("");};
		
		try { docHtml.select("tJUL").prepend(String.valueOf(outorga.getTempoCaptacao()[6]));} catch (Exception e) {docHtml.select("tJUL").prepend("");};
		try { docHtml.select("tAGO").prepend(String.valueOf(outorga.getTempoCaptacao()[7]));} catch (Exception e) {docHtml.select("tAGO").prepend("");};
		try { docHtml.select("tSET").prepend(String.valueOf(outorga.getTempoCaptacao()[8]));} catch (Exception e) {docHtml.select("tSET").prepend("");};
		try { docHtml.select("tOUT").prepend(String.valueOf(outorga.getTempoCaptacao()[9]));} catch (Exception e) {docHtml.select("tOUT").prepend("");};
		try { docHtml.select("tNOV").prepend(String.valueOf(outorga.getTempoCaptacao()[10]));} catch (Exception e) {docHtml.select("tNOV").prepend("");};
		try { docHtml.select("tDEZ").prepend(String.valueOf(outorga.getTempoCaptacao()[11]));} catch (Exception e) {docHtml.select("tDEZ").prepend("");};
		
		
		// VAZAO LITROS DIA
		try { docHtml.select("vazJanLD").prepend(String.valueOf(outorga.getVazaoDia()[0]));} catch (Exception e) {docHtml.select("vazJanLD").prepend("");};
		try { docHtml.select("vazFevLD").prepend(String.valueOf(outorga.getVazaoDia()[1]));} catch (Exception e) {docHtml.select("vazFevLD").prepend("");};
		try { docHtml.select("vazMarLD").prepend(String.valueOf(outorga.getVazaoDia()[2]));} catch (Exception e) {docHtml.select("vazMarLD").prepend("");};
		try { docHtml.select("varAbrLD").prepend(String.valueOf(outorga.getVazaoDia()[3]));} catch (Exception e) {docHtml.select("varAbrLD").prepend("");};
		try { docHtml.select("vazMaiLD").prepend(String.valueOf(outorga.getVazaoDia()[4]));} catch (Exception e) {docHtml.select("vazMaiLD").prepend("");};
		try { docHtml.select("vazJunLD").prepend(String.valueOf(outorga.getVazaoDia()[5]));} catch (Exception e) {docHtml.select("vazJunLD").prepend("");};
		
		try { docHtml.select("vazJulLD").prepend(String.valueOf(outorga.getVazaoDia()[6]));} catch (Exception e) {docHtml.select("vazJulLD").prepend("");};
		try { docHtml.select("vazAgoLD").prepend(String.valueOf(outorga.getVazaoDia()[7]));} catch (Exception e) {docHtml.select("vazAgoLD").prepend("");};
		try { docHtml.select("vazSetLD").prepend(String.valueOf(outorga.getVazaoDia()[8]));} catch (Exception e) {docHtml.select("vazSetLD").prepend("");};
		try { docHtml.select("vazOutLD").prepend(String.valueOf(outorga.getVazaoDia()[9]));} catch (Exception e) {docHtml.select("vazOutLD").prepend("");};
		try { docHtml.select("vazNovLD").prepend(String.valueOf(outorga.getVazaoDia()[10]));} catch (Exception e) {docHtml.select("vazNovLD").prepend("");};
		try { docHtml.select("vazDezLD").prepend(String.valueOf(outorga.getVazaoDia()[11]));} catch (Exception e) {docHtml.select("vazDezLD").prepend("");};
		
		try { docHtml.select("subsistema").prepend(outorga.getSubsistema());} catch (Exception e) {docHtml.select("subsistema").prepend("");};
		
		try { docHtml.select("vazaoMedia").prepend(String.format("%.2f", outorga.getVazaoMedia()));} catch (Exception e) {docHtml.select("vazaoMedia").prepend("");};
		try { docHtml.select("vazaoBombeamento").prepend(String.format("%.2f", outorga.getVazaoBombeamento()));} catch (Exception e) {docHtml.select("vazaoBombeamento").prepend("");};
		try { docHtml.select("profundidade").prepend(String.format("%.2f", outorga.getProfundidade()));} catch (Exception e) {docHtml.select("profundidade").prepend("");};
		try { docHtml.select("nivelEstatico").prepend(String.format("%.2f", outorga.getNivelEstatico()));} catch (Exception e) {docHtml.select("nivelEstatico").prepend("");};
		try { docHtml.select("nivelDinamico").prepend(String.format("%.2f", outorga.getNivelDinamico()));} catch (Exception e) {docHtml.select("nivelDinamico").prepend("");};
		
		// SUBFINALIDADE
		try { docHtml.select("subfinalidade1").prepend(outorga.getSubfinalidade()[0]);} catch (Exception e) {docHtml.select("subfinalidade1").prepend("");};
		try { docHtml.select("subfinalidade2").prepend(outorga.getSubfinalidade()[1]);} catch (Exception e) {docHtml.select("subfinalidade2").prepend("");};
		try { docHtml.select("subfinalidade3").prepend(outorga.getSubfinalidade()[2]);} catch (Exception e) {docHtml.select("subfinalidade3").prepend("");};
		try { docHtml.select("subfinalidade4").prepend(outorga.getSubfinalidade()[3]);} catch (Exception e) {docHtml.select("subfinalidade4").prepend("");};
		try { docHtml.select("subfinalidade5").prepend(outorga.getSubfinalidade()[4]);} catch (Exception e) {docHtml.select("subfinalidade5").prepend("");};
		
		// DEMANDA 
		try { docHtml.select("demanda1").prepend(String.format("%.2f", outorga.getDemanda()[0]));} catch (Exception e) {docHtml.select("demanda1").prepend("");};
		try { docHtml.select("demanda2").prepend(String.format("%.2f", outorga.getDemanda()[1]));} catch (Exception e) {docHtml.select("demanda2").prepend("");};
		try { docHtml.select("demanda3").prepend(String.format("%.2f", outorga.getDemanda()[2]));} catch (Exception e) {docHtml.select("demanda3").prepend("");};
		try { docHtml.select("demanda4").prepend(String.format("%.2f", outorga.getDemanda()[3]));} catch (Exception e) {docHtml.select("demanda4").prepend("");};
		try { docHtml.select("demanda5").prepend(String.format("%.2f", outorga.getDemanda()[4]));} catch (Exception e) {docHtml.select("demanda5").prepend("");};
		
		//DEMANDA IN
		try { docHtml.select("demandaIN1").prepend(String.format("%.2f", outorga.getDemandaIN()[0]));} catch (Exception e) {docHtml.select("demandaIN1").prepend("");};
		try { docHtml.select("demandaIN2").prepend(String.format("%.2f", outorga.getDemandaIN()[1]));} catch (Exception e) {docHtml.select("demandaIN2").prepend("");};
		try { docHtml.select("demandaIN3").prepend(String.format("%.2f", outorga.getDemandaIN()[2]));} catch (Exception e) {docHtml.select("demandaIN3").prepend("");};
		try { docHtml.select("demandaIN4").prepend(String.format("%.2f", outorga.getDemandaIN()[3]));} catch (Exception e) {docHtml.select("demandaIN4").prepend("");};
		try { docHtml.select("demandaIN5").prepend(String.format("%.2f", outorga.getDemandaIN()[4]));} catch (Exception e) {docHtml.select("demandaIN5").prepend("");};
		
		// VAZAO EXPLOTAVEL, N POCOS ...
		
		
		
		try { docHtml.select("vazaoExplotavel").prepend(String.format("%.0f", outorga.getVazaoExplotavel()));} catch (Exception e) {docHtml.select("vazaoExplotavel").prepend("");};
		try { docHtml.select("numeroDePocos").prepend(String.valueOf(outorga.getNumeroDePocos()));} catch (Exception e) {docHtml.select("numeroDePocos").prepend("");};
		try { docHtml.select("vazaoTotalOutorgavel").prepend(String.format("%.0f", outorga.getVazaoTotalOutorgavel()));} catch (Exception e) {docHtml.select("vazaoTotalOutorgavel").prepend("");};
		try { docHtml.select("porcentagemUtilizada").prepend(String.format("%.2f", outorga.getPorcentagemUtilizada()));} catch (Exception e) {docHtml.select("porcentagemUtilizada").prepend("");};
		try { docHtml.select("volumeDisponivelAtual").prepend(String.format("%.0f", outorga.getVolumeDisponivelAtual()));} catch (Exception e) {docHtml.select("volumeDisponivelAtual").prepend("");};
		
		try { docHtml.select("volumeDiponivelSuficiente").prepend(outorga.getVolumeDiponivelSuficiente());} catch (Exception e) {docHtml.select("volumeDiponivelSuficiente").prepend("");};
	
		*/
		
		
		String html = new String ();
		
		html = docHtml.toString();
		
		html = html.replace("\"", "'");
		html = html.replace("\n", "");
		
		html =  "\"" + html + "\"";
		
		//-- webview do relat�rio --//
		/*
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.loadContent(html);
		
		Scene scene = new Scene(browser);
		
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1000);
		stage.setHeight(650);
	    stage.setScene(scene);
	    stage.setMaximized(false);
	    stage.setResizable(false);
	    
	    stage.show();
	    */
	    
		return html;
	    
	   // //TabNavegadorController.html = html;
	   // TabNavegadorController.numIframe = 1;

	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Interferencia getInter() {
		return inter;
	}

	public void setInter(Interferencia inter) {
		this.inter = inter;
	}
	
	

}
