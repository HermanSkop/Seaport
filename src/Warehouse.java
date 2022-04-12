public class Warehouse {
    Port port;
    private int capacity;
    int id;
    static int lastId = 0;
    Warehouse(Port InPort){
        port = InPort;
        port.addWarehouse(this);
        capacity = 0;
        id = lastId++;
        System.out.println("Warehouse by ID: " + lastId + " is created");

    }
    Warehouse(Port InPort, int Capacity){
        port = InPort;
        port.addWarehouse(this);
        capacity = Capacity;
        id = lastId++;
        System.out.println("Warehouse by ID: " + lastId + " is created");
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
}
