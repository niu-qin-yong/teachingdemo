package com.it61.minecraft.common;

import java.sql.ResultSet;

/**
 * 
 *该接口作用是由SQL查询结果集转换成具体的JavaBean，接口的具体实现由具体DAO来完成
 * @param <T>
 */
public interface OnTransformListener<T> {
	/**
	 * @param rs
	 * @return
	 */
	T onTransform(ResultSet rs);
}
