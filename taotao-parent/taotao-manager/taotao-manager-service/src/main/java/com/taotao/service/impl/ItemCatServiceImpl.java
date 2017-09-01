package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.common.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> findItemCatList(Long parentId) {
		List<EasyUITreeNode> treelist = new ArrayList<EasyUITreeNode>();
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		for(int i=0;i<list.size();i++){
			TbItemCat tbItemCat = list.get(i);
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbItemCat.getId().intValue());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			treelist.add(treeNode);
		}
		return treelist;
	}

}
