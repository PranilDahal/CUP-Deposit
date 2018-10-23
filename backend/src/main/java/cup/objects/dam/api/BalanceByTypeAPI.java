package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.BalanceByContact;
import cup.objects.dam.BalanceByContactExtractor;

@RestController
@RequestMapping("/balances")
public class BalanceByTypeAPI {
	
	@Autowired
	BalanceByContactExtractor DAM;
	
	/**
	 * @return GET - http://localhost:8080/balances/contact | Returns ALL NonMatchingAccounts objects
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public List<BalanceByContact> getBBC() {
		return DAM.getBalancesByContact() ;
	}

}
