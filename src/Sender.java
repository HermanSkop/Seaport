import java.util.ArrayList;
import java.util.List;

public class Sender {
    String name;
    int id;
    static int lastId = -1;
    List<IrresponsibleSenderWithDangerousGoods> warnings = new ArrayList<>();
    static List<Sender> ListOfSenders = new ArrayList<>();

    Sender(){
        id = lastId+1;
        ++lastId;
    }
    Sender(String name){
        this.name = name;
        id = lastId+1;
        ++lastId;
    }

    @Override
    public String toString(){
        return "Name: " + name + "; id: " + id;
    }
    public String getFullInfo(){
        return "Name: " + name + "\n Number of warnings: 0" + warnings.size();
    }
    public boolean isResponsible(){
        return warnings.size()<2;
    }
    public void addWarning(IrresponsibleSenderWithDangerousGoods warning){
        warnings.add(warning);
    }
    public static void showListOfSenders(){
        for(Sender i : ListOfSenders){
            System.out.println(" "+ i);
        }
    }
    public boolean isInListOfSenders(Sender sender){
        return ListOfSenders.contains(sender);
    }
    public void addSender(){
        ListOfSenders.add(this);
        System.out.println("Sender " + this.name + " is successfully added");
    }

}
