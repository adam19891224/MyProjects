package com.adam.Designs.behaviour.MementoCase.master;

import com.adam.Designs.behaviour.MementoCase.memento.NoteBook;

/**
 * 主人
 *
 */
public class Master {

	//保存记录
	public NoteBook createRecord(String name, String info){
		return new NoteBook(name, info);
	}
	
	//得到记录
	public String getRecord(String name, NoteBook book){
		return book.getRecord(name);
	}
	
	
}
