package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.DDAccountTransHistory;
import cup.objects.DDAccountsByContact;
import cup.objects.DDAllAccountTransHistory;
import cup.objects.DDAllPlanTransHistory;
import cup.objects.dam.DDAccountTransHistoryExtractor;
import cup.objects.dam.DDAccountsByContactExtractor;
import cup.objects.dam.DDAllAccountTransHistoryExtractor;
import cup.objects.dam.DDAllPlanTransHistoryExtractor;

@RestController
@RequestMapping("api/v1")

public class DrillDownAPI {

	@Autowired
	DDAllPlanTransHistoryExtractor planDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v1/planTransHistory/EFNL2017000129| Returns ALL transaction history for ALL plans of a contact
	 */
	@CrossOrigin
	@RequestMapping(value = "/planTransHistory/{plannumber}", method = RequestMethod.GET)
	public List<DDAllPlanTransHistory> getAllPlanTransHistory(@PathVariable("plannumber") String plannumber) {
		return planDAM.getAllPlanTransHistory(plannumber) ;
	}
	
	@Autowired
	DDAccountsByContactExtractor accountsByContactDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v1/allaccountsbycontact/001a1deb-b99f-4bab-99cd-201f9a6ceb42 | Returns ALL accounts by contact
	 */
	@CrossOrigin
	@RequestMapping(value = "/allaccountsbycontact/{globalentityaccountid}", method = RequestMethod.GET)
	public List<DDAccountsByContact> getAllAccountsByContact(@PathVariable("globalentityaccountid") String globalentityaccountid) {
		return accountsByContactDAM.getAllAccountsByContact(globalentityaccountid) ;
	}
	
	
	@Autowired
	DDAccountTransHistoryExtractor accountDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v1/accountTransHistory/000015-2015 | Returns ALL transaction history for that account
	 */
	@CrossOrigin
	@RequestMapping(value = "/accountTransHistory/{accountnumber}", method = RequestMethod.GET)
	public List<DDAccountTransHistory> getAccountTransHistory(@PathVariable("accountnumber") String accountnumber) {
		return accountDAM.getAllAccountTransHistory(accountnumber) ;
	}
	
	@Autowired
	DDAllAccountTransHistoryExtractor allAccountDAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v1/allAccountTransHistory/582AFF18-C1A2-4483-B1EE-51B072A8C6E0 | Returns ALL transaction history for ALL accounts of a contact
	 */
	@CrossOrigin
	@RequestMapping(value = "/allAccountTransHistory/{globalentityid}", method = RequestMethod.GET)
	public List<DDAllAccountTransHistory> getAllAccountTransHistory(@PathVariable("globalentityid") String globalentityid) {
		return allAccountDAM.getAllAccountTransHistory(globalentityid) ;
	}
}
