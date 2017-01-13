package org.cats.accounting.service;

import org.cats.accounting.config.AccountCode;
import org.cats.accounting.config.JournalCode;
import org.cats.accounting.domain.Account;
import org.cats.accounting.domain.Journal;
import org.cats.accounting.domain.Posting;
import org.cats.accounting.domain.PostingItem;
import org.cats.accounting.repository.PostingRepository;
import org.cats.stock.domain.Dispatch;
import org.cats.stock.domain.DispatchItem;
import org.cats.stock.domain.Receipt;
import org.cats.stock.domain.ReceiptLine;
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


    public Posting post(Receipt receipt) {
        /*
           # Iterate on receipt lines and construct posting items
           # Call internal 'post' method
           # Return posting
         */

        Account stockAccount = accountService.getAccount(AccountCode.STOCK);
        Account receivedAccount = accountService.getAccount(AccountCode.RECEIVED);
        Journal receiptJournal = journalService.getJournalByCode(JournalCode.GOODS_RECEIVED);

        List<PostingItem> postingItems = new ArrayList<>();

        receipt.getReceiptLines().forEach(rl -> {
            PostingItem debit = PostingItem.newPostingItem();
            PostingItem credit = PostingItem.newPostingItem();

            debit.setJournalId(receiptJournal.getId());
            debit.setAccountId(receivedAccount.getId());
            debit.setQuantity(rl.getAmount() * -1);
            debit.setCommodityId(rl.getCommodityId());
            debit.setDonorId(receipt.getSupplierId());
            debit.setHubId(receipt.getHubId());
            debit.setWarehouseId(receipt.getWarehouseId());
            //debit.setProgramId(receipt.getProgramId());

            credit.setJournalId(receiptJournal.getId());
            credit.setAccountId(stockAccount.getId());
            credit.setQuantity(rl.getAmount());
            credit.setCommodityId(rl.getCommodityId());
            credit.setDonorId(receipt.getSupplierId());
            credit.setHubId(receipt.getHubId());
            credit.setWarehouseId(receipt.getWarehouseId());
            //credit.setProgramId(receipt.getProgramId());

            postingItems.add(credit);
            postingItems.add(debit);
        });


        return post( DocumentType.RECEIPT, receipt.getId(), postingItems);
    }

    public Posting post(Dispatch dispatch) {

        return post( DocumentType.DISPATCH, dispatch.getId(), new ArrayList<>());
    }

    private Posting post(DocumentType documentType, Long documentId, List<PostingItem> postingItems) {
        Posting posting = new Posting();
        posting.setDocumentId(documentId);
        posting.setDocumentType(documentType);
        posting.getPostingItems().addAll(postingItems);

        if(validate(posting)){
            postingRepository.save(posting);
        }

        return new Posting();
    }

    private Boolean validate(Posting posting){
        return true;
    }
}
