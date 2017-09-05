package com.taotao.content.service;

import java.util.List;

import com.taotao.common.EasyUIResult;
import com.taotao.common.TaotaoResult;
import com.taotao.pojo.ADItem;
import com.taotao.pojo.TbContent;

public interface ContentService {
	public EasyUIResult findContentListByCategoryId(Long categoryId,Integer page, Integer rows);
	
	public TaotaoResult saveContent(TbContent content);
	
	public List<ADItem> findContentList(Long categoryId);

}
