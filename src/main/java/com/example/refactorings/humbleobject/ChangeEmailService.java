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

        if (user.email().equals(newEmail)) {
            return;
        }
        String companyDomainName = company.domainName();
        int noOfEmployees = company.noOfEmployees();

        String emailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = emailDomain.equals(companyDomainName);

        UserType newUserType = isEmailCorporate ? UserType.Employee : UserType.Customer;
        if (user.userType() != newUserType) {
            int delta = newUserType == UserType.Employee ? 1 : -1;
            int newNumbers = noOfEmployees + delta;
            company.noOfEmployees(newNumbers);
        }
        user.email(newEmail);
        user.userType(newUserType);

        companyRepository.save(company);
        userRepository.save(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}