package img3.project.springproject.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import img3.project.springproject.entities.Expenditure;

@Repository
public interface ExpenditureRepo extends JpaRepository<Expenditure,Integer> {
	
	//Query Derivation
	List<Expenditure> findByExptagsContaining(String exptags);
	
	List<Expenditure> findByUser_Name(String username, PageRequest pageable);
	
	
	@Query("Select sum(e.amount) from Expenditure e where month(e.date)=:month group by e.cats.catcode")
	List<String> getTotalInMonth(@Param("month") int month);
	
	
	@Query("From Expenditure e where e.user.name=:name AND e.cats.catcode=:catcode")
	List<Expenditure> getExpenditureByUserAndCategorySortedById(
			@Param("name") String name,
			@Param("catcode") String catcode, PageRequest pageable);
	
	@Query("From Expenditure e JOIN e.paymode p where e.user.name=:name AND p.paymentname=:paymentname")
	List<Expenditure>getExpenditureBypaymentname(@Param("name") String name, @Param("paymentname")String paymentname,PageRequest page);
	
	
	@Query("From  Expenditure e where e.user.name=:name AND e.date BETWEEN :min and :max")
	List<Expenditure>getExpenditureBetweenDates(@Param("name")String username ,@Param("min")Date min,@Param("max")Date max,PageRequest pageable);
		
}
