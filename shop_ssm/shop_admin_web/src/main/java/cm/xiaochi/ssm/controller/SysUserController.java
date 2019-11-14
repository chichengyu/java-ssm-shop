package cm.xiaochi.ssm.controller;

import cm.xiaochi.ssm.common.constart.PagenationConstart;
import cm.xiaochi.ssm.common.exception.SysuserNotExistException;
import cm.xiaochi.ssm.common.utils.ResponseResult;
import cm.xiaochi.ssm.params.SysuserParams;
import cm.xiaochi.ssm.pojo.Role;
import cm.xiaochi.ssm.pojo.Sysuser;
import cm.xiaochi.ssm.service.RoleService;
import cm.xiaochi.ssm.service.SysuserService;
import cm.xiaochi.ssm.vo.SysyuserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/sysuser")
public class SysUserController {

    @Autowired
    private SysuserService sysuserService;
    @Autowired
    private RoleService roleService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session,Model model){
        try {
            Sysuser sysuser = sysuserService.selectByLoginNameAndPassword(loginName, password);
            session.setAttribute("sysuser",sysuser);
            return "main";
        } catch (SysuserNotExistException e) {
            // e.printStackTrace();
            model.addAttribute("errorMsg",e.getMessage());
            return "login";
        }
    }

    /**
     * 查询所有角色
     * @return
     */
    @ModelAttribute("roles")
    public List<Role> loadRoles(){
        return roleService.selectAll();
    }

    /**
     * 系统用户列表
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model,Integer pageNum){
        // 分页
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = PagenationConstart.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PagenationConstart.PAGE_SIZE);
        // 查询数据
        List<Sysuser> sysusers = sysuserService.selectAll();
        PageInfo<Sysuser> sysuserPageInfo = new PageInfo<Sysuser>(sysusers);
        model.addAttribute("sysuserPageInfo",sysuserPageInfo);
        return "sysuserManager";
    }

    /**
     * 多条件查询
     * @return
     */
    @RequestMapping("/findByParams")
    public String findByParams(Model model,SysuserParams sysuserParams,Integer pageNum){
        // 分页
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = PagenationConstart.PAGE_NUM;
        }
        PageHelper.startPage(pageNum,PagenationConstart.PAGE_SIZE);
        List<Sysuser> sysusers = sysuserService.selectByParams(sysuserParams);
        PageInfo<Sysuser> sysuserPageInfo = new PageInfo<>(sysusers);
        model.addAttribute("sysuserPageInfo",sysuserPageInfo);
        model.addAttribute("sysuserParam",sysuserParams);
        return "sysuserManager";
    }

    /**
     * 添加系统用户
     * @param sysyuserVo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(SysyuserVo sysyuserVo){
        try{
            sysuserService.insert(sysyuserVo);
            return ResponseResult.responseSuccess("添加成功");
        }catch (Exception e){
            return ResponseResult.responseError(e.getMessage());
        }
    }

    /**
     * 更新系统状态
     * @param id
     * @return
     */
    @RequestMapping("/editStatus")
    @ResponseBody
    public ResponseResult editStatus(Integer id){
        sysuserService.updateStatus(id);
        return ResponseResult.responseSuccess();
    }

}
