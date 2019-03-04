package cup.objects;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DDAllAccountTransHistory {

	String accountnumber;
	double amount;
	String status, type;
	Date paydate;
	String receiptnumber;
}
