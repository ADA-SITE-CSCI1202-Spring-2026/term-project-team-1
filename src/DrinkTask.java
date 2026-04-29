package src;

public class DrinkTask extends Task{
    public DrinkTask(){
        super("Drink Order");
    }
    @Override
    public void execute(SystemLogManager logManager){
        logManager.logInfo("Preparing Drink Order");
        logManager.logSuccess("Drink Order Completed");
    }
}
