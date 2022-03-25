package com.example.myblog.service;

import com.example.myblog.entity.FriendLink;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/22 17:34
 * qq:546359148
 *
 * 友链业务层接口
 */
public interface FriendLinkService {

    /*查询所有的友链*/
    List<FriendLink> listFriendLink();

    /*友链新增*/
    int saveFriendLink(FriendLink friendLink);

    /*根据网址查询友链*/
    FriendLink getFriendLinkByBlogaddress(String blogAddress);

    /*根据id查询友链*/
    FriendLink getFriendLink(Long id);

    /*编辑修改友链*/
    int updateFriendLink(FriendLink friendLink);

    /*删除友链*/
    void deleteFriendLink(Long id);
}
