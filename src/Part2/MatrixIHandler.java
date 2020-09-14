package Part2;

import Part2.Ex1.CliqueFinder;
import Part2.Ex4.Marines;
import Part2.Utils.MatrixUtils;

import java.io.*;

public class MatrixIHandler implements IHandler {

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
                        // print the matrix
                        MatrixUtils.printMat(primitiveMatrix);
                        // run the algorithem of ex1
                        CliqueFinder.printAllCliques(primitiveMatrix);
                    }
                    break;
                }
                case "Task2": {
                    int[][] primitiveMatrix = (int[][])  objectInputStream.readObject();
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null){
                        // print the matrix
                        MatrixUtils.printMat(primitiveMatrix);
                        // run the algorithem of ex2
                        Part2.Ex2.PathFinder.printAllPathsAscending(src,dst,primitiveMatrix);
                    }
                    break;
                }
                case "Task3": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    // print the matrix
                    MatrixUtils.printMat(primitiveMatrix);
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null) {
                        // run the algorithem of ex3
                        Part2.Ex3.PathFinder.printAllShortestPaths(src, dst, primitiveMatrix);
                    }
                }
                break;
                case "Task4": {
                    int[][] primitiveMatrix = (int[][])  objectInputStream.readObject();
                    if (primitiveMatrix != null) {
                        // run the algorithem of ex4
                        Marines.printAllSubMarines(primitiveMatrix);
                        primitiveMatrix = MatrixUtils.restoreToBinaryMatrix((primitiveMatrix));
                        // print the matrix
                        MatrixUtils.printMat(primitiveMatrix);
                    }
                    break;
                }
            }
        }
    }
}