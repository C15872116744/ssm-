package cmj.web.servlet;

import cmj.domain.SysUser;
import cmj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpSession;

/**
 * @author cmj
 * @create 2020-04-26 15:17
 */
@Controller
public class UserServlet {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(String usercode, String password, Model model, HttpSession httpSession){
        SysUser user = null;

        if(usercode!=null && password!=null){
            user= userService.login(usercode, password);
        }
        if(user!=null){

            httpSession.setAttribute("USER_SESSION",user);
            return "redirect:/customer/list.action";
//            return "customer";
        }
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }
    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String login() {
        return "login";

    }
    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.action";
    }
}
