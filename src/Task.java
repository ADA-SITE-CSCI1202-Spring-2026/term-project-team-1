package src;

public abstract class Task {
    private String name;

    public Task(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void execute(SystemLogManager logManager);
}