package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.BalanceByContact;
import cup.objects.BalanceByPlan;
import cup.objects.dam.BalanceByContactExtractor;
import cup.objects.dam.BalanceByPlanExtractor;

@RestController
@RequestMapping("api/v1/balances")
public class BalanceByTypeAPI {
	
	@Autowired
	BalanceByContactExtractor contactDAM;
	
	@Autowired
	BalanceByPlanExtractor planDAM;
	/**
	 * @return GET - http://localhost:8080/api/v1/balances/contact | Returns individual balances by contact objects
	 */
	@CrossOrigin
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public List<BalanceByContact> getBBC() {
		return contactDAM.getBalancesByContact() ;
	}

	/**
	 * @return GET - http://localhost:8080/api/v1/balances/plan | Returns individual balances by plan objects
	 */
	@CrossOrigin
	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	public List<BalanceByPlan> getBBP() {
		return planDAM.getBalancesByPlan();
	}
	
	/**
	 * @return GET - http://localhost:8080/api/v1/balances/search/bbc/{name} | Returns all GLOBAL ENTITIE's accounts based on the Name of the ENTITY
	 */
	@CrossOrigin
	@RequestMapping(value = "/search/bbc/{name}", method = RequestMethod.GET)
	public List<BalanceByContact> searchBBC(@PathVariable("name") String name) {
		return contactDAM.getBalanceByContactSearchByName(name) ;
	}
	
	/**
	 * @return GET - http://localhost:8080/api/v1/balances/search/bbp/{name} | Returns all GLOBAL ENTITIE's accounts based on the Name of the ENTITY
	 */
	@CrossOrigin
	@RequestMapping(value = "/search/bbp/{name}", method = RequestMethod.GET)
	public List<BalanceByPlan> searchBBP(@PathVariable("name") String name) {
		return planDAM.getBalanceByPlanSearchByName(name) ;
	}
	
}
