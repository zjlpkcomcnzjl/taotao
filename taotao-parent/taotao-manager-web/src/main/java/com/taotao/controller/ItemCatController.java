package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUITreeNode;
import com.taotao.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	public @ResponseBody
	List<EasyUITreeNode> findItemCatList(@RequestParam(defaultValue = "0", value = "id") Long parentId) {		
		//调用业务层查询
		List<EasyUITreeNode> list = itemCatService.findItemCatList(parentId);
		
		return list;

	}
}
