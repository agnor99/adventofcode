package com.agnor99.solver.fifth;

import com.agnor99.solver.AbstractSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver5 extends AbstractSolver {
    @Override
    public void solve() {
        List<SeatPosition> seatPositions = getData("fifth", Solver5::parseString);

        int maxSeatID = -1;
        for(SeatPosition position: seatPositions) {
            if(position.getSeatId() > maxSeatID) {
                maxSeatID = position.getSeatId();
            }
        }
        System.out.println(maxSeatID);

        Map<Integer, SeatPosition> mappedSeatPositions = new HashMap<Integer, SeatPosition>();
        for(SeatPosition position: seatPositions) {
            mappedSeatPositions.put(position.getSeatId(), position);
        }

        for(int i = 0; i < maxSeatID; i++) {
            if(mappedSeatPositions.containsKey(i-1) && !mappedSeatPositions.containsKey(i) && mappedSeatPositions.containsKey(i+1)) {
                System.out.println(i);
            }
        }

    }

    static SeatPosition parseString(String s) {
        SeatPosition seatPosition = new SeatPosition();

        for(int i = 0; i < 7; i++) {
            if(s.charAt(i) == 'B') {
                seatPosition.row += Math.pow(2,6-i);
            }
        }
        for(int i = 0; i < 3; i++) {
            if(s.charAt(i+7) == 'R') {
                seatPosition.column += Math.pow(2,2-i);
            }
        }
        return seatPosition;
    }

    private static class SeatPosition {
        int row;
        int column;

        int getSeatId() {
            return row*8 + column;
        }
    }
}
