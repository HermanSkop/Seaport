public class AntiExplosiveContainer extends Container{
    String exceptedCargoes;
    double explosiveChargeMass;

    AntiExplosiveContainer(Sender s){
        super(s);
    }

    @Override
    public int containingRestriction(){
        return 5;
    }
}
