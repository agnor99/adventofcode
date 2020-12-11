package com.agnor99.solver.day11;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeParser;

import java.util.ArrayList;
import java.util.List;

public class Solver extends AbstractSolver {
    public static final int FLOOR = 0;
    public static final int EMPTY = 1;
    public static final int OCCUPIED = 2;


    @Override
    public void solve() {
        List<Integer[]> data = getData("day11", new Parser());
        SeatMap seats = new SeatMap();
        int[][] seatData = new int[data.size()][data.get(0).length];
        for(int x = 0; x < data.size(); x++) {
            for(int y = 0; y < data.get(0).length; y++) {
                seatData[x][y] = data.get(x)[y];
            }
        }
        seats.state = seatData;

        while(seats.change()) {
        }
        System.out.println(seats.getOccupied());

        seats = new SeatMap();
        for(int x = 0; x < data.size(); x++) {
            for(int y = 0; y < data.get(0).length; y++) {
                seatData[x][y] = data.get(x)[y];
            }
        }
        seats.state = seatData;
        while(seats.change2()) {
        }
        System.out.println(seats.getOccupied());
    }

    private class Parser implements ITypeParser<Integer[]> {

        @Override
        public Integer[] parse(String toParse) {
            List<Integer> data = new ArrayList<>();
            for(char character: toParse.toCharArray()) {
                if(character == '.') {
                    data.add(FLOOR);
                }else if(character == 'L') {
                    data.add(EMPTY);
                }
            }
            Integer[] intData = new Integer[data.size()];
            for(int i= 0; i < data.size(); i++){
                intData[i] = data.get(i);
            }

            return intData;
        }
    }

    private class SeatMap {
        int[][] state;

        void print() {
            for(int[] data: state) {
                String output = "";
                for(int value: data) {
                    output+=value;
                }
                System.out.println(output);
            }
            System.out.println("");
        }
        boolean change() {
            int[][] newState = new int[state.length][state[0].length];

            for(int x = 0; x < newState.length; x++) {
                for(int y = 0; y < newState[0].length; y++) {
                    newState[x][y] = getNewStateAtPos(x, y);
                }
            }
            int[][] oldState = state;
            state = newState;
            for(int x = 0; x < state.length; x++) {
                for (int y = 0; y < state[0].length; y++) {
                    if(state[x][y] != oldState[x][y]){
                        return true;
                    }
                }
            }
            return false;
        }

        private int getNewStateAtPos(int x, int y) {
            int neighbors = getCountNeighbors(x,y);
            if(state[x][y] == OCCUPIED && neighbors >= 4) return EMPTY;
            if(state[x][y] == EMPTY && neighbors == 0) return OCCUPIED;
            return state[x][y];
        }

        boolean change2() {
            int[][] newState = new int[state.length][state[0].length];
            for(int x = 0; x < newState.length; x++) {
                for(int y = 0; y < newState[0].length; y++) {
                    newState[x][y] = getNewStateAtPos2(x, y);
                }
            }

            int[][] oldState = state;
            state = newState;
            for(int x = 0; x < state.length; x++) {
                for (int y = 0; y < state[0].length; y++) {
                    if(state[x][y] != oldState[x][y]){
                        return true;
                    }
                }
            }
            return false;
        }
        private int getCountNeighbors(int x, int y) {
            int retValue = 0;
            for(int dx = -1; dx <= 1; dx++) {
                for(int dy = -1; dy <= 1; dy++) {
                    if(dx != 0 || dy != 0) {
                        retValue = retValue + (isBlocked(x+dx, y+dy) ? 1:0);
                    }
                }
            }
            return retValue;
        }
        private  int getNewStateAtPos2(int x, int y) {
            int neighbors = getCountNeighbors2(x,y);
            if(state[x][y] == OCCUPIED && neighbors >= 5) return EMPTY;
            if(state[x][y] == EMPTY && neighbors == 0) return OCCUPIED;
            return state[x][y];
        }
        private int getCountNeighbors2(int x, int y) {
            int retValue = 0;
            for(int dx = -1; dx <= 1; dx++) {
                for(int dy = -1; dy <= 1; dy++) {
                    if(dx != 0 || dy != 0) {
                        int offsetX = dx;
                        int offsetY = dy;
                        while(isFloor(x + offsetX, y + offsetY)) {
                            offsetX += dx;
                            offsetY += dy;
                        }

                        retValue = retValue + (isBlocked(x+offsetX, y+offsetY) ? 1:0);
                    }
                }
            }
            return retValue;
        }
        private boolean isBlocked(int x, int y) {
            if(x < 0 || y < 0) {
                return false;
            }
            if(x >= state.length || y >= state[0].length) {
                return false;
            }
            return state[x][y] == OCCUPIED;
        }
        private boolean isFloor(int x, int y) {
            if(x < 0 || y < 0) {
                return false;
            }
            if(x >= state.length || y >= state[0].length) {
                return false;
            }
            return state[x][y] == FLOOR;
        }
        int getOccupied() {
            int retValue = 0;
            for(int x = 0; x < state.length; x++) {
                for(int y = 0; y < state[0].length; y++) {
                    if(isBlocked(x,y)) {
                        retValue++;
                    }
                }
            }
            return retValue;
        }
    }
}
