package com.example.myblog.controller.admin;

import com.example.myblog.entity.Type;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/17 23:43
 * qq:546359148
 *
 * 类型控制器
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    private TypeService typeService;
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    /**
     * 分页查询分类列表
     * @param model 模型对象
     * @param pageNum 页码
     * @return 跳转到类型页
     */
    @RequestMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum){
        //按照排序字段 倒序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println("pageInfo:"+pageInfo);
        return "admin/types";
    }

    /**
     * 返回新增分类页面
     * @param model 数据模型
     * @return 新增分类页面
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /***
     * 新增分类
     * @param type 新增的类型
     * @param attributes 重定向的属性
     * @return 新增页面
     *
     * attributes.addFlashAttribute：相当于重定向后，
     * 在URL后面拼接了参数，这样在重定向之后的页面后者控制器再去获取URL后年的参数就可以了
     */
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            attributes.addFlashAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }

        //新增类型不为空，再保存类型
        int t = typeService.saveType(type);
        if(t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        //返回到重定向到类型页
        return "redirect:/admin/types";
    }

    /**
     * 跳转到编辑页面 get请求方式
     * @param id 待编辑的分类
     * @param model 保存分类数据的模型对象
     * @return 返回到编辑分类页面
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        //通过id查询到分类，并将这个分类信息保存到model对象里面
        model.addAttribute("type",typeService.getType(id));
        //返回到type-input页面
        return "admin/types-input";
    }

    /**
     * 编辑修改分类
     * @param type 待编辑的分类信息
     * @param attributes 属性
     * @return 返回到types页面
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,RedirectAttributes attributes){
        //1.先通过分类名查询整个分类信息
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            //这个分类已经存在数据库中了
            attributes.addFlashAttribute("message","不能添加重复的分类");
            //重定向到输入分类页
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if(t == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else{
            attributes.addFlashAttribute("message","编辑成功");
        }
        //重定向到分类页
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id 被删除分类的id
     * @param attributes 模型数据
     * @return 返回到分类列表
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }






}
