package co.com.demo.model.pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Pokemon {
    private String id;
    private String name;
    private String image;
    private List<String> type;
    private List<String> skill;
}
