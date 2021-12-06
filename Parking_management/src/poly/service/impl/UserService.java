package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService {
	
	// -----------------------로그 라이브러리 사용------------------------------
	private Logger log = Logger.getLogger(this.getClass());
}
