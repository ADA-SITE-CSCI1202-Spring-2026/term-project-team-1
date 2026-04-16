package src;

class SimpleTask extends GettingTasks{
    public SimpleTask(){
        super("Simple Task");
    }
    @Override
    public void execute(){
        System.out.println("Executing Simple Task");
    }
}
