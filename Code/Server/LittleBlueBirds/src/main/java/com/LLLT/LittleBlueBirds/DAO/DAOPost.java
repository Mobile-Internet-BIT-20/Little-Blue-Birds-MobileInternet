package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.Post;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

public class DAOPost extends BaseDAO<Post> {

    public final String CollectionSuffix = "_Post";

    public DAOPost(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    // Create
    public void save (
            Post post
    ) {
        super.Save(post, post.getHolderId() + CollectionSuffix);
    }

    // Retrieve
    public Post findOneById (
            String PID,
            String UID
    ) {
        return super.FindOneByKey("PID", PID, UID + CollectionSuffix);
    }

    // Update
    public void update (
            Post post
    ) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>() {{

            put("PostTitle"  , post.getPostTitle()  );
            put("PostContent", post.getPostContent());
            put("ListImgPost", post.getListPostImg());

            put("TimeLastModify", post.getTimeLastModify());
        }};

        super.UpdateByKey("PID", post.getPID(), post.getHolderId() + CollectionSuffix, hashMap);
    }

    // Delete


}
