/*
 *
 */
package org.vrajan.rover.rest;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.vrajan.rover.grpc.CommandGrpc;
import org.vrajan.rover.grpc.Input;
import org.vrajan.rover.grpc.Input.Builder;

/*
 * Command REST Service to support rover movement
 */
@Slf4j
@Path("/command")
public class RoverApi {

  /** The command client. */
  @Inject
  @GrpcService("command")
  CommandGrpc.CommandBlockingStub commandClient;

  /*
   * Manage rover move operations
   *
   * @param json the date required for the rover move operations
   *
   * @return the response list of the rover current location.
   *
   * @throws InvalidProtocolBufferException the invalid protocol buffer exception
   *
   */
  @POST
  public String move(String json) throws InvalidProtocolBufferException {

    Builder inputBuilder = Input.newBuilder();

    JsonFormat.parser().ignoringUnknownFields().merge(json, inputBuilder);
    Input input = inputBuilder.build();

    log.info("Request : " + JsonFormat.printer().print(input));

    String response = JsonFormat.printer().print(commandClient.move(input));

    log.info("Response : " + response);

    return response;
  }
}
