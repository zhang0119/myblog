package com.example.myblog.controller.admin;

import com.example.myblog.entity.Picture;
import com.example.myblog.service.PictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/23 1:19
 * qq:546359148
 *
 * 照片墙后台管理控制器
 */
@Controller
@RequestMapping("/admin")
public class PictureController {

    private PictureService pictureService;
    public PictureController(PictureService pictureService){
        this.pictureService=pictureService;
    }

    /**
     * 查询照片列表
     * @param model 模型对象
     * @param pageNum 当前页码
     * @return 跳转到pictures页面
     */
    @GetMapping("/pictures")
    public String pictures(Model model,
                           @RequestParam(defaultValue = "1",value="pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Picture> pictures = pictureService.listPicture();
        PageInfo<Picture> pageInfo = new PageInfo<>(pictures);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    /**
     * 跳转到新增页面
     * @param model 存放picture对象的数据域
     * @return 返回到新增页
     */
    @GetMapping("/pictures/input")
    public String input(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    /**
     * 照片新增处理器
     */
    @PostMapping("/pictures")
    public String post(@Valid Picture picture,
                       BindingResult result,
                       RedirectAttributes attributes){
        if(result.hasErrors()){
            return "admin/pictures-input";
        }

        int p = pictureService.savePicture(picture);
        if(p == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 跳转到页面编辑页
     */
    @GetMapping("/pictures/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("picture",pictureService.getPicture(id));
        return "admin/pictures-input";
    }

    /**
     * 编辑相册
     */
    @PostMapping("/pictures/{id}")
    public String editPost(@Valid Picture picture,
                           RedirectAttributes attributes){
        int p = pictureService.updatePicture(picture);
        if(p == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else{
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 删除照片
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes){
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/pictures";
    }

}
