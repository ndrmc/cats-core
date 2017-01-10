package org.cats.stock;

import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
import org.cats.stock.repository.ReceiptLineItemRepository;
import org.cats.stock.repository.ReceiptRepository;
import org.cats.stock.services.ReceiptService;
import org.cats.stock.services.ReceiptServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;


/**
 * Created by alexander on 1/5/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ReceiptServiceTests {

    @MockBean
    private ReceiptRepository receiptRepository;

    @MockBean
    private ReceiptLineItemRepository receiptLineItemRepository;

    private ReceiptService receiptService;

    @Before
    public void setUp() {
        receiptService = new ReceiptServiceImpl(receiptRepository, receiptLineItemRepository);
    }



    @Test
    public void testGetAllReceiptsReturnsAllReceiptDocumentsReturnedByTheRepository() throws Exception {
        List<Receipt> receipts = Arrays.asList(  mock(Receipt.class), mock(Receipt.class) );

        when( receiptRepository.findAll() )
                .thenReturn(receipts );


        assertEquals( 2, receiptService.getAllReceipts().size());
    }

    @Test
    public void testGetReceiptByIdReturnsAReciptDocumentByItsId() throws Exception {
        when( receiptRepository.findOne(anyLong()) )
                .thenReturn(new Receipt() );

        Long id = 2l;

        receiptService.getReceiptById(id);


        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(receiptRepository).findOne(argument.capture());




        Assert.assertEquals(id, argument.getValue());
    }

    @Test
    public void testGetReceiptLinesForReceipt() throws Exception {

        final Integer commodityId = 89;

        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(commodityId);

        when( receiptLineItemRepository.findAllByReceiptId(anyLong()))
                .thenReturn(Arrays.asList(receiptLine));

        Assert.assertEquals(commodityId, receiptService.getReceiptLinesForReceipt(new Receipt()).get(0).getCommodityId());
    }

    @Test
    public void testDeleteReceiptLineDeletesAReceiptLine() throws Exception {

        final Integer commodityId = 89;

        ReceiptLine receiptLine = new ReceiptLine();
        receiptLine.setCommodityId(commodityId);

        doNothing().when( receiptLineItemRepository).delete(isA(ReceiptLine.class));

        receiptService.deleteReceiptLine(receiptLine);

        ArgumentCaptor<ReceiptLine> argument = ArgumentCaptor.forClass(ReceiptLine.class);
        verify(receiptLineItemRepository).delete(argument.capture());

        Assert.assertEquals(commodityId, argument.getValue().getCommodityId());
    }



    @Test
    public void testSaveReceiptPersistsAReceiptObject() throws Exception {
        when( receiptRepository.save(isA(Receipt.class)) )
                .thenReturn(new Receipt() );

        final String grnNo = "8098000";

        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNo);

        receiptService.saveReceipt(receipt);



        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepository).save(argument.capture());



        Assert.assertEquals(grnNo, argument.getValue().getGrnNo());
    }


    @Test
    public void testUpdateReceiptUpdatesAReceiptObject() throws Exception {
        when( receiptRepository.save(isA(Receipt.class)) )
                .thenReturn(new Receipt() );

        when( receiptRepository.exists(anyLong()) )
                .thenReturn(true );

        final String grnNo = "8098000";

        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNo);

        receiptService.updateReceipt(receipt);

        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepository).save(argument.capture());

        Assert.assertEquals(grnNo, argument.getValue().getGrnNo());
    }

    @Test
    public void testDeleteReceiptDeletesAReceiptObject() throws Exception {

        doNothing().when(receiptRepository).delete(isA(Receipt.class));


        final String grnNo = "8098000";

        Receipt receipt = new Receipt();
        receipt.setGrnNo(grnNo);

        receiptService.deleteReceipt(receipt);

        ArgumentCaptor<Receipt> argument = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepository).delete(argument.capture());

        Assert.assertEquals(grnNo, argument.getValue().getGrnNo());
    }
}
