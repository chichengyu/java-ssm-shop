package cm.xiaochi.ssm.service.impl;

import cm.xiaochi.ssm.dao.RoleDao;
import cm.xiaochi.ssm.pojo.Role;
import cm.xiaochi.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* 强制使用事务，有任何异常都回滚 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    /* 配置查询的方法支持事务，并且为只读 */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }
}
