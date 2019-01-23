package Main.View;

import Main.Controller.Controller;
import Main.Exceptions.NoProgramInputException;
import Main.Model.Expression.ArithExp;
import Main.Model.Expression.ConstExp;
import Main.Model.Expression.VarExp;
import Main.Model.ProgramState;
import Main.Model.Statement.*;
import Main.Model.Utils.ExeStack;
import Main.Model.Utils.Output;
import Main.Model.Utils.SymTable;

import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private void displayMenu() {
        String message = "\n";
        message += "1.Input a program\n";
        message += "2.Execute one step\n";
        message += "3.Complete program evaluation\n";
        message += "4.Change program\n";
        message += "0.Exit\n";
        System.out.print(message);
    }

    public void start() {
        int opt = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.displayMenu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    System.out.print("1/2?\n");
                    opt = scanner.nextInt();
                    switch (opt){
                        case 1:
                            loadHardcodedInput1();
                            break;
                        case 2:
                            loadHardcodedInput2();
                            break;
                    }
                    break;
//                case 2:
//                    try{
//                        controller.executeOneStep();
//                    }
//                    catch (NoProgramInputException e){
//                        System.out.print(e.getMessage());
//                    }
//                    break;
//                case 3:
//                    try{
//                        controller.executeAllSteps();
//                    }
//                    catch (NoProgramInputException e){
//                        System.out.print(e.getMessage());
//                    }
//                    break;
//                case 4:
//                    System.out.print("Input program order number:\n");
//                    opt = scanner.nextInt();
//                    controller.setCurrentProgramState(opt);
//                    break;
                case 0:
                    return;
            }
        }
    }

    private void loadHardcodedInput1() {
        IStmt example3 = new CompStmt(
                new AssignStmt("a",
                        new ArithExp('+',
                                new ConstExp(2),
                                new ArithExp('*',
                                        new ConstExp(3),
                                        new ConstExp(5)))),
                new CompStmt(
                        new AssignStmt("b",
                                new ArithExp('+',
                                        new VarExp("a"),
                                        new ConstExp(1))),
                        new CompStmt(
                                new PrintStmt(new VarExp("b")),
                                new PrintStmt(new VarExp("a"))
                        )));

        ExeStack<IStmt> exeStack3 = new ExeStack<IStmt>();
        Output<Integer> output3 = new Output<Integer>();
        SymTable<String, Integer> symTable3 = new SymTable<String, Integer>();
        exeStack3.push(example3);
        ProgramState programState3 = new ProgramState(exeStack3, output3, symTable3, example3);
        controller.addProgram(programState3);
    }

    private void loadHardcodedInput2() {
        IStmt example4 = new CompStmt(
                new AssignStmt("a",
                        new ArithExp('-',
                                new ConstExp(2),
                                new ConstExp(2))),
                new CompStmt(
                        new IfStmt(
                                new VarExp("a"),
                                new AssignStmt("v",
                                        new ConstExp(2)),
                                new AssignStmt("v",
                                        new ConstExp(3))),
                        new PrintStmt(new VarExp("v"))));
        ExeStack<IStmt> exeStack4 = new ExeStack<IStmt>();
        Output<Integer> output4 = new Output<Integer>();
        SymTable<String, Integer> symTable4 = new SymTable<String, Integer>();
        exeStack4.push(example4);
        ProgramState programState4 = new ProgramState(exeStack4, output4, symTable4, example4);
        controller.addProgram(programState4);
    }
}