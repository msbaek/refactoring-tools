package com.example.refactorings.humbleobject;

interface MessageBus {
    void sendEmailChangedMessage(int userId, String newEmail);
}
