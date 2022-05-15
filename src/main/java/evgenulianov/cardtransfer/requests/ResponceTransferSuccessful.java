package evgenulianov.cardtransfer.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTransferSuccessful extends  ResponceTransfer {
    private String operationId;
}
