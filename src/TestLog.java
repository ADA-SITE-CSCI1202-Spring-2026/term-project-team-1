package src;

public class TestLog {
    public static void main(String[] args) {
        SystemLogManager log = new SystemLogManager();
        log.logInfo("New Order: Burger");
        log.logSuccess("Burger completed");
        log.logError("Not enough ingredients");
        System.out.println(log.getAllLogsAsText());
    }
}
