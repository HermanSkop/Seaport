public class HeavyContainer extends Container implements Heavy{
    HeavyContainer(){}
    HeavyContainer(Sender senderInfo, String tare, String securityInfo, double netWeight, double grossWeight, String certificates){
        this.sender = senderInfo;
    }

    public HeavyContainer(Sender s) {
        super(s);
    }

    @Override
    public double wholeWidth() {
        return width+addWidth;
    }
    @Override
    public double wholeHeight() {
        return height+addHeight;
    }
    @Override
    public double wholeLength() {
        return length +addLength;
    }
}
