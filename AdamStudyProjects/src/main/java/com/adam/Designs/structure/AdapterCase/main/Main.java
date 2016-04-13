package com.adam.Designs.structure.AdapterCase.main;

import com.adam.Designs.structure.AdapterCase.adapter.GoddessAdapter;
import com.adam.Designs.structure.AdapterCase.entity.AngelaBaby;
import com.adam.Designs.structure.AdapterCase.interfaces.FengJie;

public class Main {

	public static void main(String[] args) {
		//创建凤姐对象，其实质是女神适配器
		FengJie fengjie = new GoddessAdapter.Builder().myBaby(new AngelaBaby()).build();
		
		//调用凤姐的show方法
		fengjie.showMySelf();
	}

}
