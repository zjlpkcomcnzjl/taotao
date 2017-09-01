package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.support.Parameter;
import com.taotao.common.EasyUIResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	
	@Resource
	private ItemService itemService;
	
	@RequestMapping(value="{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
	@RequestMapping(value="item/list")
	public @ResponseBody Object findItemByPage(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="30")Integer rows){
		EasyUIResult result = itemService.findItemByPage(page, rows);
		return result;
	}
}
