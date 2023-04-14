package com.example.supersmart;

import com.example.supersmart.controller.SupersmartController;
import com.example.supersmart.dto.Transaction;
import com.example.supersmart.entity.Item;
import com.example.supersmart.entity.ItemType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class SupersmartApplicationTests {
	final List<Item> items = new ArrayList<>();
	final Transaction transaction = new Transaction(items);

	@Autowired
	private SupersmartController controller;

	@Test
	void contextLoads() {
	}

	@Test
	void whenProcessesXZ_returnTrue() {
		items.add(new Item("a", "123", 36.5, ItemType.UNIT));
		items.add(new Item("b", "124", 35.6, ItemType.UNIT));
		items.add(new Item("c", "112", 11.2, ItemType.GREEN));
		boolean expectedNew = Objects.equals(Objects.requireNonNull(controller.validate(transaction).getBody())
				.isValid(), true);
		Assert.isTrue(expectedNew, "true");
	}

	@Test
	void whenProcessesXNotZNotY_returnFalse() {
		items.add(new Item("a", "123", 36.5, ItemType.UNIT));
		items.add(new Item("b", "124", 35.6, ItemType.UNIT));
		items.add(new Item("c", "2929290", 11.2, ItemType.GREEN));
		items.add(new Item("d", "123asdf", 11.0, ItemType.WEIGHTED));
		boolean expectedNew = Objects.equals(Objects.requireNonNull(controller.validate(transaction).getBody())
				.isValid(), false);
		Assert.isTrue(expectedNew, "true");
	}

	@Test
	void whenProcessesXYZ_returnTrue() {
		items.add(new Item("a", "123", 36.5, ItemType.UNIT));
		items.add(new Item("b", "124", 35.6, ItemType.UNIT));
		items.add(new Item("c", "33330", 11.2, ItemType.GREEN));
		items.add(new Item("d", "123asdf", 1.7, ItemType.WEIGHTED));
		boolean expectedNew = Objects.equals(Objects.requireNonNull(controller.validate(transaction).getBody())
				.isValid(), true);
		Assert.isTrue(expectedNew, "true");
	}

	@Test
	void whenNotProcessesXYZ_returnFalse() {
		items.add(new Item("a", "123", 3.5, ItemType.UNIT));
		items.add(new Item("b", "124", 3.6, ItemType.UNIT));
		items.add(new Item("c", "29330", 11.2, ItemType.GREEN));
		items.add(new Item("d", "123asdf", 10.7, ItemType.WEIGHTED));
		boolean expectedNew = Objects.equals(Objects.requireNonNull(controller.validate(transaction).getBody())
				.isValid(), false);
		Assert.isTrue(expectedNew, "true");
	}

	@Test
	void whenEmptyItemList_returnTrue() {
		boolean expectedNew = Objects.equals(Objects.requireNonNull(controller.validate(transaction).getBody())
				.isValid(), true);
		Assert.isTrue(expectedNew, "true");
	}

}
