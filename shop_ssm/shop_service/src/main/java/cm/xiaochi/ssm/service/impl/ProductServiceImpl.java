package cm.xiaochi.ssm.service.impl;

import cm.xiaochi.ssm.common.utils.StringUtils;
import cm.xiaochi.ssm.dao.ProductDao;
import cm.xiaochi.ssm.dto.ProductDto;
import cm.xiaochi.ssm.pojo.Product;
import cm.xiaochi.ssm.pojo.ProductType;
import cm.xiaochi.ssm.service.ProductService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* 强制使用事务，有任何异常都回滚 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 根据 name 查询商品
     * @param name
     */
    @Override
    public Boolean checkName(String name) {
        if (productDao.checkName(name) != null){
            return false;// 名称已存在，不能添加
        }
        return true;
    }

    /**
     * 添加商品
     */
    @Override
    public void add(ProductDto productDto) throws FileUploadException {
        // 1.文件上传
        // 文件重命名
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String fileUploadPath = productDto.getUploadPath() + '/' + fileName;
        try {
            // 上传
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(fileUploadPath));
        } catch (IOException e) {
            // e.printStackTrace();
            throw new FileUploadException("文件上传失败" + e.getMessage());
        }
        // 2.入库  把 dto 传递过来的数据设置到 Product javaBean中
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setImage(fileUploadPath);

            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);

            productDao.insert(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有商品
     * @return
     */
    /* 配置查询的方法支持事务，并且为只读 */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    /**
     * 根据Id查询一件商品
     * @param id
     * @return
     */
    @Override
    public Product selectById(int id) {
        return productDao.selectById(id);
    }

    /**
     * 修改商品
     * @param productDto
     */
    @Override
    public void edit(ProductDto productDto) throws FileUploadException {
        // 1.文件上传
        // 文件重命名
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String fileUploadPath = productDto.getUploadPath() + '/' + fileName;
        try {
            // 上传
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(fileUploadPath));
        } catch (IOException e) {
            // e.printStackTrace();
            throw new FileUploadException("文件上传失败" + e.getMessage());
        }
        // 2.入库  把 dto 传递过来的数据设置到 Product javaBean中
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setImage(fileUploadPath);
            product.setPrice(productDto.getPrice());

            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);

            productDao.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品
     * @param id
     */
    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
