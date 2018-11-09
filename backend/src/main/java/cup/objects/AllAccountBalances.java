package cup.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllAccountBalances {
	
	public String 	planNumber, serviceArea,
					supDist, companyName, 
					firstName, lastName,
					acctName, acctDesc,
					 accountType;
	public double balance;

}
