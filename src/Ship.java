public class Ship {
    int maxToxicAndExplosiveConts;
    int maxHeavyConts;
    int maxElectroConts;
    int maxAllConts;
    double maxWeight;
    static int lastId;

    int shipId;
    String name;
  //  Port homePort;
    String transportOrigin;
    String destination;
    Ship(){
        if(lastId>-1) {
            shipId = Ship.lastId++;
        }
        else {
            lastId = 1;
            shipId = lastId;
        }
        System.out.println("Ship by ID: "+lastId+" is created");
    }
}
