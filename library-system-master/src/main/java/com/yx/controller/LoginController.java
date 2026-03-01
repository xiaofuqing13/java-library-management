//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yx.controller;

import com.yx.codeutil.IVerifyCodeGen;
import com.yx.codeutil.SimpleCharVerifyCodeGenImpl;
import com.yx.codeutil.VerifyCode;
import com.yx.po.Admin;
import com.yx.po.ReaderInfo;
import com.yx.service.AdminService;
import com.yx.service.ReaderInfoService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ReaderInfoService readerService;

    public LoginController() {
    }

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/verifyCode"})
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();

        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            request.getSession().setAttribute("VerifyCode", code);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException var6) {
            System.out.println("异常处理");
        }

    }

    @RequestMapping({"/loginIn"})
    public String loginIn(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("captcha");
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        String realCode = (String)session.getAttribute("VerifyCode");
        if (!realCode.toLowerCase().equals(code.toLowerCase())) {
            model.addAttribute("msg", "验证码不正确");
            return "login";
        } else {
            if (type.equals("1")) {
                Admin admin = this.adminService.queryUserByNameAndPassword(username, password);
                if (admin == null) {
                    model.addAttribute("msg", "用户名或密码错误");
                    return "login";
                }

                session.setAttribute("user", admin);
                session.setAttribute("type", "admin");
            } else {
                ReaderInfo readerInfo = this.readerService.queryUserInfoByNameAndPassword(username, password);
                if (readerInfo == null) {
                    model.addAttribute("msg", "用户名或密码错误");
                    return "login";
                }

                session.setAttribute("user", readerInfo);
                session.setAttribute("type", "reader");
            }

            return "index";
        }
    }

    @GetMapping({"loginOut"})
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/login";
    }
}