package org.vrajan.rover.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.vrajan.rover.grpc.Instruction;
import org.vrajan.rover.grpc.Instruction.Direction;
import org.vrajan.rover.grpc.Rover;
import org.vrajan.rover.grpc.Rover.Point;

/*
 * Compute rover current location.
 */
@Getter
@Builder
@Slf4j
public class Location {

  /** The position X. */
  private int positionX;

  /** The position Y. */
  private int positionY;

  /** The point. */
  private Point point;

  /**
   * Process instructions.
   *
   * @param instructions the instructions required for the movement
   * @return the rover with the current location
   */
  public Rover processInstructions(List<Instruction> instructions) {

    instructions.stream()
        .forEach(
            i -> {
              switch (i.getDirectionValue()) {
                case Direction.LEFT_VALUE:
                  this.left();
                  break;
                case Direction.MOVE_VALUE:
                  this.move();
                  break;
                case Direction.RIGHT_VALUE:
                  this.right();
                  break;
                default:
                  break;
              }
            });

    return null;
  }

  /** Left. */
  private void left() {
    switch (point.getNumber()) {
      case Point.EAST_VALUE:
        point = Point.NORTH;
        break;
      case Point.NORTH_VALUE:
        point = Point.WEST;
        break;
      case Point.SOUTH_VALUE:
        point = Point.EAST;
        break;
      case Point.WEST_VALUE:
        point = Point.SOUTH;
        break;
    }
  }

  /** Right. */
  private void right() {
    switch (point.getNumber()) {
      case Point.EAST_VALUE:
        point = Point.SOUTH;
        break;
      case Point.NORTH_VALUE:
        point = Point.EAST;
        break;
      case Point.SOUTH_VALUE:
        point = Point.WEST;
        break;
      case Point.WEST_VALUE:
        point = Point.NORTH;
        break;
    }
  }

  /** Move. */
  private void move() {
    switch (point.getNumber()) {
      case Point.EAST_VALUE:
        this.positionX = positionX + 1;
        break;
      case Point.NORTH_VALUE:
        this.positionY = positionY + 1;
        break;
      case Point.SOUTH_VALUE:
        this.positionY = positionY - 1;
        break;
      case Point.WEST_VALUE:
        this.positionX = positionX - 1;
        break;
    }
  }
}
