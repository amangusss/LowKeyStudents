package alatoo.edu.kg.lowkeystudents_api.api.mapper;

import alatoo.edu.kg.lowkeystudents_api.api.payload.CommentDto;
import alatoo.edu.kg.lowkeystudents_api.store.entity.CommentEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(CommentEntity comments);
    CommentEntity toEntity(CommentDto dto);
}
