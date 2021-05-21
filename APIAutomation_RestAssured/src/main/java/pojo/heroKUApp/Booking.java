package pojo.heroKUApp;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    public String firstname;
    public String lastname;
    public Integer totalprice;
    public Boolean depositpaid;
    public Bookingdates bookingdates;
    public String additionalneeds;
}
