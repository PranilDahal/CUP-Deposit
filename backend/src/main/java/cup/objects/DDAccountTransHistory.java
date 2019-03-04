package cup.objects;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DDAccountTransHistory {

	String accountnumber;
	double amount;
	String status, type;
	Date paydate;
	String receiptnumber;
}
