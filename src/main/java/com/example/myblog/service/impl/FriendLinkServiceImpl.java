package com.example.myblog.service.impl;

import com.example.myblog.dao.FriendLinkDao;
import com.example.myblog.entity.FriendLink;
import com.example.myblog.service.FriendLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: zy
 * date: 2022/3/22 17:39
 * qq:546359148
 *
 * 友链业务接口实现类
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    private FriendLinkDao friendLinkDao;
    public FriendLinkServiceImpl(FriendLinkDao friendLinkDao){
        this.friendLinkDao=friendLinkDao;
    }

    /**
     * 查询出所有的友链
     * @return 返回所有的友链
     */
    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkDao.listFriendLink();
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return friendLinkDao.saveFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogAddress) {
        return friendLinkDao.getFriendLinkByBlogaddress(blogAddress);
    }

    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkDao.getFriendLink(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkDao.updateFriendLink(friendLink);
    }

    @Override
    public void deleteFriendLink(Long id) {
        friendLinkDao.deleteFriendLink(id);
    }
}
