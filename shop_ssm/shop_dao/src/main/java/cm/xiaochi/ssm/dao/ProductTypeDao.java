package cm.xiaochi.ssm.dao;

import cm.xiaochi.ssm.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao {

    /**
     * 查询所有商品类型
     */
    public List<ProductType> selectAll();

    /**
     * 根据id查询商品类型
     */
    public ProductType selectById(int id);

    /**
     * 根据name查询商品类型
     */
    public ProductType selectByName(String name);

    /**
     * 添加商品类型
     */
    public void insert(@Param("name")String name,@Param("status")int status);

    /**
     * 更新商品类型名称
     */
    public void updateName(@Param("id")int id,@Param("name")String name);

    /**
     * 更新商品类型状态
     */
    public void updateStatus(@Param("id")int id,@Param("status")int status);

    /**
     * 删除商品类型
     */
    public void deleteById(@Param("id")int id);

    /**
     * 查询所有有效的分类
     * @return
     */
    public List<ProductType> findEnable(int status);
}
