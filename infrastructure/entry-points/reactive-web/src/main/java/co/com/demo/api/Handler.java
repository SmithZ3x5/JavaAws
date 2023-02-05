package co.com.demo.api;

import co.com.demo.model.pokemon.Pokemon;
import co.com.demo.usecase.pokemon.PokemonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Handler {
    private final PokemonUseCase pokemonUseCase;
    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        Mono<Pokemon> pokemonMono = pokemonUseCase.getPokemon(serverRequest.pathVariable("id"));

        return pokemonMono.flatMap(pokemon ->
                ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(pokemon)
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        Mono<Pokemon> pokemonMono = serverRequest.bodyToMono(Pokemon.class);

        return pokemonMono.flatMap(pokemon ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(pokemonUseCase.savePokemon(pokemon)));
    }

    public Mono<ServerResponse> listenGETAllUseCase(ServerRequest serverRequest){
        Mono<List<Pokemon>> pokemonMono = pokemonUseCase.getAllPokemon();

        System.out.print(pokemonMono);

        return pokemonMono.flatMap(pokemons ->
                ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(pokemons)
                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> listenDELETEUseCase(ServerRequest serverRequest){
        Mono<Pokemon> pokemonMono = pokemonUseCase.deletePokemon(serverRequest.pathVariable("id"));

        return pokemonMono.flatMap(pokemon ->
                ServerResponse.status(HttpStatus.GONE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(pokemon))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
