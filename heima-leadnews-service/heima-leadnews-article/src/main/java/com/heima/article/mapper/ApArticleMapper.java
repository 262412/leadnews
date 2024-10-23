package com.heima.article.mapper;

import com.heima.model.article.dots.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    public List<ApArticle> loadArticleList(ArticleHomeDto dto, Short type);
}
