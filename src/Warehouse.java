public class Warehouse {
    Port port;
    private int capacity;
    int id;
    static int lastId = -1;
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
    public void addToWarehouse(){
        
    }
}
