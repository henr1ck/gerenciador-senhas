package vieira.pedro.gerenciadorsenhas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vieira.pedro.gerenciadorsenhas.dto.PasswordRequest;
import vieira.pedro.gerenciadorsenhas.dto.PasswordResponse;
import vieira.pedro.gerenciadorsenhas.entity.Password;

@Mapper
public interface PasswordMapper {

    PasswordMapper INSTANCE = Mappers.getMapper(PasswordMapper.class);

    Password toEntity(PasswordRequest dto);

    PasswordResponse toDto(Password entity);
}
