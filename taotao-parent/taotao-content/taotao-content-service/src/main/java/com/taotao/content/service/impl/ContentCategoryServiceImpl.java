package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.EasyUITreeNode;
import com.taotao.common.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;

public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> findContentCategoryByParentId(Long parentId) {
		//创建List<EasyUITreeNode>集合，封装结果集数据
		List<EasyUITreeNode> treeList = new ArrayList<EasyUITreeNode>();
		
		//创建example对象
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria createCriteria = example.createCriteria();
		//设置参数
		createCriteria.andParentIdEqualTo(parentId);
		//查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		//循环list集合，封装节点数据
		for (TbContentCategory tbContentCategory : list) {
			//把数据集封装EasyUITreeNode
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbContentCategory.getId().intValue());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			treeList.add(treeNode);
			
		}
		
		
		
		return treeList;
	}
	
	
	
	@Override
	public TaotaoResult createNode(Long parentId, String name) {
		//保存内容分类表，补全属性
		TbContentCategory category = new TbContentCategory();
		//设置属性参数
		category.setParentId(parentId);
		category.setName(name);
		//补全属性
		//状态。可选值:1(正常),2(删除)
		category.setStatus(1);
		//取值范围:大于零的整数
		category.setSortOrder(1);
		//该类目是否为父类目，1为true，0为false
		category.setIsParent(false);
		//时间
		Date date = new Date();
		category.setCreated(date);
		category.setUpdated(date);
		//保存:返回主键
		contentCategoryMapper.insert(category);
		
		//如果创建节点父节点是子节点？修改节点状态
		//根据Id查询父节点状态
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断当前节点是否是父节点
		if(!tbContentCategory.getIsParent()){
			//子节点，修改节点状态
			tbContentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
			
		}
		
		return TaotaoResult.ok(category);

	}


}
