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

import cup.objects.DDPlanTransHistory;

@Component
public class DDPlanTransHistoryExtractor {
	private static final String GET_PLAN_TRANS_HISTORY = "select distinct GE.globalentityname as Contact, P.plannumber, CATP.PAYMENTAMOUNT, gea.BALANCE,CATT.DESCRIPTION, Cata.TRANSDATE as Date, cat.RECEIPTNUMBER from GLOBALENTITYACCOUNT gea inner join CATRANSACTIONACCOUNT CATA on gea.GLOBALENTITYACCOUNTID = cata.ENTITYACCOUNTID inner join CATRANSACTION CAT on cata.CATRANSACTIONID = cat.CATRANSACTIONID inner join CATRANSACTIONSTATUS CATS on cat.CATRANSACTIONSTATUSID = cats.CATRANSACTIONSTATUSID inner join CATRANSACTIONTYPE CATT on cat.CATRANSACTIONTYPEID = catt.CATRANSACTIONTYPEID inner join CATRANSACTIONPAYMENT CATP on cat.CATRANSACTIONID = catp.CATRANSACTIONID inner join CAPAYMENTMETHOD CAP on catp.CAPAYMENTTYPEID = cap.CAPAYMENTTYPEID inner join GLOBALENTITYACCOUNTENTITY GEAE on geae.GLOBALENTITYACCOUNTID = gea.GLOBALENTITYACCOUNTID inner join GLOBALENTITY GE on ge.GLOBALENTITYID = geae.GLOBALENTITYID left join PLPLANCONTACT PC on ge.GLOBALENTITYID = PC.GLOBALENTITYID inner join PLPLAN P on P.PLPLANID = PC.PLPLANID where P.PLANNUMBER like '";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	private static class Mapper implements RowMapper<DDPlanTransHistory>{
		
		@Override
		public DDPlanTransHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			String contact = rs.getString("Contact");
            
            String plannumber = rs.getString("plannumber");
			
			double paymentamount = rs.getDouble("PAYMENTAMOUNT");
			
			double balance = rs.getDouble("BALANCE");
			
			String description = rs.getString("DESCRIPTION");
			
			Date date = rs.getDate("Date");
			
			String receiptnumber = rs.getString("RECEIPTNUMBER");
			
			return new DDPlanTransHistory( contact, plannumber, paymentamount, balance, description, date, receiptnumber);

		}

	}
	
	public List<DDPlanTransHistory> getAllPlanTransHistory(String plannumber) {

		List <DDPlanTransHistory> accts = this.jdbcTemplate.query(GET_PLAN_TRANS_HISTORY + plannumber + "' order by"
				+ " PLANNUMBER asc", new Mapper());
		return accts;
		
	}
	
	
}
