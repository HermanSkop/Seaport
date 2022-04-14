public class AntiExplosiveContainer extends Container{
    String exceptedCargoes;
    double explosiveChargeMass;
    @Override
    public int containingRestriction(){
        return 5;
    }
}
