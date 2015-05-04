package com.meigu.community.service;

import java.util.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.persistence.model.Paginate;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"rawtypes"})
public interface UserService {
	
	public 		User 		getUser(String id);
	/**用户登录查询*/
	public 		User		login(String username,String password);
	
	/**用户查询列表*/
	public		Paginate	userlist(String keyword,Integer type,Integer status,Integer cpage,Integer pageSize);
	/**添加用户*/
	public Boolean saveUser(User user) throws Exception ;
	/**更新用户*/
	public Boolean updateUser(User user) throws Exception ;
	/**
	 * 添加用户
	 * @param user
	 */
	public User save(User user);
	/**删除用户*/
	public Boolean remove(String id)throws Exception;
	/**删除用户*/
	public Boolean remove(User user)throws Exception;
	
	/**验证业主用户名是否存在
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User checkOwnerUser(String username) throws Exception;
	
	public Paginate listUser(Integer pageSize , Integer pageTotal , Map value);
}
