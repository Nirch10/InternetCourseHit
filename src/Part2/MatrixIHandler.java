package Part2;

import Part2.Ex1.CliqueFinder;
import Part2.Ex2.PathFinder;
import Part2.Ex4.SubMarine;
import Part2.Utils.MatrixUtils;

import java.io.*;
import java.util.*;

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
                        PathFinder.printAllPathsAscending(src,dst,primitiveMatrix);
                    }
                    else
                    {
                        System.out.println("from else stat");
                    }
                    break;
                }
                case "Task3": {
                    Integer[][] primitiveMatrix = (Integer[][]) objectInputStream.readObject();
                    MatrixUtils.printMat(primitiveMatrix);
                    Index src = (Index) objectInputStream.readObject();
                    System.out.println(src.toString());
                    Index dst = (Index) objectInputStream.readObject();
                    System.out.println(dst.toString());
                    if (primitiveMatrix!=null && src!=null && dst!=null) {
                        System.out.println("starting taske3");
                        Part2.Ex3.PathFinder.printAllPathsAscending(src, dst, primitiveMatrix);
                        System.out.println("after taske3");
                    }}

                break;
                case "Task4": {
                    Integer[][] primitiveMatrix = (Integer[][])  objectInputStream.readObject();
                    if (primitiveMatrix != null) {
                        Integer[][] tempMatrix = primitiveMatrix.clone();

                        //List<List<Part2.Ex4.Index>> result = SubMarine.searchForSubMarines(primitiveMatrix, primitiveMatrix.length, primitiveMatrix[0].length);
                        /*
                         *   printing the matrix
                         */
//                        MatrixUtils.printMat(MatrixUtils.restoreToBinaryMatrix(primitiveMatrix));

                        /*
                        //can print all indexes represnt the subs.
                        for (List<Part2.Ex4.Index> li : result) {
                            for (Part2.Ex4.Index item : li) {
                                System.out.print(item.toString() + ",");
                            }
                            System.out.println();
                        }*/
//                        System.out.println("there are " + result.size() + " submarines ");

                    }
                    break;
                }

            }
        }
    }

}