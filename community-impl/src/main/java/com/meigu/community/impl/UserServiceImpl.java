package com.meigu.community.impl;

import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import com.meigu.community.db.pojo.User;
import com.meigu.community.service.UserService;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class UserServiceImpl extends BaseDao<User> implements UserService {

	@Override
	public User getUser(String id) {
		return super.get(User.class, id);
	}

	@Override
	public User login(String username, String password) {
		User  user =null;
		try {
			User temp=new User();
			temp.setUsername(username);
			temp.setPassword(password);
			Criteria criteria =getCurrentSession().createCriteria(User.class);
			criteria.add(Example.create(temp));
			user=(User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**用户查询列表*/
	@Override
	public		Paginate	userlist(String keyword,Integer type,Integer status,Integer cpage,Integer pageSize){
		Paginate  page =new Paginate();
		try {
			StringBuffer  hql=new StringBuffer("from User bean where 1=1 ");
			Map<String,Object> params=new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(keyword)) {
				hql.append(" and (bean.realName like :realName or bean.mobile = :mobile )");
				params.put("realName", "%"+keyword.trim()+"%");
				params.put("mobile", keyword.trim());
			}
			if (type!=null) {
				hql.append(" and bean.type = :type ");
				params.put("type", type);
			}
			if (status!=null) {
				hql.append(" and bean.status = :status ");
				params.put("status", status);
			}
			page=this.query(hql.toString(), params, cpage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return   page;
	}

	
	
	@Override
	public Boolean saveUser(User user)  throws Exception {
		Boolean		flag=false;
		try {
			super.save(user);
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}

	@Override
	public Boolean remove(String id) throws Exception{
		Boolean  flag=false;
		try {
			User user=this.getUser(id);
			if (user!=null) {
				this.delete(user);
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return  flag;
	}
	
	public Boolean remove(User user)throws Exception{
		Boolean  flag=false;
		try {
			if (user!=null) {
				this.delete(user);
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return  flag;
	}
	
	@Override
	public Boolean updateUser(User user) throws Exception {
		Boolean		flag=false;
		try {
			if (user!=null&&StringUtil.isNotEmpty(user.getId())) {
				User temp =this.getUser(user.getId());
				if (StringUtil.isNotEmpty(user.getPassword())) {
					temp.setPassword(user.getPassword());
				}
				if (StringUtil.isNotEmpty(user.getRealName())) {
					temp.setRealName(user.getRealName());
				}
				if (user.getGender()!=null) {
					temp.setGender(user.getGender());
				}
				if (StringUtil.isNotEmpty(user.getMobile())) {
					temp.setMobile(user.getMobile());
				}
				if (StringUtil.isNotEmpty(user.getEmail())) {
					temp.setEmail(user.getEmail());
				}
				if (StringUtil.isNotEmpty(user.getIdCard())) {
					temp.setIdCard(user.getIdCard());
				}
				if (user.getStatus()!=null) {
					temp.setStatus(user.getStatus());
				}
				this.update(temp);
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	@Override
	public User save(User user) {
		try {
			super.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Paginate listUser(Integer pageSize, Integer pageTotal, Map value) {
		String hql = "from User u where u.username = ?";
		return query(hql, value, pageTotal, pageSize);
	}

	@Override
	public User checkOwnerUser(String username) throws Exception {
		User  user =null;
		try {
			User temp=new User();
			temp.setUsername(username);
			Criteria criteria =getCurrentSession().createCriteria(User.class);
			criteria.add(Example.create(temp));
			user=(User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

}
