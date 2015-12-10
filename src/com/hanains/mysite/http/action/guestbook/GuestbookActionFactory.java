package com.hanains.mysite.http.action.guestbook;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;
import com.hanains.mysite.http.action.main.IndexAction;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action = null;
		if("listform".equals(actionName)){
			action = new ListFormAction();
		}else if("insert".equals(actionName)){
			action = new InsertAction();
		}else if("deleteform".equals(actionName)){
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else{
			action = new IndexAction();
		}
		
		return action;
	}

}
