import Main.Controller.Controller;
import Main.Model.Expression.*;
import Main.Model.ProgramState;
import Main.Model.Statement.*;
import Main.Model.Utils.*;
import Main.Repository.IRepository;
import Main.Repository.Repository;
import Main.View.*;
import Main.View.View;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String... args) {
        IStmt example1 = new CompStmt(
                new OpenRFile("var_f","file1.txt"),
                new CompStmt(
                        new ReadFile("var_c", new VarExp("var_f")),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt( new VarExp("var_c"), new CompStmt(new ReadFile("var_c",
                                                new VarExp("var_f")),new PrintStmt(new VarExp("var_c"))),
                                                new PrintStmt(new ConstExp(0))),
                                        new CloseRFile(new VarExp("var_f")))
                        )));
        ExeStack<IStmt> exeStack1 = new ExeStack<IStmt>();
        Output<Integer> output1 = new Output<Integer>();
        SymTable<String, Integer> symTable1 = new SymTable<String, Integer>();
        exeStack1.push(example1);
        ProgramState programState1 = new ProgramState(exeStack1, output1, symTable1, example1);
        Repository r1=new Repository("log.txt", programState1);
        Controller c1=new Controller(r1);

        IStmt example2= new CompStmt(new OpenRFile("var_f","file1.txt"),
                new CompStmt( new ReadFile("var_c", new VarExp("var_f"+2)),
                        new CompStmt(new IfStmt(new VarExp("var_f"),new CompStmt(new ReadFile("var_c", new VarExp("var_f")),
                                new PrintStmt(new VarExp("var_c"))),
                                new PrintStmt(new ConstExp(0))),
                                new CloseRFile(new VarExp("var_f")))));

        ExeStack<IStmt> exeStack2 = new ExeStack<IStmt>();
        Output<Integer> output2 = new Output<Integer>();
        SymTable<String, Integer> symTable2 = new SymTable<String, Integer>();
        exeStack2.push(example2);
        ProgramState programState2 = new ProgramState(exeStack2, output2, symTable2, example2);
        Repository r2=new Repository("log.txt", programState2);
        Controller c2=new Controller(r2);

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
        Repository r3=new Repository("log.txt", programState3);
        Controller c3=new Controller(r3);

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
        Repository r4=new Repository("log.txt", programState4);
        Controller c4=new Controller(r4);

        IStmt example5 = new CompStmt(
                new AssignStmt("v",
                        new ConstExp(10)),
                new CompStmt(
                            new newStmt("v",
                                new ConstExp(20)),
                            new CompStmt(
                                    new newStmt("a",
                                            new ConstExp(22)),
                                new CompStmt(
                                        new wH("a",
                                                new ConstExp(30)),
                                        new CompStmt(
                                                new PrintStmt(
                                                        new VarExp("a")),
                                                new CompStmt(
                                                        new PrintStmt(
                                                                new rH("a")
                                                        ),
                                                        new AssignStmt("a", new ConstExp(0))
                                                    )
                                                )
                                        )
                                )
                )
        );

        ExeStack<IStmt> exeStack5 = new ExeStack<IStmt>();
        Output<Integer> output5 = new Output<Integer>();
        SymTable<String, Integer> symTable5 = new SymTable<String, Integer>();
        exeStack5.push(example5);
        ProgramState programState5 = new ProgramState(exeStack5, output5, symTable5, example5);
        Repository r5=new Repository("log.txt", programState5);
        Controller c5=new Controller(r5);

        TextMenu menu=new TextMenu();
        menu.addCommand(new ExitCommand("0","Exit"));
        menu.addCommand(new RunExampleCommand("1",example1.toString(),c1));
        menu.addCommand(new RunExampleCommand("2",example2.toString(),c2));
        menu.addCommand(new RunExampleCommand("3",example3.toString(),c3));
        menu.addCommand(new RunExampleCommand("4",example4.toString(),c4));
        menu.addCommand(new RunExampleCommand("5",example5.toString(),c5));
        menu.showMenu();
    }
}
