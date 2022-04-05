package com.example.refactorings.humbleobject;

public class ChangeEmailService {
    private final MessageBus messageBus;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public ChangeEmailService(MessageBus messageBus, CompanyRepository companyRepository, UserRepository userRepository) {
        this.messageBus = messageBus;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public void changeEmail(int userId, String newEmail) {
        User user = userRepository.findById(userId);
        Company company = companyRepository.findById(user.companyId());

        user.changeEmail(newEmail, company);

        companyRepository.save(company);
        userRepository.save(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}