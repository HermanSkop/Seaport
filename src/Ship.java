import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Ship {
    int maxToxicAndExplosiveConts;
    int maxHeavyConts;
    int maxElectroConts;
    int maxAllConts;
    double maxWeight;
    static int lastId = -1;

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
    public void upload(@NotNull Container cont){
        System.out.println("Container " + cont.hashCode() + " is loaded");
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

}
