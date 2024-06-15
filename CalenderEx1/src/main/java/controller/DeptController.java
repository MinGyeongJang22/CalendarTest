package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.DeptDAO;
import vo.DeptVO;

@Controller
public class DeptController {
	
	public static final String VIEW_PATH_user = "/WEB-INF/views/dept/";
	public static final String VIEW_PATH_calen = "/WEB-INF/views/calenderF/";
	
	DeptDAO dept_dao;
	
	public DeptController( DeptDAO dept_dao ) {
		this.dept_dao = dept_dao;
	}
	
	@RequestMapping("/")
	public String list()  {
		return VIEW_PATH_calen + "mainF.jsp";
	}
	
	@RequestMapping("/cal.do")
	public String calen(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		//인코딩 ? 이거 필요 없을 듯
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청받을 변수 선업
		String command = request.getParameter("command");
		
		return VIEW_PATH_calen + "calenF.jsp";
	}
	
	
	
	
	
	/*
	@RequestMapping( value = { "/" , "/list.do" } )
	public String list( Model model ) {
		//dao로부터 부서 목록 가져온다.
		List<DeptVO> list = dept_dao.selectDept();
		model.addAttribute("list", list);
		return VIEW_PATH + "dept_list.jsp";
	}
	// 새 글 추가를 위한 폼
	@RequestMapping("/insert_form.do")
	public String insertForm() {
		return VIEW_PATH + "insert_form2.jsp";
	}
	
	// 새 글 등록
	@RequestMapping("/insert.do")
	public String insertDept( DeptVO vo ) {
		dept_dao.insert(vo);
		
		// response.sendRedirect("list.do");
		return "redirect:list.do";
	}
	
	// 부서 삭제
	@RequestMapping("/del_dept.do")
	public String deleteDept(int deptno) {
		dept_dao.delete(deptno);
		
		return "redirect:list.do";
	}
	
	// 특정 부서 정보 조회
	@RequestMapping("/modify_form.do")
	public String modifyForm(int deptno,  Model model) {
		DeptVO vo = dept_dao.selectOne(deptno);
		model.addAttribute("vo", vo);
		return VIEW_PATH + "update_form.jsp";
	}
	
	// 부서 정보 수정
	//@ResponseBody 어노테이션이 있어야 
	//Ajax로 요청받은 메서드의 return값을 콜백 메서드로 전달할 수 있다.
	@RequestMapping("/modify.do")
	@ResponseBody
	public String modify(DeptVO vo, int c_deptno) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 수정에 필요한 정보인 vo와 c_deptno를 map에 저장
		map.put("vo", vo);
		map.put("c_deptno", c_deptno);
		int res = dept_dao.update(map);
		
		String result = "no";
		if( res > 0 ) {
			result = "yes";
		}
		
		//콜백메서드로 최종 결과값 전달
		return result;
	}
	 */
	
}
