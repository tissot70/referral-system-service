package kg.itschool.referralsystem.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InviteOfSubs {
    Long id;
    String receiverPhone;
    String senderPhone;
}
