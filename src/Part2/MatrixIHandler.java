package Part2;

import Part2.Ex1.CliqueFinder;
import Part2.Ex2.PathFinder;
import Part2.Ex4.SubMarine;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MatrixIHandler implements IHandler {

    private Matrix matrix;


    public MatrixIHandler() {
    }


    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {
        System.out.println("server::start handle");

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);


        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop":{
                    dowork= false;
                    break;
                }
                case "Task1": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    if (primitiveMatrix!=null){
                        CliqueFinder.printAllCliques(primitiveMatrix);
                    }
                    break;
                }
                case "Task2": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null){
                        PathFinder.printAllPathsAscending(src,dst,primitiveMatrix);
                    }
                    break;
                }
                case "Task3": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null)
                    {
                        Part2.Ex3.PathFinder.printAllPathsAscending(src,dst,primitiveMatrix);
                    }
                    break;
                }
                case "Task4": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    if (primitiveMatrix != null) {
                        List<List<Part2.Ex4.Index>> result = SubMarine.searchForSubMarines(primitiveMatrix, primitiveMatrix.length, primitiveMatrix[0].length);
                        int counter = 0;
                        for (List<Part2.Ex4.Index> li : result) {
                            for (Part2.Ex4.Index item : li) {
                                System.out.print(item.toString() + ",");

                            }
                            System.out.println();
                            counter++;
                        }
                        System.out.println("there are " + counter + " submarines ");
                    }
                    break;
                }

            }
        }
    }



    private Collection<Index> getAllNeighbors(int[][] matrix) {
        //TODO :: foreach matrix index - get all neighors from matrix.getReachable(matrix[i][j]), and add to hashset..
        return null;
    }
}
