package cm.xiaochi.ssm.dao;

import cm.xiaochi.ssm.pojo.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> selectAll();
}
