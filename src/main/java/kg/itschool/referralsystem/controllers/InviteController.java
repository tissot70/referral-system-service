package kg.itschool.referralsystem.controllers;

import kg.itschool.referralsystem.models.dto.InviteRequestDTO;
import kg.itschool.referralsystem.services.InviteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/invite")
public class InviteController {
    private InviteService inviteService;

    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendInvite(@RequestBody InviteRequestDTO inviteRequestDTO){
        return inviteService.sendInvite(inviteRequestDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getLastInviteOfSubscriber(@RequestParam Long subsId){
        return inviteService.getLastInviteOfSubscriber(subsId);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam Long inviteId){
        return inviteService.confirmInvite(inviteId);
    }

}
