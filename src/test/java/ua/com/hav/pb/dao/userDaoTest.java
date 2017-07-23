package ua.com.hav.pb.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yulia on 23.07.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class userDaoTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService service;

    @Test
    public void user_Dao_tets() {
        when(userDao.checkLogin("aaa")).thenReturn(true);
        service.foo("aaa");
        verify(userDao).checkLogin("aaa");
    }
}
