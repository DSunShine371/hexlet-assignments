package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    @Query("SELECT p FROM Product p WHERE (:min is null or p.price >= :min) AND (:max is null or p.price <= :max)")
    List<Product> findByPriceRange(@Param("min") Integer min, @Param("max") Integer max, Sort sort);
    // END
}
