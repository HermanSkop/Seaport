import java.time.LocalDate;

public class TimePassage extends Thread{
    LocalDate currentDate = null;
    
    TimePassage(){
        currentDate = LocalDate.now();
        start();
    }
    public void run() {
        try {
            while (true){
                System.out.println(currentDate.toString());
                sleep(5000);
            currentDate = currentDate.plusDays(1);
            }
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
