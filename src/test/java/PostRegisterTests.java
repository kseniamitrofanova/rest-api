import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PostRegisterTests
{
    @Test
    void successfullRegisterTest1()
    {
        String authData= "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token",is("QpwL5tke4Pnpja7X4"));
            }

    @Test
    void unsuccessfullRegisterNotEmail400Test()
    {
        String authData= "{\n" +
                "    \"email\": \"eve1.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error",is("Note: Only defined users succeed registration"));
    }

    @Test
    void unsuccessfullRegisterMissEorU400Test()
    {
        String authData= "";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error",is("Missing email or username"));
    }

    @Test
    void unsuccessfullRegister201Test()
    {
        String authData= "";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register1")

                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    @Test
    void unsuccessfullRegisterMissEmail400Test()
    {
        String authData= "{\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error",is("Missing email or username"));
    }


    @Test
    void unsuccessfullRegister400Test()
    {
        String authData= "{\n" +
                "    \"email\": \"eve1.holt@reqres.in\",\n" +
                "}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }
}
