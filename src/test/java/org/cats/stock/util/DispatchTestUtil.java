package org.cats.stock.util;

import java.util.ArrayList;
import java.util.List;

import org.cats.stock.domain.Dispatch;

public class DispatchTestUtil {

	public static Dispatch createDispatch() {
		Dispatch testDispatch= new Dispatch();
		testDispatch.setGin("123");
		testDispatch.setOperationId(1);
		testDispatch.setRequisitionNo("12345");
		return testDispatch;
	}

/*	public static List<Dispatch> createDispatchList(int howMany) {
		Dispatch testDispatch1= new Dispatch();
		testDispatch1.setGin("gin1");
		testDispatch1.setOperationId(1);
		testDispatch1.setRequisitionNo("12345");
		
		Dispatch testDispatch2= new Dispatch();
		testDispatch1.setGin("gin2");
		testDispatch1.setOperationId(1);
		testDispatch1.setRequisitionNo("123456");
		
		Dispatch testDispatch3= new Dispatch();
		testDispatch1.setGin("gin3");
		testDispatch1.setOperationId(2);
		testDispatch1.setRequisitionNo("12345");
		
		List<Dispatch> dispatches= new ArrayList<>();
		dispatches.add(testDispatch1);
		dispatches.add(testDispatch2);
		dispatches.add(testDispatch3);
		
		return dispatches;
	}*/
	
	public static List<Dispatch> createDispatchList(int howMany) {
	
		
		List<Dispatch> dispatches= new ArrayList<>();
		for( int i=0;i<howMany; i++){
			Dispatch dispatch= new Dispatch();
			dispatch.setGin("gin"+i);
			dispatches.add(dispatch);
		}
		return dispatches;
	}

	public static List<Dispatch> createDispatchesToFilter() {
		Dispatch testDispatch1= new Dispatch();
		testDispatch1.setGin("gin1");
		testDispatch1.setOperationId(1);
		testDispatch1.setRequisitionNo("12345");
		
		Dispatch testDispatch2= new Dispatch();
		testDispatch1.setGin("gin2");
		testDispatch1.setOperationId(1);
		testDispatch1.setRequisitionNo("123456");
		
		Dispatch testDispatch3= new Dispatch();
		testDispatch1.setGin("gin3");
		testDispatch1.setOperationId(2);
		testDispatch1.setRequisitionNo("12345");
		
		List<Dispatch> dispatches= new ArrayList<>();
		dispatches.add(testDispatch1);
		dispatches.add(testDispatch2);
		dispatches.add(testDispatch3);
		
		return dispatches;
	}

}
