package cm.xiaochi.ssm.controller;

import cm.xiaochi.ssm.common.constart.PagenationConstart;
import cm.xiaochi.ssm.common.exception.ProductTypeException;
import cm.xiaochi.ssm.common.utils.ResponseResult;
import cm.xiaochi.ssm.pojo.ProductType;
import cm.xiaochi.ssm.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 商品类型列表
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        /* 分页 */
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = PagenationConstart.PAGE_NUM;
        }
        // 设置分页
        PageHelper.startPage(pageNum,PagenationConstart.PAGE_SIZE);
        // 查询数据
        List<ProductType> productTypes = productTypeService.selectAll();
        // 将查询出的结构封装到 PageInfo 对象中
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "productTypeManager";
    }

    /**
     * 添加商品类型
     * 返回一个 json
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        try {
            productTypeService.add(name);
            return ResponseResult.responseSuccess("添加成功");
        } catch (ProductTypeException e) {
            // e.printStackTrace();
            return ResponseResult.responseError(e.getMessage());
        }
    }

    /**
     * 根据id查询商品类型
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ProductType productType = productTypeService.selectById(id);
        return ResponseResult.responseSuccess(productType);
    }

    /**
     * 修改商品类型
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult edit(int id,String name){
        try {
            productTypeService.edit(id,name);
            return ResponseResult.responseSuccess("修改成功");
        } catch (ProductTypeException e) {
            // e.printStackTrace();
            return ResponseResult.responseError(e.getMessage());
        }
    }

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult del(int id){
        try {
            productTypeService.del(id);
            return ResponseResult.responseSuccess("删除成功");
        } catch (ProductTypeException e) {
            // e.printStackTrace();
            return ResponseResult.responseError(e.getMessage());
        }
    }

    /**
     * 更新商品类型状态
     * @param id
     * @return
     */
    @RequestMapping("/editStatus")
    @ResponseBody
    public ResponseResult editStatus(int id){
        try {
            productTypeService.editStatus(id);
            return ResponseResult.responseSuccess("更新成功");
        } catch (ProductTypeException e) {
            // e.printStackTrace();
            return ResponseResult.responseError(e.getMessage());
        }
    }
}
