import java.time.LocalDate;

public class outOfTime extends Thread{
    int timeOfOut = 0;
    Warehouse warehouse;
    Container container;

    outOfTime(Container cont, Warehouse wh){
        timeOfOut = cont.containingRestriction();
        container = cont;
        warehouse = wh;
        if(timeOfOut!=0) start();
    }
    public void run() {
        try {
            while (timeOfOut>0){
                System.out.println(timeOfOut);
                sleep(TimePassage.dayStep);
                timeOfOut--;
            }
            if(warehouse.isInWarehouse(container)) warehouse.rmFromWarehouse(container);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
