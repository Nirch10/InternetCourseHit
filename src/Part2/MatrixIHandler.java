package Part2;

import Part2.Ex1.CliqueFinder;
import Part2.Ex4.Marines;
import Part2.Utils.MatrixUtils;

import java.io.*;

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
                    Integer[][] primitiveMatrix = (Integer[][]) objectInputStream.readObject();
                    if (primitiveMatrix!=null){
                        MatrixUtils.printMat(primitiveMatrix);
                        CliqueFinder.printAllCliques(primitiveMatrix);
                    }
                    break;
                }
                case "Task2": {
                    Integer[][] primitiveMatrix = (Integer[][])  objectInputStream.readObject();
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null){
                        MatrixUtils.printMat(primitiveMatrix);
                        Part2.Ex2.PathFinder.printAllPathsAscending(src,dst,primitiveMatrix);
                    }
                    break;
                }
                case "Task3": {
                    Integer[][] primitiveMatrix = (Integer[][]) objectInputStream.readObject();
                    MatrixUtils.printMat(primitiveMatrix);
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null) {
                        Part2.Ex3.PathFinder.printAllShortestPaths(src, dst, primitiveMatrix);
                    }}

                break;
                case "Task4": {
                    Integer[][] primitiveMatrix = (Integer[][])  objectInputStream.readObject();
                    if (primitiveMatrix != null) {
                        Marines.printAllSubMarines(primitiveMatrix);
                        primitiveMatrix = MatrixUtils.restoreToBinaryMatrix((primitiveMatrix));
                        MatrixUtils.printMat(primitiveMatrix);
                    }
                    break;
                }



            }
        }
    }

}