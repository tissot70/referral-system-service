package kg.itschool.referralsystem.services.impl;

import kg.itschool.referralsystem.dao.InviteRepo;
import kg.itschool.referralsystem.mappers.InviteMapper;
import kg.itschool.referralsystem.mappers.SubsMapper;
import kg.itschool.referralsystem.models.dto.InviteOfSubs;
import kg.itschool.referralsystem.models.dto.InviteRequestDTO;
import kg.itschool.referralsystem.models.dto.SubscriberDTO;
import kg.itschool.referralsystem.models.entities.Invite;
import kg.itschool.referralsystem.models.enums.InviteStatus;
import kg.itschool.referralsystem.services.InviteService;
import kg.itschool.referralsystem.services.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class InviteServiceImpl implements InviteService {
    private InviteRepo inviteRepo;
    private SubscriberService subscriberService;


    public InviteServiceImpl(InviteRepo inviteRepo, SubscriberService subscriberService) {
        this.inviteRepo = inviteRepo;
        this.subscriberService = subscriberService;
    }

    @Override
    public ResponseEntity<?> sendInvite(InviteRequestDTO inviteRequestDTO) {
        SubscriberDTO sender= subscriberService.findByPhone(inviteRequestDTO.getSenderPhone());
        if (Objects.isNull(sender)){
            sender = subscriberService.creatSubscriber(inviteRequestDTO.getSenderPhone());
        }
        SubscriberDTO receiver = subscriberService.findByPhone(inviteRequestDTO.getReceiverPhone());
        if (Objects.isNull(receiver)){
            receiver = subscriberService.creatSubscriber(inviteRequestDTO.getReceiverPhone());
        }
        
        int countInviteDay=inviteRepo.countInviteDay(sender.getSubsId(),receiver.getSubsId());
        if(countInviteDay>=1){
            return new ResponseEntity<>("Вы уже отправляли этому человеку приглашение",HttpStatus.CONFLICT);
        }

        int countPerMonth=inviteRepo.countInviteByPerMonth(sender.getSubsId());
        if (countPerMonth>=30){
            return new ResponseEntity<>("Вы превысили лимит за месяц 30 приглашений",HttpStatus.CONFLICT);
        }

        int countPerDay =inviteRepo.countInviteByPerDay(sender.getSubsId());
        if (countPerDay>=5){
            return new ResponseEntity<>("Вы превысили лимит 5 приглашений за день", HttpStatus.CONFLICT);
        }
        Invite inviteStatus= inviteRepo.changeStatusCanceled(SubsMapper.INSTANCE.subscriberDToToSubscriber(receiver));
        if (Objects.nonNull(inviteStatus)){
            inviteStatus.setEndDate(LocalDateTime.now());
            inviteStatus.setInviteStatus(InviteStatus.CANCELLED);
            inviteRepo.saveAndFlush(inviteStatus);
        }
        Invite invite = new Invite();
        invite.setSender(SubsMapper.INSTANCE.subscriberDToToSubscriber(sender));
        invite.setReceiver(SubsMapper.INSTANCE.subscriberDToToSubscriber(receiver));
        invite.setInviteStatus(InviteStatus.New);
        inviteRepo.saveAndFlush(invite);
        return new ResponseEntity<>("Приглашение отправлено",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getLastInviteOfSubscriber(Long subsId) {
        //найти последний активный инвайт и вернуть
        Invite invite = inviteRepo.findBySubsId(subsId);
        InviteOfSubs inviteOfSubs = InviteMapper.INSTANCE.inviteToInviteOfSubs(invite);
        invite = InviteMapper.INSTANCE.inviteOfSubsToInvite(inviteOfSubs);
        return new ResponseEntity<>(invite,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmInvite(long inviteId) {
        //находите инвайт по айди и меняете статус на ACCEPTED
        Invite invite = inviteRepo.findById(inviteId);
        invite.setInviteStatus(InviteStatus.ACCEPTED);
        invite.setEndDate(LocalDateTime.now());
        invite = inviteRepo.saveAndFlush(invite);
        InviteOfSubs inviteOfSubs=InviteMapper.INSTANCE.inviteToInviteOfSubs(invite);
        return new ResponseEntity<>(inviteOfSubs+" вы подтвердили приглашение",HttpStatus.OK);
    }
}
