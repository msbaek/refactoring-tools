package com.example.refactorings.humbleobject;

interface UserRepository {
    User findById(int userId);
    void save(User user);
}
