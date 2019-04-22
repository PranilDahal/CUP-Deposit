package cup.objects.dam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cup.objects.DDAccountsByContact;

@Component
public class DDAccountsByContactExtractor {
	private static final String GET_ACCOUNTS_BY_CONTACT = "select ge.globalentityname, ge.firstname, ge.lastname, gea.Name as AccountName, gea.AccountNumber, gea.balance from GLOBALENTITY GE inner join GLOBALENTITYACCOUNTENTITY GEAE on ge.GLOBALENTITYID = geae.GLOBALENTITYID inner join GLOBALENTITYACCOUNT GEA on geae.GLOBALENTITYACCOUNTID = gea.GLOBALENTITYACCOUNTID inner join CATRANSACTIONACCOUNT CATA on cata.ENTITYACCOUNTID = gea.GLOBALENTITYACCOUNTID where ge.GLOBALENTITYID in (select GLOBALENTITYID from GLOBALENTITY where GLOBALENTITYNAME = ";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	private static class Mapper implements RowMapper<DDAccountsByContact>{
		
		@Override
		public DDAccountsByContact mapRow(ResultSet rs, int rowNum) throws SQLException {

			String globalentityname = rs.getString("globalentityname");
			
			String firstname = rs.getString("firstname");
			
			String lastname = rs.getString("lastname");
			
			String AccountName = rs.getString("AccountName");
			
			String AccountNumber = rs.getString("AccountNumber");
			
			double balance = rs.getDouble("balance");
			
			return new DDAccountsByContact(globalentityname, firstname, lastname, AccountName, AccountNumber, balance);

		}

	}
	
	public List<DDAccountsByContact> getAllAccountsByContact(String globalentityname) {

		List <DDAccountsByContact> accts = this.jdbcTemplate.query(GET_ACCOUNTS_BY_CONTACT + "'" + globalentityname + "')", new Mapper());
		return accts;
		
	}
	
	
}
