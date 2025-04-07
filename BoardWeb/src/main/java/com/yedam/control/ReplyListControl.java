package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		//글번호(152) 댓글 데이터(json).
		String bno = req.getParameter("bno"); //원본글번호.
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.replyList(Integer.parseInt(bno));
		// [{"replyNo":10, "reply": "댓글", ...},{}, {}....{}]
		String json = "[";
		for(int i=0; i<list.size(); i++) {
			json += "{\"replyNo\":" + list.get(i).getReplyNo() + ","//
					+"\"reply\":\"" + list.get(i).getReply() + "\","//
					+"\"replyer\":\"" + list.get(i).getReplyer() + "\","//
					+"\"replyDate\":\"" + list.get(i).getReplyDate() + "\","//
					+"\"boardNo\":" + list.get(i).getBoardNo() + "}";
			if(i+1 != list.size()) {
				json += ",";
			}
		}
		json += "]";
		//
		resp.getWriter().print(json);
	}

}
