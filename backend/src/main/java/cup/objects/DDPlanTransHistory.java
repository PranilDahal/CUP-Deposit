package cup.objects;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DDPlanTransHistory {

	String Contact, plannumber;
	double paymentamount, balance;
	String description;
	Date date;
	String receiptnumber;
}
