package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.AllAccountBalances;
import cup.objects.dam.AllAccountBalancesExtractor;

@RestController
@RequestMapping("api/v2/accountbalances")
public class AllAccountBalancesAPI {

	@Autowired
	AllAccountBalancesExtractor DAM;
	
	/**
	 * @return GET - http://localhost:8080/api/v2/accountbalances/all | Returns ALL account balance objects
	 */
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<AllAccountBalances> getAllAAB() {
		return DAM.getAllAccountBalances() ;
	}
	
	/**
	 * @return GET - http://localhost:8080/api/v2/accountbalances/accountsbydistrict/<DIST> | Returns ALL account balance objects
	 */
	@CrossOrigin
	@RequestMapping(value = "/accountsbydistrict/{district}", method = RequestMethod.GET)
	public List<AllAccountBalances> getAccountsByDistrict(@PathVariable("district") String district) {
		return DAM.getAllAccountBalancesByDistrict(district) ;
	}
	
}
