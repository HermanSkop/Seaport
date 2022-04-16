import org.jetbrains.annotations.NotNull;
import java.util.*;

public class Port {
    public static final String TEXT_ERR = "\u001B[36m";
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
            else if(Objects.equals(input, "csr")) createSender();
            else if(Objects.equals(input, "cw")) createWarehouse();
            else if(Objects.equals(input, "sw")) showWarehouses();
            else if(Objects.equals(input, "ss")) showShips();
            else if(Objects.equals(input, "wi")) whatInsideWarehouse(chooseWarehouse());
            else if(Objects.equals(input, "end"))throw new Exception();
            else if(Objects.equals(input, "help")) hint();
            else System.out.println("Incorrect input: " + input + ", try 'help'");
            action();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        };
        System.out.println("haha");
    }
    public void createShip(){
        System.out.println("Would you like to input the whole information now?(y/n)");
        Ship ship = new Ship();
        ShipsInPort.add(ship);

        if(yesORNo())ship.fillInfo();

        System.out.println("New ship is added to the port.");
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
                System.out.println(contToAdd.sender);
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
        action();
    }
    public void createWarehouse(){
        try {
            try {
                System.out.println("Input capacity of the warehouse: ");
                Scanner read = new Scanner(System.in);
                String input = read.nextLine();
                if(Objects.equals(input, "stop")) throw new FinishInput(false, "Erasing changes");

            int capacity = Integer.parseInt(input);
            if(capacity>0){
                Warehouse warehouse = new Warehouse(this, capacity);
                addWarehouse(warehouse);
            }
            else throw new Exception();
            }
            catch (FinishInput e){
                System.out.println(e.getMessage());
                action();
            }
        }
        catch (Exception e){
            System.out.println("Wrong input, should be int at least 1");
            createWarehouse();
        }
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
        System.out.println("Choose id of warehouse (houses): ");
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
                return chooseWarehouse();
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
    public void showShips(){
        for(Ship i : ShipsInPort){
            System.out.println(" id: " + i.shipId);
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
        if(Objects.equals(input, "Standard")){return new StandardContainer(chooseSender());}
        else if(Objects.equals(input, "Heavy")){return new HeavyContainer(chooseSender());}
        else if(Objects.equals(input, "Liquid")){return new LiquidContainer(chooseSender());}
        else if(Objects.equals(input, "Refrigerator")){return new RefrigeratedContainer(chooseSender());}
        else if(Objects.equals(input, "ToxicLiquid")){return new ToxicLiquidContainer(chooseSender());}
        else if(Objects.equals(input, "ToxicPowdery")){return new ToxicPowderyContainer(chooseSender());}
        else if(Objects.equals(input, "AntiExplosive")){return new AntiExplosiveContainer(chooseSender());}
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
        System.out.println("ss - show existing ships in port");
        System.out.println("wi - show what is stored in warehouse");
        System.out.println("end - finish");
        System.out.println("There is also 'stop' command that works almost everywhere to remove changes and back to starting page!");
    }
    public boolean yesORNo(){
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        if(Objects.equals(input, "y") || Objects.equals(input, "yes"))return true;
        else if(Objects.equals(input, "n") || Objects.equals(input, "no"))return false;
        else {
            System.out.println("Should be yes or no");
            return yesORNo();
        }
    }
    public Sender chooseSender(){
        System.out.println("Choose the id of the sender(senders)");
        Scanner readSender = new Scanner(System.in);
        String inputSender = readSender.nextLine();
        try {
            try {
                if (Objects.equals(inputSender, "senders")) showSenders();
                else if(Objects.equals(inputSender, "stop"))throw new FinishInput(false, inputSender);
                else {
                    int tempId = Integer.parseInt(inputSender);
                    Sender tempSender = getSenderById(tempId);
                    if(tempSender!=null)throw new FinishInput(true, tempSender);
                    else System.out.println("Can't find sender by " + tempId + " id. Try 'senders' to observe existing ones.");
                }
                return chooseSender();
            }
            catch (NumberFormatException e){
                System.out.println("Input should consist int value only!");
                return chooseSender();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getSender();
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
        return null;
    }

    public Sender getSenderById(int id){
        try {
            return Sender.ListOfSenders.get(id);
        }
        catch (Exception e){
            System.out.println("Mistake is in getSenderById!!!");
            return null;
        }
    }
    public boolean isInSendersList(Sender sender){
        return sender.isInListOfSenders(sender);
    };
    public void showSenders(){
        Sender.showListOfSenders();
    }
    public void createSender(){
        try {
            System.out.println("Input name of the sender: ");
            Scanner read = new Scanner(System.in);
            String input = read.nextLine();
            if(Objects.equals(input, "stop")) throw new FinishInput(false, "Erasing changes");
            else {
                Sender sender = new Sender(input);
                sender.addSender();
            }
        }
        catch (FinishInput e){
            System.out.println(e.getMessage());
            action();
        }
    }
}
