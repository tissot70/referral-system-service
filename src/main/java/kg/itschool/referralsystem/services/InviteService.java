package kg.itschool.referralsystem.services;

import kg.itschool.referralsystem.models.dto.InviteRequestDTO;
import org.springframework.http.ResponseEntity;

public interface InviteService {

    ResponseEntity<?> sendInvite(InviteRequestDTO inviteRequestDTO);

    ResponseEntity<?> getLastInviteOfSubscriber(Long subsId);

    ResponseEntity<?> confirmInvite(long inviteId);
}
