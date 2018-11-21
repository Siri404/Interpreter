package Main.Model.Statement;

import Main.Exceptions.FileAlreadyOpenException;
import Main.Exceptions.VarAlreadyDefined;
import Main.Model.ProgramState;
import Main.Model.Utils.FileTable;
import Main.Model.Utils.SymTable;
import Main.Model.Utils.Tuple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt {
    private String var_file_id;
    private String fileName;

    public OpenRFile(String id, String file){
        this.var_file_id = id;
        this.fileName = file;
    }

    @Override
    public String toString(){
        return "Open(" + var_file_id + ",'" + fileName + "')";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws FileAlreadyOpenException, FileNotFoundException, VarAlreadyDefined {
        FileTable fileTable = programState.getFileTable();
        SymTable<String, Integer> symTable = programState.getSymTable();
        boolean found = false;
        for(Tuple<String, BufferedReader> t: fileTable.values()){
            if(t.x.equals(fileName)){
                found = true;
                break;
            }
        }
        if(found) throw new FileAlreadyOpenException("File is already open!\n");
        if(symTable.containsKey(var_file_id)) throw new VarAlreadyDefined("File id already exists!\n");

        FileReader in = null;
        BufferedReader br = null;
        in = new FileReader(fileName);
        br = new BufferedReader(in);

        Tuple<String, BufferedReader> t = new Tuple<>(fileName, br);
        int key = fileTable.add(t);
        symTable.put(var_file_id, key);

        return programState;
    }
}
