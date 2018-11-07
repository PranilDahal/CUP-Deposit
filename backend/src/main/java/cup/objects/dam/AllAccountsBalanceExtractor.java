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
public class AllAccountsBalanceExtractor {

    private static final String GET_ALLACCOUNTS_BALANCE = "SELECT distinct P.PLANNUMBER, CSM.ServiceArea, D.NAME as SUP_DIST,\n" +
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

    private static class Mapper implements RowMapper<AllAccountsBalance> {


        @Override
        public AllAccountsBalance mapRow(ResultSet rs, int rowNum) throws SQLException {

            String plannumber = rs.getString("PLANNUMBER");

            String servicearea = rs.getString("ServiceArea");

            String sup_dist = rs.getString("SUP_DIST");

            String companyname = rs.getString("CompanyName");

            String firstname  = rs.getString("FIRSTNAME");

            String lastname  = rs.getString("LASTNAME");

            String accountname = rs.getString("ACCT_NAME");

            String description = rs.getString("ACCT_DESC");

            Float balance = rs.getFloat("Balance");

            String accounttype = rs.getString("[Account Type]");

            return new AllAccountsBalance(plannumber, servicearea, sup_dist, companyname, firstname, lastname, accountname, description, balance, accounttype);

        }

    }

    public List<AllAccountsBalance> getAllAccountsBalance() {

        List <AllAccountsBalance> accts = this.jdbcTemplate.query(GET_ALLACCOUNTS_BALANCE, new Mapper());
        return accts;

    }

}