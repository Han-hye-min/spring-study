package kr.com.web.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.com.web.board.vo.NoticeFileVO;
import kr.com.web.board.vo.NoticeVO;

@Mapper
public interface NoticeMapper {
	
	//게시글 전체개수 구하기
	int getNoticeTotal(Map<String, Object> param) throws Exception;
	
	//게시글 리스트 가져오기
	List<NoticeVO.NoticeList>getNoticeList(Map<String, Object> param) throws Exception;
	
	//게시글 정보 가져오기
	NoticeVO.Notice getNotice(@Param("noId") int noId) throws Exception;
	
	//조회 수 증가
	void updateReadCnt(@Param("noId") int noId) throws Exception;
	
	//게시글 저장
	void addNotice(NoticeVO.NoticeReq newNotice) throws Exception;
	
	//게시글 파일 저장
	void addNoticeFile(NoticeFileVO noticeFile) throws Exception;
	
	//파일 정보 가져오기
	NoticeFileVO getFileInfo(@Param("fileId") int fileId) throws Exception;
	
	//게시글정보 수정
	int updateNotice(NoticeVO.Notice notice) throws Exception;
	
	//게시글 정보 삭제
	int deleteNotice(@Param("noId") int noId ) throws Exception;
	
	//게시글 첨부파일 삭제
	int deleteNoticeFile(@Param("fileId")int fileId) throws Exception;
	
	
}
