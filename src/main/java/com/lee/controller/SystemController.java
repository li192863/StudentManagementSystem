package com.lee.controller;

import com.lee.bean.Admin;
import com.lee.bean.LoginForm;
import com.lee.bean.Student;
import com.lee.bean.Teacher;
import com.lee.service.AdminService;
import com.lee.service.StudentService;
import com.lee.service.TeacherService;
import com.lee.util.CreateVerificationCodeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 公有控制类
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    // 注入业务对象
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    // 存储预返回给页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到用户登录页面
     * @return
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "system/login";
    }

    /**
     * 获取并显示验证码图片
     * @param request
     * @param response
     */
    @GetMapping("/getVerificationCodeImage")
    public void getVerificationCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 验证码图片
        BufferedImage verificationCodeImage = CreateVerificationCodeImage.getVerificationCodeImage();
        // 验证码
        String verificationCode = new String(CreateVerificationCodeImage.getVerificationCode());
        // 将验证码图片输出到登录页面
        try {
            ImageIO.write(verificationCodeImage, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 存储验证码Session
        request.getSession().setAttribute("verificationCode", verificationCode);
    }

    /**
     * 验证用户登录信息
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> login(LoginForm loginForm, HttpServletRequest request) {
        // 校验验证码信息
        String vcode = (String) request.getSession().getAttribute("verificationCode");
        if ("".equals(vcode)) {
            result.put("success", false);
            result.put("msg", "长时间未操作, 会话已失效, 请刷新页面后再试!");
            return result;
        } else if (!loginForm.getVerificationCode().equalsIgnoreCase(vcode)) {
            result.put("success", false);
            result.put("msg", "验证码输入错误");
            return result;
        }
        request.getSession().removeAttribute("verificationCode");

        // 效验用户登录信息
        switch (loginForm.getUserType()) {
            //管理员身份
            case 1:
                try {
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", admin);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "服务器异常, 登录失败!");
                    return result;
                }
                break;
            // 学生身份
            case 2:
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", student);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "服务器异常, 登录失败!");
                    return result;
                }
                break;
            // 教师身份
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", teacher);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "服务器异常, 登录失败!");
                    return result;
                }
                break;
        }
        result.put("success", false);
        result.put("msg", "用户名或密码错误");
        return result;
    }

    /**
     * 跳转到系统主页面
     * @return
     */
    @GetMapping("/goSystemMainView")
    public String goSystemMainView() {
        return "system/main";
    }

    /**
     * 注销用户信息
     * @param request
     * @param response
     */
    @GetMapping("/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userInfo");
        request.getSession().removeAttribute("userType");

        // 注销后重定向到登录页面
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
