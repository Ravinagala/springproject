package img3.project.springproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import img3.project.springproject.entities.PaymentMode;

@Repository
public interface PaymentModeRepo extends JpaRepository<PaymentMode,Integer> {
	
	
	@Query("Select paymentname from PaymentMode p where p.puser.name=:name")
	List<String> getPaymentModes(@Param("name") String name);
	
	PaymentMode getByPaycode(int paycode);


}
