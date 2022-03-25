package com.example.myblog.controller.admin;

import com.example.myblog.entity.FriendLink;
import com.example.myblog.service.FriendLinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * author: zy
 * date: 2022/3/22 17:44
 * qq:546359148
 *
 * 友链后台管理控制器
 */
@Controller
@RequestMapping("/admin")
public class FriendController {

    private FriendLinkService friendLinkService;
    public FriendController(FriendLinkService friendLinkService) {
        this.friendLinkService = friendLinkService;
    }

    /**
     * 将所有的友链都遍历出来
     * @param model 模型对象
     * @param pageNum 当前页码
     * @return 返回到friendlinks页面
     */
    @GetMapping("/friendlinks")
    public String friend(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<FriendLink> listFriendLinks = friendLinkService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<>(listFriendLinks);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendlinks";
    }

    /**
     * 跳转到友链新增页面
     * @param model 存放数据的对象
     * @return 返回到friendlinks-input页面
     */
    @GetMapping("/friendlinks/input")
    public String input(Model model){
        model.addAttribute("friendlink",new FriendLink());
        return "/admin/friendlinks-input";
    }

    /**
     * 友链新增,使用post请求方式
     * @param friendLink 新增的友链
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/friendlinks")
    public String post(@Valid FriendLink friendLink,
                       BindingResult result,
                       RedirectAttributes attributes){
        FriendLink type1 = friendLinkService.getFriendLinkByBlogaddress(friendLink.getBlogAddress());
        if(type1 != null){
            attributes.addFlashAttribute("message","不能添加相同的网址");
            //如果博客网址重复，就重定向到新增页面，并携带提示信息
            return "redirect:/admin/friendlinks/input";
        }
        if(result.hasErrors()){
            return "admin/friendlinks-input";
        }

        /*这里给friendLink设置创建时间*/
        friendLink.setCreateTime(new Date());

        int F = friendLinkService.saveFriendLink(friendLink);
        if(F==0){
            attributes.addFlashAttribute("message","新增失败!");
        }else{
            attributes.addFlashAttribute("message","新增成功!");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * 跳转到友链修改页面
     * @param id 待修改的友链id
     * @param model 存放友链的模型对象
     * @return 返回到友链输入页
     */
    @GetMapping("/friendlinks/{id}/input")
    public String editInput(@PathVariable("id")Long id,Model model){
        /*通过id查询出整个友链对象,并存放到friendlink对象里面*/
        model.addAttribute("friendlink",friendLinkService.getFriendLink(id));
        return "admin/friendlinks-input";
    }

    /**
     * 编辑修改友链
     * @param friendLink 待编辑的友链
     * @param attributes 属性
     * @return 重定向到admin/friendlinks
     */
    @PostMapping("/friendlinks/{id}")
    public String editPost(@Valid FriendLink friendLink,
                           RedirectAttributes attributes){
        /*这里给friendLink设置创建时间*/
        friendLink.setCreateTime(new Date());
        int t = friendLinkService.updateFriendLink(friendLink);
        if(t == 0){
            attributes.addFlashAttribute("message","编辑失败!");
        }else{
            attributes.addFlashAttribute("message","编辑成功!");
        }



        return "redirect:/admin/friendlinks";
    }

    /**
     * 删除友链
     * @param id 待删除的友链
     * @param attributes 属性
     * @return 重定向回到friendlinks请求中
     */
    @GetMapping("/friendlinks/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes){
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/friendlinks";
    }
}
