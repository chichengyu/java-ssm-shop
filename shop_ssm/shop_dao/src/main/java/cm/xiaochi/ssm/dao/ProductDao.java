package cm.xiaochi.ssm.dao;

import cm.xiaochi.ssm.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 根据 name 查询商品是否存在
     * @param name
     * @return
     */
    public Product checkName(String name);

    /**
     * 添加商品
     * @param product
     */
    public void insert(Product product);

    /**
     * 查询所有商品
     */
    public List<Product> selectAll();

    /**
     * 根据id查询一件商品
     * @param id
     */
    public Product selectById(@Param("id") int id);

    /**
     * 修改商品
     * @param product
     */
    public void update(Product product);

    /**
     * 删除商品
     * @param id
     */
    public void delete(int id);
}
