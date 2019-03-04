package cup.objects.dam;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cup.objects.DDAllAccountTransHistory;

@Component
public class DDAllAccountTransHistoryExtractor {
	private static final String GET_ALL_ACCOUNT_TRANS_HISTORY = "select distinct ge.globalentityname as ContactName, gea.ACCOUNTNUMBER, catp.PAYMENTAMOUNT as Amount, \r\n" + 
			"cats.name as Status, catt.name as Type, catp.PAYMENTDATE as PayDate, cat.RECEIPTNUMBER \r\n" + 
			"from GLOBALENTITY GE\r\n" + 
			"left join GLOBALENTITYACCOUNTENTITY GEAE on ge.GLOBALENTITYID = geae.GLOBALENTITYID\r\n" + 
			"left join GLOBALENTITYACCOUNT gea on geae.GLOBALENTITYACCOUNTID = gea.GLOBALENTITYACCOUNTID\r\n" + 
			"left join Users U on ge.GLOBALENTITYID = u.GLOBALENTITYID\r\n" + 
			"left join CATRANSACTION CAT on u.GLOBALENTITYID = cat.GLOBALENTITYID\r\n" + 
			"left join CATRANSACTIONSTATUS CATS on cat.CATRANSACTIONSTATUSID = cats.CATRANSACTIONSTATUSID\r\n" + 
			"left join CATRANSACTIONTYPE CATT on cat.CATRANSACTIONTYPEID = catt.CATRANSACTIONTYPEID\r\n" + 
			"left join CATRANSACTIONPAYMENT CATP on cat.CATRANSACTIONID = catp.CATRANSACTIONID\r\n" + 
			"left join CAPAYMENTMETHOD CAP on catp.CAPAYMENTTYPEID = cap.CAPAYMENTTYPEID\r\n" + 
			"where gea.ACCOUNTNUMBER is not null and catp.PAYMENTAMOUNT is not null and ge.GLOBALENTITYID = '";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	private static class Mapper implements RowMapper<DDAllAccountTransHistory>{
		
		@Override
		public DDAllAccountTransHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

			String accountnumber = rs.getString("ACCOUNTNUMBER");
			
			double amount = rs.getDouble("Amount");
			
			String status = rs.getString("Status");
			
			String type = rs.getString("Type");
			
			Date paydate = rs.getDate("PayDate");
			
			String receiptnumber = rs.getString("RECEIPTNUMBER");
			
			return new DDAllAccountTransHistory(accountnumber, amount, status, type, paydate, receiptnumber);

		}

	}
	
	public List<DDAllAccountTransHistory> getAllAccountTransHistory(String globalentityid) {

		List <DDAllAccountTransHistory> accts = this.jdbcTemplate.query(GET_ALL_ACCOUNT_TRANS_HISTORY + globalentityid + "'", new Mapper());
		return accts;
		
	}
	
	
}

