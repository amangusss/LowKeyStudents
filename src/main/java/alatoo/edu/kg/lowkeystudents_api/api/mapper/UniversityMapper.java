package alatoo.edu.kg.lowkeystudents_api.api.mapper;
import alatoo.edu.kg.lowkeystudents_api.store.entity.user.UniversityEntity;
import alatoo.edu.kg.lowkeystudents_api.api.payload.UniversityDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityDto toDto(UniversityEntity university);
    UniversityEntity toEntity(UniversityDto dto);
}