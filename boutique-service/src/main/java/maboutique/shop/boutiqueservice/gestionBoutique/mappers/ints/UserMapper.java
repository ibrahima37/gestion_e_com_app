package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDetailDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;

public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    UserDetailDto toDetailDto(User entity);
}
