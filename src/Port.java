import org.jetbrains.annotations.NotNull;
import java.util.*;

public class Port {
    List<Ship> ShipsInPort = new ArrayList<>();
    List<Warehouse> WarehousesInPort = new ArrayList<>();

    Port(){
        addWarehouse(new Warehouse(this, 3));
    }


    public void action(){
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            if (Objects.equals(input, "cs"))createShip();
            else if(Objects.equals(input, "cc")) createCont();
            else if(Objects.equals(input, "cw")) createWarehouse();
            else if(Objects.equals(input, "sw")) showWarehouses();
            else if(Objects.equals(input, "wi")) whatInsideWarehouse(chooseWarehouse());
            else if(Objects.equals(input, "end"))throw new Exception();
            else if(Objects.equals(input, "help")) hint();
            else System.out.println("Incorrect input: " + input + " try 'help'");
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
            else if(Objects.equals(inputType, "AntiExplosive")) throw new FinishInput(true, inputType);
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
                Container contToAdd = stringToCont(e.getMessage());
                if(Objects.equals(place, "Warehouse")){
                    Warehouse warehouse = chooseWarehouse();
                    System.out.println(warehouse.id + " is id of the chosen warehouse");
                    warehouse.addToWarehouse(contToAdd);
                }
                else {
                    System.out.println("Not ready yet. Back to an action");
                    action();
                }
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
    }
    public void createWarehouse(){

    }
    public void whatInsideWarehouse(Warehouse warehouse){
        warehouse.inside();
    }

    public void addWarehouse(@NotNull Warehouse house){
        WarehousesInPort.add(house);
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
            return choosePlace();
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getMessage();
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
        return "";
    }
    public Warehouse chooseWarehouse(){
        System.out.println("Choose id of warehouse you want to use to store a container(houses): ");
        Scanner readWarehouse = new Scanner(System.in);
        String inputWarehouse = readWarehouse.nextLine();
        try {
            try {
                if (Objects.equals(inputWarehouse, "houses")) showWarehouses();
                else if(Objects.equals(inputWarehouse, "stop"))throw new FinishInput(false, inputWarehouse);
                else {
                    int tempId = Integer.parseInt(inputWarehouse);
                    Warehouse tempHouse = getHouseById(tempId);
                    if(tempHouse!=null)throw new FinishInput(true, tempHouse);
                    else System.out.println("Can't find warehouse by " + tempId + " id. Try 'houses' to observe existing ones.");
                }
                return chooseWarehouse();
            }
            catch (NumberFormatException e){
                System.out.println("Input should consist int value only!");
                chooseWarehouse();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getWareHouse();
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
        return null;
    }
    public void showWarehouses(){
       for(Warehouse i : WarehousesInPort){
            System.out.println(" id: " + i.id);
        }
    }
    public Warehouse getHouseById(int id){
        try {
            return WarehousesInPort.get(id);
        }
        catch (Exception e){
            return null;
        }
    }
    public Container stringToCont(String input){
        if(Objects.equals(input, "Standard")){return new StandardContainer();}
        else if(Objects.equals(input, "Heavy")){return new HeavyContainer();}
        else if(Objects.equals(input, "Liquid")){return new LiquidContainer();}
        else if(Objects.equals(input, "Refrigerator")){return new RefrigeratedContainer();}
        else if(Objects.equals(input, "ToxicLiquid")){return new ToxicLiquidContainer();}
        else if(Objects.equals(input, "ToxicPowdery")){return new ToxicPowderyContainer();}
        else if(Objects.equals(input, "AntiExplosive")){return new AntiExplosiveContainer();}
        else{
            System.out.println("No matched containers, null is returned!!!");
            return null;
        }
    }
    public void hint(){
        System.out.println("cs - create ship");
        System.out.println("cc - create container");
        System.out.println("cw - create warehouse");
        System.out.println("sw - show existing warehouses in port");
        System.out.println("wi - show what is stored in warehouse");
        System.out.println("end - finish");

    }
}
