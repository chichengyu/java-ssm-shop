package cm.xiaochi.ssm.service;

import cm.xiaochi.ssm.pojo.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> selectAll();
}
