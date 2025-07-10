package lab5_20250710.src.main.java.com.example.usermanagement.repository;

import com.example.usermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
