package com.agnor99.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolver {
    public abstract void solve();

    protected final <T> List<T> getData(String packageQualifier, ITypeParser<T> parser) {
        List<T> data = new ArrayList<>();
        for(String s: getStrings(packageQualifier)) {
            data.add(parser.parse(s));
        }
        return data;
    }
    protected final List<String> getStrings(String packageQualifier) {
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/com/agnor99/solver/" + packageQualifier + "/Input"));
            String line = reader.readLine();
            while (line != null) {
                strings.add(line);
                line = reader.readLine();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }
}
