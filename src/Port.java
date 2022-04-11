import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Port {
    List<Ship> ShipsInPort = new ArrayList<>();
    public void action(){
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            if (Objects.equals(input, "create ship"))createShip();
            else if(Objects.equals(input, "add cont"))addCont();
            else if(Objects.equals(input, "end"))throw new Exception();
            else System.out.println("Incorrect input: " + input);
            action();
        }
        catch (Exception e){
            System.out.println("Closing terminal..");
        };

    }
    public void createShip(){
        Ship ship = new Ship();
        ShipsInPort.add(ship);
        System.out.println("New ship is created and added to the port.");
    }
    public void addCont(){
        System.out.println("Container is successfully added");
    }
}
