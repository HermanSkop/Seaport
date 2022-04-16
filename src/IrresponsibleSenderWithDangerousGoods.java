import java.time.LocalDate;

public class IrresponsibleSenderWithDangerousGoods extends Exception{
    LocalDate dateOfArrival;
    LocalDate dateOfDisposal;
    int containerId;
    IrresponsibleSenderWithDangerousGoods(LocalDate ArrivalDate, LocalDate DisposalDate, int contId){
        dateOfArrival =ArrivalDate;
        dateOfDisposal = DisposalDate;
        containerId = contId;
    }
}
