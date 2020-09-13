package Part2;

public enum eCellStatus {
    NOTEDGE(0), // 0
    EDGE(1), // 1
    VISITED(2); // 1

    private final int status;

    eCellStatus(int status) {this.status = status; }
    public int getStatus(){return status;}
}
