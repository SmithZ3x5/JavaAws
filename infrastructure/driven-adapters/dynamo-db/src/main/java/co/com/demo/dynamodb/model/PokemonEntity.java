package co.com.demo.dynamodb.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.List;

/* Enhanced DynamoDB annotations are incompatible with Lombok #1932
         https://github.com/aws/aws-sdk-java-v2/issues/1932*/
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {

    private String id;
    private String name;
    private String image;
    private List<String> type;
    private List<String> skill;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PokemonId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //@DynamoDbSortKey
    @DynamoDbAttribute("PokemonName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("PokemonImageId")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @DynamoDbAttribute("PokemonTypeId")
    public List<String> getType() {
        return type;
    }

    public void setType(List type) {
        this.type = type;
    }

    @DynamoDbAttribute("PokemonSkillId")
    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List skill) {
        this.skill = skill;
    }
}
