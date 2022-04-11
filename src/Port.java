import java.util.*;

public class Port {
    List<Ship> ShipsInPort = new ArrayList<>();
    List<Ship> WarehousesInPort = new ArrayList<>();
    public void action(){
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            if (Objects.equals(input, "create ship"))createShip();
            else if(Objects.equals(input, "create cont")) createCont();
            else if(Objects.equals(input, "end"))throw new Exception();
            else System.out.println("Incorrect input: " + input);
            action();
        }
        catch (Exception e){
            System.out.println("Closing terminal..");
            System.exit(0);
        };
        System.out.println("haha");
    }
    public void createShip(){
        Ship ship = new Ship();
        ShipsInPort.add(ship);
        System.out.println("New ship is created and added to the port.");
    }
    public void createCont(){
        System.out.println("What type of container you would like to create?(types)");
        Scanner readType = new Scanner(System.in);
        String inputType = readType.nextLine();
        try {
            if (Objects.equals(inputType, "types")){getTypesOfConts(); createCont();}
            else if(Objects.equals(inputType, "Standard")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Heavy")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Liquid")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Refrigerator")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "ToxicLiquid")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "ToxicPowdery")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "stop"))throw new FinishInput(false, inputType);
            else {
                System.out.println("Incorrect input: " + inputType);
                createCont();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                String place = choosePlace();
                if(Objects.equals(place, "Warehouse")){
                    //continue
                }
                else {

                }
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
    }
    public void getTypesOfConts(){
        System.out.println(" Standard;");
        System.out.println(" Heavy;");
        System.out.println(" Liquid;");
        System.out.println(" Refrigerator;");
        System.out.println(" ToxicLiquid;");
        System.out.println(" ToxicPowdery;");
    }
    public String choosePlace(){
        System.out.println("Where would you like to store this container?(Warehouse/Ship)");
        Scanner readPlace = new Scanner(System.in);
        String inputPlace = readPlace.nextLine();
        try {
            if (Objects.equals(inputPlace, "Warehouse"))throw new FinishInput(true, inputPlace);
            else if(Objects.equals(inputPlace, "Ship"))throw new FinishInput(true, inputPlace);
            else if(Objects.equals(inputPlace, "stop"))throw new FinishInput(false, inputPlace);
            else System.out.println("Incorrect input: " + inputPlace);
            choosePlace();
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getInput();
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
        return "";
    }
    public Warehouse choseWarehouse(){
        System.out.println("Choose warehouse you want to use to store a container(houses): ");
        Scanner readWarehouse = new Scanner(System.in);
        String inputWarehouse = readWarehouse.nextLine();
        try {
            //finish function first
            if (Objects.equals(inputWarehouse, "houses"));
            else if(Objects.equals(inputWarehouse, "Ship"))throw new FinishInput(true, inputWarehouse);
            else if(Objects.equals(inputWarehouse, "stop"))throw new FinishInput(false, inputWarehouse);
            else System.out.println("Incorrect input: " + inputWarehouse);
            choosePlace();
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
    }
}
