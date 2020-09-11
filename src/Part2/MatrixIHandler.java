package Part2;

import Part2.Utils.MatrixUtils;
import java.io.*;
import static Part2.Utils.PrintUtils.*;

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
                        printAllCliques(primitiveMatrix);
                    }
                    break;
                }
                case "Task2": {
                    Integer[][] primitiveMatrix = (Integer[][])  objectInputStream.readObject();
                    Index src = (Index) objectInputStream.readObject();
                    Index dst = (Index) objectInputStream.readObject();
                    if (primitiveMatrix!=null && src!=null && dst!=null){
                        MatrixUtils.printMat(primitiveMatrix);
                        printAllPaths(src,dst,primitiveMatrix);
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
                        printShortestPaths(src, dst, primitiveMatrix);
                        System.out.println("after taske3");
                    }}

                break;
                case "Task4": {
                    Integer[][] primitiveMatrix = (Integer[][])  objectInputStream.readObject();
                    if (primitiveMatrix != null) {
                        System.out.println("starting task 4");
                        printSubMarinesCounter(primitiveMatrix);
                        System.out.println("after task 4");
                    }
                    break;
                }

            }
        }
    }

}