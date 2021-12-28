package poly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("TestController")
public class TestController {

	@ResponseBody 
	   @RequestMapping(value = "test") 
	   public String flaskTest(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			   @RequestParam(value="fileUpload") MultipartFile mf) throws Exception { 
	 
		return "/test";
	}
}
