package team.odds.mentor.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.odds.mentor.model.Endorsement;
import team.odds.mentor.model.dto.EndorsementRequestDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EndorsementMapper {
    Endorsement toEndorsement(EndorsementRequestDto endorsementRequestDto);
}
