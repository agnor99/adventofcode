package com.agnor99.solver.sixth;

import com.agnor99.solver.AbstractSolver;

import java.util.List;

public class Solver6 extends AbstractSolver {
    @Override
    public void solve() {
        List<CustomsDeclarationGroup> groups = getBatchData("sixth", (List<String> batch) -> {
           CustomsDeclarationGroup group = new CustomsDeclarationGroup();
           for(String s: batch) {
               for(int i = 0; i < s.length(); i++) {
                   group.answers[s.charAt(i)-97] = true;
               }
           }
           return group;
        });

        int answers = 0;
        for(CustomsDeclarationGroup group: groups) {
            for(int i = 0; i < group.answers.length; i++) {
                if(group.answers[i]) {
                    answers++;
                }
            }
        }
        System.out.println(answers);
    }


    private class CustomsDeclarationGroup {
        boolean[] answers = new boolean[26];
    }
}
