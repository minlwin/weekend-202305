package com.jdc.balance.data.test.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.jdc.balance.model.form.TransactionForm;
import com.jdc.balance.model.form.TransactionItemForm;

public class TransactionServiceTestInputs {

	public static Stream<Arguments> creationTestSuccess() {
		return Stream.of(
			Arguments.of(data1(), 8),
			Arguments.of(data2(), 8)
				);
	}
	
	public static Stream<Arguments> creationTestEmptyError() {
		
		var data1 = data1();
		data1.setIssueAt(null);
		var data2 = data1();
		data2.setLedgerId(0);
		
		return Stream.of(
				Arguments.of(data1),
				Arguments.of(data2));
	}
	
	public static Stream<Arguments> creationTestNoUserError() {
		
		var data1 = data1();
		data1.setUsername(null);
		var data2 = data1();
		data2.setUsername("");
		
		return Stream.of(
				Arguments.of(data1),
				Arguments.of(data2));
	}

	public static Stream<Arguments> creationTestNoItemsError() {
		
		var data1 = data1();
		data1.setItems(new ArrayList<>());
		var data2 = data1();
		data2.setItems(null);
		
		return Stream.of(
				Arguments.of(data1),
				Arguments.of(data2));
	}
	
	public static Stream<Arguments> updateTestSuccess() {
		return Stream.of(Arguments.of(1, data1()), Arguments.of(2, data2()));
	}
	
	public static Stream<Arguments> updateTestEmptyError() {
		var data1 = data1();
		data1.setIssueAt(null);
		var data2 = data1();
		data2.setLedgerId(0);
		
		return Stream.of(Arguments.of(1, data1), Arguments.of(2, data2));
	}

	public static Stream<Arguments> updateTestNoItemError() {
		var data = data1();
		data.setItems(null);
		return Stream.of(Arguments.of(1, data));
	}

	public static Stream<Arguments> updateTestNoUserError() {
		var data = data1();
		data.setUsername(null);
		return Stream.of(Arguments.of(1, data));
	}

	private static TransactionForm data1() {
		var form = new TransactionForm();
		form.setUsername("thidar@gmail.com");
		form.setLedgerId(1);
		form.setIssueAt(LocalDate.now().minusDays(5));
		form.setRemark("Test Insert Success 1");
		form.setDeleted(false);
		
		var items = new ArrayList<TransactionItemForm>();
		form.setItems(items);
		items.add(new TransactionItemForm("Item 1", 3000, 2));
		return form;
	}
	
	private static TransactionForm data2() {
		var form = new TransactionForm();
		form.setUsername("koko@gmail.com");
		form.setLedgerId(4);
		form.setIssueAt(LocalDate.now().minusDays(4));
		form.setRemark("Test Insert Success 1");
		form.setDeleted(false);
		
		var items = new ArrayList<TransactionItemForm>();
		form.setItems(items);
		items.add(new TransactionItemForm("Item 1", 1500, 2));
		items.add(new TransactionItemForm("Item 2", 2500, 3));
		items.add(new TransactionItemForm("Item 3", 3500, 1));
		return form;
	}
}
