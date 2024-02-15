import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class StatusTests
{

    @Test
    void checkTotal20()
    {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total",is(20));
    }

    @Test
    void checkTotal20WithLogs()
    {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .body("total",is(20));
    }

    @Test
    void checkTotal20ResponseLogs()
    {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total",is(21));
    }

    @Test
    void checkTotal20WithStatusLogs()
    {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .body("total",is(20))
                .body("browsers.firefox", hasKey("97.0"));
    }
}
