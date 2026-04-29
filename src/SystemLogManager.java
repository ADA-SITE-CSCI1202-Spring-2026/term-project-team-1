package src;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SystemLogManager {
    private final List<String> logs;
    private final DateTimeFormatter timeFormatter;

    public SystemLogManager(){
        logs = new ArrayList<>();
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //24 hour format
    }
    public void logInfo(String message){
        addLog("INFO" , message);
    }
    public void logSuccess(String message){
        addLog("SUCCESS" , message);
    }
    public void logError(String message){
        addLog("ERROR" , message);
    }

    private void addLog(String type , String message){
        String time = LocalTime.now().format(timeFormatter);
        String logMessage = "[" + time + "] [" + type + "] " + message;
        logs.add(logMessage);
    }

    public List<String> getLogs(){
        return new ArrayList<>(logs);
    }

    public String getAllLogsAsText(){
        return String.join("\n", logs);
    }
}
