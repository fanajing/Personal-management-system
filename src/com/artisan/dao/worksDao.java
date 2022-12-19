package com.artisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.artisan.model.works;
import com.artisan.util.StringUtil;


/**
 * 作品Dao类
 *
 */
public class worksDao {

	/**
	 * 作品添加
	 *
	 * */
	public int add(Connection con, works schoolClass)throws Exception{
		String sql="insert into t_works values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, schoolClass.getworkName());
		System.out.println(schoolClass.getworkName());
		pstmt.setString(2, schoolClass.getCalssDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询作品集合
	 *
	 */
	public ResultSet list(Connection con, works schoolClass)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_works");
		if(StringUtil.isNotEmpty(schoolClass.getworkName())){
			sb.append(" and worksName like '%"+schoolClass.getworkName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除作品
	 *
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_works where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	/**
	 * 更新作品
	 *
	 */
	public int update(Connection con, works schoolClass)throws Exception{
		String sql="update t_works set worksName=?,worksDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, schoolClass.getworkName());
		pstmt.setString(2, schoolClass.getCalssDesc());
		pstmt.setInt(3, schoolClass.getId());
		return pstmt.executeUpdate();
	}
}
