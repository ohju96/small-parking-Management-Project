package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.service.User;

@Service("UserService")
public class UserImp implements User {
	
	// -----------------------로그 라이브러리 사용------------------------------
	private Logger log = Logger.getLogger(this.getClass());
}
