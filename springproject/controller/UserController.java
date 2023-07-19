package img3.project.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import img3.project.springproject.repo.UserRepo;

@RestController
public class UserController {

	@Autowired
	UserRepo ur;
	
//	@GetMapping("/getuser")
//	public List<User> getUser()
//	{
//		return ur.findAll();
//	}
	
}

