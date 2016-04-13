function dateFormatter(value, row, index){
	if (value != null) {
		var date = new Date(value);
		return date.toLocaleString();
	}
}

function boolFormatter(value,row,index){
	if(value != null){
		if(value){
			return "<span style='color: red;'>已禁用</span>";
		}else{
			return "<span style='color: green;'>已启用</span>"
		}
	}
}