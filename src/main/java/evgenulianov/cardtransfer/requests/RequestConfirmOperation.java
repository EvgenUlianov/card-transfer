package evgenulianov.cardtransfer.requests;

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
public class RequestConfirmOperation {

    static final private String DEFAULT_CODE = "0000";

    static public String getDefaultCode() {
        return DEFAULT_CODE;
    }
    //    {
//        "operationId": "0000",
//        "code": "0000"
//    }

    @NotBlank
    private String operationId;

    @NotBlank
    private String code;

}
