package com.hanains.mysite.http.action.board;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;
import com.hanains.mysite.http.action.main.IndexAction;

public class BoardActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("list".equals(actionName)){
			action = new ListAction();
		}else if("writeform".equals(actionName)){
			action = new WriteFormAction();
		}else if("write".equals(actionName)){
			action = new WriteAction();
		}else if("modify".equals(actionName)){
			action = new ModifyAction();
		}else if("view".equals(actionName)){
			action = new ViewAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else{
			action = new IndexAction();
		}
		return action;
	}
	

}
