package com.doc.mgr;

import java.util.ArrayList;
import java.util.Arrays;

import com.doc.api.Children;
import com.doc.dto.ChildrenDto;
import com.doc.exceptions.ChildNotFoundException;
import com.doc.exceptions.InvalidSearchKeyException;

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
	
	public ArrayList<ChildrenDto> searchChildren(String key){
		
		if(key.trim().isEmpty()){
			throw new InvalidSearchKeyException();
		}
		
		ArrayList<String> keyList = new ArrayList<>(Arrays.asList(key.split("\\s")));
		
		if(keyList.size() == 1){
			//possible id / number
			try{
				Integer id = Integer.valueOf(keyList.get(0));
				return findChildById(id);
			} catch (NumberFormatException e){
				//do nothing, 
			} catch (ChildNotFoundException e){
				//do nothing, 
			}
		}
		
		return getChildrenDtos(dao.searchChildren(keyList));			 
		
	}
	
	public ArrayList<ChildrenDto> findChildById(Integer id){
		ArrayList<ChildrenDto> dtos = new ArrayList<>();
		Children child = dao.findChildById(id);
		dtos.add(new ChildrenDto(child));
		return dtos;
	}
	
	
	
	private ArrayList<ChildrenDto> getChildrenDtos(ArrayList<Children> childrens){
		ArrayList<ChildrenDto> childrenDtos = new ArrayList<>();
		for(Children children: childrens){
			childrenDtos.add(new ChildrenDto(children));
		}
		return childrenDtos;
	}
	
	
}