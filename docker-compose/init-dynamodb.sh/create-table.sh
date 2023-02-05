aws dynamodb create-table \
   --table-name PokemonTable \
   --attribute-definitions AttributeName=PokemonId,AttributeType=S AttributeName=PokemonName,AttributeType=S AttributeName=PokemonImageId,AttributeType=S AttributeName=PokemonTypeId,AttributeType=S AttributeName=PokemonSkillId,AttributeType=S\
   --key-schema AttributeName=PokemonId,KeyType=HASH \
   --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
   --endpoint-url http://localhost:4566