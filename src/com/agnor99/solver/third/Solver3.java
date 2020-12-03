package com.agnor99.solver.third;

import com.agnor99.solver.AbstractSolver;

import java.util.ArrayList;
import java.util.List;

public class Solver3 extends AbstractSolver {
    List<List<Boolean>> trees;
    @Override
    public void solve() {
        trees = getData("third",(String line) -> {
            List<Boolean> retValues = new ArrayList<>();
            for(int i = 0; i < line.length(); i++) {
                retValues.add('#' == line.charAt(i));
            }
            return retValues;
        });
        System.out.println(getTressWithSlope(3, 1));
        System.out.println(getTressWithSlope(1,1)*getTressWithSlope(3,1)*getTressWithSlope(5,1)*getTressWithSlope(7,1)*getTressWithSlope(1,2));
    }

    private int getTressWithSlope(int dx, int dy) {
        int y = 0;
        int x = 0;
        int numTrees = 0;
        while(y < trees.size()-dy) {
            y+=dy;
            x+=dx;
            if(trees.get(y).get(x%(trees.get(0).size()))) {
                numTrees++;
            }
        }
        return numTrees;
    }

}
