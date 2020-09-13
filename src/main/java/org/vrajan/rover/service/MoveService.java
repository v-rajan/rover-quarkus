package org.vrajan.rover.service;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.vrajan.rover.grpc.Input;
import org.vrajan.rover.grpc.Output;
import org.vrajan.rover.grpc.Output.Builder;
import org.vrajan.rover.grpc.Rover;
import org.vrajan.rover.model.Location;

/*
 * Compute rover current location based on the supplied instructions.
 */
@ApplicationScoped
@Slf4j
public class MoveService {

  /**
   * Process rover movement.
   *
   * @param input the input
   * @return the output
   */
  public Output processRovers(Input input) {

    Builder output = Output.newBuilder();

    input.getInstructionsList().stream()
        .forEach(
            i -> {

              // Create location to process instructions for rover.
              Rover rover = i.getRover();
              Location location =
                  Location.builder()
                      .point(rover.getPoint())
                      .positionX(rover.getPositionX())
                      .positionY(rover.getPositionY())
                      .build();

              location.processInstructions(i.getInstructionsList());

              // Add processed rover.
              output.addRovers(
                  Rover.newBuilder()
                      .setPositionY(location.getPositionY())
                      .setPositionX(location.getPositionX())
                      .setPoint(location.getPoint())
                      .build());
            });

    return output.build();
  }
}
