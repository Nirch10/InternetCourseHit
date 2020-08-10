package Part1;

public enum TaskType {
    IO(1),
    COMPUTATIONAL(2),
    UNKNOWN(3);

    private Integer priority;
    TaskType(int priorityInput) {
        priority = priorityInput;
    }

    public Integer getPriority() {return priority;}
    public void setPriority(int oPriority){priority = oPriority;}

    public static TaskType getTask(int priority){
        switch (priority){
            case 1:
                return IO;
            case 2:
                return COMPUTATIONAL;
            case 3:
                return UNKNOWN;
            default:
                return null;
        }
    }
    public String toString(){return getTask(priority).toString();}
}
