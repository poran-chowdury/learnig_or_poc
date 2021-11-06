package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 02:51
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String _id;

    private String name;

    private String mobileNumber;

    private String state;

    private String city;

    private String zip;

    private String address;

    private String user;

    private Double __v;

}
