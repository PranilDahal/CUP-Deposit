package cup.objects;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceByContact {
	
	public String transGlobalName, accountNumber,
	typeName, accountName, description;
	
	public int globalEntityId, caTransactionId;
	
	public String transactionType;
	
	public Date transactionDate;
	
	public String receiptNumber;
	
	public double paymentAmount1;
	
	public String receivedBy;
	
	public Date paymentDate;
	
	public String paymentMethod;
	
	public double paymentAmount2, deposit, withdrawal, balance;
	
}
