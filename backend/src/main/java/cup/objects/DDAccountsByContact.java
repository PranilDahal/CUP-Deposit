package cup.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DDAccountsByContact {
	String globalentityname, firstname, lastname, AccountName, AccountNumber;
	double balance;
}
