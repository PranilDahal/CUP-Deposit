package ApiTests;

import org.junit.Test;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.get;

public class CUPTest {

    @Test
    public void NonMatchingAccountsTest() {
        get("http://localhost:8080/nonmatching/all")
                .then()
                .assertThat()
                .statusCode(200)
                .body("typename", Matchers.equalTo("Condition Check"));
    }

    @Test
    public void AllAccountsBalanceTest() {

    }
}
