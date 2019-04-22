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

import cup.objects.DDAccountTransHistory;

@Component
public class DDAccountTransHistoryExtractor {
	private static final String GET_ACCOUNT_TRANS_HISTORY = "select distinct gea.NAME as AccountName, gea.ACCOUNTNUMBER, catp.PAYMENTAMOUNT as Amount, cats.name as Status, catt.name as Type, catp.PAYMENTDATE as PayDate, cat.RECEIPTNUMBER, gea.BALANCE from GLOBALENTITYACCOUNT gea inner join CATRANSACTIONACCOUNT CATA on gea.GLOBALENTITYACCOUNTID = cata.ENTITYACCOUNTID inner join CATRANSACTION CAT on cata.CATRANSACTIONID = cat.CATRANSACTIONID inner join CATRANSACTIONSTATUS CATS on cat.CATRANSACTIONSTATUSID = cats.CATRANSACTIONSTATUSID inner join CATRANSACTIONTYPE CATT on cat.CATRANSACTIONTYPEID = catt.CATRANSACTIONTYPEID inner join CATRANSACTIONPAYMENT CATP on cat.CATRANSACTIONID = catp.CATRANSACTIONID inner join CAPAYMENTMETHOD CAP on catp.CAPAYMENTTYPEID = cap.CAPAYMENTTYPEID where gea.ACCOUNTNUMBER = '";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	private static class Mapper implements RowMapper<DDAccountTransHistory>{
		
		@Override
		public DDAccountTransHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			String accountName = rs.getString("AccountName");
			
			String accountnumber = rs.getString("ACCOUNTNUMBER");
			
			double amount = rs.getDouble("Amount");
			
			String status = rs.getString("Status");
			
			String type = rs.getString("Type");
			
			Date paydate = rs.getDate("PayDate");
			
			String receiptnumber = rs.getString("RECEIPTNUMBER");

			double balance = rs.getDouble("BALANCE");
			
			return new DDAccountTransHistory( accountName, accountnumber, amount, status, type, paydate, receiptnumber, balance);

		}

	}
	
	public List<DDAccountTransHistory> getAllAccountTransHistory(String accountnumber) {

		List <DDAccountTransHistory> accts = this.jdbcTemplate.query(GET_ACCOUNT_TRANS_HISTORY + accountnumber + "' order by"
				+ " gea.ACCOUNTNUMBER asc", new Mapper());
		return accts;
		
	}
	
	
}
