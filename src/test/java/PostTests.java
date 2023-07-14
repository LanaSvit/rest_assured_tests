import io.restassured.RestAssured;

public class PostTests {
    public void getReturns200() {
        RestAssured
                .given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
