package com.it61.minecraft.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO模板
 *
 * @param <T>
 */
public  class DAOTemplate<T> {
	private OnTransformListener< T> listener;
	
	/**
	 * 设置转换器
	 * @param transfor
	 */
	public void setOnTransformListener(OnTransformListener< T> transfor){
		this.listener=transfor;
	}
	
	/**
	 * insert、delete、update的操作都可以由该方法实现
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	public void update(String sql, Object[] args) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=ConnectionFactory.getConnection();
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
	 * 只返回一个结果时的查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public T queryOne(String sql,Object[] args){  
        T obj=null;  
        try {  
            Connection conn=null;  
            PreparedStatement pstmt=null;  
            ResultSet rs=null;  
            try {  
                conn=ConnectionFactory.getConnection();  
                pstmt=conn.prepareStatement(sql);  
                for (int i = 0; i < args.length; i++) {  
                    pstmt.setObject(i+1, args[i]);  
                }  
                rs=pstmt.executeQuery();  
                if (rs.next()) {  
                    if (listener!=null) { 
                        obj=listener.onTransform(rs);
                    }  
                }  
            } finally {  
                ConnectionFactory.close(rs, pstmt, conn);  
            }  
        } catch (Exception e) { 
        	System.out.println(e.getMessage());
        }  
        return obj;  
    }  
	
	/**
	 * 返回多个结果的查询
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> queryAll(String sql,Object[] args){
		List<T>list=new ArrayList<T>();
		try {
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn=ConnectionFactory.getConnection();
				pstmt=conn.prepareStatement(sql);
				if(args != null){
					for (int i = 0; i < args.length; i++) {
						pstmt.setObject(i+1, args[i]);
					}
				}
				rs=pstmt.executeQuery();
				while (rs.next()) {
					if (listener!=null) {
						T obj=listener.onTransform(rs);
						list.add(obj);			
					}
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
