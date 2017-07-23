package ua.com.hav.pb.dao.file;

import ua.com.hav.pb.dao.UserDao;
import ua.com.hav.pb.domain.User;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class UserDaoFileImpl implements UserDao {

    public final static int ID = 0;
    public final static int LOGIN = 1;
    public final static int PASSWORD = 2;
    public final static int NAME = 3;

    private CsvFileUtil fileUtil;

	public UserDaoFileImpl(String fileName) {
	    fileUtil = new CsvFileUtil(fileName, asList(ID));
		fileUtil.ensureFile("users");
	}	

	public synchronized boolean save(User user) {
        List<List<String>> lines = fileUtil.read();
        long count = lines.stream().filter(line -> line.get(LOGIN).equals(user.getLogin())).count();
        if (count == 0L) {
            Long maxId = lines.stream()
                    .mapToLong(line -> Long.parseLong(line.get(ID)))
                    .max()
                    .getAsLong();

            lines.add(asList("" + ++maxId, user.getLogin(), user.getPassword(), user.getName()));
            fileUtil.write(lines);
            return true;
        }
        return false;
	}

    @Override
    public synchronized boolean checkLogin(String login) {
        return fileUtil.read().stream()
                .filter(line -> line.get(LOGIN).equals(login))
                .count() == 0L;
    }

    public synchronized User login(String login, String password) {
		User user = null;
        List<List<String>> lines = fileUtil.read();
//        lines.stream().forEach(System.out::println);
        Optional<List<String>> any = lines.stream()
                .filter(line -> line.get(LOGIN).equals(login) && line.get(PASSWORD).equals(password))
                .findFirst();
        List<String> line = (any.isPresent()) ? any.get() : null;
        if (line != null) {
            user = new User();
            Long id = Long.parseLong(line.get(ID));
            user.setId(id);
            user.setName(line.get(NAME));
            user.setPassword("<-SECURED->");
            user.setLogin(login);
        }
		return user;
	}

}