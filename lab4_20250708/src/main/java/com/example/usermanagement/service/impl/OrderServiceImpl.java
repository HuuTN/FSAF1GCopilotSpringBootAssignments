package lab5_20250710.src.main.java.com.example.usermanagement.service.impl;

import com.example.usermanagement.entity.Order;
import com.example.usermanagement.entity.OrderItem;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.exception.EntityNotFoundException;
import com.example.usermanagement.repository.OrderRepository;
import com.example.usermanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public void cancelOrder(Long orderId) {
        // Find the order by ID
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Update the order status to 'Cancelled'
        order.setStatus("Cancelled");
        orderRepository.save(order);

        // Update the stock of associated products
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
    }
}
