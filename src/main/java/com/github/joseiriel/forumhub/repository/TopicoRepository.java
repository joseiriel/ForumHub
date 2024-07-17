package com.github.joseiriel.forumhub.repository;

import com.github.joseiriel.forumhub.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findAllByOrderByDataDesc();
}