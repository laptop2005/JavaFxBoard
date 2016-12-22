package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.conf.SqlMapConfig;
import vo.BoardVO;

public class BoardDAO {
	
	public List<BoardVO> selectBoardList(String boardTitle, String boardWriter) throws SQLException{
		SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
		
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("boardTitle", boardTitle);
		paramMap.put("boardWriter", boardWriter);
		
		List<BoardVO> list = (List<BoardVO>) sqlMap.queryForList("board.selectBoardList",paramMap);
		
		return list;
	}
	
	public BoardVO selectBoard(String boardId) throws SQLException {
		SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
		
		BoardVO vo = (BoardVO)sqlMap.queryForObject("board.selectBoard", boardId);
		
		return vo;
	}
	
	public void insertBoard(BoardVO vo) throws SQLException {
		SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
		
		HashMap paramMap = new HashMap();
		paramMap.put("boardId", vo.getBoardId());
		paramMap.put("boardTitle", vo.getBoardTitle());
		paramMap.put("boardWriter", vo.getBoardWriter());
		paramMap.put("boardContent", vo.getBoardContent());
		
		sqlMap.update("board.insertBoard", paramMap);
	}
	
	public void updateBoard(BoardVO vo) throws SQLException {
		SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
		
		HashMap paramMap = new HashMap();
		paramMap.put("boardId", vo.getBoardId());
		paramMap.put("boardTitle", vo.getBoardTitle());
		paramMap.put("boardWriter", vo.getBoardWriter());
		paramMap.put("boardContent", vo.getBoardContent());
		
		sqlMap.update("board.updateBoard", paramMap);
	}
	
	public void deleteBoard(String boardId) throws SQLException {
		SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
		
		sqlMap.update("board.deleteBoard", boardId);
	}
}
