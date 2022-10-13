public class ToxicPowderyContainer extends HeavyContainer implements ToxicContainer{
    //Common Substances: Formaldehyde; Mercury; Lead; Asbestos; Hazardous/Toxic Air Pollutants; Per- and Polyfluoroalkyl Substances (PFAS); Pesticide Chemicals. Glyphosate; Polychlorinated Biphenyls (PCBs)
    String typeOfMaterial = null;
    ToxicPowderyContainer(Sender s){
        super(s);
    }

    @Override
    public int containingRestriction(){
        return 14;
    }
}
