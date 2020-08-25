package Part2.Ex4;

public class Index  {
    public int col;
    public int row;
    public int val;
    public boolean visited;

    Index(int row, int col){
        this.col = col;
        this.row = row;
        val = (this.row + this.col ) % 2;
        visited = false;
    }

    @Override
    public String toString(){

        return "(" + row + "," + col+ ") ";
    }
}
