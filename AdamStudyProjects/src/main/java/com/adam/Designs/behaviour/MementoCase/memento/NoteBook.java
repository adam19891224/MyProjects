package com.adam.Designs.behaviour.MementoCase.memento;

import java.util.Map;

import com.adam.Utils.Utils;

/**
 * 备忘录模式作业
 * @author adam
 * 2015.6.2
 */
public class NoteBook {

	private static final Map<Object, Object> NOTE_RECORD = Utils.getMap();
	
	@SuppressWarnings("static-access")
	public NoteBook(String name, String info){
		this.NOTE_RECORD.put(name, info);
	}
	
	@SuppressWarnings("static-access")
	public String getRecord(String name){
		return (String) this.NOTE_RECORD.get(name);
	}
	
}
