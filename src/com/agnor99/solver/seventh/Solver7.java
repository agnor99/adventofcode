package com.agnor99.solver.seventh;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver7 extends AbstractSolver {

    static List<Bag> bags;

    @Override
    public void solve() {
        bags = getData("seventh", new BagParser());

        int numBagsContainShinyGold = 0;
        for(Bag bag: bags) {
            if(bag.containsShinyGold()) {
                numBagsContainShinyGold++;
            }
        }
        System.out.println(numBagsContainShinyGold);

        int numBagsContainedInShinyGold;
        for(Bag bag: bags) {
            if(bag.isShinyGold()){
                System.out.println(bag.getNumBags());
            }
        }

    }


    private static class BagParser implements ITypeParser<Bag> {

        @Override
        public Bag parse(String toParse) {
            Bag bag = new Bag();

            bag.color = toParse.split(" bags")[0];

            String contains = toParse.split(" contain ")[1];
            String[] bagInfos = contains.split(", ");

            for(String bagInfo: bagInfos) {
                String[] bagInfoInWords = bagInfo.split(" ");
                if(bagInfoInWords[0].equals("no")) {
                    continue;
                }
                int amountOfBags = Integer.parseInt(bagInfoInWords[0]);
                String bagColor = "";
                for(int i = 1; i < bagInfoInWords.length-1; i++) {
                    bagColor+= bagInfoInWords[i] + " ";
                }
                bagColor = bagColor.substring(0, bagColor.length()-1);
                bag.contains.put(bagColor, amountOfBags);
            }
            return bag;
        }
    }


    private static class Bag {
        String color;
        Map<String, Integer> contains = new HashMap<>();

        boolean hasSearchedForShinyGold = false;
        boolean containsShinyGold = false;

        boolean hasSearchedForBagCount = false;
        int numberOfBags;


        boolean isEmpty() { return contains.size() == 0;}

        boolean isColor(String toCheck) {
            return color.equals(toCheck);
        }

        boolean isShinyGold() {
            return isColor("shiny gold");
        }

        boolean containsShinyGold() {
            if(hasSearchedForShinyGold) return containsShinyGold;

            hasSearchedForShinyGold = true;
            for(String color: contains.keySet()) {
                for(Bag bag: Solver7.bags) {
                    if (!bag.isColor(color)) {
                        continue;
                    }
                    if (bag.isShinyGold()) {
                        containsShinyGold = true;
                        return true;
                    } else {
                        if (bag.containsShinyGold()){
                            containsShinyGold = true;
                            return true;
                        }
                    }
                    break;
                }
            }
            return false;
        }

        int getNumBags() {
            if(hasSearchedForBagCount) {return numberOfBags;}
            for(Bag bag: bags) {
                if(contains.containsKey(bag.color)) {
                    numberOfBags+= contains.get(bag.color);
                    numberOfBags+= contains.get(bag.color)*bag.getNumBags();
                }
            }
            hasSearchedForBagCount = true;
            return numberOfBags;
        }
    }
}
