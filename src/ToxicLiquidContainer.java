public class ToxicLiquidContainer extends HeavyContainer implements ToxicContainer{
    // Toxic units (TU) are used in the field of toxicology to quantify the interactions of toxicants in binary mixtures of chemicals.
    double TU = 0;
    ToxicLiquidContainer(Sender s){
        super(s);
    }

    @Override
    public int containingRestriction(){
        return 10;
    }
}
