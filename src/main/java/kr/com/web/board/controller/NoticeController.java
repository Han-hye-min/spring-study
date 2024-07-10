package kr.com.web.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.com.web.board.service.NoticeService;
import kr.com.web.board.vo.NoticeFileVO;
import kr.com.web.board.vo.NoticeVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;
	
	@GetMapping("/list.do")
	public ModelAndView getList(@RequestParam(name = "nowPage", defaultValue="0") int nowPage) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardList");
		
		// client에 전송할 데이터 객체 선언
		NoticeVO.NoticeResp resp = new NoticeVO.NoticeResp();
		
		try {
			resp = noticeService.getNoticeList(nowPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 클라이언트에 전송할 데이터 넣기
			view.addObject("data", resp);
		}
		return view;
	}

	@GetMapping("/detail.do")
	public ModelAndView getDetail(@RequestParam(name = "nowPage", defaultValue="0") int nowPage,
								  @RequestParam("noId") int noId,
								  @CookieValue(name="notice", defaultValue="") String cookieValue,
								  HttpServletResponse response) {

		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardDetail");
		view.addObject("nowPage", nowPage);
		NoticeVO.Notice notice = new NoticeVO.Notice();
		
		try {
			notice = noticeService.getDetail(noId, cookieValue, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			view.addObject("notice", notice);
		}
		
		return view;
	}
	
	@GetMapping("/delete.do")
	public ModelAndView listDelete(@RequestParam(name="noId", defaultValue="0") int noId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/notice/list.do");
		view.addObject("noId", noId);
		
		try {
			noticeService.deleteNoticeFile(noId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	@GetMapping("/write.do")
	public ModelAndView writeView(@RequestParam(name="nowPage", defaultValue="0") int nowPage) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardWrite");
		view.addObject("nowPage", nowPage);
		
		return view;
	}
	
	
	@PostMapping("/add.do")
	public ModelAndView writeNotice(NoticeVO.NoticeReq newNotice) {
		System.out.print(newNotice.getFile());
		ModelAndView view = new ModelAndView(); 
		view.setViewName("redirect:/notice/list.do");
		try {
			noticeService.addNotice(newNotice);
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("error/writeError");
		}
		return view;
	}
	
	@GetMapping("/up/view.do")
	public ModelAndView getUpdateView(@RequestParam(name = "nowPage", defaultValue="0") int nowPage,
								  @RequestParam("noId") int noId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardUpdate");
		
		try {
			NoticeVO.Notice notice = noticeService.getNoticeInfo(noId);
			view.addObject("notice", notice);
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}
	
	@PostMapping("/update.do")
	public ModelAndView updateNotice(NoticeVO.NoticeReq newNotice) {
		System.out.print(newNotice.getFile());
		ModelAndView view = new ModelAndView(); 
		view.setViewName("redirect:/notice/list.do");
		try {
			noticeService.updateNotice(newNotice);
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("error/writeError");
		}
		return view;
	}
	
	@GetMapping("/file/down.do")
	public ResponseEntity<UrlResource>  downBoardFile(@RequestParam("fileId") int fileId) {
	      String originFileName = null;
	       String storedFileName = null;
	       //파일다운로드 시 필요 
	       HttpHeaders header = new HttpHeaders();
	       UrlResource  res = null;
	      
	       try {
	          
	    	   NoticeFileVO fileVO = noticeService.getFileInfo(fileId);
	          
	          if(fileVO != null) {
	             
	             originFileName = fileVO.getFileName();
	             storedFileName  = fileVO.getFileStoredName();
	             
	             String fullPath = fileVO.getFilePath() + storedFileName;
	             
	             File file = new File(fullPath);
	             
	             if(!file.exists()) {
	                throw new Exception("파일이 존재하지 않습니다.");
	             }
	             //파일은 전송할 때 필요한 파일의  확장 타입 
	             String mimeType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
	             
	             if(mimeType == null) {
	                // 일반 이진파일 타입 
	                mimeType = "octet-stream";
	             }
	             //전송할 파일을 객체 담는다 
	             res =  new UrlResource(file.toURI());
	             //한글깨짐 방지 정책
	             //+ 기호는 경로에서 오류발생 때문에 encode 후 + 기호를 바이트 코드인 %20 으로 변경 
	             String encodedName = URLEncoder.encode(originFileName, "UTF-8").replace("+", "%20");
	             //http 헤더 세팅
	             //파일 다운로드 옵션 과 다운로드 할 때의 파일 이름 지정 
	             header.set("Content-Disposition",   "attachment;filename="  + encodedName +";filename*=UTF-8''" + encodedName);
	            // 캐쉬 사용 안함 
	             header.setCacheControl("no-cache"); 
	             //다운로드할 파일의 타입 
	               header.setContentType(MediaType.parseMediaType(mimeType));
	             
	          }else {
	             throw new Exception("SQL 오류 .");
	          }
	          
	       }catch (Exception e) {
	         e.printStackTrace();
	      }
	     
	       return new ResponseEntity<UrlResource>(res, header, HttpStatus.OK);
	   }
	
}
