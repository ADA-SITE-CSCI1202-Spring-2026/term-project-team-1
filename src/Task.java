package src;

abstract class Task {
    private String Name;
    public Task(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public abstract void execute(SystemLogManager logManager);
}
