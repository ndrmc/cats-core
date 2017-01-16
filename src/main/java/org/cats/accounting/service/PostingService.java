package org.cats.accounting.service;

import org.cats.accounting.config.AccountCode;
import org.cats.accounting.config.JournalCode;
import org.cats.accounting.domain.*;
import org.cats.accounting.repository.PostingItemRepository;
import org.cats.accounting.repository.PostingRepository;
import org.cats.stock.domain.Dispatch;
import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
import org.cats.stock.enums.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 1/12/17.
 */

@Service
public class PostingService {

    private PostingRepository postingRepository;

    private PostingItemRepository postingItemRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    JournalService journalService;


    @Autowired
    public PostingService(PostingRepository postingRepository, PostingItemRepository postingItemRepository) {
        this.postingRepository = postingRepository;
        this.postingItemRepository = postingItemRepository;
    }


    public void debuggingHook() {
        int x = 3;
    }

    public boolean postingExistsForDocument( DocumentType documentType, Long id) {
        return null != postingRepository.findByDocumentTypeAndDocumentId(documentType, id);
    }

    /**
     * Constructs and returns posting entries from a Receipt docuement
     *
     * @param receipt
     * @return
     */
    public Posting post(Receipt receipt) {

        Account stockAccount = accountService.getAccount(AccountCode.STOCK);
        Account receivedAccount = accountService.getAccount(AccountCode.RECEIVED);

        Journal receiptJournal = journalService.getJournalByCode(JournalCode.GOODS_RECEIVED);

        List<PostingItem> postingItems = new ArrayList<>();

        for (ReceiptLine rl : receipt.getReceiptLines()) {
            /*
                    DEBIT
            */
            PostingItem debit = PostingItem.newPostingItem();

            debit.setAccountId(receivedAccount.getId());
            debit.setJournalId(receiptJournal.getId());

            debit.setDonorId(receipt.getSupplierId());

            debit.setHubId(receipt.getHubId());
            debit.setWarehouseId(receipt.getWarehouseId());

            debit.setProjectId(rl.getProjectId());
            debit.setBatchId(rl.getBatchId());


            debit.setProgramId(receipt.getProgramId());

            debit.setCommodityId(rl.getCommodityId());
            debit.setCommodityCategoryId(rl.getCommodityCategoryId());

            debit.setQuantity(rl.getAmount().multiply(new BigDecimal(-1)));

            postingItems.add(debit);

            /*
                    CREDIT
             */
            PostingItem credit = PostingItem.newPostingItem();

            credit.setAccountId(stockAccount.getId());
            credit.setJournalId(receiptJournal.getId());
            credit.setDonorId(receipt.getSupplierId());

            credit.setHubId(receipt.getHubId());
            credit.setWarehouseId(receipt.getWarehouseId());

            credit.setProjectId(rl.getProjectId());
            credit.setBatchId(rl.getBatchId());

            credit.setProgramId(receipt.getProgramId());

            credit.setCommodityId(rl.getCommodityId());
            credit.setCommodityCategoryId(rl.getCommodityCategoryId());

            credit.setQuantity(rl.getAmount());


            postingItems.add(credit);
        }



        return post( DocumentType.RECEIPT, receipt.getId(), PostingType.NORMAL, postingItems);
    }

    public Posting post(Dispatch dispatch) {

        return post( DocumentType.DISPATCH, dispatch.getId(), PostingType.NORMAL, new ArrayList<>());
    }

    @Transactional
    private Posting post(DocumentType documentType, Long documentId, PostingType postingType,  List<PostingItem> postingItems) {

        if(!validatePost(postingItems)){
            throw new IllegalArgumentException("Posting items did not pass validation.");
        }

        Posting posting = new Posting();
        posting.setDocumentId(documentId);
        posting.setDocumentType(documentType);
        posting.setPostingType(postingType);
        posting.setPostingItems(postingItems);

        postingRepository.save(posting);

        for (PostingItem postingItem : postingItems) {
            postingItem.setPosting(posting);
            postingItemRepository.save(postingItem);
        }

        return posting;
    }

    private Boolean validatePost(List<PostingItem> postingItems)
    {
        BigDecimal sumOfQuantities = new BigDecimal(0);

        for (PostingItem postingItem : postingItems) {
            sumOfQuantities = sumOfQuantities.add(postingItem.getQuantity()) ;
        }

        return sumOfQuantities.equals(new BigDecimal(0));
    }
}
