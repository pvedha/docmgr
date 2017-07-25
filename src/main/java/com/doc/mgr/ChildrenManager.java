package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.Children;
import com.doc.dto.ChildrenDto;

public class ChildrenManager extends DocManager {
	
	public int addChild(ChildrenDto child) {
		return dao.addChild(child);
	};
	
	public  ArrayList<ChildrenDto> readAllChildren(){
		ArrayList<Children> childrens = dao.readAllChildren();
		return getChildrenDtos(childrens);
	}
	
	public int updateChild(ChildrenDto dto){
		return dao.updateChild(dto);
	}
		
	private ArrayList<ChildrenDto> getChildrenDtos(ArrayList<Children> childrens){
		ArrayList<ChildrenDto> childrenDtos = new ArrayList<>();
		for(Children children: childrens){
			childrenDtos.add(new ChildrenDto(children));
		}
		return childrenDtos;
	}
	
	
}