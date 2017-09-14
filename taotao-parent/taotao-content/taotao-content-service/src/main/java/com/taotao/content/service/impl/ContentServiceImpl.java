package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.EasyUIResult;
import com.taotao.common.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.content.service.JedisService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.ADItem;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisService jedisService;
	
	private final String REDIS_CACHE_AD = "REDIS_CACHE_AD";

	@Override
	public EasyUIResult findContentListByCategoryId(Long categoryId,
			Integer page, Integer rows) {
		//创建example对象
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		//设置分页参数
		PageHelper.startPage(page, rows);
		//查询
		List<TbContent> list = contentMapper.selectByExample(example);
		//创建pageinfo对象，获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		
		//构造返回值对象
		EasyUIResult result = new EasyUIResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);	
		
		return result;
	}

	@Override
	public TaotaoResult saveContent(TbContent content) {
		//删除缓存,实现缓存和数据库数据同步
		jedisService.hdel(REDIS_CACHE_AD, content.getCategoryId()+"");

		// 补全属性
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		contentMapper.insert(content);

		return TaotaoResult.ok();


	}
	
	

	@Override
	public List<ADItem> findContentList(Long categoryId) {
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			// 先查询缓存
			String jsonStr = jedisService.hget(REDIS_CACHE_AD, categoryId + "");
			if (StringUtils.isNotBlank(jsonStr)) {

				@SuppressWarnings("unchecked")
				List<ADItem> list = jsonMapper.readValue(jsonStr, List.class);

				return list;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 创建广告集合，封装首页分类数据
		List<ADItem> adList = new ArrayList<ADItem>();

		// 创建example对象
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria createCriteria = example.createCriteria();
		// 设置查询参数
		createCriteria.andCategoryIdEqualTo(categoryId);
		// 查询
		List<TbContent> contentList = contentMapper.selectByExample(example);
		// 循环，封装首页分类数据
		for (TbContent tbContent : contentList) {
			// 创建AdItem对象
			ADItem ad = new ADItem();
			// 设置商品提示
			ad.setAlt(tbContent.getTitle());
			// 设置首页商品图片地址
			ad.setSrc(tbContent.getPic());
			// 设置首页商品图片地址2
			ad.setSrcB(tbContent.getPic2());
			// 设置商品购买地址
			ad.setHref(tbContent.getUrl());

			// 把ad放入adList集合
			adList.add(ad);

		}

		// 把查询数据添加redis数据库
		//把adlist集合转换层json字符串，存入redis
		try {
			jedisService.hset(REDIS_CACHE_AD, categoryId + "",
					jsonMapper.writeValueAsString(adList));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adList;

	}

}
