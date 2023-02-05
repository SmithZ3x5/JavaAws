package co.com.demo.dynamodb.gateway;

import co.com.demo.dynamodb.DynamoDBTemplateAdapter;
import co.com.demo.model.pokemon.Pokemon;
import co.com.demo.model.pokemon.gateways.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PokemonGatewayImpl implements PokemonRepository {
    private final DynamoDBTemplateAdapter adapter;

    @Override
    public Mono<Pokemon> savePokemon(Pokemon pokemon) {
        return adapter.save(pokemon).flatMap(p ->
                Mono.just(new Pokemon(
                                pokemon.getId(),
                                pokemon.getName(),
                                pokemon.getImage(),
                                pokemon.getType(),
                                pokemon.getSkill())));
    }

    @Override
    public Mono<Pokemon> getPokemon(String id) {
        return adapter.getById(id);
    }

    @Override
    public Mono<List<Pokemon>> getAllPokemon() {
        return adapter.scan();
    }

    @Override
    public Mono<Pokemon> deletePokemon(String id) {
        return adapter.deleteById(id);
    }
}
