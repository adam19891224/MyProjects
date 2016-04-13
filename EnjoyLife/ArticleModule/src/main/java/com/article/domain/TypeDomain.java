package com.article.domain;

import com.article.dao.TypeMapper;
import com.article.vo.Type;
import com.foundation.view.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/20
 */
@Component
public class TypeDomain extends Type {

    @Autowired
    private TypeMapper typeMapper;

    public List<Tree> selectToTree(){
        return typeMapper.selectToTree();
    }

}
