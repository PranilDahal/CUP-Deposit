package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.BalanceByContact;
import cup.objects.BalanceByPlan;
import cup.objects.dam.BalanceByContactExtractor;
import cup.objects.dam.BalanceByPlanExtractor;

@RestController
@RequestMapping("/balances")
public class BalanceByTypeAPI {
	
	@Autowired
	BalanceByContactExtractor contactDAM;
	
	@Autowired
	BalanceByPlanExtractor planDAM;
	/**
	 * @return GET - http://localhost:8080/balances/contact | Returns individual balances by contact objects
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public List<BalanceByContact> getBBC() {
		return contactDAM.getBalancesByContact() ;
	}

	/**
	 * @return GET - http://localhost:8080/balances/plan | Returns individual balances by plan objects
	 */
	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	public List<BalanceByPlan> getBBP() {
		return planDAM.getBalancesByPlan();
	}
	
}
