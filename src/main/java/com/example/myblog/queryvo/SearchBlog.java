package com.example.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: zy
 * date: 2022/3/22 1:22
 * qq:546359148
 *
 * 搜索博客管理列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {

    private String title;
    private Long typeId;
}
