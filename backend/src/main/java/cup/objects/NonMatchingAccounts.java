package cup.objects;

import java.sql.Date;

public class NonMatchingAccounts {
	
	public String name;
	
	public String typename;
	
	public String lastchangedby;
	
	public Date lastchangedon;
	
	public String companyname;
	
	public String firstname;
	
	public String lastname;

	public NonMatchingAccounts(String name, String typename, String lastchangedby, Date lastchangedon,
			String companyname, String firstname, String lastname) {
		super();
		this.name = name;
		this.typename = typename;
		this.lastchangedby = lastchangedby;
		this.lastchangedon = lastchangedon;
		this.companyname = companyname;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getLastchangedby() {
		return lastchangedby;
	}

	public void setLastchangedby(String lastchangedby) {
		this.lastchangedby = lastchangedby;
	}

	public Date getLastchangedon() {
		return lastchangedon;
	}

	public void setLastchangedon(Date lastchangedon) {
		this.lastchangedon = lastchangedon;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
