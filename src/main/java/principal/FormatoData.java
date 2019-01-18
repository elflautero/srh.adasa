package principal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatoData {
	
	public String formatarData (Timestamp timestamp) {
		
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);

	}
	
}
