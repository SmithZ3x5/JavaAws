#!/bin/bash

# -- > Create DynamoDb Table
echo Creating  DynamoDb \'PokemonTable\' table ...
echo $(awslocal dynamodb create-table \
          --table-name PokemonTable \
          --attribute-definitions AttributeName=PokemonId,AttributeType=S AttributeName=PokemonName,AttributeType=S AttributeName=PokemonImageId,AttributeType=S AttributeName=PokemonTypeId,AttributeType=S AttributeName=PokemonSkillId,AttributeType=S\
          --key-schema AttributeName=PokemonId,KeyType=HASH \
          --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5)

# --> List DynamoDb Tables
echo Listing tables ...
echo $(awslocal dynamodb list-tables)