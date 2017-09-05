package com.taotao.content.service;

import java.util.List;

import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;

public interface ContentCategoryService {
	public List<EasyUITreeNode> findContentCategoryByParentId(Long parentId);
	public TaotaoResult createNode(Long parentId, String name);
}
