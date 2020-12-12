package com.agnor99.solver.day12;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeParser;

import java.util.List;

public class Solver extends AbstractSolver {
    @Override
    public void solve() {
        Ship ship = new Ship();
        List<MovementCommand> commands = getData("day12", new MovementCommandParser());
        for(MovementCommand command: commands) {
            command.applyMovement(ship);
        }
        System.out.println(Math.abs(ship.east) + Math.abs(ship.north));
    }
    public class Ship {
        int north = 0;
        int east = 0;
        Facing facing = Facing.EAST;

    }

    private class MovementCommandParser implements ITypeParser<MovementCommand> {

        @Override
        public MovementCommand parse(String toParse) {
            char character = toParse.charAt(0);
            int amount = Integer.parseInt(toParse.substring(1));
            switch (character) {
                case 'L':
                    amount = 360 - amount;
                case 'R':
                    amount = amount/90;
                    return new  RotationMovementCommand(amount);
                case 'N':
                case 'E':
                case 'S':
                case 'W':
                    return new AbsoluteMovementCommand(amount, character);
                case 'F':
                    return new RelativeMovementCommand(amount);
            }
            throw new UnsupportedOperationException();
        }
    }


    public abstract class MovementCommand {
        abstract void applyMovement(Ship ship);
    }
    public class AbsoluteMovementCommand extends MovementCommand{
        int amount;
        Facing direction;

        AbsoluteMovementCommand(int amount, char facing) {
            this.amount = amount;
            switch (facing) {
                case 'N':
                    direction = Facing.NORTH;
                    break;
                case 'E':
                    direction = Facing.EAST;
                    break;
                case 'S':
                    direction = Facing.SOUTH;
                    break;
                case 'W':
                    direction = Facing.WEST;
                    break;
            }
        }
        @Override
        void applyMovement(Ship ship) {
            ship.north += direction.north*amount;
            ship.east += direction.east*amount;
        }
    }
    public class RelativeMovementCommand extends MovementCommand {
        int amount;

        RelativeMovementCommand(int amount) {
            this.amount = amount;
        }

        @Override
        void applyMovement(Ship ship) {
            ship.north += ship.facing.north*amount;
            ship.east += ship.facing.east*amount;
        }
    }
    public class RotationMovementCommand extends MovementCommand{
        int amount; //Amount of Steps to the right

        RotationMovementCommand(int amount) {
            this.amount = amount;
        }

        @Override
        void applyMovement(Ship ship) {
            Facing oldFacing = ship.facing;
            int oldIndex = 0;
            for(int i = 0; i < Facing.values().length;i++) {
                if(Facing.values()[i] == oldFacing) {
                    oldIndex = i;
                    break;
                }
            }
            ship.facing = Facing.values()[(oldIndex+amount)%4];
        }
    }


    enum Facing{
        NORTH(1,0),
        EAST(0,1),
        SOUTH(-1,0),
        WEST(0,-1);

        int north;
        int east;
        Facing(int north, int east) {
            this.north = north;
            this.east = east;
        }
    }
}
