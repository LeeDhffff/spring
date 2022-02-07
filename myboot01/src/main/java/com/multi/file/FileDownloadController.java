package com.multi.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileDownloadController {
	
	@RequestMapping("/fileDownloadList")
	public ModelAndView fileDownloadList() {
		ModelAndView mv = new ModelAndView();
		
		//
		File path = new File("C:/springWorkspace/upload");
		String[] fileList = path.list();
		
		mv.addObject("fileList", fileList);
		mv.setViewName("upload/fileDownloadListView");
		
		return mv;
	}
	
	@RequestMapping("/fileDownload/{file}")
	public void fileDownload(@PathVariable String file,
											   HttpServletResponse response) throws IOException {
		
		File f = new File("C:/springWorkspace/upload", file);
		
		// file 다운로드 설정
		response.setContentType("application/download");
		response.setContentLength((int)f.length());
		response.setHeader("Content-Disposition", "attatchment;filename=\"" + file + "\"" );
		
		// response 객체를 통해서 서버로부터 파일 다운로드
		OutputStream os = response.getOutputStream();
		
		// 파일 입력 객체 생성
		FileInputStream fis = new FileInputStream(f);
		FileCopyUtils.copy(fis, os);
		
		fis.close();
		os.close();
	}

}