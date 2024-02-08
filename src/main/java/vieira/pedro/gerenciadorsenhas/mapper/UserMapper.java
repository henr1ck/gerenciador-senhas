package vieira.pedro.gerenciadorsenhas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vieira.pedro.gerenciadorsenhas.dto.UserRequest;
import vieira.pedro.gerenciadorsenhas.dto.UserResponse;
import vieira.pedro.gerenciadorsenhas.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest userRequest);
    UserResponse toDto(User user);
}
