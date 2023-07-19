package img3.project.springproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import img3.project.springproject.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

	
}



