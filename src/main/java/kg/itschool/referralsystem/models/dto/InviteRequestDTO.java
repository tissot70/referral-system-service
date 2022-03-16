package kg.itschool.referralsystem.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InviteRequestDTO {
        String senderPhone;
        String receiverPhone;
}
