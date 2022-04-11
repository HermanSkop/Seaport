public class FinishInput extends Exception{
    private String input;
    private boolean finishedWell;
    FinishInput(boolean finishedwell, String Input){
        finishedWell = finishedwell;
        input = Input;
    }

    public String getInput() {
        return input;
    }
    public boolean isFinishedWell(){
        return finishedWell;
    }
}
