public abstract class Container {
    double height = 6;
    double lenght = 12;
    double width = 8;
    String senderInfo = null;
    String tare = null;
    String securityInfo = null;
    double netWeight = 0;
    double grossWeight = 0;
    String certificates = null;
    @Override
    public String toString(){
        return "Sender: " + senderInfo + "\n tare: " + tare + "\n Security: " + securityInfo + "\n Net weight: " + netWeight+ "\n Gross weight: " + grossWeight+ "\n Certificates: " + certificates;
    };
}
