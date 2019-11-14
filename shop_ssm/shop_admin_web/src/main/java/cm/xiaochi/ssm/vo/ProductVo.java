package cm.xiaochi.ssm.vo;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

/** 视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来
 * 接收表单传递的数据
 */
public class ProductVo {
    /* 与表单字段对应 */
    private Integer id;
    private String name;
    private Double price;
    private CommonsMultipartFile file;// 接收上传的文件对象，spnringmvc封装的
    private Integer productTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
