package team.odds.mentor.model.mapper;


import org.mapstruct.*;
import team.odds.mentor.model.User;
import team.odds.mentor.model.dto.UserResponseDto;
import team.odds.mentor.model.dto.UserRequestDto;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "expertise", ignore = true)
    User toUser(UserRequestDto userRequestDto);


    @Mapping(target = "expertise", ignore = true)
    @Mapping(target = "fullNameEN", expression = "java(user.getFirstNameEN() + \" \" + user.getLastNameEN())")
    @Mapping(target = "fullNameTH", expression = "java(user.getFirstNameTH() + \" \" + user.getLastNameTH())")
    UserResponseDto toUserResponse(User user);

}
