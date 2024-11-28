package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T15:07:08+0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity toEntity(CommentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setContent( dto.getContent() );

        return commentEntity;
    }

    @Override
    public CommentResponseDto toDto(CommentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CommentResponseDto.CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        commentResponseDto.authorUsername( entityAuthorUsername( entity ) );
        commentResponseDto.postId( entityPostId( entity ) );
        commentResponseDto.id( entity.getId() );
        commentResponseDto.content( entity.getContent() );
        commentResponseDto.createdAt( entity.getCreatedAt() );
        commentResponseDto.updatedAt( entity.getUpdatedAt() );

        return commentResponseDto.build();
    }

    private String entityAuthorUsername(CommentEntity commentEntity) {
        UserEntity author = commentEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author.getUsername();
    }

    private Long entityPostId(CommentEntity commentEntity) {
        PostEntity post = commentEntity.getPost();
        if ( post == null ) {
            return null;
        }
        return post.getId();
    }
}
