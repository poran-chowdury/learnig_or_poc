package payload;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class RegisterCompanyPayload {
    private String name;
    private String address;
    private String tin;
}
