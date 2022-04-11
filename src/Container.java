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
    String certificates = null;

    @Override
    public String toString(){
        return    "Container " + this.hashCode() + " info:\n Width: " + width + "\n Length: " + length + "\n Height: " + height + "\n Sender: " + senderInfo + "\n tare: " + tare + "\n Security: " + securityInfo + "\n Net weight: " + netWeight+ "\n Gross weight: " + grossWeight+ "\n Certificates: " + certificates;
    };
    public void uploadTo(@NotNull Ship ship){
        ship.upload(this);
    }

}
