package Tests;

import Part1.TaskType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class TaskTypeTest {
    static TaskType tester;
    @BeforeClass
    public static void testSetup() throws IOException {

    }
    @AfterClass
    public static void testCleanup() {
        // Do your cleanup here like close URL connection , releasing resources etc
    }

    //Get tests
    @Test
    public void testFunctionality(){
        tester.setPriority(1);
        tester.toString();
        System.out.println(tester.getPriority());
        tester.setPriority(2);
        tester.toString();
        System.out.println(tester.getPriority());
        tester.setPriority(4);
        tester.toString();
        System.out.println(tester.getPriority());
    }
}
