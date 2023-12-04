package com.darkanddarker.auction.model;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.exception.UnauthorizedException;

public enum VerificationEventType {
    SUCCESS {
        @Override
        public void execute() {
        }
    },
    FAILURE {
        @Override
        public void execute() {
            throw new BadRequestException("인증번호가 올바르지 않습니다.");
        }
    },
    EXPIRED {
        @Override
        public void execute() {
            throw new BadRequestException("만료된 인증번호 입니다.");
        }
    };

    public abstract void execute();
}
