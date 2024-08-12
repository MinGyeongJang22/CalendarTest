package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CalendarDAO;
import vo.CalendarVO;

@Controller
public class CalendarController {
	
	public static final String VIEW_PATH_calen = "/WEB-INF/views/calenderF/";

	CalendarDAO calen_dao;
	
	public CalendarController( CalendarDAO calen_dao ) {
		this.calen_dao = calen_dao;
	}
	
	@RequestMapping(value= {"/", "/list.do"})
	public String list(Model model)  {
		// 임시로 모든 리스트 출력
		System.out.println("asdasdas");
		List<CalendarVO> list = calen_dao.selectcalender();
		model.addAttribute("list", list);
		return VIEW_PATH_calen + "mainF.jsp";
	}
	
	// 사용자 달력 조회
	@RequestMapping("/cal_user.do")
	public String calendar_user(String id, Model model, HttpServletRequest request, HttpServletResponse response)  {
		// 요청받을 변수 선언
		String command = request.getParameter("command");
		List<CalendarVO> list = calen_dao.select_userCalender(id);
		model.addAttribute("list", list);
		
		return VIEW_PATH_calen + "calenF.jsp";
	}
	
	// 팀 달력 조회
	@RequestMapping("/cal_team.do")
	public String calendar_team(String team_name, Model model ,HttpServletRequest request, HttpServletResponse response ) {
		// 요청받을 변수 선언
		String command = request.getParameter("command");
		List<CalendarVO> list = calen_dao.select_teamCalender(team_name);
		model.addAttribute("list", list);
		
		return VIEW_PATH_calen + "calenF.jsp";
	}
	
	@RequestMapping("/calendar_insert.do")
	@ResponseBody
	public String insertForm(CalendarVO vo) {
		int res = calen_dao.insert(vo);
		
		String result = "no";
		if( res > 0 ) {
			result = "yes";
		}
		
		//콜백메서드로 최종 결과값 전달
		return result;
	}
	
	@RequestMapping("/calendar_day_update.do")
	@ResponseBody
	public String day_update(CalendarVO vo) throws Exception {
		System.out.println(vo.getStart_day());
		System.out.println(vo.getEnd_day());
		System.out.println(vo.getDate_idx());
		
		/*
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startdate = isoFormat.parse(vo.getStart_day());
		String start_day = dbFormat.format(startdate);
		
		Date enddate = isoFormat.parse(vo.getEnd_day());
		String end_day = dbFormat.format(enddate);
		
		vo.setStart_day(start_day);
		vo.setEnd_day(end_day);
		*/
		int res = calen_dao.day_update(vo);
		
		String result = "no";
		if( res > 0 ) {
			result = "yes";
		}
		
		//콜백메서드로 최종 결과값 전달
		return result;
	}
}
