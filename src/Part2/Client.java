package Part2;

import Part2.Utils.MatrixUtils;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
        static final int dimensionLimit = 500;
        static int[][] matToPreform;
        static Index sourceIndex;
        static Index destinationIndex;

        public static void main(String[] args) throws IOException, ClassNotFoundException {
                Socket socket = new Socket("127.0.0.1", 8010);
                System.out.println("client::Socket");
                Scanner scn = new Scanner((System.in));
                // open the socket for connection
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream toServer = new ObjectOutputStream(outputStream);
                ObjectInputStream fromServer = new ObjectInputStream(inputStream);

                // sending commands for task #1
                System.out.println("Starting Task #1");
                System.out.println("****************");
                // randomize matrix with user defined size
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                // send data to server
                toServer.writeObject("Task1");
                toServer.writeObject(matToPreform);
                System.out.println("check for result at server side");
                System.out.println("Please press enter to continue to next task");
                scn.nextLine();

                // sending commands for task #2
                System.out.println("Starting Task #2");
                System.out.println("****************");
                // randomize matrix with user defined size
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                // print the matrix
                MatrixUtils.printMat(matToPreform);
                // get user input for source and destination indexes - CAN choose only indexes with 1 as its value
                sourceIndex = MatrixUtils.getIndex("source", matToPreform);
                destinationIndex = MatrixUtils.getIndex("destination",matToPreform);
                // send data to server
                toServer.writeObject("Task2");
                toServer.writeObject(matToPreform);
                toServer.writeObject(sourceIndex);
                toServer.writeObject(destinationIndex);
                System.out.println("check for result at server side");
                System.out.println("Please press enter to continue to next task");
                scn.nextLine();

                // sending commands for task #3
                System.out.println("Starting Task #3");
                System.out.println("****************");
                // randomize matrix with user defined size
                matToPreform = MatrixUtils.InitMatrix(dimensionLimit);
                // print the matrix
                MatrixUtils.printMat(matToPreform);
                // get user input for source and destination indexes - CAN choose only indexes with 1 as its value
                sourceIndex = MatrixUtils.getIndex("source", matToPreform);
                destinationIndex = MatrixUtils.getIndex("destination", matToPreform);
                // send data to server
                toServer.writeObject("Task3");
                toServer.writeObject(matToPreform);
                toServer.writeObject(sourceIndex);
                toServer.writeObject(destinationIndex);
                System.out.println("check for result at server side");
                System.out.println("Please press enter to continue to next task");
                scn.nextLine();

                // sending commands for task #4
                System.out.println("Starting Task #4");
                System.out.println("****************");
                // randomize matrix with legitimate subs
                matToPreform = MatrixUtils.CreateMatrixForSubMarine(5,5,4);
                // send data to server
                toServer.writeObject("Task4");
                toServer.writeObject(matToPreform);
                System.out.println("check for result at server side");
                System.out.println("Please press enter to end server-client connection");
                scn.nextLine();
                toServer.writeObject("stop");
                System.out.println("client::Close all streams!!!!");
                // closing socket for the connections
                fromServer.close();
                toServer.close();
                socket.close();
                System.out.println("client::Close socket!!!!");
        }
}