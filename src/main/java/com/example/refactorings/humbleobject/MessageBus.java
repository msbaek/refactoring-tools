package com.example.refactorings.humbleobject;

interface MessageBus {
    void sendEmailChangedMessage(long userId, String newEmail);
}
