package com.supermarket;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;

public class CheckoutTest {
    private Checkout checkoutTest = new Checkout();
	private Inventory inventory = new Inventory(LocalDateTime.of(2021, 3, 7, 0, 0, 0));
	private String testItemA = "A";
	private String testItemB = "B";
	private String testItemC = "C";
	private String testItemD = "D";
	private double expectedItemPrice = 50.99;
	private double expectedItemTotal = 31.98;

	@Before
	public void initTests() {
		Inventory.checkInventory();
	}

    @Test
	public void testCheckInventory() throws Exception {
		Item inventoryItem = checkoutTest.checkInventory(testItemA);
		assertTrue(inventoryItem != null);
	}

    @Test
	public void testCheckInventoryInvalidItem() throws Exception {
		Item inventoryItem = checkoutTest.checkInventory("testItemA");
		assertTrue(inventoryItem == null);
	}

    @Test
	public void testScan() throws Exception {
		checkoutTest.clearCart();
		checkoutTest.scan(testItemA);
		double testItemPrice = checkoutTest.getCart().get(testItemA).getPrice();
		assertTrue(testItemPrice == expectedItemPrice);
	}

    @Test
	public void testCalculatePrice() throws Exception {
		checkoutTest.clearCart();
		checkoutTest.scan(testItemA);
		checkoutTest.scan(testItemB);
		checkoutTest.scan(testItemC);
		double testTotalPrice = checkoutTest.calculateTotal();
		assertTrue(testTotalPrice == 102.97);
	}

    @Test
	public void testCalculateTotalSpecialPrice() throws Exception {
		checkoutTest.clearCart();
		checkoutTest.scan(testItemA);
		checkoutTest.scan(testItemA);
		checkoutTest.scan(testItemA);
		double testTotalPrice = checkoutTest.calculateTotal();
		assertTrue(testTotalPrice == 130);
	}

    @Test
	public void testCalculateTotalInvalidInventory() throws Exception {
		checkoutTest.clearCart();
		checkoutTest.scan(testItemC);
		checkoutTest.scan(testItemC);
		checkoutTest.scan(testItemC);
		double testTotalPrice = checkoutTest.calculateTotal();
		assertFalse(testTotalPrice == expectedItemTotal);
	}
    
    
}
