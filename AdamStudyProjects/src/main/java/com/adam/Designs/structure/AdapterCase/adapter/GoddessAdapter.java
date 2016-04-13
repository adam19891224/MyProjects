package com.adam.Designs.structure.AdapterCase.adapter;

import com.adam.Designs.structure.AdapterCase.entity.AngelaBaby;
import com.adam.Designs.structure.AdapterCase.interfaces.FengJie;

/**
 * 女神适配器
 * @author adam
 *
 */
public class GoddessAdapter implements FengJie {
	
	/**
	 * 概述
	 * 我们这个例子生动形象
	 * 我们的希望类是女神Angelababy，但是现实很骨感，我们却只有凤姐的接口
	 * 所以我们可以通过女神适配器，让凤姐改变，这样同样可以拥有我们自己的Angelababy
	 */
	
	private AngelaBaby angelaBaby;

	public void showMySelf() {
		angelaBaby.smile();
	}
	
	
	//构建器
	public static class Builder{
		
		private AngelaBaby ab;
		
		public Builder myBaby(AngelaBaby a){
			this.ab = a;
			return this;
		}
		
		public GoddessAdapter build(){
			return new GoddessAdapter(this);
		}
		
	}

	private GoddessAdapter(Builder b){
		this.angelaBaby = b.ab;
	}
	
}
