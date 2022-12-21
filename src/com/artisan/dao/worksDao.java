package com.artisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.artisan.model.works;
import com.artisan.util.StringUtil;


/**
 * ��ƷDao��
 *
 */
public class worksDao {

	/**
	 * ��Ʒ���
	 *
	 * */
	public int add(Connection con, works work)throws Exception{
		String sql="insert into t_works values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, work.getworkName());
		System.out.println(work.getworkName());
		pstmt.setString(2, work.getCalssDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ��ѯ��Ʒ����
	 *
	 */
	public ResultSet list(Connection con, works work)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_works");
		if(StringUtil.isNotEmpty(work.getworkName())){
			sb.append(" and worksName like '%"+work.getworkName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * ɾ����Ʒ
	 *
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_works where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	/**
	 * ������Ʒ
	 *
	 */
	public int update(Connection con, works work)throws Exception{
		String sql="update t_works set worksName=?,worksDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, work.getworkName());
		pstmt.setString(2, work.getCalssDesc());
		pstmt.setInt(3, work.getId());
		return pstmt.executeUpdate();
	}
}
