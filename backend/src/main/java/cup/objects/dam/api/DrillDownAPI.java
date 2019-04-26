package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.DDPlanTransHistory;
import cup.objects.dam.DDPlanTransHistoryExtractor;
import cup.objects.DDAccountTransHistory;
import cup.objects.DDAccountsByContact;
import cup.objects.dam.DDAccountTransHistoryExtractor;
import cup.objects.dam.DDAccountsByContactExtractor;

@RestController
@RequestMapping("api/v2")

public class DrillDownAPI {

	@Autowired
	DDPlanTransHistoryExtractor planTransDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v2/planTransHistory| Returns ALL transaction history for ALL plans of a contact
	 */
	@CrossOrigin
	@RequestMapping(value = "/planTransHistory/{plannumber}", method = RequestMethod.GET)
	public List<DDPlanTransHistory> getAllPlanTransHistory(@PathVariable("plannumber") String plannumber) {
		return planTransDAM.getAllPlanTransHistory(plannumber) ;
	}
	
	@Autowired
	DDAccountsByContactExtractor accountsByContactDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v2/allaccountsbycontact/001a1deb-b99f-4bab-99cd-201f9a6ceb42 | Returns ALL accounts by contact
	 */
	@CrossOrigin
	@RequestMapping(value = "/allaccountsbycontact/{globalentityaccountname}", method = RequestMethod.GET)
	public List<DDAccountsByContact> getAllAccountsByContact(@PathVariable("globalentityaccountname") String globalentityaccountname) {
		return accountsByContactDAM.getAllAccountsByContact(globalentityaccountname) ;
	}
	
	
	@Autowired
	DDAccountTransHistoryExtractor accountTransDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v2/accountTransHistory/000015-2015 | Returns ALL transaction history for that account
	 */
	@CrossOrigin
	@RequestMapping(value = "/accountTransHistory/{accountnumber}", method = RequestMethod.GET)
	public List<DDAccountTransHistory> getAccountTransHistory(@PathVariable("accountnumber") String accountnumber) {
		return accountTransDAM.getAllAccountTransHistory(accountnumber) ;
	}
	
}
