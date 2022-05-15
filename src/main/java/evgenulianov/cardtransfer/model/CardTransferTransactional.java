package evgenulianov.cardtransfer.model;

import evgenulianov.cardtransfer.requests.RequestCardToCardTransfer;
import lombok.Data;

import java.util.Date;

@Data
public class CardTransferTransactional {

    final private Date date;
    private String cardFromNumber;
    private String cardToNumber;
    private String currency;
    private int value;

    public CardTransferTransactional(RequestCardToCardTransfer request){
        date = new Date();
        this.setCardFromNumber(request.getCardFromNumber());
        this.setCardToNumber(request.getCardToNumber());
        this.setCurrency(request.getAmount().getCurrency());
        this.setValue(request.getAmount().getValue());
    }


}
