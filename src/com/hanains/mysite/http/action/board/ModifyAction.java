package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		UserVo v = (UserVo) session.getAttribute("authUser");
		if(v==null){
			HttpUtil.redirect(response, "/mysite/user?a=loginform&result=fail");
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no_str = request.getParameter("no");
		Long no = Long.parseLong(no_str);
		System.out.println(title + ":" + content);
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo(no);
		
		dao.revise(vo);
		HttpUtil.redirect(response, "/mysite/board?a=list");
	}

}
