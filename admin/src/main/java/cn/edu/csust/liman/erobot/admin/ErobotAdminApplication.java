package cn.edu.csust.liman.erobot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@MapperScan("cn.edu.csust.liman.erobot.admin.dao")
@SpringBootApplication
public class ErobotAdminApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(ErobotAdminApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                response.setHeader("Access-Control-Allow-origin", "*");
//                if (!"/login".equals(request.getRequestURI())) {
//                    HttpSession session = request.getSession();
//                    String accountName = (String) session.getAttribute("account");
//                    if (accountName == null) {
//                        return false;
//                    }
//                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });
    }
}
