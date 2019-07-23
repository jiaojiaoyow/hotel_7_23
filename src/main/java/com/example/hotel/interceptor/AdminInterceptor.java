//package com.example.hotel.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
//
//public class AdminInterceptor implements HandlerInterceptor {
//    @Override
//    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
//            throws Exception {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
//            throws Exception {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = null;
//        out = response.getWriter();
//        // 获取请求的URL
//        String url = request.getRequestURI();
//        // URL:除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
//        if(url.indexOf("/api/back/")>=0 ||  url.indexOf("/api/back/login")<0){
//            return false;
//        }
//        // 获取Session
//        HttpSession session = request.getSession();
//        users user = (users) session.getAttribute("USER_SESSION");
//        // 判断Session中是否有用户数据，如果有，则返回true,继续向下执行
//        if(user != null){
//            return true;
//        }
//        // 不符合条件的给出提示信息，并转发到登录页面
//        request.setAttribute("msg", "您还没有登录，请先登录！");
//        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
//                .forward(request, response);
//        return false;
//    }
//}
