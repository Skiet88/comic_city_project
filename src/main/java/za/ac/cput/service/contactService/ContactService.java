package za.ac.cput.service.contactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService{
    @Autowired
    private ContactRepository repo;

    @Override
    public Contact create(Contact contact) {

        if (contact != null) {
            System.out.println("checking if existing contact exists");

            Optional<Contact> existingContact = repo.findById(contact.getEmail());

            if (existingContact.isPresent()) {
                System.out.println("found contact");
                contact = existingContact.get();
                System.out.println("Linked existing contact");
            } else {
                System.out.println("saving new contact");
                contact = repo.save(contact);
                System.out.println("Saved");
            }
        }
        return  contact;
    }

    @Override
    public Contact read(String s) {
        return repo.findById(s).orElse(null);
    }

    @Override
    public Contact update(Contact contact) {
        return repo.save(contact);
    }

    @Override
    public boolean delete(String s) {
        repo.deleteById(s);
        return repo.existsById(s);
    }

    @Override
    public List<Contact> getAll() {
        return repo.findAll();
    }
}
