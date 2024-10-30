package alatoo.edu.kg.lowkeystudents_api.api.mapper;
import alatoo.edu.kg.lowkeystudents_api.store.entity.user.StudentEntity;
import alatoo.edu.kg.lowkeystudents_api.api.payload.StudentDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(StudentEntity students);
    StudentEntity toEntity(StudentDto dto);
}