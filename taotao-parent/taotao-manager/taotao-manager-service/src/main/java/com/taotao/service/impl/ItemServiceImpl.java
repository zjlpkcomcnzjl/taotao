package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	//张加娄
	@Override
	public TbItem findItemByID(Long itemId) {
		
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria createCriteria = example.createCriteria();
		//根据Id查询，设置参数
		createCriteria.andIdEqualTo(itemId);
		
		List<TbItem> itemList = itemMapper.selectByExample(example);
		TbItem item = null;
		if(itemList!=null && itemList.size()>0){
			item = itemList.get(0);
		}
		
		return item;
	}
	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		
		TbItemExample example = new TbItemExample();
		
		PageHelper.startPage(page, rows);
		//5.查询
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//6.创建pageInfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		EasyUIResult result = new EasyUIResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}


}
