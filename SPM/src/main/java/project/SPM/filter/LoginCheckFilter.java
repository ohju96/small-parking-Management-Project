package project.SPM.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import project.SPM.Entity.UserEntity;
import project.SPM.web.SessionConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/user/regUser/insert",
            "/user/regUser",
            "/user/logIn",
            "/user/logIn/page",
            "/css/*",
            "/session-info"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            log.info("인증 체크 필터 시작 = {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 = {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

                    log.info("미인증 사용자 요청 = {}", requestURI);
                    //로그인으로 redirect
                    httpResponse.sendRedirect("/user/logIn"); //?redirectURL=" + requestURI 입력했던 경로로 가고 싶을 떄
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e; //예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 한다.
        }finally {
            log.info("인증 체크 필터 종료 = {}", requestURI);
        }
    }

    /**
     * 화이트 리스트인 경우 인증 체크를 하지 않는 로직
     * 화이트 리스트에 없는 것은 false로 되면서 로그인 체크가 된다.
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
