package src;

class SimpleTask extends Task{
    public SimpleTask(){
        super("Simple Task");
    }
    @Override
    public void execute(SystemLogManager logManager){
        logManager.logInfo("Executing Simple Task");
        logManager.logSuccess("Simple Task Completed");
    }
}
