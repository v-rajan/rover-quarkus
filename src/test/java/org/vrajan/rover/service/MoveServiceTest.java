package org.vrajan.rover.service;

import com.google.protobuf.util.JsonFormat;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.vrajan.rover.BaseTest;
import org.vrajan.rover.grpc.Input;
import org.vrajan.rover.grpc.Output;
import org.vrajan.rover.grpc.Plateau;

@QuarkusTest
@Slf4j
public class MoveServiceTest extends BaseTest {

  @Inject MoveService moveService;

  @Test
  public void processInstructionTest() throws Exception {

    Input input =
        Input.newBuilder()
            .setPlateau(Plateau.newBuilder().setPositionY(5).setPositionX(5).build())
            .addInstructions(this.createRoverAInstructions())
            .addInstructions(this.createRoverBInstructions())
            .build();

    Output output = this.moveService.processRovers(input);

    String response = JsonFormat.printer().print(output);

    log.info("Response : " + response);

    JSONAssert.assertEquals(
        this.fromFile("./src/test/resources/payload/output/response001.json"),
        JsonFormat.printer().print(output),
        JSONCompareMode.STRICT);
  }
}
