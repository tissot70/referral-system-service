package kg.itschool.referralsystem.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberDTO {
    Long subsId;
    String phone;
    boolean active;
    LocalDate editDate;
}
