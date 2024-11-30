package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.payload.post.PostRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {CommentMapper.class})
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    PostEntity toEntity(PostRequestDto dto);

    @Mapping(target = "author", source = "author")
    PostResponseDto toDto(PostEntity entity);
}
