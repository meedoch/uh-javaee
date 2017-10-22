package tn.undefined.universalhaven.buisness;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;



import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.enumerations.UserRole;

@Local
public interface UserServiceLocal {

	boolean banUser(long id);

	public boolean addUser(User user);

	public int getAgeAverage(); // for statistic    

	public Collection<User> getAvailableVolenteers(); 

	public Map<String, Long> getGenderStats(); //  

	public Map<String,Integer>  getUserCountPerRole(); 

	public List<User> getUserPerRole(UserRole role);

	public int importUserList(String fileLocation);

	public List<User> searchForUser(String name);

	public boolean updateUser(User user);

	
	public User findUser();
	
	public int changePassword(String old ,String neew , String username);

}
