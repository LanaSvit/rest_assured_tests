import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bareer hjhcxjhc")
                .addCookie("myCookie", "value1")
                .addCookie("myCookie2", "value2")
                .build();
    }
    @Test
    public void getAllEmployeesTest(){
        Response response = RestAssured.given().log().all().get("/employees");
        response.then().statusCode(200);
        response.prettyPrint();
        response.jsonPath().get("data.findAll{it.employee_salary>10000}");
        response.jsonPath().get("data.find{it.employee_age<20}.id");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonAllEmployeesShema.json"));
    }

    @Test
    public void getSingleEmployeeTest(){
        Response response = RestAssured.get("/employee/{id}", 4);
        response.then().body("status", equalTo("success"));
        response.then().body("data.employee_name", equalTo("Cedric Kelly"));
        response.prettyPrint();
    }

    @Test
    public void createNewEmployeeTest(){
//        JSONObject body = new JSONObject();
//        body.put("name", "testName");
//        body.put("salary", "10000");
//        body.put("age", "38");
//        Response response = RestAssured.given()
//                .body(body)
//                .post("/create");
//        response.prettyPrint();

//        JSONObject response2 = new JSONObject(response.asString());
//        response2.get("message");
//        ((JSONObject)response2.get("data")).get("id");

        CreateEmployeeBody body = new CreateEmployeeBody().builder()
                .name("testName")
                .salary("10000")
                .age("38")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .post("/create");
        response.prettyPrint();

        response.as(ResponseEmployee.class);
    }
}
