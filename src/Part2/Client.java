package Part2;

import Part2.Utils.MatrixUtils;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
        static final int dimensionLimit = 500;
        static final int port = 8010;
        static final String serverIp = "127.0.0.1";
        static Integer[][] matToPreform;
        static Index sourceIndex;
        static Index destinationIndex;
        static Scanner scn = new Scanner((System.in));
        private static InputStream inputStream;
        private static OutputStream outputStream;
        private static  ObjectOutputStream toServer ;
        private static ObjectInputStream fromServer;
        private static Socket socket;

        private static void moveToNextEx(){
                System.out.println("Type enter btn to continue");
                scn.nextLine();
        }
        private static void Ex1Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #1");
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                toServer.writeObject("Task1");
                toServer.writeObject(matToPreform);
                System.out.println("Head to your server for the result");

        }
        private static void Ex2Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #2");
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                sourceIndex = MatrixUtils.getIndex("source", matToPreform);
                destinationIndex = MatrixUtils.getIndex("destination",matToPreform);
                toServer.writeObject("Task2");
                toServer.writeObject(matToPreform);
                toServer.writeObject(sourceIndex);
                toServer.writeObject(destinationIndex);
                System.out.println("Head to your server for the result");

        }
        private static void Ex3Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #3");
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                sourceIndex = MatrixUtils.getIndex("source", matToPreform);
                destinationIndex = MatrixUtils.getIndex("destination", matToPreform);
                try {
                        toServer.writeObject("Task3");
                        toServer.writeObject(matToPreform);
                        toServer.writeObject(sourceIndex);
                        toServer.writeObject(destinationIndex);
                } catch (Exception ex) {
                        System.out.println("problem");
                }
                System.out.println("Head to your server for the result");

        }
        private static void Ex4Runner(ObjectOutputStream toServer) throws IOException {
                System.out.println("Starting Task #4");
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit,0);
                matToPreform = addSubMarines(matToPreform);
                toServer.writeObject("Task4");
                toServer.writeObject(matToPreform);
                System.out.println("Head to your server for the result");
        }

        private static Integer[][] addSubMarines(Integer[][] matrix){
                int startRow = 0,startCol = 0,endRow = 0,endCol = 0;
                while (startRow != -1 && startCol != -1 && endCol != -1 && endRow != -1){
                        System.out.println("Type your marine start row index (to finish type -1)");
                        startRow = scn.nextInt();
                        if(startRow == -1)break;
                        System.out.println("Type your marine start col index (to finish type -1)");
                        startCol = scn.nextInt();
                        if(startCol == -1)break;
                        System.out.println("Type your marine end row index (to finish type -1)");
                        endRow = scn.nextInt();
                        if(endRow == -1)break;
                        System.out.println("Type your marine end col index (to finish type -1)");
                        endCol = scn.nextInt();
                        if(tryAddSubmarine(startRow,startCol,endRow,endCol,matrix))
                                System.out.println("Added marine successfully");
                        else
                                System.out.println("Wrong inputs..");
                }
                return matrix;
        }
        private static boolean tryAddSubmarine(int startRow, int startCol, int endRow, int endCol, Integer[][] matrix) {
                if(startCol < 0 || startRow < 0 || endCol < 0 || endRow < 0)
                        return false;
                if(startRow >= matrix.length || endRow >= matrix.length || startCol >= matrix[0].length || endCol >= matrix[0].length)
                        return false;
                for(int i =startRow; i <= endRow; i++)
                        for (int j = startCol; j <= endCol; j++)
                                matrix[i][j] = 1;
                return true;
        }

        private static void connectToServer(String serverIp, int port) throws IOException {
                socket = new Socket(serverIp, port);
                System.out.println("client::Socket");
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                toServer = new ObjectOutputStream(outputStream);
                fromServer = new ObjectInputStream(inputStream);
        }

        public static void main(String[] args) throws IOException, ClassNotFoundException {
                scn = new Scanner((System.in));
                connectToServer(serverIp, port);
                Ex1Runner(toServer);
                moveToNextEx();
                Ex2Runner(toServer);
                moveToNextEx();
                Ex3Runner(toServer);
                moveToNextEx();
                Ex4Runner(toServer);

                System.out.println("Please press enter to end server-client connection");
                scn.nextLine();

                toServer.writeObject("stop");

                System.out.println("client::Closing all streams!!!!");
                fromServer.close();
                toServer.close();
                socket.close();
                System.out.println("client::Closing socket!!!!");
        }

}