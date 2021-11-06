package dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCompanyResponse {
    private Data_ data;
    private String message;
    private String status;
}
