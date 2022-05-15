package evgenulianov.cardtransfer.controllers;

import evgenulianov.cardtransfer.requests.RequestCardToCardTransfer;
import evgenulianov.cardtransfer.requests.RequestConfirmOperation;
import evgenulianov.cardtransfer.requests.ResponceTransfer;
import evgenulianov.cardtransfer.requests.ResponceTransferSuccessful;
import evgenulianov.cardtransfer.service.ServiceTransfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
//@Validated
@Log4j2
public class RestControllerTransfer {

    final private ServiceTransfer serviceTransfer;

    @PostMapping("/transfer")
    public ResponseEntity<ResponceTransfer> transfer(@Valid @RequestBody RequestCardToCardTransfer request)  throws RuntimeException, BindException {
        log.info(String.format("Open: %s", request.toString()));
        int id = serviceTransfer.openTransaction(request);
        ResponceTransfer responce = new ResponceTransferSuccessful(Integer.toString(id));
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/confirmOperation")
    public ResponseEntity<ResponceTransfer> confirmOperation(@Valid @RequestBody RequestConfirmOperation request)  throws RuntimeException, BindException, NumberFormatException, NotFoundException {
        log.info(String.format("Commit: %s", request.toString()));
        serviceTransfer.commitTransaction(request);
        ResponceTransfer responce = new ResponceTransferSuccessful(request.getOperationId());
        ResponseEntity<ResponceTransfer> responseEntity = new ResponseEntity<ResponceTransfer>(responce, HttpStatus.OK);
        return responseEntity;
    }


}
