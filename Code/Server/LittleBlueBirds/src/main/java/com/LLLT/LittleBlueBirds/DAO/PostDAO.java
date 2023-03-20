package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.Post;
import org.springframework.data.mongodb.core.MongoTemplate;

public class PostDAO extends BaseDAO<Post> {

    private final String Suffix = "_Post";
    public PostDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public Post findOneById (String PostId, String Holder) {
        return super.findOneByKey("PostId", PostId, Holder + this.Suffix);
    }

    public void Save (Post post) {
        super.Save(post, post.getHolder() + this.Suffix);
    }
}
