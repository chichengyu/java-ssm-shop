package cm.xiaochi.ssm.service;

import cm.xiaochi.ssm.common.exception.SysuserNotExistException;
import cm.xiaochi.ssm.params.SysuserParams;
import cm.xiaochi.ssm.pojo.Sysuser;
import cm.xiaochi.ssm.vo.SysyuserVo;

import java.util.List;

public interface SysuserService {

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
     * @param sysyuserVo
     */
    public void insert(SysyuserVo sysyuserVo);

    /**
     * 修改系统用户
     * @param sysyuserVo
     */
    public void update(SysyuserVo sysyuserVo);

    /**
     * 更新系统用户状态
     * @param id
     */
    public void updateStatus(int id);


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
     */
    public Sysuser selectByLoginNameAndPassword(String loginName, String password) throws SysuserNotExistException;
}
