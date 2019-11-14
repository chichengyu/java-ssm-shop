package cm.xiaochi.ssm.service;

import cm.xiaochi.ssm.common.exception.ProductTypeException;
import cm.xiaochi.ssm.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    /**
     * 查询所有商品类型
     */
    public List<ProductType> selectAll();

    /**
     * 添加商品类型
     *   判断商品类型名称是否已存在
     *   如果已存在，抛出异常
     *   如果不存在，则保持
     */
    public void add(String name) throws ProductTypeException;

    /**
     *  根据 id 查询商品类型
     */
    public ProductType selectById(int id);

    /**
     * 修改商品类型
     */
    public void edit(int id,String name) throws ProductTypeException;

    /**
     * 删除商品类型
     */
    public void del(int id) throws ProductTypeException;

    /**
     * 更新商品类型状态
     * @param id
     * @throws ProductTypeException
     */
    public void editStatus(int id) throws ProductTypeException;

    /**
     * 查询所有有效的商品分类
     * @return
     */
    public List<ProductType> findEnable();
}
