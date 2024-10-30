package alatoo.edu.kg.lowkeystudents_api.api.mapper;

import alatoo.edu.kg.lowkeystudents_api.api.payload.PostDto;
import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto (PostEntity post);
    PostEntity tEntity(PostDto dto);
}
