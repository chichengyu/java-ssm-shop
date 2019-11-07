package cm.xiaochi.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class SysUserController {

    @RequestMapping("/login")
    public String login(){
        return "main";
    }
}
