package com.agnor99.solver.first;

import com.agnor99.solver.AbstractSolver;

import java.util.ArrayList;
import java.util.List;

public class Solver extends AbstractSolver {
    List<Integer> values = new ArrayList<>();
    @Override
    public void solve() {
        values = getInteger("first");
        System.out.println(getSolution());
    }
    private int getSolution() {
        for(int i = 0; i < values.size()-1; i++) {
            for(int j = i+1; j < values.size(); j++) {
                if(values.get(i)+values.get(j) == 2020) {
                    return values.get(i)*values.get(j);
                }
            }
        }
        return 0;
    }
}
