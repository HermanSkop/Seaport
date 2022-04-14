import java.time.LocalDate;

public class TimePassage extends Thread{
    static LocalDate currentDate = null;
    // dayStep is in millis
    static int dayStep = 1000;
    TimePassage(){
        currentDate = LocalDate.now();
        start();
    }
    public void run() {
        try {
            while (true){
               // System.out.println(currentDate.toString());
                sleep(dayStep);
                currentDate = currentDate.plusDays(1);
            }
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
