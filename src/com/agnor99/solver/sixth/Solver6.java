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

        System.out.println(getTrueInDeclarations(groups));

        groups = getBatchData("sixth", (List<String> batch) -> {
            CustomsDeclarationGroup group = new CustomsDeclarationGroup();
            for(int i = 0; i < group.answers.length; i++) {
                group.answers[i] = true;
            }
            for(String s: batch) {
                CustomsDeclarationGroup personDeclaration = new CustomsDeclarationGroup();
                for(int i = 0; i < s.length(); i++) {
                    personDeclaration.answers[s.charAt(i)-97] = true;
                }
                for(int i = 0; i < 26; i++) {
                    group.answers[i] = group.answers[i] && personDeclaration.answers[i];
                }
            }
            return group;
        });

        System.out.println(getTrueInDeclarations(groups));

    }

    private int getTrueInDeclarations(List<CustomsDeclarationGroup> groups) {
        int answers = 0;
        for(CustomsDeclarationGroup group: groups) {
            for(int i = 0; i < group.answers.length; i++) {
                if(group.answers[i]) {
                    answers++;
                }
            }
        }
        return answers;
    }

    private class CustomsDeclarationGroup {
        boolean[] answers = new boolean[26];
    }
}
