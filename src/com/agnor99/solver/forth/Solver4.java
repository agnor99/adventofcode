package com.agnor99.solver.forth;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeBatchParser;

import java.util.List;

public class Solver4 extends AbstractSolver {
    @Override
    public void solve() {
        List<Passport> passports = getBatchData("forth", new PassportBatchParser());

        int valid = 0;
        int valid2 = 0;
        for(Passport p: passports) {
            p.validate();
            p.validate2();
            if(p.valid) {
                valid++;
            }
            if(p.valid2) {
                valid2++;
            }
        }
        System.out.println(valid + " out of " + passports.size() + " Passports were valid");
        System.out.println(valid2 + " out of " + passports.size() + " Passports were valid and logicly");

    }

    private static class PassportBatchParser implements ITypeBatchParser<Passport> {
        @Override
        public Passport parse(List<String> toParse) {
            Passport passport = new Passport();
            try {
                for (String s : toParse) {
                    String[] values = s.split(" ");
                    for (String t : values) {
                        String[] keyValue = t.split(":");

                        switch (keyValue[0]) {
                            case "byr":
                                passport.byr = keyValue[1];
                                break;
                            case "iyr":
                                passport.iyr = keyValue[1];
                                break;
                            case "eyr":
                                passport.eyr = keyValue[1];
                                break;
                            case "hgt":
                                passport.hgt = keyValue[1];
                                break;
                            case "hcl":
                                passport.hcl = keyValue[1];
                                break;
                            case "ecl":
                                passport.ecl = keyValue[1];
                                break;
                            case "pid":
                                passport.pid = keyValue[1];
                                break;
                            case "cid":
                                passport.cid = keyValue[1];
                                break;
                        }
                    }

                }
            }catch (Exception e) {
                passport.valid = false;
                passport.valid2 = false;
            }
            return passport;
        }
    }


    private static class Passport {
        String byr = "-1";
        String iyr = "-1";
        String eyr = "-1";
        String hgt = "-1";
        String hcl = "-1";
        String ecl = "-1";
        String pid = "-1";
        String cid = "-1";
        boolean valid = true;
        boolean valid2 = true;

        void validate() {
            if(!valid) return;
            valid = !(byr.equals("-1") || iyr.equals("-1") || eyr.equals("-1") || hgt.equals("-1") || hcl.equals("-1") || ecl.equals("-1") || pid.equals("-1"));
        }

        void validate2() {
            if(!valid2) return;
            try {
                int num = Integer.parseInt(byr);
                if(num > 2002 || num < 1920) {
                    valid2 = false;
                    return;
                }
                num = Integer.parseInt(iyr);
                if(num > 2020 || num < 2010) {
                    valid2 = false;
                    return;
                }
                num = Integer.parseInt(eyr);
                if(num > 2030 || num < 2020) {
                    valid2 = false;
                    return;
                }
                if(hgt.substring(hgt.length()-2).equals("cm")) {
                    num = Integer.parseInt(hgt.substring(0,hgt.length()-2));
                    if(num > 193 || num < 150) {
                        valid2 = false;
                        return;
                    }
                }else if(hgt.substring(hgt.length()-2).equals("in")) {
                    num = Integer.parseInt(hgt.substring(0,hgt.length()-2));
                    if(num > 76 || num < 59) {
                        valid2 = false;
                        return;
                    }
                }else {
                    valid2 = false;
                    return;
                }
                if(!hcl.matches("#[0-9,a-f]{6}")) {
                    valid2 = false;
                    return;
                }
                String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                boolean validColor = false;
                for(String s: validColors) {
                    if(s.equals(ecl)) {
                        validColor = true;
                    }
                }
                if(!validColor) {
                    valid2 = false;
                    return;
                }
                if(!pid.matches("[0-9]{9}")) {
                    valid2 = false;
                    return;
                }


            }catch(Exception e) {
                valid2 = false;
            }
        }
        boolean isValid() {
            return valid;
        }

    }
}
