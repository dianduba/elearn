package cn.dianduba.elearn.system.dao;

import java.util.List;

import cn.dianduba.elearn.system.Permission;
import cn.dianduba.elearn.system.Role;
import cn.dianduba.elearn.system.User;

import cn.dianduba.elearn.annotation.MyBaticsDao;
import cn.dianduba.elearn.system.Menu;


@MyBaticsDao
public interface UserDao {
	
	public User getUser(int userId);
	
	public User getUserByUsername(String username);
	
	public List<Role> getRolesOfUser(int userId);
	
	public List<Permission> getPermissionsOfUser(int userId);
	
	public List<Menu> getMenusOfUser(int userId);
	
	public List<Integer> getBooksOfUser(int userId);
	
	public void favoriteBook(int userId, int bookId);
	
	public int cancelFavorite(int userId, int bookId);
	
	public int useHits(String username, int hits);
	
	public int recordClick(int userId, int bookId, int pageId, int regionId);
	
	public void addUser(User user);
}
