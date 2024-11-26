package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.store.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommentEntity toEntity(CommentRequestDto dto);

    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "postId", source = "post.id")
    CommentResponseDto toDto(CommentEntity entity);
}
