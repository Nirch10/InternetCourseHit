package Part2;

public enum CELLSTATUS {
    NOTEDGE(0), // 0
    EDGE(1), // 1
    VISITED(2); // 1

    private final int status;

    CELLSTATUS(int status) {this.status = status; }
    public int getStatus(){return status;}
}
