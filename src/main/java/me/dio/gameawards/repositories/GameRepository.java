package me.dio.gameawards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.gameawards.domain.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
