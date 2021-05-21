package pojo.heroKUApp;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingsResponse {
    public String bookingid;
    public Booking booking;
}
