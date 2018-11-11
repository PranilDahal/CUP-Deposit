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

import cup.objects.NonMatchingAccounts;

@Component
public class NonMatchingAccountsExtractor {

	private static final String GET_NONMATCHING_ACCOUNTS = "select P.PLANNUMBER, A.NAME, T.TYPENAME, U.FNAME +' ' + U.LNAME as LASTCHANGEDBY, A.LASTCHANGEDON, G.GLOBALENTITYNAME as COMPANYNAME,\r\n" + 
			"G.FIRSTNAME, G.LASTNAME, U.EMAIL\r\n" + 
			"From GLOBALENTITYACCOUNT A\r\n" + 
			"left outer join PLPLAN P on P.PLANNUMBER = A.NAME  \r\n" + 
			"inner join GLOBALENTITYACCOUNTTYPE T on A.GLOBALENTITYACCOUNTTYPEID = T.GLOBALENTITYACCOUNTTYPEID\r\n" + 
			"left outer join USERS U on A.LASTCHANGEDBY = U.SUSERGUID\r\n" + 
			"left outer join GLOBALENTITYACCOUNTENTITY GA on A.GLOBALENTITYACCOUNTID = GA.GLOBALENTITYACCOUNTID\r\n" + 
			"left outer join GLOBALENTITY G on GA.GLOBALENTITYID = G.GLOBALENTITYID\r\n" + 
			"where T.TYPENAME like 'Condition Check%' and P.PLANNUMBER IS NULL\r\n" + 
			"order by PLANNUMBER";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private static class Mapper implements RowMapper<NonMatchingAccounts> {


		@Override
		public NonMatchingAccounts mapRow(ResultSet rs, int rowNum) throws SQLException {

			String name = rs.getString("NAME");
			
			String typename = rs.getString("TYPENAME");
			
			String lastchangedby = rs.getString("LASTCHANGEDBY");
			
			Date lastchangedon = rs.getDate("LASTCHANGEDON");
			
			String companyname = rs.getString("COMPANYNAME");
			
			String firstname = rs.getString("FIRSTNAME");
			
			String lastname  = rs.getString("LASTNAME");
			
			String email = rs.getString("EMAIL");

			return new NonMatchingAccounts(name, typename, lastchangedby,
					lastchangedon, companyname, firstname, lastname, email);

		}

	}
	
	public List<NonMatchingAccounts> getAllNonmatchingAccounts() {

		List <NonMatchingAccounts> accts = this.jdbcTemplate.query(GET_NONMATCHING_ACCOUNTS, new Mapper());
		return accts;
		
	}
	
}