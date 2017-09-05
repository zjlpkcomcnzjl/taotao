package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	//根据内容分类父Id，查询内容分类树形菜单
	@RequestMapping("/content/category/list")
	public @ResponseBody
	List<EasyUITreeNode> findContentCategoryByParentId(
			@RequestParam(defaultValue = "0", 
			value = "id") Long parentId) {
		
		List<EasyUITreeNode> list = contentCategoryService.findContentCategoryByParentId(parentId);
		
		return list;

	}
	
	@RequestMapping("/content/category/create")
	public @ResponseBody TaotaoResult createNode(Long parentId,String name){
		
		TaotaoResult createNode = contentCategoryService.createNode(parentId, name);
		return createNode;
	}


}
