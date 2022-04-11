import org.jetbrains.annotations.NotNull;

public class Ship {
    int maxToxicAndExplosiveConts;
    int maxHeavyConts;
    int maxElectroConts;
    int maxAllConts;
    double maxWeight;
    static int lastId = 0;

    int shipId;
    String name;
  //  Port homePort;
    String transportOrigin;
    String destination;
    Ship(){
        shipId = lastId++;
        System.out.println("Ship by ID: " + lastId + " is created");
    }
    public void upload(@NotNull Container cont){
        System.out.println("Container " + cont.hashCode() + " is loaded");
    }
}
