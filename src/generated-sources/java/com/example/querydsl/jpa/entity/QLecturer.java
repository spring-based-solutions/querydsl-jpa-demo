package com.example.querydsl.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLecturer is a Querydsl query type for Lecturer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLecturer extends EntityPathBase<Lecturer> {

    private static final long serialVersionUID = 970076996L;

    public static final QLecturer lecturer = new QLecturer("lecturer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final BooleanPath sex = createBoolean("sex");

    public QLecturer(String variable) {
        super(Lecturer.class, forVariable(variable));
    }

    public QLecturer(Path<? extends Lecturer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLecturer(PathMetadata metadata) {
        super(Lecturer.class, metadata);
    }

}

