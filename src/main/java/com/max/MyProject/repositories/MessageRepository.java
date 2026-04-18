package com.max.MyProject.repositories;

import com.max.MyProject.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findFirst50ByOrderByCreatedAtDesc();
}
