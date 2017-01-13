package org.cats.accounting.service;

import org.cats.accounting.config.AccountCode;
import org.cats.accounting.config.JournalCode;
import org.cats.accounting.domain.Account;
import org.cats.accounting.domain.Journal;
import org.cats.accounting.domain.Posting;
import org.cats.accounting.domain.PostingItem;
import org.cats.accounting.repository.PostingRepository;
import org.cats.stock.domain.Dispatch;
import org.cats.stock.domain.Receipt;
import org.cats.stock.enums.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 1/12/17.
 */

@Service
public class PostingService {

    private PostingRepository postingRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    JournalService journalService;


    @Autowired
    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
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

        receipt.getReceiptLines().forEach(rl -> {

            /*
                    DEBIT
             */
            PostingItem debit = PostingItem.newPostingItem();

            debit.setAccountId(receivedAccount.getId());
            debit.setJournalId(receiptJournal.getId());
            debit.setDonorId(receipt.getSupplierId());

            debit.setHubId(receipt.getHubId());
            debit.setWarehouseId(receipt.getWarehouseId());


            debit.setProgramId(receipt.getProgramId());

            debit.setCommodityId(rl.getCommodityId());
            debit.setCommodityCategoryId(rl.getCommodityCategoryId());

            debit.setQuantity(rl.getAmount() * -1);

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


            credit.setProgramId(receipt.getProgramId());

            credit.setCommodityId(rl.getCommodityId());
            credit.setCommodityCategoryId(rl.getCommodityCategoryId());

            credit.setQuantity(rl.getAmount());


            postingItems.add(credit);
        });


        return post( DocumentType.RECEIPT, receipt.getId(), postingItems);
    }

    public Posting post(Dispatch dispatch) {

        return post( DocumentType.DISPATCH, dispatch.getId(), new ArrayList<>());
    }

    private Posting post(DocumentType documentType, Long documentId, List<PostingItem> postingItems) {

        if(!validatePost(postingItems)){
            throw new IllegalArgumentException("Posting items did not pass validation.");
        }

        Posting posting = new Posting();
        posting.setDocumentId(documentId);
        posting.setDocumentType(documentType);
        posting.getPostingItems().addAll(postingItems);

        postingRepository.save(posting);

        return posting;
    }

    private Boolean validatePost(List<PostingItem> postingItems)
    {
        Float sumOfQuantities = 0f;

        for (PostingItem postingItem : postingItems) {
            sumOfQuantities += postingItem.getQuantity();
        }

        if( sumOfQuantities > 0f ) return false;

        return true;
    }
}
