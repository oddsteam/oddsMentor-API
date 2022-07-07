package team.odds.mentor.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.dto.ExpertiseRequestDto;
import team.odds.mentor.model.dto.ExpertiseUserResponseDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertiseMapper {
    Expertise toExpertise(ExpertiseRequestDto expertiseRequestDto);

    ExpertiseUserResponseDto toExpertiseUserResponseDto(Expertise expertise);
}
