package com.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.core.po.BaseDict;

/**
 * 数据字典的service接口
 *
 */
public interface BaseDictService {

	//根据类别代码查询数据字典
		public List<BaseDict> findBaseDicByTypeCode(@Param("typecode")String typecode);
}
