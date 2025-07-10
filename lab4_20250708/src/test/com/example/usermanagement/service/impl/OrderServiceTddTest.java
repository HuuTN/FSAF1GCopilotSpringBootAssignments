package lab5_20250710.src.test.com.example.usermanagement.service.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTddTest {

    @Test
    public void testCancelPendingOrder() {
        // Arrange
        Order mockOrder = new Order();
        mockOrder.setStatus("PENDING");
        Product mockProduct = new Product();
        mockProduct.setStock(10);
        mockOrder.setProduct(mockProduct);

        OrderService orderService = new OrderServiceImpl();

        // Act
        orderService.cancelOrder(mockOrder);

        // Assert
        assertEquals("CANCELLED", mockOrder.getStatus());
        assertEquals(10, mockProduct.getStock());
    }
}
