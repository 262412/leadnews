package com.heima.article.service;

import com.heima.model.article.dots.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {
    public ResponseResult load(ArticleHomeDto dto, Short type);
}