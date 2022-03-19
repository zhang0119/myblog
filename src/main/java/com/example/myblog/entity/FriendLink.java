package com.example.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author: zy
 * date: 2022/3/17 15:56
 * qq:546359148
 *
 * 友链实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {

    private Long id;
    private String blogName;
    private String blogAddress;
    private String pictureAddress;
    private Date createTime;
}
