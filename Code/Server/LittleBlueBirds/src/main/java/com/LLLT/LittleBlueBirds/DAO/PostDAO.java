package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.Post;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

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
    public void Update (Post post) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
            put("Title"  , post.getTitle()  );
            put("Content", post.getContent());
            put("Img"    , post.getImg()    );
        }};
        super.UpdateByKey("PostId", post.getPostId(), post.getHolder() + this.Suffix, hashMap);
    }
}
