<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<%request.setCharacterEncoding("utf-8");%>
<%
	// 결과 문자열
	String result = "----------------------<br>";
	// 웹 애플리케이션상의 절대경로 저장
	String realFolder = "";
	// 파일 업로드 폴더 지정
	String saveFolder = "/upload";
	// 인코딩 타입
	String encType = "utf-8";
	// 최대 업로드될 파일 크기 5Mb
	int maxSize = 5 * 1024 * 1024;
	
	// 현재 jsp페이지의 웹 애플리케이션 상의 절대 경로를 구함
	ServletContext context = getServletContext();
	realFolder = context.getRealPath(saveFolder);
	
	try {
		MultipartRequest upload = null;
		
		// 파일 업로드를 수행하는 MultipartRequest 객체 생성
		upload = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		// <input type="file">이 아닌 모든 파라미터를 얻어냄
		Enumeration<?> params = upload.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String)params.nextElement();
			String value = upload.getParameter(name);
			result += name + " : " + value + "<br>";
		}
		
		// <input type="file">인 모든 파라미터를 얻어냄
		Enumeration<?> files = upload.getFileNames();
		while (files.hasMoreElements()) {
			String name = (String)files.nextElement();
			String fileName = upload.getFilesystemName(name);
			String original = upload.getOriginalFileName(name);
			String type = upload.getContentType(name);
			
			// 결과문자열 누적
			result += "파라메터 이름 : " + name + "<br>";
			result += "실제 파일 이름 : " + original +"<br>";
		    result += "저장된 파일 이름 : " + fileName +"<br>";
		    result += "파일 타입 : " + type +"<br>";
		    
		    // 업로드된 파일의 정보를 얻어내기 위해 File객체로 생성
		    File file = upload.getFile(name);
		    if (file != null) {
		    	result += "크기 : " + file.length() + " bytes <br>";
		    }
		}
		
		result += "----------------------<br>";
		out.println(result);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
%>