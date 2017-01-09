package org.cats.stock.util;

import java.util.ArrayList;
import java.util.List;

import org.cats.stock.domain.Dispatch;
import org.cats.stock.domain.DispatchItem;

public class DispatchTestUtil {

	public static Dispatch createDispatch() {
		Dispatch testDispatch= new Dispatch();
		testDispatch.setGin("123");
		testDispatch.setOperationId(1);
		testDispatch.setRequisitionNo("12345");
		return testDispatch;
	}
	
	public static List<Dispatch> createDispatchList(int howMany) {
	
		
		List<Dispatch> dispatches= new ArrayList<>();
		for( int i=0;i<howMany; i++){
			Dispatch dispatch= new Dispatch();
			dispatch.setGin("gin"+i);
			dispatches.add(dispatch);
		}
		return dispatches;
	}
	
	public static  DispatchItem createDispatchItem() {
		DispatchItem item = new DispatchItem();
		return item;
	}

	public static  List<DispatchItem> createDispatchItemList(int size) {
		List<DispatchItem> items = new ArrayList<>();
		for(int i=0; i<size;i++){
			DispatchItem item =new DispatchItem();
			items.add(item);
		}
		return items;
	}
	

}
