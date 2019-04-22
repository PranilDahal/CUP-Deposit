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

import cup.objects.BalanceByPlan;

@Component
public class BalanceByPlanExtractor {
	private static final String GET_BALANCES_BY_PLAN = "SELECT CT.GLOBALENTITYNAME as TRANS_GLOBAL_NAME, P.PLANNUMBER,\r\n" + 
			"		PC.PLPLANCONTACTID, P.ASSIGNEDTO, \r\n" + 
			"  	   GEA.ACCOUNTNUMBER, GEAT.TYPENAME ,GEA.NAME AS ACCOUNT_NAME, GEA.DESCRIPTION,\r\n" + 
			"      CT.GlobalEntityID, CT.CATRANSACTIONID,   CTT.Name AS TransactionType, CT.TRANSACTIONDATE,\r\n" + 
			"	   CT.RECEIPTNUMBER,-- CATransaction.Note,\r\n" + 
			"	   CTP.PAYMENTAMOUNT, \r\n" + 
			"	   U.FNAME + ' ' + U.LNAME as RECEIVEDBY, \r\n" + 
			"       CTP.PaymentDate, CPM.Name AS PaymentMethod, CTP.SupplementalData, CTP.PAYMENTNOTE,\r\n" + 
			"	   CTP.PAYMENTAMOUNT, D.DEPOSIT, W.WITHDRAWAL, GEA.BALANCE\r\n" + 
			"	  \r\n" + 
			"FROM GLOBALENTITYACCOUNT GEA\r\n" + 
			"INNER JOIN GLOBALENTITYACCOUNTENTITY GEAE ON GEA.GLOBALENTITYACCOUNTID = GEAE.GLOBALENTITYACCOUNTID\r\n" + 
			"INNER JOIN GLOBALENTITY GE ON GEAE.GLOBALENTITYID = GE.GLOBALENTITYID\r\n" + 
			"INNER JOIN PLPLANCONTACT PC ON GE.GLOBALENTITYID = PC.GLOBALENTITYID\r\n" + 
			"INNER JOIN PLPLAN P ON PC.PLPLANID = P.PLPLANID\r\n" + 
			"INNER JOIN GLOBALENTITYACCOUNTTYPE GEAT ON GEA.GLOBALENTITYACCOUNTTYPEID = GEAT.GLOBALENTITYACCOUNTTYPEID\r\n" + 
			"INNER JOIN CATRANSACTIONACCOUNT CTA ON GEA.GLOBALENTITYACCOUNTID = CTA.ENTITYACCOUNTID\r\n" + 
			"INNER JOIN CATRANSACTION CT ON CTA.CATRANSACTIONID = CT.CATRANSACTIONID\r\n" + 
			"INNER JOIN CATRANSACTIONTYPE CTT ON CTT.CATRANSACTIONTYPEID = CT.CATRANSACTIONTYPEID \r\n" + 
			"INNER JOIN CATRANSACTIONSTATUS CTS ON CT.CATRANSACTIONSTATUSID = CTS.CATRANSACTIONSTATUSID\r\n" + 
			"INNER JOIN CATRANSACTIONPAYMENT CTP ON CT.CATRANSACTIONID = CTP.CATRANSACTIONID\r\n" + 
			"INNER JOIN CAPAYMENTMETHOD CPM ON CTP.CAPAYMENTMETHODID = CPM.CAPAYMENTMETHODID\r\n" + 
			"LEFT OUTER JOIN USERS U ON U.SUSERGUID = CTP.LASTCHANGEDBY\r\n" + 
			"\r\n" + 
			"Full Join (SELECT  SUM(P.PAYMENTAMOUNT) as [DEPOSIT], GE.accountnumber\r\n" + 
			"			FROM catransactionpayment P\r\n" + 
			"			left outer join catransaction T on P.catransactionid = T.catransactionid\r\n" + 
			"			left outer join catransactiontype CT on T.catransactiontypeid = CT.CATRANSACTIONTYPEID\r\n" + 
			"			left outer join catransactionaccount a on T.catransactionid = A.catransactionid\r\n" + 
			"			left outer join globalentityaccount GE on A.entityaccountid = ge.globalentityaccountid\r\n" + 
			"		where  CT.NAME = 'ACCOUNT DEPOSIT'\r\n" + 
			"		group by GE.ACCOUNTNUMBER) D on GEA.ACCOUNTNUMBER = D.ACCOUNTNUMBER\r\n" + 
			"\r\n" + 
			"Full Join (SELECT  SUM(P.PAYMENTAMOUNT) as [WITHDRAWAL], GE.accountnumber\r\n" + 
			"			FROM catransactionpayment P\r\n" + 
			"			left outer join catransaction T on P.catransactionid = T.catransactionid\r\n" + 
			"			left outer join catransactiontype CT on T.catransactiontypeid = CT.CATRANSACTIONTYPEID\r\n" + 
			"			left outer join catransactionaccount a on T.catransactionid = A.catransactionid\r\n" + 
			"			left outer join globalentityaccount GE on A.entityaccountid = ge.globalentityaccountid\r\n" + 
			"		where  CT.NAME = 'ACCOUNT WITHDRAWAL'\r\n" + 
			"		group by GE.ACCOUNTNUMBER) W on GEA.ACCOUNTNUMBER = W.ACCOUNTNUMBER\r\n" + 
			"\r\n" + 
			"where TYPENAME = 'Condition Check'\r\n"; //" + 
			//"ORDER BY Trans_global_name, ACCOUNT_NAME, CT.TRANSACTIONDATE
			
private JdbcTemplate jdbcTemplate;

			@Autowired
			public void setDataSource(DataSource datasource) {
				this.jdbcTemplate = new JdbcTemplate(datasource);
			}

			private static class Mapper implements RowMapper<BalanceByPlan>{
				
				@Override
				public BalanceByPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
					String transGlobalName = rs.getString("TRANS_GLOBAL_NAME");
					String planNumber = rs.getString("PLANNUMBER");
					String plPlanContactId = rs.getString("PLPLANCONTACTID");
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

					return new BalanceByPlan(transGlobalName, planNumber, plPlanContactId, accountNumber, typeName, accountName, description,
							globalEntityId, caTransactionId, transactionType, transactionDate, receiptNumber, paymentAmount1,
							receivedBy, paymentDate, paymentMethod, paymentAmount2, deposit, withdrawal, balance);

				}
			}
			
			public List<BalanceByPlan> getBalancesByPlan(){
				List<BalanceByPlan> accts = this.jdbcTemplate.query(GET_BALANCES_BY_PLAN  + 
			"ORDER BY Trans_global_name, ACCOUNT_NAME, CT.TRANSACTIONDATE", new Mapper());
				return accts;
			}

		public List<BalanceByPlan> getBalanceByPlanSearchByNumber(String planNumber){
			List<BalanceByPlan> accts = this.jdbcTemplate.query(GET_BALANCES_BY_PLAN+ "and PLANNUMBER like '%"+planNumber+"%' " + 
			"ORDER BY Trans_global_name, ACCOUNT_NAME, CT.TRANSACTIONDATE", new Mapper());
			return accts;
		}

}
