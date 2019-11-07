package cm.xiaochi.ssm.service.impl;

import cm.xiaochi.ssm.common.constart.ProductTypeConstant;
import cm.xiaochi.ssm.common.exception.ProductTypeException;
import cm.xiaochi.ssm.dao.ProductTypeDao;
import cm.xiaochi.ssm.pojo.ProductType;
import cm.xiaochi.ssm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/* 强制使用事务，有任何异常都回滚 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProductTypeServicveImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    /**
     * 查询所有商品类型
     * @return List<ProductType>
     */
    /* 配置查询的方法支持事务，并且为只读 */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<ProductType> selectAll() {
        return productTypeDao.selectAll();
    }

    /**
     *  添加商品类型
     * @param name
     * @throws ProductTypeException
     */
    @Override
    public void add(String name) throws ProductTypeException {
        ProductType productType = productTypeDao.selectByName(name);
        if (productType != null){
            throw new ProductTypeException("商品类型已存在！");
        }
        productTypeDao.insert(name, ProductTypeConstant.Product_TYPE_ENABLE);
    }

    /**
     * 根据id查询商品类型
     * @param id
     * @return
     */
    @Override
    public ProductType selectById(int id) {
        return productTypeDao.selectById(id);
    }

    /**
     * 修改商品类型
     */
    @Override
    public void edit(int id,String name) throws ProductTypeException {
        productTypeDao.updateName(id,name);
    }

    /**
     * 删除商品类型
     * @param id
     */
    @Override
    public void del(int id) throws ProductTypeException {
        productTypeDao.deleteById(id);
    }

    /**
     * 更新商品类型状态
     * @param id
     * @throws ProductTypeException
     */
    public void editStatus(int id) throws ProductTypeException{
        ProductType productType = productTypeDao.selectById(id);
        int status = productType.getStatus();
        if (status == ProductTypeConstant.Product_TYPE_ENABLE){
            status = ProductTypeConstant.Product_TYPE_DISABLE;
        }else{
            status = ProductTypeConstant.Product_TYPE_ENABLE;
        }
        productTypeDao.updateStatus(id, status);
    }
}
