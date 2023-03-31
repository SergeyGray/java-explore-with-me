package ru.practicum.main_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.Request;
import ru.practicum.main_server.model.enums.RequestStatusEnum;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query(value = "select r from Request as r where r.event.id = ?1 and r.requester.id = ?2")
    Request getRequest(int eventId, int userId);

    @Query(value = "select r from Request as r where r.event.id = ?1 and r.event.initiator.id= ?2")
    List<Request> getRequests(int eventId, int userId);

    @Query(value = "select r from Request as r where r.id in (?1) and r.status = ?2 ")
    List<Request> getRequestByIdEventId(List<Integer> requestIds, RequestStatusEnum requestStatusEnum);

    List<Request> findByRequesterId(int userId);

    @Query(value = "select count(r) from Request as r where r.event.id = ?1 and r.status = 'CONFIRMED'")
    Optional<Integer> getConfirmedRequest(int eventId);

    @Query(value = "select r.event.id, count(r.status) from Request as r where r.event in (?1) " +
            "and r.status = 'CONFIRMED' group by r.event.id")
    Map<Integer, Integer> getConfirmedRequestsListInteger(List<Event> events);
}
