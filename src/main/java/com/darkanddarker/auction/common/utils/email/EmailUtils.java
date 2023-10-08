package com.darkanddarker.auction.common.utils.email;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailUtils {
    void sendVerificationCode(String email, String code) throws MessagingException, IOException;

}
