package kr.com.web.board.vo;

import lombok.Data;

@Data
public class NoticeFileVO {
	private int fileId;
	private int noId;
	private String fileName;
	private String fileStoredName;
	private String filePath;
	
}
