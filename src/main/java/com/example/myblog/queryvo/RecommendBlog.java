package com.example.myblog.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: zy
 * date: 2022/3/23 3:32
 * qq:546359148
 *
 * 最新推荐实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;
}
