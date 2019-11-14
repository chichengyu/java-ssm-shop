package cm.xiaochi.ssm.controller;

import cm.xiaochi.ssm.common.constart.PagenationConstart;
import cm.xiaochi.ssm.common.utils.ResponseResult;
import cm.xiaochi.ssm.dto.ProductDto;
import cm.xiaochi.ssm.pojo.Product;
import cm.xiaochi.ssm.pojo.ProductType;
import cm.xiaochi.ssm.service.ProductService;
import cm.xiaochi.ssm.service.ProductTypeService;
import cm.xiaochi.ssm.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 点击添加商品时，查询所有商品分类数据
     * @return
     */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes(){
        return productTypeService.findEnable();
    }

    /**
     * 验证商品名称是否存在
     * @param name
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String,Object> checkName(String name){
        HashMap<String, Object> map = new HashMap<>();
        if (productService.checkName(name)){
            map.put("valid",true);
        }else{
            map.put("valid",false);
            map.put("message","商品（"+ name +"）已存在");
        }
        return map;
    }

    /**
     * 添加商品
     * @param productVo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(ProductVo productVo,HttpSession session,Model model,Integer pageNum){
        // 上传路径 用 session 获取
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            // 将 vo 转化为 dto
            ProductDto productDto = new ProductDto();
            // productDto.setName(productVo.getName());// 一个个写太麻烦，用工具 commons-beanutils
            // 将 vo 对象的属性复制到 dto
            PropertyUtils.copyProperties(productDto,productVo);
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);

            productService.add(productDto);
            model.addAttribute("successMsg","添加成功");
        } catch (Exception e) {
            // e.printStackTrace();
            model.addAttribute("errorMsg","添加失败" + e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    /**
     * 查询所有商品
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        // 分页
        if (pageNum == null){
            pageNum = PagenationConstart.PAGE_NUM;
        }
        PageHelper.startPage(pageNum,PagenationConstart.PAGE_SIZE);
        // 查询数据
        List<Product> products = productService.selectAll();

        PageInfo<Product> productPageInfo = new PageInfo<Product>(products);

        model.addAttribute("productPageInfo",productPageInfo);
        return "productManager";
    }

    /**
     * 查询一件商品
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id){
        return ResponseResult.responseSuccess(productService.selectById(id));
    }

    /**
     * 修改商品
     * @param productVo
     * @param session
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/edit")
    public String edit(ProductVo productVo,HttpSession session,Model model,Integer pageNum){
        // 获取上传路径
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            // 将 vo 转化为 dto
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto,productVo);
            productDto.setUploadPath(uploadPath);
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());

            productService.edit(productDto);
            model.addAttribute("successMsg","修改成功");
        } catch (Exception e) {
            // e.printStackTrace();
            model.addAttribute("errorMsg",e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult delete(int id){
        productService.delete(id);
        return ResponseResult.responseSuccess();
    }
}
