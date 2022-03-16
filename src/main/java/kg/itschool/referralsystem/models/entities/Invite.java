package kg.itschool.referralsystem.models.entities;

import kg.itschool.referralsystem.models.enums.InviteStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp
    LocalDateTime startDate;
    LocalDateTime endDate;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    InviteStatus inviteStatus;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    Subscriber sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    Subscriber receiver;
}
