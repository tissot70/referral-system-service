package kg.itschool.referralsystem.services;

import kg.itschool.referralsystem.models.dto.SubscriberDTO;
import org.springframework.http.ResponseEntity;

public interface SubscriberService {
    SubscriberDTO findByPhone(String phone);

    SubscriberDTO creatSubscriber(String phone);

    ResponseEntity<?> changePermission(Long subsId, boolean value);
}
