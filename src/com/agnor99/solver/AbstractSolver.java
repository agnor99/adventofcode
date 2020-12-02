package com.agnor99.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolver {
    public abstract void solve();

    protected final List<Integer> getInteger(String packageQualifier) {
        List<Integer> integer = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/com/agnor99/solver/" + packageQualifier + "/Input"));
            String line = reader.readLine();
            while (line != null) {
                integer.add(Integer.parseInt(line));
                line = reader.readLine();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return integer;
    }

}
