package cup.objects.dam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cup.objects.BalanceByContact;

@Component
public class BalanceByContactExtractor {

	private static final String GET_BALANCES_BY_CONTACT = "SELECT distinct CATRANSACTION.GLOBALENTITYNAME as TRANS_GLOBAL_NAME,\r\n"
			+ "  	GLOBALENTITYACCOUNT.ACCOUNTNUMBER, GLOBALENTITYACCOUNTTYPE.TYPENAME ,GLOBALENTITYACCOUNT.NAME AS ACCOUNT_NAME, GLOBALENTITYACCOUNT.DESCRIPTION,\r\n"
			+ "       CATransaction.GlobalEntityID, CATRANSACTION.CATRANSACTIONID,   CATransactionType.Name AS TransactionType, CATRANSACTION.TRANSACTIONDATE,\r\n"
			+ "	   CATRANSACTION.RECEIPTNUMBER,\r\n" + "	   	   CATRANSACTIONPAYMENT.PAYMENTAMOUNT, \r\n"
			+ "		  USERS.FNAME + ' ' + USERS.LNAME as RECEIVEDBY, \r\n"
			+ "       CATransactionPayment.PaymentDate, CAPaymentMethod.Name AS PaymentMethod,-- CATransactionPayment.SupplementalData, CATRANSACTIONPAYMENT.PAYMENTNOTE,\r\n"
			+ "	   CATRANSACTIONPAYMENT.PAYMENTAMOUNT, D.DEPOSIT, W.WITHDRAWAL, GLOBALENTITYACCOUNT.BALANCE\r\n"
			+ "	  \r\n" + "FROM GLOBALENTITYACCOUNT \r\n"
			+ "INNER JOIN GLOBALENTITYACCOUNTTYPE ON GLOBALENTITYACCOUNT.GLOBALENTITYACCOUNTTYPEID = GLOBALENTITYACCOUNTTYPE.GLOBALENTITYACCOUNTTYPEID\r\n"
			+ "INNER JOIN CATRANSACTIONACCOUNT ON GLOBALENTITYACCOUNT.GLOBALENTITYACCOUNTID = CATRANSACTIONACCOUNT.ENTITYACCOUNTID\r\n"
			+ "INNER JOIN CATRANSACTION ON CATRANSACTIONACCOUNT.CATRANSACTIONID = CATRANSACTION.CATRANSACTIONID\r\n"
			+ "INNER JOIN CATRANSACTIONTYPE ON CATRANSACTIONTYPE.CATRANSACTIONTYPEID = CATRANSACTION.CATRANSACTIONTYPEID \r\n"
			+ "INNER JOIN CATRANSACTIONSTATUS ON CATRANSACTION.CATRANSACTIONSTATUSID = CATRANSACTIONSTATUS.CATRANSACTIONSTATUSID\r\n"
			+ "INNER JOIN CATRANSACTIONPAYMENT ON CATRANSACTION.CATRANSACTIONID = CATRANSACTIONPAYMENT.CATRANSACTIONID\r\n"
			+ "INNER JOIN CAPAYMENTMETHOD ON CATRANSACTIONPAYMENT.CAPAYMENTMETHODID = CAPAYMENTMETHOD.CAPAYMENTMETHODID\r\n"
			+ "LEFT OUTER JOIN USERS ON USERS.SUSERGUID = CATRANSACTIONPAYMENT.LASTCHANGEDBY\r\n" + "\r\n"
			+ "Full Join (SELECT  SUM(P.PAYMENTAMOUNT) as [DEPOSIT], GE.accountnumber\r\n"
			+ "			FROM catransactionpayment P\r\n"
			+ "			left outer join catransaction T on P.catransactionid = T.catransactionid\r\n"
			+ "			left outer join catransactiontype CT on T.catransactiontypeid = CT.CATRANSACTIONTYPEID\r\n"
			+ "			left outer join catransactionaccount a on T.catransactionid = A.catransactionid\r\n"
			+ "			left outer join globalentityaccount GE on A.entityaccountid = ge.globalentityaccountid\r\n"
			+ "		where  CT.NAME = 'ACCOUNT DEPOSIT'\r\n"
			+ "		group by GE.ACCOUNTNUMBER) D on GLOBALENTITYACCOUNT.ACCOUNTNUMBER = D.ACCOUNTNUMBER\r\n" + "\r\n"
			+ "Full Join (SELECT  SUM(P.PAYMENTAMOUNT) as [WITHDRAWAL], GE.accountnumber\r\n"
			+ "			FROM catransactionpayment P\r\n"
			+ "			left outer join catransaction T on P.catransactionid = T.catransactionid\r\n"
			+ "			left outer join catransactiontype CT on T.catransactiontypeid = CT.CATRANSACTIONTYPEID\r\n"
			+ "			left outer join catransactionaccount a on T.catransactionid = A.catransactionid\r\n"
			+ "			left outer join globalentityaccount GE on A.entityaccountid = ge.globalentityaccountid\r\n"
			+ "		where  CT.NAME = 'ACCOUNT WITHDRAWAL'\r\n"
			+ "		group by GE.ACCOUNTNUMBER) W on GLOBALENTITYACCOUNT.ACCOUNTNUMBER = W.ACCOUNTNUMBER\r\n"
			+ "				\r\n" + "\r\n" + "\r\n" + "where TYPENAME = 'Condition Check'\r\n";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	private static class Mapper implements RowMapper<BalanceByContact>{

		@Override
		public BalanceByContact mapRow(ResultSet rs, int rowNum) throws SQLException {
			String transGlobalName = rs.getString("TRANS_GLOBAL_NAME");
			String accountNumber = rs.getString("ACCOUNTNUMBER");
			String typeName = rs.getString("TYPENAME");
			String accountName = rs.getString("ACCOUNT_NAME");
			String description = rs.getString("DESCRIPTION");
			String globalEntityId = rs.getString("GlobalEntityID");
			String caTransactionId = rs.getString("CATRANSACTIONID");
			String transactionType = rs.getString("TransactionType");
			Date transactionDate = rs.getDate("TRANSACTIONDATE");
			String receiptNumber = rs.getString("RECEIPTNUMBER");
			int paymentAmount1 = rs.getInt("PAYMENTAMOUNT");
			String receivedBy = rs.getString("RECEIVEDBY");
			Date paymentDate = rs.getDate("PaymentDate");
			String paymentMethod = rs.getString("PaymentMethod");
			int paymentAmount2 = rs.getInt("PAYMENTAMOUNT");
			int deposit = rs.getInt("DEPOSIT");
			int withdrawal = rs.getInt("WITHDRAWAL");
			int balance = rs.getInt("BALANCE");

			return new BalanceByContact(transGlobalName, accountNumber, typeName, accountName, description,
					globalEntityId, caTransactionId, transactionType, transactionDate, receiptNumber, paymentAmount1,
					receivedBy, paymentDate, paymentMethod, paymentAmount2, deposit, withdrawal, balance);

		}
	}
	
	public List<BalanceByContact> getBalancesByContact(){
		List<BalanceByContact> accts = this.jdbcTemplate.query(GET_BALANCES_BY_CONTACT+ "ORDER BY ACCOUNT_NAME, CATRANSACTION.TRANSACTIONDATE\r\n", new Mapper());
		return accts;
	}
	
	public List<BalanceByContact> getBalanceByContactSearchByName(String ContactName){
		List<BalanceByContact> accts = this.jdbcTemplate.query(GET_BALANCES_BY_CONTACT+ "and GLOBALENTITYNAME like '%"+ContactName+"%'", new Mapper());
		return accts;
	}
}
