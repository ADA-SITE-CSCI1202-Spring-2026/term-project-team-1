package src;

class SimpleTask extends Task{
    public SimpleTask(){
        super("Simple Task");
    }
    @Override
    public void execute(SystemLogManager logManager){
        LogManager.logInfo("Executing Simple Task");
        LogManager.logSuccess("Simple Task Completed");
    }
}
