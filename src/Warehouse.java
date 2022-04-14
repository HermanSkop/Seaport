import java.util.*;

public class Warehouse {
    Port port;
    private int capacity;
    int id;
    static int lastId = -1;
    List<Container> storedConts = new ArrayList<>();

    Warehouse(Port InPort){
        port = InPort;
        capacity = 0;
        id = lastId+1;
        ++lastId;
        System.out.println("Warehouse by ID: " + id + " is created");

    }
    Warehouse(Port InPort, int Capacity){
        port = InPort;
        capacity = Capacity;
        id = lastId+1;
        lastId++;
        System.out.println("Warehouse by ID: " + id + " is created");
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void addToWarehouse(Container container){
        outOfTime timeOut = new outOfTime(container, this);
        storedConts.add(container);
        System.out.println(container.hashCode() + " is added to " + id + " Warehouse");
    }
    public boolean isInWarehouse(Container container){
        return storedConts.contains(container);
    }
    public void rmFromWarehouse(Container container){
        storedConts.remove(container);
        System.out.println(container.hashCode() + " is removed from Warehouse by " + this.id + " id");
    }
    public void inside(){
        System.out.println(storedConts.toString());
    }
}