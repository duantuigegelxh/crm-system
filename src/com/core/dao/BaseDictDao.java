package com.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.core.po.BaseDict;

/**
 * 数据字典
 *
 */
public interface BaseDictDao {

	//根据类别代码查询数据字典
	public List<BaseDict> selectBaseDicByTypeCode(@Param("typecode")String typecode);
}
