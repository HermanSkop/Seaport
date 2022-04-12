public class FinishInput extends Exception{
    private String message;
    private boolean finishedWell;
    private Warehouse wareHouse;

    FinishInput(boolean finishedwell, String Message){
        finishedWell = finishedwell;
        message = Message;
    }
    FinishInput(boolean finishedwell, Warehouse house){
        wareHouse = house;
        finishedWell = finishedwell;
    }

    public String getMessage() {
        return message;
    }
    public boolean isFinishedWell(){
        return finishedWell;
    }
    public Warehouse getWareHouse() {
        return wareHouse;
    }
}
