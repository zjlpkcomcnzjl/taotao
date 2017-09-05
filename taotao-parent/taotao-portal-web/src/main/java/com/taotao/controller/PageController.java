package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.ADItem;

@Controller
public class PageController {
	@Value("${CATEGORY_ID}")
	private Long CATEGORY_ID;
	@Value("${WIDTH}")
	private Integer WIDTH;
	@Value("${WIDTHB}")
	private Integer WIDTHB;
	@Value("${HEIGHT}")
	private Integer HEIGHT;
	@Value("${HEIGHTB}")
	private Integer HEIGHTB;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("index")
	public String showIndex(Model model){
		List<ADItem> list = contentService.findContentList(CATEGORY_ID);
		//循环首页商品分类数据，设置图片宽，高
		for (ADItem adItem : list) {
			
			adItem.setHeight(HEIGHT);
			adItem.setHeightB(HEIGHTB);
			adItem.setWidth(WIDTH);
			adItem.setWidthB(WIDTHB);
			
		}
		
		//前台页面获取json格式数组数据
		ObjectMapper mapper = new ObjectMapper();
		
//		String strJson = JsonUtils.objectToJson(list);
		String strJson = null;
		try {
			strJson = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//页面需要回显数据
		model.addAttribute("ad1", strJson);
		return "index";
	}

}
