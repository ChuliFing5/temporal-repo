package proy.fing.core.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proy.fing.core.dao.db.user.UserDAO;
import proy.fing.core.dto.UserDTO;
import proy.fing.core.model.User;
import proy.fing.core.service.interfaces.UserService;
import proy.fing.core.service.mapper.UserMapper;

@Service
public class UserBean implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void createUser(UserDTO userDTO) {

		User user = this.userMapper.convert(userDTO);
		this.userDAO.save(user);
	}

}
