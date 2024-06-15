package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVO;

public class DeptDAO {
	SqlSession sqlSession;
	
	public DeptDAO( SqlSession sqlSession ) {
		this.sqlSession = sqlSession;
	}
	
	// 부서목록 가져오기
	public List<DeptVO> selectDept(){
		//스프링은 자동으로 commit 해준다.
		//스프링은 자동으로 닫아주기 때문에 sqlSession.close()가 팔요없다
		List<DeptVO> list = sqlSession.selectList("d.dept_list");
		return list;
	}
	
	// 새 부서 추가
	public int insert( DeptVO vo ) {
		int res = sqlSession.insert("d.dept_insert", vo);
		return res;
	}
	
	// 부서 삭제
	public int delete( int deptno ) {
		int res = sqlSession.delete("d.dept_delete", deptno);
		return res;
	}
	
	//특정 부서 정보 조회
	public DeptVO selectOne( int deptno ) {
		DeptVO vo = sqlSession.selectOne("d.dept_selectOne", deptno);
		return vo;
	}
	
	// 부서 정보 업데이트
	public int update( Map<String, Object> map ) {
		int res = sqlSession.update("d.dept_update", map);
		return res;
	}
}
