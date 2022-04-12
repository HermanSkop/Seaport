import java.util.*;

public class Main {
    public static void main(String[] args) {
        Port mainPort = new Port();
        Warehouse mainWarehouse = new Warehouse(mainPort, 3);
        System.out.println("'create cont' is the only option yet:");
        mainPort.action();
    }
}
