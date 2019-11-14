package cm.xiaochi.ssm.service;

import cm.xiaochi.ssm.dto.ProductDto;
import cm.xiaochi.ssm.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    /**
     * 根据 name 查询商品
     * @param name
     */
    Boolean checkName(String name);

    /**
     * 添加商品
     */
    public void add(ProductDto productDto) throws FileUploadException;

    /**
     * 查询所有商品
     */
    public List<Product> selectAll();

    /**
     * 根据id查询一件商品
     * @param id
     * @return
     */
    public Product selectById(int id);

    /**
     * 修改商品
     * @param productDto
     */
    public void edit(ProductDto productDto) throws FileUploadException;

    /**
     * 删除商品
     * @param id
     */
    public void delete(int id);
}
