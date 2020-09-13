package org.vrajan.rover;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.vrajan.rover.grpc.Instruction;
import org.vrajan.rover.grpc.Instruction.Direction;
import org.vrajan.rover.grpc.Instructions;
import org.vrajan.rover.grpc.Rover;
import org.vrajan.rover.grpc.Rover.Point;

public class BaseTest {

  public Instructions createRoverAInstructions() {

    Rover rover = Rover.newBuilder().setPositionX(1).setPositionY(2).setPoint(Point.NORTH).build();

    return Instructions.newBuilder()
        .addInstructions(Instruction.newBuilder().setDirection(Direction.LEFT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.LEFT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.LEFT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.LEFT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .setRover(rover)
        .build();
  }

  public Instructions createRoverBInstructions() {

    Rover rover = Rover.newBuilder().setPositionX(3).setPositionY(3).setPoint(Point.EAST).build();

    return Instructions.newBuilder()
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.RIGHT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.RIGHT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.RIGHT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.RIGHT))
        .addInstructions(Instruction.newBuilder().setDirection(Direction.MOVE))
        .setRover(rover)
        .build();
  }

  public String fromFile(String fileName) throws Exception {
    return new String(Files.readAllBytes(Paths.get(fileName)));
  }
}
