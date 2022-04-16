public class FinishInput extends Exception{
    private String message;
    private boolean finishedWell;
    private Warehouse wareHouse;
    private Sender sender;

    FinishInput(boolean finishedwell, String Message){
        finishedWell = finishedwell;
        message = Message;
    }
    FinishInput(boolean finishedwell, Warehouse house){
        wareHouse = house;
        finishedWell = finishedwell;
    }
    FinishInput(boolean finishedwell, Sender sender){
        this.sender = sender;
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
    public Sender getSender() {
        return sender;
    }
}
