import org.jetbrains.annotations.NotNull;

public abstract class Container {
    double height = 6;
    double length = 12;
    double width = 8;
    String senderInfo = null;
    String tare = null;
    String securityInfo = null;
    double netWeight = 0;
    double grossWeight = 0;
    String certificates = "no certificates";
    boolean onShip = false;
    int id = 0;
    static int lastId = -1;

    Container(){
        id = lastId+1;
        ++lastId;
    }

    @Override
    public String toString(){
        return Integer.toString(id);
    };
    public String toString(String fullInfo){
        return    "Container " + this.hashCode() + " info:\n Width: " + width + "\n Length: " + length + "\n Height: " + height + "\n Sender: " + senderInfo + "\n tare: " + tare + "\n Security: " + securityInfo + "\n Net weight: " + netWeight+ "\n Gross weight: " + grossWeight+ "\n Certificates: " + certificates;
    };
    public void uploadTo(@NotNull Ship ship){
        ship.upload(this);
    }
    public int containingRestriction(){
        return 0;
    }
}
