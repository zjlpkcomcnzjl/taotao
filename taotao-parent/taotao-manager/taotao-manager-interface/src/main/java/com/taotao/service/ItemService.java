package com.taotao.service;

import com.taotao.common.EasyUIResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	//根据商品Id查询商品
	public TbItem findItemByID(Long itemId);
	
	public EasyUIResult findItemByPage(Integer page,Integer rows);
}
