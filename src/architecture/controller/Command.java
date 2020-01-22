package architecture.controller;

import java.io.IOException;

public interface Command {
    
    public void toExecute() throws IOException;

}
