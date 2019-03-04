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

import cup.objects.DDAllPlanTransHistory;

@Component
public class DDAllPlanTransHistoryExtractor {
	private static final String GET_ALL_PLAN_TRANS_HISTORY = "select distinct GE.globalentityname as ContactName, P.plannumber as PlanNum, catp.PAYMENTAMOUNT as Amount, \r\n" + 
			"cats.name as Status, catt.name as Type, catp.PAYMENTDATE as PayDate, cat.RECEIPTNUMBER \r\n" + 
			"from plplan P\r\n" + 
			"left join plplancontact PC ON P.plplanid = PC.plplanid\r\n" + 
			"left join GLOBALENTITY GE on pc.GLOBALENTITYID = GE.GLOBALENTITYID\r\n" + 
			"left join Users U on ge.GLOBALENTITYID = u.GLOBALENTITYID\r\n" + 
			"left join CATRANSACTION CAT on u.GLOBALENTITYID = cat.GLOBALENTITYID\r\n" + 
			"left join CATRANSACTIONSTATUS CATS on cat.CATRANSACTIONSTATUSID = cats.CATRANSACTIONSTATUSID\r\n" + 
			"left join CATRANSACTIONTYPE CATT on cat.CATRANSACTIONTYPEID = catt.CATRANSACTIONTYPEID\r\n" + 
			"left join CATRANSACTIONPAYMENT CATP on cat.CATRANSACTIONID = catp.CATRANSACTIONID\r\n" + 
			"left join CAPAYMENTMETHOD CAP on catp.CAPAYMENTTYPEID = cap.CAPAYMENTTYPEID\r\n" + 
			"where catp.PAYMENTAMOUNT is not NULL and ge.GLOBALENTITYNAME like '%EZ Permits%'\r\n" + 
			"--where PLANNUMBER like '%RCUP-CP97014-25224%'\r\n" + 
			"order by PlanNum, PayDate desc";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	private static class Mapper implements RowMapper<DDAllPlanTransHistory>{
		
		@Override
		public DDAllPlanTransHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

			String plannumber = rs.getString("PlanNum");
			
			double amount = rs.getDouble("Amount");
			
			String status = rs.getString("Status");
			
			String type = rs.getString("Type");
			
			Date paydate = rs.getDate("PayDate");
			
			String receiptnumber = rs.getString("RECEIPTNUMBER");
			
			return new DDAllPlanTransHistory(plannumber, amount, status, type, paydate, receiptnumber);

		}

	}
	
	public List<DDAllPlanTransHistory> getAllPlanTransHistory() {

		List <DDAllPlanTransHistory> accts = this.jdbcTemplate.query(GET_ALL_PLAN_TRANS_HISTORY, new Mapper());
		return accts;
		
	}
}
