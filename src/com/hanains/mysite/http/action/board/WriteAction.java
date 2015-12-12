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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession(true);
		UserVo v = (UserVo) session.getAttribute("authUser");
		if(v==null){
			HttpUtil.redirect(response, "/mysite/user?a=loginform&result=fail");
			return;
		}
		Long no = v.getNo();
		System.out.println(v+":"+no);
		String str="";
		String member_no = str.valueOf(no);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMember_no(member_no);
		
		
		BoardDao dao = new BoardDao();
		dao.insert(vo);
		
		HttpUtil.redirect(response, "/mysite/board?a=list");
	}

}
