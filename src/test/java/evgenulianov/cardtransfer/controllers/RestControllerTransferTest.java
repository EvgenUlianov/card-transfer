package evgenulianov.cardtransfer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import evgenulianov.cardtransfer.requests.RequestCardToCardTransfer;
import evgenulianov.cardtransfer.service.ServiceTransfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestControllerTransfer.class)
class RestControllerTransferTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private RequestMappingHandlerAdapter handlerAdapter;

    @MockBean
    private ServiceTransfer service;

    @Test
    void transfer() {

        ArgumentCaptor<RequestCardToCardTransfer> argumentCaptor = ArgumentCaptor.forClass(RequestCardToCardTransfer.class);
        RequestCardToCardTransfer expectedRequest = new RequestCardToCardTransfer();
        expectedRequest.setCardFromNumber("1234123412341234");
        expectedRequest.setCardToNumber("1234563456345645");
        expectedRequest.setCardFromCVV("123");
        expectedRequest.setCardFromValidTill("08/22");
        RequestCardToCardTransfer.Amount expectedAmount = new RequestCardToCardTransfer.Amount();
        expectedAmount.setCurrency("RUR");
        expectedAmount.setValue(10000);
        expectedRequest.setAmount(expectedAmount);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonRequest;
        try {
            jsonRequest = ow.writeValueAsString(expectedRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        RequestBuilder request = MockMvcRequestBuilders
                .post("/transfer")
                .content(jsonRequest)
//                .content("{\"cardFromNumber\":\"12341234787641234\",\"cardToNumber\":\"1234563456345645\",\"cardFromCVV\":\"123\",\"cardFromValidTill\":\"08/22\",\"amount\":{\"currency\":\"RUR\",\"value\":100000}}")
                .contentType(MediaType.APPLICATION_JSON);

        try {
            ResultActions resultActions = this.mockMvc.perform(request);
            resultActions.andExpect(status().isOk());
            Mockito.verify(service).openTransaction(argumentCaptor.capture());

            RequestCardToCardTransfer actualRequest= argumentCaptor.getValue();

            Assertions.assertEquals(actualRequest, expectedRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void confirmOperation() {
    }
}