package org.cats;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cats.stock.receipt.controllers.ReceiptController;
import org.cats.stock.receipt.domain.Receipt;
import org.cats.stock.receipt.services.ReceiptService;
import org.cats.stock.receipt.services.ReceiptServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by alexander on 1/5/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTests  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReceiptsReturnsAllReceipts() throws Exception{

        List<Receipt> receipts = Arrays.asList(new Receipt());

        when( receiptService.getAllReceipts() )
                .thenReturn(receipts );

        this.mockMvc.perform(get("/receipts")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }


    @Test
    public void testGetReceiptReturnReceiptIdentifiedByAnId() throws Exception{


        Receipt receipt = new Receipt();
        receipt.setCommoditySourceId(2);

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(receipt);

        this.mockMvc.perform(get("/receipts/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    //@Test
    public void testCreateReceipt() throws Exception{

        final String grnNo = "8349238";

        Map receiptObj = new HashMap();
        receiptObj.put("grnNo", grnNo);


        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNo);


        /*ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptService).saveReceipt(argument.capture());
        assertEquals(grnNo, argument.getValue().getGrnNo());*/

        when( receiptService.saveReceipt(isA(Receipt.class)) )
                .thenReturn(receipt);

        this.mockMvc.perform(post("/receipts")
                .content(new ObjectMapper().writeValueAsString(receiptObj))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.grnNo").value(grnNo));
    }
}
