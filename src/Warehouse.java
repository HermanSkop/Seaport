import javax.swing.*;
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
    public boolean addToWarehouse(Container container){
        if (container.sender.isResponsible()) {
            if (storedConts.size() != capacity) {
                outOfTime timeOut = new outOfTime(container, this);
                storedConts.add(container);
                System.out.println(container.hashCode() + " is added to " + id + " Warehouse");
                return true;
            }
        }
        return false;
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

    public Container getContainer(){
        System.out.println("Choose position of container you need(conts)");
        Scanner readCont = new Scanner(System.in);
        String inputCont = readCont.nextLine();
        try {
            try {
                if (Objects.equals(inputCont, "conts")) inside();
                else if(Objects.equals(inputCont, "stop"))throw new FinishInput(false, inputCont);
                else {
                    int tempId = Integer.parseInt(inputCont);
                    Container container = storedConts.get(tempId);
                    if(container!=null)throw new FinishInput(true, container);
                    else System.out.println("Can't find cont by " + tempId + " id. Try 'conts' to observe existing ones.");
                }
                return getContainer();
            }
            catch (NumberFormatException e){
                System.out.println("Input should consist int value only!");
                return getContainer();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getContainer();
            }
            else {
                System.out.println("Erasing changes..");
                return null;
            }
        }

    }

}