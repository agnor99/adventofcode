package com.agnor99.solver.ninth;

import com.agnor99.solver.AbstractSolver;

import java.util.List;

public class Solver9 extends AbstractSolver {

    @Override
    public void solve() {
        List<Long> data = getData("ninth", Long::parseLong);

        for(int i = 25; i < data.size(); i++) {
            if(!checkForSum(data, i)) {
                System.out.println("Weakness-Property: " + data.get(i));

                System.out.println("Weakness-Sum: " + calculateWeakness(searchWeaknessSet(data, data.get(i))));
                break;
            }
        }
    }

    private boolean checkForSum(List<Long> data, int index) {
        for(int offset = -25; offset < 1; offset++) {
            for(int offset2 = offset+1; offset2 < 0; offset2++) {
                if(data.get(index) == data.get(index + offset) + data.get(index + offset2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Long> searchWeaknessSet(List<Long> data, long weakness) {
        for(int i = 0; i < data.size()-2; i++) {
            int j = 2;
            while(getSum(data.subList(i,i+j)) <= weakness) {
                if(getSum(data.subList(i, i+j)) == weakness) {
                    return data.subList(i, i+j);
                }
                j++;
            }
        }
        return null;
    }

    private long getSum(List<Long> data) {
        long sum = 0L;
        for(Long value: data) {
            sum += value;
        }
        return sum;
    }

    private long calculateWeakness(List<Long> data) {
        return getMax(data) + getMin(data);
    }

    private long getMax(List<Long> data) {
        long maxValue = Long.MIN_VALUE;
        for(long l: data) {
            if(l > maxValue) {
                maxValue = l;
            }
        }
        return maxValue;
    }
    private long getMin(List<Long> data) {
        long minValue = Long.MAX_VALUE;
        for(long l: data) {
            if(l < minValue) {
                minValue = l;
            }
        }
        return minValue;
    }
}
