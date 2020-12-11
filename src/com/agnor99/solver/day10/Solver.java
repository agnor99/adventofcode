package com.agnor99.solver.day10;

import com.agnor99.solver.AbstractSolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver extends AbstractSolver {
    int numMinDifferences = 0;
    int numMaxDifferences = 0;
    @Override
    public void solve() {
        List<Integer> joltages = getData("day10", Integer::parseInt);
        joltages.sort(Comparator.comparingInt((Integer a) -> a));
        addNumber(joltages.get(0));
        for(int i = 0; i < joltages.size() -1; i++) {
            int difference = joltages.get(i+1) - joltages.get(i);
            addNumber(difference);
        }
        addNumber(3);
        System.out.println(numMaxDifferences * numMinDifferences);
        solve2(joltages);
    }

    private void addNumber(int difference) {
        if(difference == 1) {
            numMinDifferences++;
        }
        if(difference == 3) {
            numMaxDifferences++;
        }
    }
    private void solve2(List<Integer> joltages) {
        long possibilities = 0L;
        List<JoltageAdapter> adapters = new ArrayList<>();
        adapters.add(new JoltageAdapter(0));
        for(Integer integer: joltages) {
            adapters.add(new JoltageAdapter(integer));
        }
        for(int i = 0; i < adapters.size(); i++) {
            try{
                int offset = 1;
                while(adapters.get(i+offset).joltage <= adapters.get(i).joltage + 3) {
                    JoltageAdapter.combineJoltageAdapter(adapters.get(i), adapters.get(i+offset));
                    offset++;
                }
            }catch(Exception e) {

            }
        }
        System.out.println(adapters.get(0).getCombinations());
    }


    private static class JoltageAdapter {
        List<JoltageAdapter> connectFrom = new ArrayList<>();
        List<JoltageAdapter> connectTo = new ArrayList<>();
        int joltage;
        long combinations = Long.MIN_VALUE;
        JoltageAdapter(int joltage) {
            this.joltage = joltage;
        }
        long getCombinations() {
            if(combinations != Long.MIN_VALUE) {
                return combinations;
            }
            if(connectTo.size() == 0) {
                combinations = 1;
                return combinations;
            }
            combinations = 0;
            for(JoltageAdapter adapter: connectTo) {
                combinations+=adapter.getCombinations();
            }
            return combinations;
        }
        static void combineJoltageAdapter(JoltageAdapter from, JoltageAdapter to) {
            from.connectTo.add(to);
            to.connectFrom.add(from);
        }
    }
}
