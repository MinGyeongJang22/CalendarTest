package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CalendarVO;

public class CalendarDAO {
	SqlSession sqlSession;
	
	public CalendarDAO( SqlSession sqlSession ) {
		this.sqlSession = sqlSession;
	}
	
	// 달력 목록 가져오기
	public List<CalendarVO> selectcalender(){
		List<CalendarVO> list = sqlSession.selectList("calen.calendar_list");
		return list;
	}
	
	// 사용자의 일정 조회
	public List<CalendarVO> select_userCalender(String id){
		List<CalendarVO> list = sqlSession.selectList("calen.calendar_Userlist", id);
		return list;
	}
	
	// 팀별 일정 조회
	public List<CalendarVO> select_teamCalender(String team_name){
		List<CalendarVO> list = sqlSession.selectList("calen.calendar_Teamlist", team_name);
		return list;
	}

	// 새 일정 추가
	public int insert( CalendarVO vo ) {
		int res = sqlSession.insert("calen.calendar_insert", vo);
		return res;
	}
	
	// 일정 삭제
	public int delete( int date_idx ) {
		int res = sqlSession.delete("calen.calendar_delete", date_idx);
		return res;
	}
	
	//특정 일정 정보 조회
//	public CalenderVO selectOne( int date_idx ) {
//		CalenderVO vo = sqlSession.selectOne("d.dept_selectOne", date_idx);
//		return vo;
//	}
	
	// 부서 정보 업데이트
//	public int update( Map<String, Object> map ) {
//		int res = sqlSession.update("d.dept_update", map);
//		return res;
//	}
}
