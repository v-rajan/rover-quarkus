package org.vrajan.rover.grpc;

import com.google.protobuf.util.JsonFormat;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.vrajan.rover.BaseTest;

@QuarkusTest
@Slf4j
class CommandTest extends BaseTest {

  @Inject
  @GrpcService("command")
  CommandGrpc.CommandBlockingStub commandClient;

  @Test
  void moveTest() throws Exception {

    Input input =
        Input.newBuilder()
            .setPlateau(Plateau.newBuilder().setPositionY(5).setPositionX(5).build())
            .addInstructions(this.createRoverAInstructions())
            .addInstructions(this.createRoverBInstructions())
            .build();

    log.info("Request : " + JsonFormat.printer().print(input));

    Output output = commandClient.move(input);

    String response = JsonFormat.printer().print(output);

    log.info("Response : " + response);

    JSONAssert.assertEquals(
        this.fromFile("./src/test/resources/payload/output/response001.json"),
        response,
        JSONCompareMode.STRICT);
  }
}
