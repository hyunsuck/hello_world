package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class SignupControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 회원가입화면. WEB_INF/views/signForm.jsp
		req.getRequestDispatcher("WEB_INF/views/signForm.jsp").forward(req, resp);
		
		
		// 2. 회원가입처리.
	}

}
