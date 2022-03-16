package kg.itschool.referralsystem.services.impl;

import kg.itschool.referralsystem.dao.SubscriberRepo;
import kg.itschool.referralsystem.mappers.SubsMapper;
import kg.itschool.referralsystem.models.dto.SubscriberDTO;
import kg.itschool.referralsystem.models.entities.Subscriber;
import kg.itschool.referralsystem.services.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private SubscriberRepo subscriberRepo;

    public SubscriberServiceImpl(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }

    @Override
    public SubscriberDTO findByPhone(String receiverPhone) {
        return SubsMapper.INSTANCE.subscriberToSubscriberDTO(subscriberRepo.findByPhone(receiverPhone));
    }

    @Override
    public SubscriberDTO creatSubscriber(String phone) {
        SubscriberDTO subscriberDTO = new SubscriberDTO();
        Subscriber subscriber =SubsMapper.INSTANCE.subscriberDToToSubscriber(subscriberDTO);
        subscriber.setPhone(phone);
        subscriber.setActive(true);
        subscriber = subscriberRepo.saveAndFlush(subscriber);
        return SubsMapper.INSTANCE.subscriberToSubscriberDTO(subscriber);
    }

    @Override
    public ResponseEntity<?> changePermission(Long subsId, boolean value) {
        // найти по айди пользователя и присвоить значение value полю active
        Subscriber subscriber;
        subscriber = subscriberRepo.findBySubsId(subsId);
        subscriber.setActive(value);
        subscriber = subscriberRepo.saveAndFlush(subscriber);
        return new ResponseEntity<>("поле изменено", HttpStatus.OK);
    }
}
