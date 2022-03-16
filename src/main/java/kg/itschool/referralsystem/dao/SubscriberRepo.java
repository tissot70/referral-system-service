package kg.itschool.referralsystem.dao;

import kg.itschool.referralsystem.models.dto.SubscriberDTO;
import kg.itschool.referralsystem.models.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber,Long> {

    Subscriber findByPhone(String receiverPhone);
    Subscriber findBySubsId(Long subsId);
}
