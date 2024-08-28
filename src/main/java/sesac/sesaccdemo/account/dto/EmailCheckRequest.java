package sesac.sesaccdemo.account.dto;

import jakarta.validation.constraints.Email;

public record EmailCheckRequest(@Email String email) {

}
