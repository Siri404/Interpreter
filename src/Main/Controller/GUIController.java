package Main.Controller;


import Main.Model.Expression.*;
import Main.Model.ProgramState;
import Main.Model.Statement.*;
import Main.Model.Utils.*;
import Main.Repository.Repository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GUIController implements Observer{
    private Controller controller;
    private IStmt example1, example2, example3, example4, example5, example6, example7, example8;

    public GUIController(){
        example1 = new CompStmt(
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

        example2= new CompStmt(
                new OpenRFile("var_f","file1.txt"),
                new CompStmt(
                        new ReadFile("var_c", new VarExp("var_f")),
                        new IfStmt(new VarExp("var_f"),new CompStmt(
                                    new ReadFile("var_c", new VarExp("var_f")),
                                    new PrintStmt(new VarExp("var_c"))),
                                new PrintStmt(new ConstExp(0)))));

        example3 = new CompStmt(
                new AssignStmt("v",
                        new ConstExp(6)),
                new CompStmt(
                        new WhileStmt(
                                new ArithExp('-',
                                        new VarExp("v"),
                                        new ConstExp(4)),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",
                                                new ArithExp('-',
                                                        new VarExp("v"),
                                                        new ConstExp(1)))
                                )),
                        new PrintStmt(new VarExp("v"))));

        example4 = new CompStmt(
                new AssignStmt("a",
                        new ArithExp('-',
                                new ConstExp(2),
                                new BooleanExp(
                                        new ConstExp(2),
                                        new ConstExp(3),
                                        "<"
                                ))),
                new CompStmt(
                        new IfStmt(
                                new BooleanExp(
                                        new VarExp("a"),
                                        new ConstExp(1),
                                        ">"
                                ),
                                new AssignStmt("v",
                                        new ConstExp(2)),
                                new AssignStmt("v",
                                        new ConstExp(3))),
                        new PrintStmt(new VarExp("v"))));

        example5 = new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new newStmt("v", new ConstExp(20)),
                        new CompStmt(
                                new newStmt("a", new ConstExp(22)),
                                new CompStmt(
                                        new wH("a", new ConstExp(30)),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("a")),
                                                new CompStmt(
                                                        new PrintStmt(new rH("a")
                                                        ),
                                                        new AssignStmt("a", new ConstExp(0))
                                                )
                                        )
                                )
                        )
                )
        );

        example6 = new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new newStmt("a", new ConstExp(22)),
                        new CompStmt(
                                new ForkStmt(new CompStmt(
                                        new wH("a", new ConstExp(30)),
                                        new CompStmt(
                                                new AssignStmt("v", new ConstExp(32)),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new rH("a")))))),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new PrintStmt(new rH("a"))))));

        example7 = new CompStmt(
                new newStmt("v1", new ConstExp(20)),
                new CompStmt(
                        new newStmt("v2", new ConstExp(30)),
                        new CompStmt(
                                new newLockStmt("x"),
                                new CompStmt(
                                        new ForkStmt(new CompStmt(
                                                new ForkStmt(new CompStmt(
                                                        new Lock("x"),
                                                        new CompStmt(
                                                                new wH("v1", new ArithExp('-',new rH("v1"), new ConstExp(1))),
                                                                new Unlock("x")))),
                                                new CompStmt(
                                                        new Lock("x"),
                                                        new CompStmt(
                                                                new wH("v1", new ArithExp('+', new rH("v1"), new ConstExp(1))),
                                                                new Unlock("x"))))),
                                        new CompStmt(
                                                new newLockStmt("q"),
                                                new CompStmt(
                                                        new ForkStmt(new CompStmt(
                                                                new ForkStmt(
                                                                new CompStmt(
                                                                        new Lock("q"),
                                                                        new CompStmt(
                                                                                new wH("v2", new ArithExp('+', new rH("v2"), new ConstExp(5))),
                                                                                new Unlock("q")))),
                                                                new CompStmt(
                                                                        new AssignStmt("m",new ConstExp(100)),
                                                                        new CompStmt(
                                                                                new Lock("q"),
                                                                                new CompStmt(
                                                                                        new wH("v2", new ArithExp('+', new rH("v2"), new ConstExp(1))),
                                                                                        new Unlock("q")))))),
                                                        new CompStmt(
                                                                new AssignStmt("z", new ConstExp(200)),
                                                                new CompStmt(
                                                                        new AssignStmt("z", new ConstExp(300)),
                                                                        new CompStmt(
                                                                                new AssignStmt("z", new ConstExp(400)),
                                                                                new CompStmt(
                                                                                        new Lock("x"),
                                                                                        new CompStmt(
                                                                                                new PrintStmt(new rH("v1")),
                                                                                                new CompStmt(
                                                                                                        new Unlock("x"),
                                                                                                        new CompStmt(
                                                                                                                new Lock("q"),
                                                                                                                new CompStmt(
                                                                                                                        new PrintStmt(new rH("v2")),
                                                                                                                        new Unlock("q")))))))))))))));

        example8 = new CompStmt(
                new AssignStmt("v", new ConstExp(20)),
                new CompStmt(
                        new ForStmt(
                                new AssignStmt("v", new ConstExp(0)),
                                new BooleanExp(new VarExp("v"), new ConstExp(3), "<"),
                                new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ConstExp(1))),
                                new ForkStmt(new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ConstExp(1)))))),
                        new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(10)))));
    }

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button oneStep;

    @FXML
    private Button select;

    @FXML
    private TableView<Map.Entry<Integer, Tuple<String, BufferedReader>>> fileTable;


    @FXML
    private TableView<Map.Entry<String, Integer>> symbolTable;


    @FXML
    private TableView<Map.Entry<Integer, Integer>> heapTable;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> lockTable;

    @FXML
    private ListView<Integer> output;

    @FXML
    private ListView<ProgramState> programStates;

    @FXML
    private ListView<IStmt> exeStack;

    @FXML
    private TextField NrProgramStates;






    @FXML
    private void oneStep(ActionEvent event){
        try {
            controller.oneStepAllPrg(controller.getRepo().getProgramList());
            selectionChange();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void selectProgram(ActionEvent event){
        int ex = programListView.getSelectionModel().getSelectedIndex();
        controller = getControllerExample(ex);
        updateViews(0);
        System.out.println(controller.getRepo().getProgramList().get(0).getProgram().toString());

    }

    @FXML
    private void selectionChange(){
        int opt = programStates.getSelectionModel().getSelectedIndex();
        updateViews(opt);
    }


    private ObservableList<String> getPrograms(){
        ObservableList<String> programs = FXCollections.observableArrayList();
        programs.add(example1.toString());
        programs.add(example2.toString());
        programs.add(example3.toString());
        programs.add(example4.toString());
        programs.add(example5.toString());
        programs.add(example6.toString());
        programs.add(example7.toString());
        programs.add(example8.toString());
        return programs;
    }

    @FXML
    public void initialize(){
        programListView.setItems(getPrograms());
        configure();
    }

    @SuppressWarnings("unchecked")
    private void configure(){
        TableColumn<Map.Entry<Integer, Integer>, Integer> address = new TableColumn<>("Address");
        address.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));

        TableColumn<Map.Entry<Integer, Integer>, Integer> heapValue = new TableColumn<>("Value");
        heapValue.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        heapTable.getColumns().addAll(address, heapValue);

        TableColumn<Map.Entry<Integer, Integer>, Integer> lockAddress = new TableColumn<>("Address");
        lockAddress.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));

        TableColumn<Map.Entry<Integer, Integer>, Integer> lockValue = new TableColumn<>("Value");
        lockValue.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        lockTable.getColumns().addAll(lockAddress, lockValue);

        // config the file table
        TableColumn<Map.Entry<Integer, Tuple<String, BufferedReader>>, Integer> identifier = new TableColumn<>("Descriptor");
        identifier.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));

        TableColumn<Map.Entry<Integer, Tuple<String, BufferedReader>>, String> fileName = new TableColumn<>("File");
        fileName.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().x));

        fileTable.getColumns().addAll(identifier, fileName);

        // symbol table
        TableColumn<Map.Entry<String, Integer>, String> variableNameColumn = new TableColumn<>("Variable Name");
        variableNameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));

        TableColumn<Map.Entry<String, Integer>, Integer> varValueColumn = new TableColumn<>("Value");
        varValueColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        symbolTable.getColumns().addAll(variableNameColumn, varValueColumn);
    }

    private void updateViews(int progIndex) {

        // variables to store the updated state of the current program
        Heap heap;
        LockTable lockT;
        FileTable files;
        ExeStack<IStmt> stack;
        SymTable<String, Integer> symbols;
        Output<Integer> out;
        List<ProgramState> progStates;
        // attempt to get updated values
        try {
            heap = controller.getRepo().getProgramList().get(progIndex).getHeap();
            lockT = controller.getRepo().getProgramList().get(progIndex).getLockTable();
            files = controller.getRepo().getProgramList().get(progIndex).getFileTable();
            stack = controller.getRepo().getProgramList().get(progIndex).getExeStack();
            symbols = controller.getRepo().getProgramList().get(progIndex).getSymTable();
            out = controller.getRepo().getProgramList().get(progIndex).getOutput();
            progStates = controller.getRepo().getProgramList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        heapTable.setItems(FXCollections.observableArrayList(heap.entrySet()));
        heapTable.refresh();

        lockTable.setItems(FXCollections.observableArrayList(lockT.entrySet()));
        lockTable.refresh();

        fileTable.setItems(FXCollections.observableArrayList(files.entrySet()));
        fileTable.refresh();

        symbolTable.setItems(FXCollections.observableArrayList(symbols.entrySet()));
        symbolTable.refresh();

        exeStack.setItems(FXCollections.observableArrayList(stack));
        exeStack.refresh();

        output.setItems(FXCollections.observableArrayList(out));
        output.refresh();

        programStates.setItems(FXCollections.observableArrayList(progStates));
        programStates.refresh();
        NrProgramStates.textProperty().setValue(String.valueOf(progStates.size()));
    }

    private Controller getControllerExample(int opt){
        ExeStack<IStmt> exeStack1 = new ExeStack<IStmt>();
        Output<Integer> output1 = new Output<Integer>();
        SymTable<String, Integer> symTable1 = new SymTable<String, Integer>();
        ProgramState programState1;
        switch (opt){
            case 0:
                exeStack1.push(example1);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example1);
                break;
            case 1:
                exeStack1.push(example2);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example2);
                break;
            case 2:
                exeStack1.push(example3);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example3);
                break;
            case 3:
                exeStack1.push(example4);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example4);
                break;
            case 4:
                exeStack1.push(example5);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example5);
                break;
            case 5:
                exeStack1.push(example6);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example6);
                break;
            case 6:
                exeStack1.push(example7);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example7);
                break;
            case 7:
                exeStack1.push(example8);
                programState1 = new ProgramState(exeStack1, output1, symTable1, example8);
                break;
            default:
                programState1 = new ProgramState(exeStack1, output1, symTable1, example1);

        }

        Repository r1=new Repository("log.txt", programState1);
        return new Controller(r1, this);
    }

    public void update() {
        updateViews(programListView.getSelectionModel().getSelectedIndex());
    }


}
