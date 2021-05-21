package pojo.jsonMock;

import lombok.*;

@Getter@Setter@Builder@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    public int id;
    public String body;
    public int postId;

}
