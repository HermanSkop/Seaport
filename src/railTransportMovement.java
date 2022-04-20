import java.time.LocalDate;

public class railTransportMovement extends Thread{
    LocalDate arrivalDate = null;
    static int nextArrival = 30000;
    Port port = null;

    railTransportMovement(railwayTransport RailwayTransport, Port port){
        arrivalDate = RailwayTransport.arrivalDate;
        this.port = port;
        start();
    }
    public void run() {
        try {
            System.out.println("Railway transport is on its way");
            System.out.println("Next transport is expected to arrive in " + nextArrival/TimePassage.dayStep + " days");
            sleep(nextArrival);
            port.railTrans = new railwayTransport(port);
            System.out.println("New railway transport has arrived!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
