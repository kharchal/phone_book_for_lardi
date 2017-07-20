package ua.com.hav.pb.dao.file;

import javenue.csv.Csv;
import ua.com.hav.pb.dao.UserDao;
import ua.com.hav.pb.domain.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class UserDaoFileImpl implements UserDao {

	private String fileName;

	public UserDaoFileImpl(String fileName) {
		this.fileName = fileName;		
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				FileWriter fileWriter = new FileWriter(fileName);
				fileWriter.write("#user");
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	public synchronized boolean save(User user) {
        try {
        Csv.Reader reader = new Csv.Reader(new FileReader(fileName))
                .delimiter(',').ignoreComments(true);
            List<List<String>> list = new ArrayList<>();
            List<String> line = reader.readLine();

			Long maxId = 0L;
            while (line != null) {
                list.add(line);
                System.out.println(line);
				Long id = Long.parseLong(line.get(0));
				if (id > maxId) {
					maxId = id;
				}
                line = reader.readLine();
            }
            reader.close();
			list.add(asList( "" + ++maxId, user.getLogin(), user.getPassword(), user.getName()));
			Csv.Writer writer = new Csv.Writer(fileName).delimiter(',');
			for (List<String> ln : list) {
			    for (String s : ln) {
                    writer.value(s);
                }
                writer.newLine();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public synchronized User login(String login, String password) {
		User user = null;
		try {
            Csv.Reader reader = new Csv.Reader(new FileReader(fileName))
                    .delimiter(',').ignoreComments(true);
            List<String> line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                if (line.get(1).equals(login) && line.get(2).equals(password)) {
                    user = new User();
                    Long id = Long.parseLong(line.get(0));
                    user.setId(id);
                    user.setName(line.get(3));
                    user.setPassword("*****");
                    user.setLogin(login);
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

}