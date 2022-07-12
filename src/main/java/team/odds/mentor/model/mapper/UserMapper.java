package team.odds.mentor.model.mapper;

import org.mapstruct.*;
import team.odds.mentor.model.User;
import team.odds.mentor.model.dto.UserRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "accountType", source = "type")
    @Mapping(target = "expertise", expression = "java(mapExpertise(userRequestDto.getExpertise()))")
    User toUser(UserRequestDto userRequestDto);

    default List<String> mapExpertise(List<UserRequestDto.Expertise> expertise) {
        return expertise.stream()
                .map(UserRequestDto.Expertise::getId)
                .collect(Collectors.toList());
    }
}
