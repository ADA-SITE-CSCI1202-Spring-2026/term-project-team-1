package src;

abstract class Task {
    String Name;
    public Task(String Name){
        this.Name = Name;
    }
    public abstract void execute();
}
