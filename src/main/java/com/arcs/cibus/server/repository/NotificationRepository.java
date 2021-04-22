package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT COUNT(n) FROM cibus_notification n "
            + "WHERE (:userId is null or n.user.id = :userId) "
    )
    int countNotificationsUnread(@Param("userId") Long userId);

    @Query("SELECT n FROM cibus_notification n "
            + "WHERE (:userId is null or n.user.id = :userId) "
            + "ORDER BY n.date "
    )
    List<Notification> findAllOrdered(@Param("userId") Long userId);
}
