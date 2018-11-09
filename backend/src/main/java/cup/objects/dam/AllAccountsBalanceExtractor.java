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
public class AllAccountBalancesExtractor {

    private static final String GET_ALLACCOUNT_BALANCES = "SELECT distinct P.PLANNUMBER, CSM.ServiceArea, D.NAME as SUP_DIST,\n" +
            "ge.GLOBALENTITYNAME as CompanyName, ge.FIRSTNAME, ge.LASTNAME, GlobalEntityAccount.Name as ACCT_NAME, \n" +
            "GlobalEntityAccount.Description as ACCT_DESC, GlobalEntityAccount.Balance, \n" +
            "GlobalEntityAccountType.TypeName AS [Account Type] \n" +
            "FROM \n" +
            "GlobalEntityAccount \n" +
            "left outer JOIN\n" +
            "GlobalEntityAccountType ON GlobalEntityAccount.GlobalEntityAccountTypeID = GlobalEntityAccountType.GlobalEntityAccountTypeID left outer JOIN\n" +
            "GlobalEntityAccountEntity ON GlobalEntityAccount.GlobalEntityAccountID = GlobalEntityAccountEntity.GlobalEntityAccountID left outer join\n" +
            "GLOBALENTITY GE on GLOBALENTITYACCOUNTENTITY.GLOBALENTITYID = GE.GLOBALENTITYID inner join \n" +
            "PLPLAN P on P.PLANNUMBER = GLOBALENTITYACCOUNT.NAME left outer join \n" +
            "CUSTOMSAVERPLANMANAGEMENT CSM on P.PLPLANID = CSM.ID left outer join \n" +
            "DISTRICT D on P.DISTRICTID = D.DISTRICTID\n" +
            "WHERE GLOBALENTITYACCOUNTTYPE.DESCRIPTION like 'DRP%'\n" +
            "order by P.PLANNUMBER, GLOBALENTITYACCOUNT.NAME";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    private static class Mapper implements RowMapper<AllAccountBalances> {


        @Override
        public AllAccountBalances mapRow(ResultSet rs, int rowNum) throws SQLException {

            String planNumber = rs.getString("PLANNUMBER");

            String serviceArea = rs.getString("ServiceArea");

            String supDist = rs.getString("SUP_DIST");

            String companyName = rs.getString("CompanyName");

            String firstName  = rs.getString("FIRSTNAME");

            String lastName  = rs.getString("LASTNAME");

            String acctName = rs.getString("ACCT_NAME");

            String acctDesc = rs.getString("ACCT_DESC");

            double balance = rs.getDouble("Balance");

            String accountType = rs.getString("[Account Type]");

            return new AllAccountBalances(planNumber, serviceArea, supDist, companyName, firstName, lastName, acctName, acctDesc, balance, accountType);

        }

    }

    public List<AllAccountBalances> getAllAccountsBalance() {

        List <AllAccountBalances> accts = this.jdbcTemplate.query(GET_ALLACCOUNT_BALANCES, new Mapper());
        return accts;

    }

}