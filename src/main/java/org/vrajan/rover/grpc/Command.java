package org.vrajan.rover.grpc;

import io.grpc.stub.StreamObserver;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.vrajan.rover.service.MoveService;

/*
 * Command gRPC Service to support rover movement
 */
@Singleton
public class Command extends CommandGrpc.CommandImplBase {

  /** The move service. */
  @Inject MoveService moveService;

  /*
   * Manage rover move operations
   *
   * @param input the date required for the rover move operations
   * @param responseObserver the response list of the rover current location.
   */
  @Override
  public void move(Input input, StreamObserver<Output> responseObserver) {

    responseObserver.onNext(moveService.processRovers(input));

    responseObserver.onCompleted();
  }
}
