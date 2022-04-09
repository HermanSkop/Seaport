public class Main {
    public static void main(String[] args) {
        Ship s = new Ship();
        Container cont = new Container() {
            @Override
            public String toString() {
                return "Sender: " + senderInfo + "\n tare: " + tare + "\n Security: " + securityInfo + "\n Net weight: " + netWeight+ "\n Gross weight: " + grossWeight+ "\n Certificates: " + certificates;
            }
        };

        System.out.println(cont.toString());

        HeavyContainer cont1 = new HeavyContainer();

    }
}
