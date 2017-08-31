package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Resource
	private ItemService itemService;
	
	@RequestMapping(value="findItemByID/{itemId}")
	public @ResponseBody TbItem findItemByID(@PathVariable Long itemId){
		TbItem item = itemService.findItemByID(itemId);
		System.out.println(item.getTitle());
		return item;
	}
}
