package com.adam.Designs.behaviour.MementoCase.housekeeper;

import com.adam.Designs.behaviour.MementoCase.memento.NoteBook;

/**
 * 备忘录操作者
 *
 */
public class Keeper {

	private NoteBook noteBook = null;
	
	public void saveRecord(NoteBook book){
		this.noteBook = book;
	}
	
	public NoteBook getBook(){
		return this.noteBook;
	}
	
}
