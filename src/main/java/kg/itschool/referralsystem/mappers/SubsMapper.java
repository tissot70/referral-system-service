package kg.itschool.referralsystem.mappers;

import kg.itschool.referralsystem.models.dto.SubscriberDTO;
import kg.itschool.referralsystem.models.entities.Subscriber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubsMapper {
    SubsMapper INSTANCE = Mappers.getMapper(SubsMapper.class);

    SubscriberDTO subscriberToSubscriberDTO(Subscriber subscriber);
    Subscriber subscriberDToToSubscriber(SubscriberDTO subscriberDTO);
}
