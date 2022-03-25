package com.example.myblog.dao;

import com.example.myblog.entity.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/22 16:10
 * qq:546359148
 */
@Repository
public interface FriendLinkDao {

    /*查询友链管理列表*/
    List<FriendLink> listFriendLink();

    /*新增友链*/
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
