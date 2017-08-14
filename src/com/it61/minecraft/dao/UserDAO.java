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

public class UserDAO {
	
	/**
	 * 根据用户id删除用户
	 * @param user
	 * @throws Exception
	 */
	public void delete(int userId) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=ConnectionFactory.getConnection();
			
			String sql = "delete from users where id=?";
			Object[] args = {userId};
			
			pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			pstmt.executeUpdate();
		} finally {
			ConnectionFactory.close(null, pstmt, conn);
		}
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public void insert(User user) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=ConnectionFactory.getConnection();
			
			String sql = "insert into "
					+ "users(username,password,phonenumber,experience)"
					+ "values(?,?,?,?)";
			Object[] args = {user.getUserName(),user.getPassword(),user.getPhoneNumber(),user.getExperience()};
			
			pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			pstmt.executeUpdate();
		} finally {
			ConnectionFactory.close(null, pstmt, conn);
		}
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=ConnectionFactory.getConnection();
			
			String sql = "update users "
					+ "set username=?,password=?,nick_name=?,gender=?,age=?,birth=?,class=?,phonenumber=?,photo=?,star=?,email=?,grade=?"
					+ " where id=?"; //注意where和之前的字符之间有空格，不要因为换行忘记了
			Object[] args = {
					user.getUserName(),user.getPassword(),user.getNickName()
					,user.getGender(),user.getAge(),user.getBirth()
					,user.getBanji(),user.getPhoneNumber(),user.getPhoto()
					,user.getStar(),user.getEmail(),user.getGrade()
					,user.getId()};
			
			pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			pstmt.executeUpdate();
		} finally {
			ConnectionFactory.close(null, pstmt, conn);
		}		
	}
	
	/**
	 * 查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User query(String userName,String password){
        User user=null;  
        try {  
            Connection conn=null;  
            PreparedStatement pstmt=null;  
            ResultSet rs=null;  
            try {  
                conn=ConnectionFactory.getConnection();  
                
        		String sql = "select * from users where username=? and password=?";
        		Object[] args = {userName,password};
        		
                pstmt=conn.prepareStatement(sql);  
                for (int i = 0; i < args.length; i++) {  
                    pstmt.setObject(i+1, args[i]);  
                }  
                rs=pstmt.executeQuery();  
                if (rs.next()) {  
        			int id = rs.getInt("id");
        			String username = rs.getString("username");
        			String pwd = rs.getString("password");
        			String nickName = rs.getString("nick_name");
        			String gender = rs.getString("gender");
        			int age = rs.getInt("age");
        			String birth = rs.getString("birth");
        			int banji = rs.getInt("class");
        			String phoneNumber = rs.getString("phonenumber");
        			InputStream photo = rs.getBinaryStream("photo");
        			String star = rs.getString("star");
        			String email = rs.getString("email");
        			int grade = rs.getInt("grade");
        			int experience = rs.getInt("experience");
        			Timestamp registerTime = rs.getTimestamp("register_time");
  
        			user = new User(id,username,pwd,nickName,gender,age,banji,phoneNumber,star,email,grade,experience,birth,photo,registerTime);
                }  
            } finally {  
                ConnectionFactory.close(rs, pstmt, conn);  
            }  
        } catch (Exception e) { 
        	System.out.println(e.getMessage());
        }  
        return user;  
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAll(){
		List<User>list=new ArrayList<User>();
		try {
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=ConnectionFactory.getConnection();
				
				String sql = "select * from users";
				Object[] args = {};
				
				pstmt=conn.prepareStatement(sql);
				if(args != null){
					for (int i = 0; i < args.length; i++) {
						pstmt.setObject(i+1, args[i]);
					}
				}
				rs=pstmt.executeQuery();
				while (rs.next()) {
					
        			int id = rs.getInt("id");
        			String username = rs.getString("username");
        			String pwd = rs.getString("password");
        			String nickName = rs.getString("nick_name");
        			String gender = rs.getString("gender");
        			int age = rs.getInt("age");
        			String birth = rs.getString("birth");
        			int banji = rs.getInt("class");
        			String phoneNumber = rs.getString("phonenumber");
        			InputStream photo = rs.getBinaryStream("photo");
        			String star = rs.getString("star");
        			String email = rs.getString("email");
        			int grade = rs.getInt("grade");
        			int experience = rs.getInt("experience");
        			Timestamp registerTime = rs.getTimestamp("register_time");
  
        			User user = new User(id,username,pwd,nickName,gender,age,banji,phoneNumber,star,email,grade,experience,birth,photo,registerTime);
                
        			
					list.add(user);			
				}
			} finally {
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
