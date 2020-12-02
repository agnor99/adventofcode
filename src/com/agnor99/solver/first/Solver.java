package com.agnor99.solver.first;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    public void solve() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/com/agnor99/solver/first/Input"));
            String line = reader.readLine();
            List<Integer> values = new ArrayList<>();
            while (line != null) {
                values.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            for(int i = 0; i < values.size()-1; i++) {
                for(int j = i+1; j < values.size(); j++) {
                    if(values.get(i)+values.get(j) == 2020) {
                        System.out.println(values.get(i)*values.get(j));
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
