package co.com.demo.model.pokemon.gateways;

import co.com.demo.model.pokemon.Pokemon;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PokemonRepository {
    Mono<Pokemon> savePokemon(Pokemon pokemon);
    Mono<Pokemon> getPokemon(String id);
    Mono<List<Pokemon>> getAllPokemon();
    Mono<Pokemon> deletePokemon(String id);
}
