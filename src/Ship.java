import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Ship {
    int maxToxicAndExplosiveConts;
    int maxHeavyConts;
    int maxElectroConts;
    int maxAllConts;
    double maxWeight;
    static int lastId = -1;
    List<Container> storedConts = new ArrayList<>();

    int shipId;
    String name;
    Port homePort;
    String transportOrigin;
    String destination;
    Ship(){
        shipId = lastId+1;
        ++lastId;
        System.out.println("Ship by ID: " + lastId + " is created");
    }
    Ship(Boolean commonOrExtended){
        shipId = lastId+1;
        ++lastId;
        System.out.println("Ship by ID: " + lastId + " is created");
        if(commonOrExtended){
            maxToxicAndExplosiveConts = 5;
            maxElectroConts = 5;
            maxHeavyConts = 5;
            maxAllConts = 20;
            maxWeight = 400;
        }
        else {
            maxToxicAndExplosiveConts = 10;
            maxElectroConts = 10;
            maxHeavyConts = 10;
            maxAllConts = 40;
            maxWeight = 800;
        }
    }
    public void upload(@NotNull Container cont){
        System.out.println("Container " + cont.hashCode() + " is uploaded");
    }
    public void fillInfo(){
        inputMaxWeight();
        inputMaxAllConts();
        inputMaxTAEC();
        inputMaxHeavyConts();
        inputMaxElectroConts();
    }

    public void inputMaxTAEC() {
        System.out.println("Input maximum Toxic And Explosive Conts");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            int tempMax = Integer.parseInt(input);
            if(tempMax>-1)maxToxicAndExplosiveConts = tempMax;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Should be int at least 0");
            inputMaxTAEC();
        }
    }
    public void inputMaxHeavyConts() {
        System.out.println("Input maximum Heavy Conts");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            int tempMax = Integer.parseInt(input);
            if(tempMax>-1)maxHeavyConts = tempMax;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Should be int at least 0");
            inputMaxHeavyConts();
        }
    }
    public void inputMaxElectroConts() {
        System.out.println("Input maximum Electro Conts");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            int tempMax = Integer.parseInt(input);
            if(tempMax>-1)maxElectroConts = tempMax;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Should be int at least 0");
            inputMaxElectroConts();
        }
    }
    public void inputMaxAllConts() {
        System.out.println("Input maximum Conts");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            int tempMax = Integer.parseInt(input);
            if(tempMax>0)maxAllConts = tempMax;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Should be int higher than 0");
            inputMaxAllConts();
        }
    }
    public void inputMaxWeight() {
        System.out.println("Input maximum waight");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        try {
            double tempMax = Double.parseDouble(input);
            if(tempMax>0)maxWeight = tempMax;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Should be double higher than 0");
            inputMaxWeight();
        }
    }
    public void inputName() {
        try {
        System.out.println("Input Name");
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        name = input;
        }
        catch (Exception e){
            System.out.println("Should be string");
            inputName();
        }
    }
    public void inputOrigin() {
        try {
            System.out.println("Input Origin");
            Scanner read = new Scanner(System.in);
            String input = read.nextLine();
            transportOrigin = input;
        }
        catch (Exception e){
            System.out.println("Should be string");
            inputOrigin();
        }
    }
    public void inputDestinition() {
        try {
            System.out.println("Input Destinition");
            Scanner read = new Scanner(System.in);
            String input = read.nextLine();
            destination = input;
        }
        catch (Exception e){
            System.out.println("Should be string");
            inputDestinition();
        }
    }

    @Override
    public String toString(){
        return Integer.toString(hashCode());
    };
    public String toString(boolean fullInfo){
        return    "Ship " + this.hashCode() + "\n Id: " + shipId + "\n name: " + name + " info:\n maxToxicAndExplosiveConts: " + maxToxicAndExplosiveConts + "\n maxHeavyConts: " + maxHeavyConts
                + "\n maxElectroConts: " + maxElectroConts + "\n maxAllConts: " + maxAllConts + "\n maxWeight: " + maxWeight
                + "\n homePort: " + homePort+ "\n transportOrigin: " + transportOrigin+ "\n destination: " + destination;
    };

    public void addToShip(Container container){
        ContSuitability e = suitRequirements(container);
        if(e.isSuitable()) {
            storedConts.add(container);
            System.out.println("Container " + container.hashCode() + " is added to " + shipId + " ship");
        }
        else System.out.println("Container cannot be added, because " + e.getMessage());

    }
    public void unloadFromShip(Container containerToUnload){
        try {
            storedConts.remove(containerToUnload);
        }
        catch (Exception e){
            System.out.println("Cannot find such container");
        }
    }
    public ContSuitability suitRequirements(Container container){
        try{
            if((getCurrWeight()+container.grossWeight)>maxWeight) throw new ContSuitability(false, "Ship cannot fit anymore containers due to weight overload!");
            if((getCurrNumOfConts()+1)>maxAllConts)  throw new ContSuitability(false, "Ship cannot fit anymore containers due to lack of space for All containers!");
            if((getCurrHeavyConts()+1)>maxHeavyConts)  throw new ContSuitability(false, "Ship cannot fit anymore containers due to lack of space for Heavy containers!");
            if((getCurrElectroConts()+1)>maxElectroConts)  throw new ContSuitability(false, "Ship cannot fit anymore containers due lack of space for Electro containers!");
            if((getCurrDangConts()+1)>maxToxicAndExplosiveConts)  throw new ContSuitability(false, "Ship cannot fit anymore containers due lack of space for Dangerous containers!");



            else throw new ContSuitability(true, "Everything is fine");
        }
        catch (ContSuitability e){
            return e;
        }
    }

    public int getCurrDangConts(){
        int num = 0;
        for(Container i:storedConts){
            if(Objects.equals(i.getClass().getName(), ToxicLiquidContainer.class.getName())||Objects.equals(i.getClass().getName(), ToxicPowderyContainer.class.getName())||Objects.equals(i.getClass().getName(), AntiExplosiveContainer.class.getName())){
                num++;
            }

        }
        return num;
    }
    public int getCurrElectroConts(){
        int num = 0;
        for(Container i:storedConts){
            if(Objects.equals(i.getClass().getName(), ElectroContainer.class.getName())){
                num++;
            }

        }
        return num;
    }
    public int getCurrHeavyConts(){
        int num = 0;
        for(Container i:storedConts){
            if(i.getClass()==HeavyContainer.class){
                num++;
            }

        }
        return num;
    }
    public int getCurrNumOfConts(){
        int num = 0;
        for(Container i:storedConts){
            num++;
        }
        return num;
    }
    public double getCurrWeight(){
        double weight = 0;
        for(Container i:storedConts){
            weight+=i.grossWeight;
        }
        return weight;
    }

    public Container getContainer(){
        System.out.println("Choose position of container you need(conts)");
        Scanner readCont = new Scanner(System.in);
        String inputCont = readCont.nextLine();
        try {
            try {
                if (Objects.equals(inputCont, "conts")) inside();
                else if(Objects.equals(inputCont, "stop"))throw new FinishInput(false, inputCont);
                else {
                    int tempId = Integer.parseInt(inputCont);
                    Container container = storedConts.get(tempId);
                    if(container!=null)throw new FinishInput(true, container);
                    else System.out.println("Can't find container by " + tempId + " id. Try 'conts' to observe existing ones.");
                }
                return getContainer();
            }
            catch (NumberFormatException e){
                System.out.println("Input should consist int value only!");
                return getContainer();
            }
        }
        catch (FinishInput e){
            if(e.isFinishedWell()) {
                return e.getContainer();
            }
            else {
                System.out.println("Erasing changes..");
                return null;
            }
        }

    }

    public void inside(){
        System.out.println(storedConts.toString());
    }
}
