package tn.undefined.universalhaven.buisness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.transaction.Transactional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.buisness.UserServiceLocal;

@Stateless
public class UserService implements UserServiceLocal {
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean banUser(long id) {
		try {

			User user = em.find(User.class, id);
			user.setIsActif(false);
			em.merge(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addUser(User user) {
		try {

			em.persist(user);
			user.setPassword(generateHash(user.getPassword()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public int getAgeAverage() {
		TypedQuery<Long> query = em.createQuery(
				"SELECT (sum(DATEDIFF(SYSDATE() , u.birthDate)))/count(u.id)/365 from User u ", Long.class);

		int age = query.getSingleResult().intValue();
		return age;
	}

	@Override
	@Transactional
	public Collection<User> getAvailableVolenteers() {
		Query query = em.createQuery("SELECT u from User u where u.assignedCamp IS NULL");
		return (Collection<User>) query.getResultList();
	}

	@Override
	public Map<String, Long> getGenderStats() {
		Map<String, Long> map = new HashedMap<>();
		TypedQuery<Long> query = em.createQuery("select count(u.gender) from User u where u.gender like :male ",
				Long.class);
		query.setParameter("male", "%male%");
		map.put("male", query.getSingleResult());

		TypedQuery<Long> query1 = em.createQuery("select count(u.gender) from User u where u.gender like :female ",
				Long.class);
		query1.setParameter("female", "%female%");
		map.put("female", query1.getSingleResult());
		return map;
	}

	@Override
	public Map<String, Integer> getUserCountPerRole() {

		Map<String, Integer> map = new HashedMap<>();
		for (UserRole rol : UserRole.values()) {
System.out.println(rol);
			TypedQuery<Long> query = em.createQuery("select count(u.role) from User u where u.role = :role ",
					Long.class);
			query.setParameter("role",  rol );
			map.put(rol.toString(), query.getSingleResult().intValue());
		}
		return map;
	}

	@Override
	public List<User> getUserPerRole(UserRole role) {
		TypedQuery<User> query = em.createQuery("select u from User u where u.role=:role ", User.class);
		query.setParameter("role", role);

		return query.getResultList();
	}

	@Override
	public int importUserList(String fileLocation) {
		List<User> listUsersXL = new ArrayList<User>();
		TypedQuery<User> query = em.createQuery("select u from User u  ", User.class);
		List<User> listUsers = new ArrayList<User>();
		listUsers = query.getResultList();

		String FILE_NAME = fileLocation;
		try {

			FileInputStream excelFile = new FileInputStream(new File(fileLocation));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator(); // Traversing
																	// over each
																	// row of
																	// XLSX file
			while (rowIterator.hasNext()) {
				User us = new User();
				Row row = rowIterator.next(); // For each row, iterate through
												// each columns
				row.getOutlineLevel();
				if (row.getRowNum() != 0) {

					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getColumnIndex() == 0) {
							us.setSurname(cell.getStringCellValue());

						}
						if (cell.getColumnIndex() == 1) {
							us.setName(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 2) {
							us.setAddress(cell.getStringCellValue());

						}
						if (cell.getColumnIndex() == 3) {

							us.setSkills(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							us.setProfession(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 5) {
							us.setMotivation(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 6) {
							us.setLogin(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 7) {
							us.setEmail(cell.getStringCellValue());

						}
						if (cell.getColumnIndex() == 8) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

							String newDate = dateFormat.format(cell.getDateCellValue());
							System.out.println(newDate);

							try {
								us.setBirthDate(dateFormat.parse(newDate));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						if (cell.getColumnIndex() == 9) {
							us.setCountry(cell.getStringCellValue());

						}
						if (cell.getColumnIndex() == 10) {
							us.setGender(cell.getStringCellValue());

						}

					}
					listUsersXL.add(us);

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (User user : listUsersXL)
			if (!listUsers.contains(user)) {
				System.out.println("dellaa");
				em.persist(user);
			}

		return 1;
	}

	@Override
	public List<User> searchForUser(String name) {
		TypedQuery<User> query = em.createQuery("select u from User u " + "where u.name LIKE :name "
				+ "or surname LIKE :surname " + "or u.email LIKE :email ", User.class);
		query.setParameter("name", "%" + name + "%");
		query.setParameter("surname", "%" + name + "%");
		query.setParameter("email", "%" + name + "%");

		return query.getResultList();

	}

	@Override
	public boolean updateUser(User user) {
		try {
			em.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public User findUser() {
		long id = 1;
		User us = em.find(User.class, id);
		return us;
	}

	@Override
	public int changePassword(String old, String neew, String username) {

		Query query = em.createQuery("select u from User u where u.login = :login", User.class);
		query.setParameter("login", username);
		User u = (User) query.getSingleResult();

		if (verifPassword(old, u.getPassword())) {
			String newPass = generateHash(neew);
			u.setPassword(newPass);
			SendMail(u.getEmail(), "password changed", "" + " thank you for using our application ");
			return 1;
		}
		return -1;
	}

	// function send mail //
	public boolean SendMail(String to, String subject, String body) {

		final String username = "tiestohd@gmail.com";
		final String password = "bestdelloo1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(body, "text/html");

			Transport.send(message);

			return true;

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}

	/// send email ///

	/// function hash passpword ///

	public static final String SALT = "hamdidellaa";

	public String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

	public boolean verifPassword(String password, String hashPassword) {
		String saltedPassword = password;
		String hashedPassword = generateHash(saltedPassword);
		System.out.println("hhhhh" + " " + hashedPassword);
		if (hashedPassword.equalsIgnoreCase(hashPassword))
			return true;
		return false;

	}
	
	@Override
	public UserRole authenticate(String username, String password) throws Exception {
		
		Query query=  em.createQuery("select u from User u where login=:login");
		query.setParameter("login", username);
		
		List<User> users = query.getResultList();
		if ((users==null ) || (users.isEmpty())) {
			throw new Exception();
		}
		
		if (verifPassword(password, users.get(0).getPassword())==false) {
			throw new Exception();
		}
		
		return users.get(0).getRole();
		
		
	}

	/// hash passowrd end //

}
