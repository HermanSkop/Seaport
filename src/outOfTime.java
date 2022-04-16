import java.time.LocalDate;

public class outOfTime extends Thread{
    int timeOfOut = 0;
    Warehouse warehouse;
    Container container;
    LocalDate dateOfArrival;

    outOfTime(Container cont, Warehouse wh){
        timeOfOut = cont.containingRestriction();
        container = cont;
        warehouse = wh;
        dateOfArrival = TimePassage.getCurrentDate();
        System.out.println(dateOfArrival + " is date of arrival");
        if(timeOfOut!=0) start();
    }
    public void run() {
        try {
            while (timeOfOut>0){
               // System.out.println(timeOfOut);
                sleep(TimePassage.dayStep);
                timeOfOut--;
            }
            try {
                if (warehouse.isInWarehouse(container)) {
                    warehouse.rmFromWarehouse(container);
                    throw new IrresponsibleSenderWithDangerousGoods(dateOfArrival, TimePassage.getCurrentDate(), container.id);
                }
            }
            catch (IrresponsibleSenderWithDangerousGoods e){
                container.sender.addWarning(e);
                System.out.println("Container " + container.id + " is disposed due to timeout! Sender " + container.sender + " has received the warning");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
