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
			Arguments.of(data11(), 8),
			Arguments.of(data12(), 8)
				);
	}
	
	private static TransactionForm data11() {
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
	
	private static TransactionForm data12() {
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
