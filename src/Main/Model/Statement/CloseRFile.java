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
import java.io.IOException;

public class CloseRFile implements IStmt {
    private IExpression exp_file_id;

    public CloseRFile(IExpression exp_file_id){
        this.exp_file_id = exp_file_id;
    }

    public String toString(){
        return "Close file";
    }

    public ProgramState execute(ProgramState programState) throws DivisionByZeroException, VarNotDefinedException, IOException, InvalidFileException {
        FileTable fileTable = programState.getFileTable();
        SymTable<String, Integer> symTable = programState.getSymTable();
        Heap heap = programState.getHeap();

        int bufferedReader_id = exp_file_id.evaluate(symTable, heap);
        if(!fileTable.containsKey(bufferedReader_id)) throw new InvalidFileException("Invalid buffered reader id!\n");
        BufferedReader bufferedReader = fileTable.get(bufferedReader_id).y;
        bufferedReader.close();

        fileTable.remove(bufferedReader_id);
        return null;
    }
}
