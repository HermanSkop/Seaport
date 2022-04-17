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
            else if(Objects.equals(input, "csr")) createSender();
            else if(Objects.equals(input, "cw")) createWarehouse();
            else if(Objects.equals(input, "sw")) showWarehouses();
            else if(Objects.equals(input, "ss")) showShips();
            else if(Objects.equals(input, "wiw")) whatInsideWarehouse(chooseWarehouse());
            else if(Objects.equals(input, "wis")) whatInsideShip(chooseShip());
            else if(Objects.equals(input, "wisr")) whatInsideSenders();
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
        Ship ship = new Ship();
        ShipsInPort.add(ship);
        System.out.println("Would you like to input the whole information now?(y/n)");
        if(yesORNo())ship.fillInfo();
        System.out.println("New ship is added to the port.");
    }
    public void createCont(){
        System.out.println("What type of container you would like to create?(types)");
        Scanner readType = new Scanner(System.in);
        String inputType = readType.nextLine();
        try {
            if (Objects.equals(inputType, "types")){getTypesOfConts(); createCont();}
            else if(Objects.equals(inputType, "Standard")||Objects.equals(inputType, "std")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Heavy")||Objects.equals(inputType, "h")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "AntiExplosive")||Objects.equals(inputType, "ae")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Liquid")||Objects.equals(inputType, "l")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "Refrigerator")||Objects.equals(inputType, "r")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "ToxicLiquid")||Objects.equals(inputType, "tl")) throw new FinishInput(true, inputType);
            else if(Objects.equals(inputType, "ToxicPowdery")||Objects.equals(inputType, "tp")) throw new FinishInput(true, inputType);
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
                if(Objects.equals(place, "Warehouse")||Objects.equals(place, "w")){
                    Warehouse warehouse = chooseWarehouse();
                    System.out.println(warehouse.id + " is id of the chosen warehouse");
                    warehouse.addToWarehouse(contToAdd);
                }
                else if(Objects.equals(place, "Ship")||Objects.equals(place, "s")) {
                    Ship ship = chooseShip();
                    System.out.println(ship.shipId + " is id of the chosen ship");
                    ship.addToShip(contToAdd);
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

    public void addWarehouse(@NotNull Warehouse house){
        WarehousesInPort.add(house);
    }


    public String choosePlace(){
        System.out.println("Where would you like to store this container?(Warehouse/Ship)");
        Scanner readPlace = new Scanner(System.in);
        String inputPlace = readPlace.nextLine();
        try {
            if (Objects.equals(inputPlace, "Warehouse")||Objects.equals(inputPlace, "w"))throw new FinishInput(true, inputPlace);
            else if(Objects.equals(inputPlace, "Ship")||Objects.equals(inputPlace, "s"))throw new FinishInput(true, inputPlace);
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
    public Ship chooseShip(){
        System.out.println("Choose id of ship (ships): ");
        Scanner readShip = new Scanner(System.in);
        String inputShip = readShip.nextLine();
        try {
            try {
                if (Objects.equals(inputShip, "ships")) showShips();
                else if(Objects.equals(inputShip, "stop"))throw new FinishInput(false, inputShip);
                else {
                    int tempId = Integer.parseInt(inputShip);
                    Ship tempShip = getShipById(tempId);
                    if(tempShip!=null)throw new FinishInput(true, tempShip);
                    else System.out.println("Can't find ship by " + tempId + " id. Try 'ships' to observe existing ones.");
                }
                return chooseShip();
            }
            catch (NumberFormatException e){
                System.out.println("Input should consist int value only!");
                return chooseShip();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getShip();
            }
            else {
                System.out.println("Erasing changes..");
                action();
            }
        };
        return null;
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

    public Container stringToCont(String input){
        if(Objects.equals(input, "Standard")||Objects.equals(input, "std")){return new StandardContainer(chooseSender());}
        else if(Objects.equals(input, "Heavy")||Objects.equals(input, "h")){return new HeavyContainer(chooseSender());}
        else if(Objects.equals(input, "Liquid")||Objects.equals(input, "l")){return new LiquidContainer(chooseSender());}
        else if(Objects.equals(input, "Refrigerator")||Objects.equals(input, "r")){return new RefrigeratedContainer(chooseSender());}
        else if(Objects.equals(input, "ToxicLiquid")||Objects.equals(input, "tl")){return new ToxicLiquidContainer(chooseSender());}
        else if(Objects.equals(input, "ToxicPowdery")||Objects.equals(input, "tp")){return new ToxicPowderyContainer(chooseSender());}
        else if(Objects.equals(input, "AntiExplosive")||Objects.equals(input, "ae")){return new AntiExplosiveContainer(chooseSender());}
        else{
            System.out.println("No matched containers, null is returned!!!");
            return null;
        }
    }
    public void hint(){
        System.out.println("cs - create ship");
        System.out.println("cc - create container");
        System.out.println("cw - create warehouse");
        System.out.println("csr - create sender");
        System.out.println("sw - show existing warehouses in port");
        System.out.println("ss - show existing ships in port");
        System.out.println("wiw - show what is stored in warehouse");
        System.out.println("wis - show what is stored in ship");
        System.out.println("wisr - show all possible senders");
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

    public void getTypesOfConts(){
        System.out.println(" Standard;");
        System.out.println(" Heavy;");
        System.out.println(" AntiExplosive");
        System.out.println(" Liquid;");
        System.out.println(" Refrigerator;");
        System.out.println(" ToxicLiquid;");
        System.out.println(" ToxicPowdery;");
    }
    public Sender getSenderById(int id){
        try {
            return Sender.ListOfSenders.get(id);
        }
        catch (Exception e){
            return null;
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
    public Ship getShipById(int id){
        try {
            return ShipsInPort.get(id);
        }
        catch (Exception e){
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

    public void whatInsideWarehouse(Warehouse warehouse){
        warehouse.inside();
    }
    public void whatInsideShip(Ship ship){
        ship.inside();
    }
    public void whatInsideSenders(){
        Sender.showListOfSenders();
    }


}
