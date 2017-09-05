package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.EasyUIResult;
import com.taotao.common.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	public @ResponseBody
	EasyUIResult findContentListByCategoryId(Long categoryId,Integer page,Integer rows) {
		EasyUIResult result = contentService.findContentListByCategoryId(categoryId, page, rows);
		return result;
	}
	
	//保存分类内容
	@RequestMapping("/content/save")
	public @ResponseBody TaotaoResult saveContent(TbContent content){
		
		TaotaoResult saveContent = contentService.saveContent(content);
		return saveContent;
	}


}
