package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.InvalidFileException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.FileTable;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private String var_name;
    private IExpression exp_file_id;

    public ReadFile(String var_name, IExpression exp_file_id){
        this.var_name = var_name;
        this.exp_file_id = exp_file_id;
    }

    public String toString(){
        return "Read(" + exp_file_id.toString() +"," + var_name +")";
    }

    public ProgramState execute(ProgramState programState) throws DivisionByZeroException, InvalidFileException, IOException, VarNotDefinedException {
        FileTable fileTable = programState.getFileTable();
        SymTable<String, Integer> symTable = programState.getSymTable();
        Heap heap = programState.getHeap();

        BufferedReader bufferedReader;
        int bufferedReader_id = exp_file_id.evaluate(symTable, heap);
        if(!fileTable.containsKey(bufferedReader_id)) throw new InvalidFileException("Invalid buffered reader id!\n");
        bufferedReader = fileTable.get(bufferedReader_id).y;

        String line = bufferedReader.readLine();
        int value;
        if(line == null){
            value = 0;
        }
        else{
            value=Integer.parseInt(line);
        }
        if(symTable.containsKey(var_name)){
            symTable.replace(var_name, value);
        }
        else{
            symTable.put(var_name, value);
        }
        return programState;
    }
}
