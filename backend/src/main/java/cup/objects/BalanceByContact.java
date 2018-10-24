package cup.objects;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceByContact {
	
	public String transGlobalName, accountNumber,
	typeName, accountName, description, globalEntityId, caTransactionId, transactionType;
	
	public Date transactionDate;
	
	public String receiptNumber;
	
	public int paymentAmount1;
	
	public String receivedBy;
	
	public Date paymentDate;
	
	public String paymentMethod;
	
	public int paymentAmount2, deposit, withdrawal, balance;
	
}
