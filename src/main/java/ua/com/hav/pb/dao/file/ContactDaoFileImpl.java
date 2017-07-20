package ua.com.hav.pb.dao.file;

import javenue.csv.Csv;
import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.domain.Contact;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ContactDaoFileImpl implements ContactDao {

	private String fileName;

	public ContactDaoFileImpl(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				FileWriter fileWriter = new FileWriter(fileName);
				fileWriter.write("#contact");
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	public synchronized void save(Contact contact, Long userId) {
		try {
			Csv.Reader reader = new Csv.Reader(new FileReader(fileName)).delimiter(',').ignoreComments(true);
			List<List<String>> lines = new ArrayList<>();
			Long maxId = 0L;
			List<String> line = reader.readLine();
			while (line != null) {
				lines.add(line);
				Long id = Long.parseLong(line.get(0));
				if (id > maxId) {
					maxId = id;
				}
				line = reader.readLine();
			}
			reader.close();
			List<String> newLine = asList("" + ++maxId, contact.getFirstName(), contact.getLastName(),
					contact.getMiddleName(), contact.getMobile(), contact.getHome(), contact.getAddress(),
					contact.getEmail(), "" + userId);

			lines.add(newLine);
			Csv.Writer writer = new Csv.Writer(fileName).delimiter(',');
			for (List<String> ln : lines) {
                System.out.println("line = " + ln);
                for (String s: ln) {
                    System.out.print("s = " + s);
                    writer.value(s);
				}
                System.out.println();
                writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public synchronized void delete(Long id, Long userId) {
		try {
			Csv.Reader reader = new Csv.Reader(new FileReader(fileName));
			List<List<String>> lines = new ArrayList<>();
			List<String> line = reader.readLine();
			while (line != null) {
				if (!(Long.parseLong(line.get(0)) == id) || !(Long.parseLong(line.get(8)) == userId)) {
					lines.add(line);
				}
				line = reader.readLine();
			}
			reader.close();
			Csv.Writer writer = new Csv.Writer(fileName).delimiter(',');
			for (List<String> ln : lines) {
				for (String s : ln) {
					writer.value(s);
				}
				writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized Contact find(Long id, Long userId) {
		if (id == null || userId == null) {
			throw new IllegalArgumentException();
		}
		Contact contact = null;
		try {
			Csv.Reader reader = new Csv.Reader(new FileReader(fileName)).delimiter(',').ignoreComments(true);
			List<String> line = reader.readLine();
			while (line != null) {
				if (Long.parseLong(line.get(0)) == id && Long.parseLong(line.get(8)) == userId) {
					contact = assemble(line);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contact;

	}

	public synchronized List<Contact> findForUser(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException();
		}
		List<Contact> contacts = new ArrayList<>();
		try {
			Csv.Reader reader = new Csv.Reader(new FileReader(fileName)).delimiter(',')
                    .ignoreComments(true).ignoreEmptyLines(true);
			List<String> line = reader.readLine();
			while (line != null) {

				if (Long.parseLong(line.get(8)) == userId) {
					contacts.add(assemble(line));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public synchronized List<Contact> find(String str, Long userId) {
		if (str == null || userId == null) {
			throw new IllegalArgumentException();
		}
		boolean empty = str.equals("");
		List<Contact> contacts = new ArrayList<>();
		try {
			Csv.Reader reader = new Csv.Reader(new FileReader(fileName)).delimiter(',').ignoreComments(true);
			List<String> line = reader.readLine();
			while (line != null) {
				if (Long.parseLong(line.get(8)) == userId) {
					if (empty) {
						contacts.add(assemble(line));
					} else if (line.get(1).contains(str)
							|| line.get(2).contains(str)
							|| line.get(4).contains(str)
							|| line.get(5).contains(str)) {

						contacts.add(assemble(line));
						
					}
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public Contact assemble(List<String> arr) {
		Contact contact = null;
		if (arr != null && arr.size() == 9) {
			contact.setId(Long.parseLong(arr.get(0)));
			contact.setFirstName(arr.get(1));
			contact.setLastName(arr.get(2));
			contact.setMiddleName(arr.get(3));
			contact.setMobile(arr.get(4));
			contact.setHome(arr.get(5));
			contact.setAddress(arr.get(6));
			contact.setEmail(arr.get(7));
			contact.setUserId(Long.parseLong(arr.get(8)));
		}
		return contact;
	}

}