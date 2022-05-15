package evgenulianov.cardtransfer.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTransferUnsuccessful extends  ResponceTransfer {
    private String message;
    private int id;
}
