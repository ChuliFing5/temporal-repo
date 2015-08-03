package proy.fing.web.service;

import java.util.List;

import proy.fing.web.model.Person;
import proy.fing.web.model.User;

public interface UserService {
	
	public void addUser(User u);
	public User getUser(String userName);
	public void deleteUser(String userName);
	public List<User> getAllUsers();

}
