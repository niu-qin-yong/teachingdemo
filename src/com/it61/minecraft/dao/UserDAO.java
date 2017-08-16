package com.it61.minecraft.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.it61.minecraft.bean.User;
import com.it61.minecraft.common.ConnectionFactory;
import com.it61.minecraft.common.DAOTemplate;
import com.it61.minecraft.common.OnTransformListener;

public class UserDAO implements OnTransformListener<User> {
	private DAOTemplate<User> template;
	
	//测试
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		try {
			List<User> user = dao.queryAll();
			System.out.println(user.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserDAO(){
		template = new DAOTemplate<User>();
		template.setOnTransformListener(this);
	}
	
	/**
	 * 根据用户id删除用户
	 * (注意：因为user id是其他表，比如friends，中的外键依赖的id，所以如果该user id被其他外键依赖，那么无法删除)
	 * @param user
	 * @throws Exception
	 */
	public void delete(int userId) throws Exception{
		String sql = "delete from users where id=?";
		Object[] args = {userId};
		template.update(sql, args);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public void insert(User user) throws Exception{
		String sql = "insert into "
				+ "mc_user(user_name,user_pass_word,user_phone_number,user_experience)"
				+ "values(?,?,?,?)";
		Object[] args = {user.getUserName(),user.getPassword(),user.getPhoneNumber(),user.getExperience()};
		
		template.update(sql, args);
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception{
		String sql = "update users "
				+ "set username=?,password=?,nick_name=?,gender=?,age=?,birth=?,class=?,phonenumber=?,photo=?,star=?,email=?,grade=?"
				+ " where id=?"; //注意where和之前的字符之间有空格，不要因为换行忘记了
		Object[] args = {
				user.getUserName(),user.getPassword(),user.getNickName()
				,user.getGender(),user.getAge(),user.getBirth()
				,user.getBanji(),user.getPhoneNumber(),user.getPhoto()
				,user.getStar(),user.getEmail(),user.getGrade()
				,user.getId()};
		template.update(sql, args);
	}
	
	/**
	 * 查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User query(String userName,String password){
		String sql = "select * from mc_user where user_name=? and user_pass_word=?";
		Object[] args = {userName,password};
        return template.queryOne(sql, args);  
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAll(){
		String sql = "select * from users";
		Object[] args = {};
		return template.queryAll(sql, args);
	}

	@Override
	public User onTransform(ResultSet rs) {
		User user = null;
		try{
			int id = rs.getInt("ID");
			String username = rs.getString("user_name");
			String pwd = rs.getString("user_pass_word");
			String nickName = rs.getString("user_nick_name");
			String gender = rs.getString("user_gender");
			int age = rs.getInt("user_age");
			String birth = rs.getString("user_birth_day");
			int banji = rs.getInt("user_class");
			String phoneNumber = rs.getString("user_phone_number");
			InputStream photo = rs.getBinaryStream("user_photo");
			String star = rs.getString("user_star");
			String email = rs.getString("user_email");
			int grade = rs.getInt("user_grade");
			int experience = rs.getInt("user_experience");
			Timestamp registerTime = rs.getTimestamp("user_register_time");
			
			user = new User(id,username,pwd,nickName,gender,age,banji,phoneNumber,star,email,grade,experience,birth,photo,registerTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
}
