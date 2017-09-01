package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		
		PageHelper.startPage(1, 10);
		//5.查询
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//6.创建pageInfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//7.获取分页信息
		System.out.println("总页数:"+pageInfo.getPages());
		System.out.println("总记录数:"+pageInfo.getTotal());
		
		return item;
	}


}
