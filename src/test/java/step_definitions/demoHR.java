package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.DBUtility;
import utils.Database;

import java.util.List;
import java.util.Map;

public class demoHR {

    @Given("user establish connection to database {string}")
    public void user_establish_connection_to_database(String database) {
        DBUtility.establishConnection(Database.POSTGRESQL, database );
    }

    @Then("user executes query {string} and verifies result {string}")
    public void user_executes_query_and_verifies_result(String query, String result) {
        List<Map<String, Object>> results = DBUtility.getQueryResults(query);
        outerloop:
        for(Map<String, Object> map : results) {
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getKey().matches("dept_name") && entry.getValue().equals(result)) {
                    System.out.println("Successfull query : " + entry.getValue());
                    Assert.assertEquals(entry.getValue(), result);
                    break outerloop;
                }
            }
        }
    }

    @Then("user executes query {string} and verifies column name {string} and result value {string}")
    public void user_executes_query_and_verifies_column_name_and_result_value(String query, String columName, String value) {
        List<Map<String, Object>> results = DBUtility.getQueryResults(query);

        outerloop:
        for (Map<String, Object> map : results) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().matches(columName) && entry.getValue().equals(value)){
                    System.out.println("Column Name: "+entry.getKey());
                    System.out.println("Value: "+entry.getValue());
                    Assert.assertEquals(entry.getKey(),columName);
                    Assert.assertEquals(entry.getValue(),value);
                    break outerloop;
                }
            }
        }
    }

    @Then("user gets total row count for query {string}")
    public void user_gets_total_row_count_for_query(String query) {
        int count = DBUtility.getRowsCount(query);
        System.out.println("Count: " + count);
        Assert.assertEquals(count, 9);
    }

    @Then("user closes connection to database")
    public void user_closes_connection_to_database() {
        DBUtility.closeConnections();
    }


    @Then("User1 executes query {string} and verifies column name {string} and result value {string}")
    public void user1_executes_query_and_verifies_column_name_and_result_value(String query, String columName, String value) {
        List<Map<String, Object>> results = DBUtility.getQueryResults(query);

        outerloop:
        for (Map<String, Object> map : results) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().matches(columName) && entry.getValue().equals(value)){
                    System.out.println("Column Name: "+entry.getKey());
                    System.out.println("Value: "+entry.getValue());
                    Assert.assertEquals(entry.getKey(),columName);
                    Assert.assertEquals(entry.getValue(),value);
                    break outerloop;
                } else {

                }
            }
        }
    }

}
