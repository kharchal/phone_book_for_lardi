package ua.com.hav.pb.dao.file;

import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.domain.Contact;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class ContactDaoFileImpl implements ContactDao {

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int LAST_NAME = 2;
    public static final int MIDDLE_NAME = 3;
    public static final int MOBILE = 4;
    public static final int PHONE = 5;
    public static final int ADDRESS = 6;
    public static final int EMAIL = 7;
    public static final int USER_ID = 8;
    public static final int MAX_SIZE = 9;

    private CsvFileUtil fileUtil;

	public ContactDaoFileImpl(String fileName) {
		System.out.println("contact dao fileName = " + fileName);
		fileUtil = new CsvFileUtil(fileName, asList(ID, USER_ID));
		fileUtil.ensureFile("contacts");
	}	

	public synchronized void save(Contact contact, Long userId) {
        List<List<String>> lines = fileUtil.read();
        String id = String.valueOf(contact.getId());
        if (contact.getId() == null) {
//        	long maxId = 0L;
//        	for (List<String> line : lines) {
//				long l = Long.parseLong("123");
//				System.out.println("line = " + line);
//				System.out.println(line.size());
//				String idFormFile = line.get(ID);
//				System.out.println("id = '" + idFormFile + "'; size i = " + idFormFile.length());
//				char[] chars = idFormFile.toCharArray();
//				byte[] bytes = idFormFile.getBytes();
//				idFormFile = "";
//				if (!idFormFile.matches("[0-9]")) {
//
//					for (char ch : chars) {
//					System.out.println("ch = '" + ch + "'; ? = " + ((int) ch));
//					if (ch >= '0' && ch <= '9') {
//						idFormFile += ch;
//					}
//				}
//				int xx = Integer.parseInt(line.get(ID));
//					if (xx > maxId) {
//        			maxId = xx;
//				}
//			}
            Long maxId =
					lines.stream()
                    .mapToLong(line -> Long.parseLong(line.get(ID)))
//							.forEach(System.ou
                    .max()
                    .getAsLong();
            id = String.valueOf(++maxId);
        } else {
            lines = lines.stream()
                    .filter(line -> !(line.get(ID).equals(String.valueOf(contact.getId()))
                                    && line.get(USER_ID).equals(String.valueOf(userId))))
                    .collect(toList());
        }
        lines.add(asList(id, contact.getFirstName(), contact.getLastName(),
					contact.getMiddleName(), contact.getMobile(), contact.getHome(),
                    contact.getAddress(), contact.getEmail(), "" + userId));

        fileUtil.write(lines);
	}

	public synchronized void delete(Long id, Long userId) {
        if (id == null || userId == null) {
            throw new IllegalArgumentException();
        }
        String idString = String.valueOf(id);
        String userIdString = String.valueOf(userId);
        List<List<String>> lines = fileUtil.read().stream()
                .filter(line -> !(line.get(ID).equals(idString) && line.get(USER_ID).equals(userIdString)))
                .collect(toList());
        fileUtil.write(lines);

	}

	public synchronized Contact find(Long id, Long userId) {
		if (id == null || userId == null) {
			throw new IllegalArgumentException();
		}
        String idString = String.valueOf(id);
        String userIdString = String.valueOf(userId);
		return assemble(fileUtil.read().stream()
                .filter(line -> line.get(ID).equals(idString) && line.get(USER_ID).equals(userIdString))
                .collect(toList()).get(0));
	}

	public synchronized List<Contact> findForUser(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException();
		}
        String userIdString = String.valueOf(userId);

        return fileUtil.read().stream()
                .filter(line -> line.get(USER_ID).equals(userIdString))
                .collect(toList())
                .stream()
                .map(line -> assemble(line))
                .collect(toList());
    }

	public synchronized List<Contact> find(String str, Long userId) {
		if (str == null || userId == null) {
			throw new IllegalArgumentException();
		}
        String userIdString = String.valueOf(userId);
        boolean empty = str.equals("");
        final String search = str.toLowerCase();
        return fileUtil.read().stream()
                .filter(line -> line.get(USER_ID).equals(userIdString))
                .filter(line -> (empty) ? true :
                        line.get(NAME).contains(search)
                        || line.get(LAST_NAME).contains(search)
                        || line.get(MOBILE).contains(search)
                        || line.get(PHONE).contains(search))
                .collect(toList())
                .stream()
                .map(line -> assemble(line))
                .collect(toList());

	}

	private Contact assemble(List<String> arr) {
		Contact contact = null;
		if (arr != null && arr.size() == MAX_SIZE) {
		    contact = new Contact();
			contact.setId(Long.parseLong(arr.get(ID)));
			contact.setFirstName(arr.get(NAME));
			contact.setLastName(arr.get(LAST_NAME));
			contact.setMiddleName(arr.get(MIDDLE_NAME));
			contact.setMobile(arr.get(MOBILE));
			contact.setHome(arr.get(PHONE));
			contact.setAddress(arr.get(ADDRESS));
			contact.setEmail(arr.get(EMAIL));
			contact.setUserId(Long.parseLong(arr.get(USER_ID)));
		}
		return contact;
	}

}