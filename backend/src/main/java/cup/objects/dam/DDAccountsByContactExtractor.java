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
	private static final String GET_ACCOUNTS_BY_CONTACT = "select ge.globalentityid, ge.globalentityname, ge.firstname, ge.lastname, gea.Name, gea.AccountNumber from GLOBALENTITY GE\r\n" + 
			"left join GLOBALENTITYACCOUNTENTITY GEAE on ge.GLOBALENTITYID = geae.GLOBALENTITYID\r\n" + 
			"left join GLOBALENTITYACCOUNT GEA on geae.GLOBALENTITYACCOUNTID = gea.GLOBALENTITYACCOUNTID\r\n" + 
			"where gea.GLOBALENTITYACCOUNTID is not null and ge.GLOBALENTITYID = ";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	private static class Mapper implements RowMapper<DDAccountsByContact>{
		
		@Override
		public DDAccountsByContact mapRow(ResultSet rs, int rowNum) throws SQLException {

			String globalentityid = rs.getString("globalentityid");
			
			String globalentityname = rs.getString("globalentityname");
			
			String firstname = rs.getString("firstname");
			
			String lastname = rs.getString("lastname");
			
			String Name = rs.getString("Name");
			
			String AccountNumber = rs.getString("AccountNumber");
			
			return new DDAccountsByContact(globalentityid, globalentityname, firstname, lastname, Name, AccountNumber);

		}

	}
	
	public List<DDAccountsByContact> getAllAccountsByContact(String globalentityid) {

		List <DDAccountsByContact> accts = this.jdbcTemplate.query(GET_ACCOUNTS_BY_CONTACT + "'" + globalentityid + "'", new Mapper());
		return accts;
		
	}
	
	
}
