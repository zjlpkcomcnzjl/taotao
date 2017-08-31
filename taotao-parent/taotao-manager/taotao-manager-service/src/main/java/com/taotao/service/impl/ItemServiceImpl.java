package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
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


}
