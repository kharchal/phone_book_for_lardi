package ua.com.hav.pb.dao;

import ua.com.hav.pb.domain.Contact;

import java.util.List;

/**
 * Created by yulia on 15.07.2017.
 */
public interface ContactDao {
//    List<Contact> findAll();
    List<Contact> findForUser(Long userId);
    List<Contact> find(String str, Long userId);
    Contact find(Long id, Long userId);
    void save(Contact contact, Long userId);
    void delete(Long id, Long userId);
}
