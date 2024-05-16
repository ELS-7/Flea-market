package com.xenus.sts.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xenus.sts.vo.CommMap;
import com.xenus.sts.vo.Returns;

//DAO
@Component("stsDao")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StsDao {
	private final String namespace = "com.xenus.sts.dao.stsDao.";
	private SqlSession   sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(StsDao.class);
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
				this.sqlSession = sqlSession;
	}
	
	//login
	public CommMap xenusLogin (CommMap param) { return sqlSession.selectOne(namespace + "xenusLogin", param); }

	//get
	public ArrayList<CommMap> stsGetMember		(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetMember",  param); }
	public ArrayList<CommMap> stsGetProduct		(CommMap param) throws Exception {	logger.debug("4.stsDao.stsGetProduct"); return (ArrayList)sqlSession.selectList(namespace + "stsGetProduct", param); }
	public ArrayList<CommMap> stsGetProductAll	(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetProductAll", param); }
	public ArrayList<CommMap> stsGetCart		(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetCart", 	 param); }
	public ArrayList<CommMap> stsGetSold		(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetSold", 	 param); }
	public ArrayList<CommMap> stsGetProdUnSold	(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetProductUnSold", param); }
	public ArrayList<CommMap> stsGetWish		(CommMap param) throws Exception {	return (ArrayList)sqlSession.selectList(namespace + "stsGetWish", 	 param); }
	
	//set
	public Returns stsSetMemberIns				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetMemberIns", 	param);	}	
	public Returns stsSetMemberMod				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetMemberMod", 	param);	}
	
	public Returns stsSetProductIns				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetProductIns", param);	}		
	
	public Returns stsSetProductMod				(CommMap param) throws Exception {	
		return sqlSession.selectOne(namespace + "stsSetProductMod", param);	
	}	

	public Returns stsSetCartIns				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetCartIns", 	param);	}
	public Returns stsSetCartMod				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetCartMod", 	param);	}
	
	public Returns stsSetSoldIns				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetSoldIns", 	param);	}
	
	public Returns stsSetWishIns				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetWishIns", 	param);	}
	public Returns stsSetWishMod				(CommMap param) throws Exception {	return sqlSession.selectOne(namespace + "stsSetWishMod", 	param);	}

}