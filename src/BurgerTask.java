package src;

public class BurgerTask extends Task{
    public BurgerTask(){
        super("Burger Order");
    }
    @Override
    public void execute(SystemLogManager logManager){
        logManager.logInfo("Cooking Burger Order");
        logManager.logSuccess("Burger Order Completed");
    }
}
