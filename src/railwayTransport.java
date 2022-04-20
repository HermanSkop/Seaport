import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class railwayTransport{
    static int maxConts = 3;
    LocalDate arrivalDate = null;
    List<Container> containers = new ArrayList<>();
    Port port = null;

    railwayTransport(Port port){
        arrivalDate = LocalDate.now();
        this.port = port;
    }
    public void addContainerToTransport(Container container){
        if(getCurrConts()+1==maxConts) {
            containers.add(container);
            port.railTrans = null;
            new railTransportMovement(this, port);
        }
        else containers.add(container);
        System.out.println("Container is successfully added to railway transport");
    }
    public int getCurrConts(){
        return containers.size();
    }
}
