package com.heima.article.service.impl;

import com.heima.common.Constants.ArticleConstants;
import com.heima.model.article.dots.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    // 自动注入ApArticleMapper，用于后续的文章列表加载
    @Autowired
    private ApArticleMapper apArticleMapper;
    // 定义最大页面大小的常量，避免一次性加载过多数据
    private final static Short MAX_PAGE_SIZE = 50;

    /**
     * 加载文章列表的公共方法
     * 该方法根据用户请求的类型和参数，从数据库中加载相应文章列表
     *
     * @param dto 文章首页请求参数封装对象，包含分页和筛选条件
     * @param type 请求类型，决定是加载更多还是加载最新文章
     * @return 返回包含文章列表的响应结果
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        // 初始化分页大小，如果未设置则默认为10
        Integer size = dto.getSize();
        if(size == null || size == 0){
            size = 10;
        }
        // 限制分页大小不超过最大页面大小
        size = Math.min(size, MAX_PAGE_SIZE);
        // 修正文章加载类型，仅当请求类型为加载最新时，才保持不变，否则默认为加载更多
        if(!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        // 初始化标签，如果未设置则使用默认标签
        if(StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        // 初始化最大发布时间，如果未设置则为当前时间
        if(dto.getMaxBehotTime() == null){
            dto.setMaxBehotTime(new Date());
        }
        // 初始化最小发布时间，如果未设置则为当前时间
        if(dto.getMinBehotTime() == null){
            dto.setMinBehotTime(new Date());
        }
        // 调用Mapper方法加载文章列表
        List<ApArticle> articleList = apArticleMapper.loadArticleList(dto,type);
        // 返回包含文章列表的响应结果
        return ResponseResult.okResult(articleList);
    }
}
