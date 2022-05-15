package evgenulianov.cardtransfer.requests;

import evgenulianov.cardtransfer.model.CardTransferTransactional;
import evgenulianov.cardtransfer.validators.ValidCardFromValidTill;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
@NoArgsConstructor
public class RequestCardToCardTransfer {

//    {
//        "cardFromNumber": "1234123412341234",
//        "cardFromValidTill": "0822",
//        "cardFromCVV": "123",
//        "cardToNumber": "2345234523452345",
//        "amount": {
//            "value": 100,
//            "currency": "RUR"
//        }
//    }


    @Data
    @Validated
    @NoArgsConstructor
    public static class Amount{
        @Min(value = 1, message = "amount.value must be more than 0")
        private int value;
        @NotBlank
        private String currency;
    }

    @NotBlank
    @Pattern(regexp = "^([0-9]{1,16})$", message = "cardFromNumber must be 16 digits")
    private String cardFromNumber;

    @NotBlank
    @ValidCardFromValidTill
    private String cardFromValidTill;

    @NotBlank
    @Pattern(regexp = "^([0-9]{1,3})$", message = "cardFromCVV must be 3 digits")
    private String cardFromCVV;

    @NotBlank
    @Pattern(regexp = "^([0-9]{1,16})$", message = "cardToNumber must be 16 digits")
    private String cardToNumber;

    @NotNull
    private Amount amount;

}
