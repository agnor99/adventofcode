package com.agnor99.solver.second;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeParser;

import java.util.List;

public class Solver2 extends AbstractSolver {

    @Override
    public void solve() {
        List<Password> passwordList = getData("second", new PasswordParser());
        int countCorrect = 0;
        for(Password p: passwordList) {
            if(p.isValid()) {
                countCorrect++;
            }
        }
        System.out.println(countCorrect);
    }




    private class PasswordParser implements ITypeParser<Password> {
        @Override
        public Password parse(String toParse) {
            return new Password(toParse);
        }
    }

    private class Password {
        int minAmount;
        int maxAmount;
        char character;
        String password;

        Password(String s) {
            String[] parts = s.split("[- :]");
            minAmount = Integer.parseInt(parts[0]);
            maxAmount = Integer.parseInt(parts[1]);
            character = parts[2].charAt(0);
            password = parts[4];
        }
        boolean isValid() {
            int amount = 0;
            for(int i = 0; i < password.length(); i++) {
                if(password.charAt(i) == character) {
                    amount++;
                    if(amount > maxAmount) {
                        return false;
                    }
                }
            }
            return amount >= minAmount;
        }
    }
}
