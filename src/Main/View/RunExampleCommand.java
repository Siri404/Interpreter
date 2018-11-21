package Main.View;

import Main.Controller.Controller;
import Main.Exceptions.*;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private Controller ctrl;
    public RunExampleCommand(String key, String description, Controller ctrl){
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute(){
        try{
            ctrl.executeAllSteps();
        }catch (EmptyStackException | DivisionByZeroException | VarNotDefinedException | FileAlreadyOpenException |
                VarAlreadyDefined | IOException | InvalidFileException | NoProgramInputException e) {
            System.out.print(e.getMessage());
        }
    }
}
