package com.biminds.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysResourceEntity;
import com.biminds.framework.mvc.mybatis.BaseDao;

/**
 * 系统资源
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午8:57:01
 * @since 1.0.0
 */
@Repository
public class SysResourceDao extends BaseDao<SysResourceEntity, Serializable> {

	/**
	 * 根据用户ID查询用户资源列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午10:34:50
	 */
	public List<SysResourceEntity> selectResourceByUserId(String userId) {
		return getSqlSession().selectList(
				"SysResourceDao.selectResourceByUserId", userId);
	}

}
