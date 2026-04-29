package src;

public class FriesTask extends Task{
    public FriesTask(){
        super("Fries Order");
    }
    @Override
    public void execute(SystemLogManager logManager){
        logManager.logInfo("Cooking Fries Order");
        logManager.logSuccess("Fries Order Completed");
    }
}
