package org.vrajan.rover.rest;

import static io.restassured.RestAssured.given;

import com.google.protobuf.util.JsonFormat;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.vrajan.rover.BaseTest;
import org.vrajan.rover.grpc.Input;
import org.vrajan.rover.grpc.Plateau;

@QuarkusTest
@Slf4j
class RoverApiTest extends BaseTest {

  @Test
  void moveTest() throws Exception {

    Input input =
        Input.newBuilder()
            .setPlateau(Plateau.newBuilder().setPositionY(5).setPositionX(5).build())
            .addInstructions(this.createRoverAInstructions())
            .addInstructions(this.createRoverBInstructions())
            .build();

    String request = JsonFormat.printer().print(input);

    log.info("Request : " + request);

    String response =
        given().body(request).post("/command").then().statusCode(200).extract().asString();

    log.info("Response : " + response);

    JSONAssert.assertEquals(
        this.fromFile("./src/test/resources/payload/output/response001.json"),
        response,
        JSONCompareMode.STRICT);
  }
}
