import java.math.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sender {
    String name;
    String surname;
    char[] PESEL = new char[11];
    String address;

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

    public LocalDate getDateOfBirth(){
        char[] pesel = PESEL;
        char[] temp = PESEL;
        int year;
        int month;
        int day;


        temp[0] = pesel[0];
        temp[1] = pesel[1];

        year = Character.getNumericValue(temp[0])*10;
        year+= Character.getNumericValue(temp[1]);
        System.out.println(year);
        if (year>22)year += 1900;
        else year+=2000;

        temp[0] = pesel[2];
        temp[1] = pesel[3];

        month = Character.getNumericValue(temp[2])*10;
        month+= Character.getNumericValue(temp[3]);
        System.out.println(month);

        temp[0] = pesel[4];
        temp[1] = pesel[5];

        day = Character.getNumericValue(temp[4])*10;
        day+= Character.getNumericValue(temp[5]);
        System.out.println(day);


        System.out.println(LocalDate.of(year,month,day));
        return LocalDate.of(year,month,day);
    }

    public void setPESEL(String PESEL) {
        if(String.valueOf(PESEL).length()!=11) {
            char[] pesel = this.PESEL;
            char[] temp;
            if((pesel[2]>1) || (pesel[2]==1&&pesel[3]>2)) System.out.println("wrong PESEL number(month)");
            else if (pesel[4]>3&&pesel[5]>1) System.out.println("wrong PESEL number(day)");
            else this.PESEL = PESEL.toCharArray();
        }
        else System.out.println("PESEL should consist of 11 numerals!");
    }
}
