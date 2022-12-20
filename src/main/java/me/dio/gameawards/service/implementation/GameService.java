package me.dio.gameawards.service.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import me.dio.gameawards.domain.model.Game;
import me.dio.gameawards.repositories.GameRepository;
import me.dio.gameawards.service.IGameService;
import me.dio.gameawards.service.exception.BusinessException;
import me.dio.gameawards.service.exception.NoContentException;

@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository repository;

	@Override
	public List<Game> findAll() {
		List<Game> games = repository.findAll(Sort.by(Direction.DESC,"votes"));

		return games;
	}

	@Override
	public Game findById(Long id) {
		Optional<Game> game = repository.findById(id);
		return game.orElseThrow(() -> new NoContentException());

	}

	@Override
	public void insert(Game game) {
		if (Objects.nonNull(game.getId())) {
			throw new BusinessException("O id é diferente de null na inclusão");
		}
		repository.save(game);
	}

	@Override
	public void delete(Long id) {
		Game gameDb = findById(id);
		repository.delete(gameDb);
//		if (gameDb.getId() != null) {
//			repository.delete(gameDb);
//		}
//		throw new BusinessException("O id de exclusão não pode ser nulo. ");

	}

	@Override
	public void update(Long id, Game game) {
		Game gameDb = findById(id);

		if (gameDb.getId().equals(game.getId())) {
			repository.save(game);

		} else {
			throw new BusinessException("Os IDS não coincidem.");
		}

	}

	@Override
	public void vote(Long id) {
		Game gameDb = findById(id);
		gameDb.setVotes(gameDb.getVotes() + 1);

		update(id, gameDb);

	}

}
