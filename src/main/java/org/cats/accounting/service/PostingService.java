package org.cats.accounting.service;

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
    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }


    public void debuggingHook() {
        int x = 3;
    }


    public Posting post(Receipt receipt) {

        for (ReceiptLine receiptLine : receipt.getReceiptLines()) {

        }

        return post( DocumentType.RECEIPT, receipt.getId(), new ArrayList<>());
    }

    public Posting post(Dispatch dispatch) {

        for (DispatchItem dispatchItem : dispatch.getDispatchItems()) {

        }

        return post( DocumentType.DISPATCH, dispatch.getId(), new ArrayList<>());
    }

    private Posting post(DocumentType documentType, Long documentId, List<PostingItem> postingItems) {
        return new Posting();
    }
}
