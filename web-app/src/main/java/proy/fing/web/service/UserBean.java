package proy.fing.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import proy.fing.web.model.User;


@Service
public class UserBean implements UserService {

	private Map<String, User> users = new HashMap<String, User>();

	@Override
	public void addUser(User u) {
		this.users.put(u.getUserName(), u);
	}

	@Override
	public User getUser(String userName) {
		return this.users.get(userName);
	}

	@Override
	public void deleteUser(String userName) {
		this.users.remove(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<User>(this.users.values());
	}

	@PostConstruct
	public void generateUsers() {
		// llenamos algunas usuarios de ejemplo

		User u1 = new User();
		u1.setUserName("chulito");
		u1.setPassword("pass1");
		
		User u2 = new User();
		u2.setUserName("maimai");
		u2.setPassword("pass2");

		this.users.put(u1.getUserName(), u1);
		this.users.put(u2.getUserName(), u2);

	}
}
