package hello.itemservice.web.validation.dto;

import hello.itemservice.web.validation.custom.ContactNumberConstraint;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull
    private String name;

    @Email
    private String email;

    @ContactNumberConstraint
    private String phoneNumber;
}
