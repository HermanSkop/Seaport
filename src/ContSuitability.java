public class ContSuitability extends Exception{
    private String message;
    private boolean isSuitable;

    ContSuitability(boolean isSuitable, String message){
        this.isSuitable = isSuitable;
        this.message = message;
    }

    public boolean isSuitable() {
        return isSuitable;
    }

    public String getMessage() {
        return message;
    }
}
