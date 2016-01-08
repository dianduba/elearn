package com.naijux.elearn.system.dao;

import java.util.List;

import com.naijux.elearn.annotation.MyBaticsDao;
import com.naijux.elearn.system.Menu;
import com.naijux.elearn.system.Permission;
import com.naijux.elearn.system.Role;
import com.naijux.elearn.system.User;


@MyBaticsDao
public interface UserDao {
	
	public User getUser(int userId);
	
	public User getUserByUsername(String username);
	
	public List<Role> getRolesOfUser(int userId);
	
	public List<Permission> getPermissionsOfUser(int userId);
	
	public List<Menu> getMenusOfUser(int userId);
	
	public List<String> getBooksOfUser(int userId);
	
	public void favoriteBook(int userId, String bookId);
	
	public int cancelFavorite(int userId, String bookId);
	
	public int useHits(String username, int hits);
	
	public void addUser(User user);
}
