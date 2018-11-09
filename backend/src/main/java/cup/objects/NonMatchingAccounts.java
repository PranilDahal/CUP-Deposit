package cup.objects;

import java.sql.Date;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class NonMatchingAccounts {
	
	public String name;
	
	public String typename;
	
	public String lastchangedby;
	
	public Date lastchangedon;
	
	public String companyname;
	
	public String firstname;
	
	public String lastname;
	
	public String email;

}
