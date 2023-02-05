package co.com.demo.usecase.pokemon;

import co.com.demo.model.pokemon.Pokemon;
import co.com.demo.model.pokemon.gateways.PokemonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class PokemonUseCase {
    private final PokemonRepository pokemonRepository;

    public Mono<Pokemon> savePokemon(Pokemon pokemon){
        return pokemonRepository.savePokemon(pokemon).switchIfEmpty(Mono.empty());
    }

    public Mono<Pokemon> getPokemon(String id){
        return pokemonRepository.getPokemon(id).switchIfEmpty(Mono.empty());
    }

    public Mono<List<Pokemon>> getAllPokemon(){
        return pokemonRepository.getAllPokemon();
    }
    public Mono<Pokemon> deletePokemon(String id){
        return pokemonRepository.deletePokemon(id).switchIfEmpty(Mono.empty());
    }
}
