package evgenulianov.cardtransfer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import evgenulianov.cardtransfer.requests.RequestCardToCardTransfer;
import evgenulianov.cardtransfer.requests.RequestConfirmOperation;
import evgenulianov.cardtransfer.requests.ResponceTransfer;
import evgenulianov.cardtransfer.requests.ResponceTransferSuccessful;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestControllerTransferTestTestcontainers {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> cardtransfer = new GenericContainer<>("cardtransfer:latest")
            .withExposedPorts(5500);


    @Test
    void transferAndConfirmOperation() {

        int currentPort = cardtransfer.getMappedPort(5500);

        RequestCardToCardTransfer expectedRequest = new RequestCardToCardTransfer();
        expectedRequest.setCardFromNumber("1234123412341234");
        expectedRequest.setCardToNumber("1234563456345645");
        expectedRequest.setCardFromCVV("123");
        expectedRequest.setCardFromValidTill("08/22");
        RequestCardToCardTransfer.Amount expectedAmount = new RequestCardToCardTransfer.Amount();
        expectedAmount.setCurrency("RUR");
        expectedAmount.setValue(10000);
        expectedRequest.setAmount(expectedAmount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestCardToCardTransfer> transferEntity = new HttpEntity<>(expectedRequest, headers);


        String urlTransfer = "http://localhost:" + currentPort + "/transfer";
        ResponseEntity<ResponceTransferSuccessful> responseTransfer = restTemplate.postForEntity(
                urlTransfer,
                transferEntity,
                ResponceTransferSuccessful.class);
        String operationId = responseTransfer.getBody().getOperationId();

        RequestConfirmOperation expectedConfirmRequest = new RequestConfirmOperation();
        expectedConfirmRequest.setOperationId(operationId);
        expectedConfirmRequest.setCode(RequestConfirmOperation.getDefaultCode());

        HttpEntity<RequestConfirmOperation> confirmEntity = new HttpEntity<>(expectedConfirmRequest, headers);

        String urlConfirmOperation = "http://localhost:" + currentPort + "/confirmOperation";
        ResponseEntity<ResponceTransferSuccessful> responseConfirmOperation = restTemplate.postForEntity(
                urlConfirmOperation,
                confirmEntity,
                ResponceTransferSuccessful.class);

        String operationId2 = responseConfirmOperation.getBody().getOperationId();
        Assertions.assertEquals(operationId, operationId2);

    }

}