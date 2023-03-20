package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CommentDAO extends BaseDAO<Comment> {
    public CommentDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }


}
