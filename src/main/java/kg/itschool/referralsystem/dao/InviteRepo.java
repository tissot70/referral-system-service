package kg.itschool.referralsystem.dao;

import kg.itschool.referralsystem.models.entities.Invite;
import kg.itschool.referralsystem.models.entities.Subscriber;
import kg.itschool.referralsystem.models.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends JpaRepository<Invite,Long> {

    @Query(value = "select count(i.id) from invites as i where i.sender_id=?1 and i.start_date>=(select now() \\:\\: date \\+ interval '0h')",nativeQuery = true)
    int countInviteByPerDay(long subsId);

    @Query(value = "select count (id) from invites where sender_id=?1 and start_date>=(select date_trunc ( 'month', current_date));",nativeQuery = true)
    int countInviteByPerMonth(long subsId);

    @Query(value = "select * from invites where receiver_id=?1 and status='New' and start_date>=(select now() \\:\\: date \\+ interval '0h')",nativeQuery = true)
    Invite findBySubsId(Long subsId);

    @Query(value = "select count (sender_id) from invites where sender_id=?1 and receiver_id = ?2 and start_date>=(select now() \\:\\: date \\+ interval '0h')", nativeQuery = true)
    int countInviteDay(Long senderId, Long receiverId);

    @Query(value = "select * from invites where receiver_id=?1 and start_date>=(select now() \\:\\: date \\+ interval '0h') order by start_date desc limit 1", nativeQuery = true)
    Invite changeStatusCanceled(Subscriber receiverId);

    Invite findById(long inviteId);


}