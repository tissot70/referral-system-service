package kg.itschool.referralsystem.mappers;

import kg.itschool.referralsystem.models.dto.InviteOfSubs;
import kg.itschool.referralsystem.models.dto.InviteRequestDTO;
import kg.itschool.referralsystem.models.entities.Invite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InviteMapper {
    InviteMapper INSTANCE= Mappers.getMapper(InviteMapper.class);

    InviteRequestDTO inviteToInviteRequestDTO(Invite invite);
    Invite inviteRequestDTOToInvite(InviteRequestDTO inviteRequestDTO);

    InviteOfSubs inviteToInviteOfSubs(Invite invite);
    Invite inviteOfSubsToInvite(InviteOfSubs inviteOfSubs);

}
