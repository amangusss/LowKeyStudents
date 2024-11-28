package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import alatoo.edu.kg.lowkeystudents.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T15:07:08+0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PostEntity toEntity(PostRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        PostEntity postEntity = new PostEntity();

        postEntity.setTitle( dto.getTitle() );
        postEntity.setDescription( dto.getDescription() );

        return postEntity;
    }

    @Override
    public PostResponseDto toDto(PostEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PostResponseDto.PostResponseDtoBuilder postResponseDto = PostResponseDto.builder();

        postResponseDto.authorUsername( entityAuthorUsername( entity ) );
        postResponseDto.id( entity.getId() );
        postResponseDto.title( entity.getTitle() );
        postResponseDto.description( entity.getDescription() );
        postResponseDto.createdAt( entity.getCreatedAt() );
        postResponseDto.updatedAt( entity.getUpdatedAt() );
        postResponseDto.comments( commentEntityListToCommentResponseDtoList( entity.getComments() ) );

        return postResponseDto.build();
    }

    private String entityAuthorUsername(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author.getUsername();
    }

    protected List<CommentResponseDto> commentEntityListToCommentResponseDtoList(List<CommentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponseDto> list1 = new ArrayList<CommentResponseDto>( list.size() );
        for ( CommentEntity commentEntity : list ) {
            list1.add( commentMapper.toDto( commentEntity ) );
        }

        return list1;
    }
}
