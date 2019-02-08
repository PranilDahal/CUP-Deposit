package cup.objects.dam.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cup.objects.AllAccountBalances;
import cup.objects.dam.AllAccountBalancesExtractor;

@RestController
@RequestMapping("api/v1/accountbalances")
public class AllAccountBalancesAPI {

	@Autowired
	AllAccountBalancesExtractor DAM;
	
	/**
	 * @return GET - http://localhost:8080/allAccountBalances | Returns ALL account balance objects
	 */
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<AllAccountBalances> getAllAAB() {
		return DAM.getAllAccountBalances() ;
	}
	
}
