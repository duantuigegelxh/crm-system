package com.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.dao.BaseDictDao;
import com.core.po.BaseDict;
import com.core.service.BaseDictService;
/**
 * 数据字典Service接口的实现类
 *
 */
@Service("BaseDictService")
public class BaseDictServiceImpl implements BaseDictService{

	@Autowired
	private BaseDictDao baseDictDao;
	@Override
	public List<BaseDict> findBaseDicByTypeCode(String typecode) {
		// 根据类别代码查询数据字典
		return baseDictDao.selectBaseDicByTypeCode(typecode);
	}

}
