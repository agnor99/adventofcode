package com.agnor99.solver.eight;

import com.agnor99.solver.AbstractSolver;
import com.agnor99.solver.ITypeParser;

import java.util.ArrayList;
import java.util.List;

public class Solver8 extends AbstractSolver {
    @Override
    public void solve() {
        List<Command> commands = getData("eight", new CommandParser());
        executeProgram(commands, false);

        for(int i = 0; i < commands.size(); i++) {
            if (commands.get(i).keyWord.equals("nop")) {
                commands.get(i).keyWord = "jmp";
                executeProgram(commands, true);
                commands.get(i).keyWord = "nop";
            }
            if (commands.get(i).keyWord.equals("jmp")) {
                commands.get(i).keyWord = "nop";
                executeProgram(commands, true);
                commands.get(i).keyWord = "jmp";
            }
        }
    }
    private void executeProgram(List<Command> commands, boolean onlyOnFinish) {

        boolean[] handled = new boolean[commands.size()];

        int nextLine = 0;
        int accumulator = 0;

        try {
            while (!handled[nextLine]) {
                handled[nextLine] = true;
                Command currentCommand = commands.get(nextLine);
                switch (currentCommand.keyWord) {
                    case "acc":
                        accumulator += currentCommand.value;
                    case "nop":
                        nextLine++;
                        break;
                    case "jmp":
                        nextLine += currentCommand.value;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Program finished without error with an accumulator of " + accumulator);
        }
        if(!onlyOnFinish) {
            System.out.println("Program finished with an accumulator of " + accumulator);
        }
    }

    private static class CommandParser implements ITypeParser<Command> {
        @Override
        public Command parse(String toParse) {
            Command command = new Command();
            String[] parts = toParse.split(" ");
            command.keyWord = parts[0];
            command.value = Integer.parseInt(parts[1]);
            return command;
        }
    }

    private static class Command {
        String keyWord;
        int value;
    }
}
