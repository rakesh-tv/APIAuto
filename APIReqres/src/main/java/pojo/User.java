package pojo;

import lombok.*;

@Getter@Setter@Builder@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String name;
    public String job;
    public String id;
    public String createdAt;
    public String updatedAt;

}
