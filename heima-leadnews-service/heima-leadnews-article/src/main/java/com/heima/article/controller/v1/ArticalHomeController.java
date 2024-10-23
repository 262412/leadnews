package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.common.Constants.ArticleConstants;
import com.heima.model.article.dots.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")  // 文章首页数据查询接口  /api/v1/home/load  文章列表查询接口  /api/v1/home/load_list  文章详情查询接口  /api/v1/home/detail  文章点赞接口  /api/v1/home/like  文章收藏接口  /api/v1/home/collect  文章评论列表 接口  /api/v1/home/comment
public class ArticalHomeController {
// 自动注入ApArticleService，用于处理文章相关业务
@Autowired
private ApArticleService apArticleService;

/**
 * 加载文章列表接口
 * 该接口用于根据用户请求加载文章列表，采用的是加载更多的方式
 *
 * @param dto 包含加载请求信息的DTO对象，如用户ID、文章类别等
 * @return 返回文章列表的响应结果，包括文章信息和加载状态
 */
@PostMapping("/load")
public ResponseResult load(@RequestBody ArticleHomeDto dto) {
    return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
}

/**
 * 加载更多文章列表接口
 * 该接口用于在现有文章列表的基础上，加载更多文章，通常用于滚动加载场景
 *
 * @param dto 包含加载请求信息的DTO对象，如用户ID、文章类别等
 * @return 返回更多文章列表的响应结果，包括新加载的文章信息和加载状态
 */
@PostMapping("/loadmore")
public ResponseResult loadmore(@RequestBody ArticleHomeDto dto) {
    return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
}

/**
 * 加载最新文章列表接口
 * 该接口用于加载最新的文章列表，通常用于刷新页面或查看最新内容的场景
 *
 * @param dto 包含加载请求信息的DTO对象，如用户ID、文章类别等
 * @return 返回最新文章列表的响应结果，包括最新文章信息和加载状态
 */
@PostMapping("/loadnew")
public ResponseResult loadnew(@RequestBody ArticleHomeDto dto) {
    return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_NEW);
}
}

