package principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatoData {
	
	
	public String formatarData (LocalDateTime d) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss");

		String agoraFormatado = d.format(formatter);
		
		return agoraFormatado;

	}
	
	/*
	 
	// capturar demanda para a TabEnderecoController
	Demanda dGeral = new Demanda ();
	
	// formatar data para  mostar no label (data de atualizacao do documento)
	DateTimeFormatter formatterDT = new DateTimeFormatterBuilder()
		.parseCaseInsensitive()
		.append(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm:ss"))
		.toFormatter();
	
	
	 */

}
