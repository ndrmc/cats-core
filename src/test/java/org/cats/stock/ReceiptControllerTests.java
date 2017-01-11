package org.cats.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cats.stock.controller.ReceiptController;
import org.cats.stock.domain.Receipt;
import org.cats.stock.service.ReceiptServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private ReceiptServiceImpl receiptService;

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
        receipt.setCommoditySourceId(2l);

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(receipt);

        this.mockMvc.perform(get("/receipts/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testCreateReceipt() throws Exception {

        final String grnNo = "8349238";

        Map receiptObj = new HashMap();
        receiptObj.put("grnNo", grnNo);


        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNo);

        when( receiptService.saveReceipt(isA(Receipt.class)) )
                .thenReturn(receipt);

        this.mockMvc.perform(post("/receipts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(receiptObj))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.grnNo").value(grnNo));

        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptService).saveReceipt(argument.capture());


        assertEquals(grnNo, argument.getValue().getGrnNo());

    }

    @Test
    public void testUpdateReceiptPatchesAnExistingReceiptDocument()  throws Exception  {

        final String originalGrnNo = "8349238";
        final String newGrnNo = "999999";

        Map receiptPatchObj = new HashMap();
        receiptPatchObj.put("grnNo", newGrnNo);


        Receipt receipt = new Receipt();
        receipt.setGrnNo(originalGrnNo);

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(receipt);

        when( receiptService.updateReceipt(isA(Receipt.class)) )
                .thenReturn(receipt);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(patch("/receipts/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content( mapper.writeValueAsString(receiptPatchObj))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.grnNo").value(newGrnNo));

        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptService).updateReceipt(argument.capture());


        assertEquals(newGrnNo, argument.getValue().getGrnNo());

    }

    @Test
    public void testDeleteReceiptDeletesAReceiptDocument() throws Exception  {

        final String grnNumber = "8349238";

        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNumber);

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(receipt);

        doNothing().when(receiptService).deleteReceipt(isA(Receipt.class));

        this.mockMvc.perform(delete("/receipts/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());


        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptService).deleteReceipt(argument.capture());


        assertEquals(grnNumber, argument.getValue().getGrnNo());

    }
}
