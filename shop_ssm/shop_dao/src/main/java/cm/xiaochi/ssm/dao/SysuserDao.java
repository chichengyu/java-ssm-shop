package cm.xiaochi.ssm.dao;

import cm.xiaochi.ssm.params.SysuserParams;
import cm.xiaochi.ssm.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysuserDao {

    /**
     * 查询所有系统用户
     * @return
     */
    public List<Sysuser> selectAll();

    /**
     * 根据id查询系统用户
     * @param id
     * @return
     */
    public Sysuser selectById(int id);

    /**
     * 添加系统用户
     * @param sysuser
     */
    public void insert(Sysuser sysuser);

    /**
     * 修改系统用户
     * @param sysuser
     */
    public void update(Sysuser sysuser);

    /**
     * 更新用户状态
     * @param id
     * @param isValid
     */
    public void updateStatus(@Param("id")int id,@Param("isValid")int isValid);

    /**
     * 多条件查询
     * @param sysuserParams
     * @return
     */
    public List<Sysuser> selectByParams(SysuserParams sysuserParams);

    /**
     * 登录查询
     * @param loginName
     * @param password
     * @param isValid
     * @return
     */
    public Sysuser selectByLoginNameAndPassword(@Param("loginName")String loginName,@Param("password")String password,@Param("isValid")int isValid);
}
