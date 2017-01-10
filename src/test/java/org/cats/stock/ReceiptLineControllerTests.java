package org.cats.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cats.stock.controllers.ReceiptLineController;
import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
import org.cats.stock.services.ReceiptService;
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
@WebMvcTest(ReceiptLineController.class)
public class ReceiptLineControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testGetReceiptLinesByReceiptId() throws Exception{

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(mock(Receipt.class));

        when( receiptService.getReceiptLinesForReceipt(isA(Receipt.class)) )
                .thenReturn(Arrays.asList(new ReceiptLine(), new ReceiptLine()));

        this.mockMvc.perform(get("/receiptLines/receipt/2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray());

    }


    @Test
    public void testGetReceiptLineReturnReceiptLineIdentifiedByAnId() throws Exception{


        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(2);

        when( receiptService.getReceiptLineById(anyLong()) )
                .thenReturn(receiptLine);

        this.mockMvc.perform(get("/receiptLines/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void testCreateReceiptLine() throws Exception {

        final Integer commodityId = 34;

        Map receiptLineObj = new HashMap();
        receiptLineObj.put("commodityId", commodityId);

        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(commodityId);

        when( receiptService.getReceiptById(anyLong()) )
                .thenReturn(mock(Receipt.class));

        when( receiptService.saveReceiptLineItem(isA(ReceiptLine.class)) )
                .thenReturn(receiptLine);


        this.mockMvc.perform(post("/receiptLines/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(receiptLineObj))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.commodityId").value(commodityId));

        ArgumentCaptor<ReceiptLine> argument = ArgumentCaptor.forClass(ReceiptLine.class);
        verify(receiptService).saveReceiptLineItem(argument.capture());


        assertEquals(commodityId, argument.getValue().getCommodityId());

    }

    @Test
    public void testUpdateReceiptLinePatchesAnExistingReceiptLineDocument()  throws Exception  {

        final Integer originalCommodityId = 32;
        final Integer newCommodityId = 35;

        Map receiptLinePatchObj = new HashMap();
        receiptLinePatchObj.put("commodityId", newCommodityId);


        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(originalCommodityId);

        when( receiptService.getReceiptLineById(anyLong()) )
                .thenReturn(receiptLine);

        when( receiptService.saveReceiptLineItem(isA(ReceiptLine.class)) )
                .thenReturn(receiptLine);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(patch("/receiptLines/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content( mapper.writeValueAsString(receiptLinePatchObj))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.commodityId").value(newCommodityId));

        ArgumentCaptor<ReceiptLine> argument = ArgumentCaptor.forClass(ReceiptLine.class);
        verify(receiptService).saveReceiptLineItem(argument.capture());


        assertEquals(newCommodityId, argument.getValue().getCommodityId());

    }


    @Test
    public void testDeleteReceiptLineDeletesAReceiptLineDocument()  throws Exception  {

        final Integer commodityId = 990;


        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(commodityId);

        when( receiptService.getReceiptLineById(anyLong()) )
                .thenReturn(receiptLine);

        doNothing().when(receiptService).deleteReceiptLine(isA(ReceiptLine.class));

        this.mockMvc.perform(delete("/receiptLines/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());


        ArgumentCaptor<ReceiptLine> argument = ArgumentCaptor.forClass(ReceiptLine.class);
        verify(receiptService).deleteReceiptLine(argument.capture());


        assertEquals(commodityId, argument.getValue().getCommodityId());

    }

}
