package cm.xiaochi.ssm.service.impl;

import cm.xiaochi.ssm.common.constart.SysuserConstant;
import cm.xiaochi.ssm.common.exception.SysuserNotExistException;
import cm.xiaochi.ssm.dao.SysuserDao;
import cm.xiaochi.ssm.params.SysuserParams;
import cm.xiaochi.ssm.pojo.Role;
import cm.xiaochi.ssm.pojo.Sysuser;
import cm.xiaochi.ssm.service.SysuserService;
import cm.xiaochi.ssm.vo.SysyuserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/* 强制使用事务，有任何异常都回滚 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuserDao sysuserDao;

    /**
     * 查询所有系统用户
     * @return
     */
    /* 配置查询的方法支持事务，并且为只读 */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Sysuser> selectAll() {
        return sysuserDao.selectAll();
    }

    /**
     * 根据id查询系统用户
     * @param id
     * @return
     */
    @Override
    public Sysuser selectById(int id) {
        return sysuserDao.selectById(id);
    }

    /**
     * 添加系统用户
     * @param sysyuserVo
     */
    @Override
    public void insert(SysyuserVo sysyuserVo) {
        Sysuser sysuser = new Sysuser();
        try {
            PropertyUtils.copyProperties(sysuser,sysyuserVo);

            // 默认为有效
            sysuser.setIsValid(SysuserConstant.SYSUSER_VALID);
            // 角色ID
            Role role = new Role();
            role.setId(sysyuserVo.getRoleId());
            sysuser.setRole(role);
            // 创建时间为当前时间
            sysuser.setCreateDate(new Date());

            // 入库
            sysuserDao.insert(sysuser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改系统用户
     * @param sysyuserVo
     */
    @Override
    public void update(SysyuserVo sysyuserVo) {

        // sysuserDao.update();
    }

    /**
     * 更新系统用户账号
     * @param id
     */
    @Override
    public void updateStatus(int id) {
        Sysuser sysuser = sysuserDao.selectById(id);
        Integer valid = sysuser.getIsValid();
        if (valid == SysuserConstant.SYSUSER_VALID){
            valid = SysuserConstant.SYSUSER_INVALID;
        }else{
            valid = SysuserConstant.SYSUSER_VALID;
        }
        sysuserDao.updateStatus(id,valid);
    }

    /**
     * 多条件查询
     * @param sysuserParams
     * @return
     */
    @Override
    public List<Sysuser> selectByParams(SysuserParams sysuserParams) {
        return sysuserDao.selectByParams(sysuserParams);
    }

    /**
     * 登录查询
     * @param loginName
     * @param password
     */
    @Override
    public Sysuser selectByLoginNameAndPassword(String loginName, String password) throws SysuserNotExistException {
        Sysuser sysuser = sysuserDao.selectByLoginNameAndPassword(loginName, password, SysuserConstant.SYSUSER_VALID);
        if (sysuser != null){
            return sysuser;
        }
        throw new SysuserNotExistException("用户名或密码不正确");
    }
}
