package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Util.UtilJudge;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class BaseDAO<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> Clazz = (java.lang.Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected BaseDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Create
    protected void Save (
            T      Data          ,
            String CollectionName
    ) {
        mongoTemplate.insert(Data, CollectionName);
    }

    // Retrieve
    protected T FindOneByKey (
            String Key  ,
            String Value,
            String CollectionName

    ) {

        Query query = new Query(Criteria.where(Key).is(Value));
        return mongoTemplate.findOne(query, Clazz, CollectionName);
    }

    // Update
    protected void UpdateByKey (
            String Key           ,
            String Value         ,
            String CollectionName,
            HashMap<String, Object> Data
    ) {

        Update update = new Update();

        for (Map.Entry<String, Object> map : Data.entrySet()) {

            if (map.getValue() instanceof String) {

                update.set(map.getKey(),
                        UtilJudge.Numbers.isNumeric(map.getValue().toString()) ? Integer.valueOf(map.getValue().toString()) : map.getValue()
                );

                continue;
            }

            if (map.getValue() instanceof List<?>) {

                update.set(map.getKey(), map.getValue());

                continue;
            }

            System.out.println(map.getValue().getClass());
            break;
        }

        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.upsert(query, update, Clazz, CollectionName);
    }


    // Delete
    protected void RemoveByKey (
            String Key           ,
            String Value         ,
            String CollectionName
    ) {

        Query query = new Query(Criteria.where(Key).is(Value));
        mongoTemplate.remove(query, Clazz, CollectionName);
    }
}
