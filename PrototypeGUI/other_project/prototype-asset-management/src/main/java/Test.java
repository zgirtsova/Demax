
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Project;
import orm.dao.PictureDao;
import orm.dao.ProjectDao;
import orm.dao.TestDao;
import orm.dto.NewProductDto;
import orm.dto.NewProjectDto;
import orm.dto.PictureDto;
import util.CurrentConnection;
import util.Filter;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// try {
		// 	TransactionsService tService = TransactionsService.getTransactionsServiceInstance();
		// 	PictureDao pictureDao = PictureDao.getPictureDaoInstance();
		// 	String email = "wat";
		// 	NewProjectDto proj = new NewProjectDto();
		// 	proj.setCompanyName("work2");
		// 	proj.setProjectManager("work2");
		// 	proj.setProjectName("work2");
		// 	proj.setStatus("New");
		// 	NewProductDto prod = new NewProductDto();
		// 	prod.setDescription("workssss2");
		// 	prod.setProductName("work2");
		// 	prod.setProjectName("c");
		// 	prod.setQuantity(6);
		// 	prod.setSerialNumber("wor2");
		// 	prod.setStatus("New");
		// 	PictureDto pic = new PictureDto();
		// 	pic.setPicture(new String(Base64.getEncoder().encode(pictureDao.getById("c57ee8626a48a5b8f8134a20476253facc497637d83a8eee0b615b8a3f1f6e91").getPicture())));
		// 	tService.testTransaction(proj, email, prod, pic);
		// } catch (ClassNotFoundException | SQLException e) {
		// 	e.printStackTrace();
		// }
		
		// TODO Auto-generated method stub
//		System.out.println("Hello");
		// try {
		// 	ProjectDao p = ProjectDao.getProjectDaoInstance();
		// 	Project pr = p.getById(2);
		// 	pr.setStatus(3);
		// 	p.update(pr);
		// } catch (ClassNotFoundException | SQLException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
		// response.getWriter().append("Served at: ").append(request.getContextPath());
//		Filter filter = new Filter(null, null, null, null, null, null, false, false, true);
//		Integer key = 0;
//		key ^= 0;
//		key ^= 1 <<1;
//		key ^= 1 << 2;
//		key ^= 1 <<3;
//		key ^= filter.sortRule << 4;
//		System.out.println(Byte.parseByte(Integer.toBinaryString(key),2));
		
		
		
		
		
		
		
// 		try {


// //			Dao<User> user = new UserDao();
// //			ProductDao product = new ProductDao();
// //			ProjectDao project = new ProjectDao();
// //			Dao<Action> action = new ActionDao();
// //			Dao<Picture> picture = new PictureDao();
// //			System.out.println(System.getProperty("java.version"));
// //			for(int i=0;i<100;i++) {
// //				Product pro = new Product();
// //				pro.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
// //				pro.setDescription("adgdgasdgsgsfgs");
// //				pro.setName("adgadgsdgsdgd");
// //				pro.setProjectId(2);
// //				pro.setQuantity(1241);
// //				pro.setSerialNumber("a"+i);
// //				pro.setStatus(1);
// //				product.insert(pro);
// //			}
// //			List<Project> list = project.filter(null, Timestamp.valueOf(LocalDateTime.now()), null);
// //			System.out.println(list);
// //			for(Project prod : list) {
// //				System.out.println(prod.getName()+"   "+prod.getCreatedOn()+"   "+prod.getNumOfProducts());
// //			}
			
// //			product.getPerPage();
// //			
// //			List<Product> list = product.getPerPage();
// //			for(Product prod : list) {
// //				System.out.println(prod.getSerialNumber()+"   "+prod.getCreatedOn());
// //			}
			
			
			
			
// //			User userEnt = new User();
// //			Product prod = new Product();
// //			Project proj = new Project();
// //			Action acti = new Action();
// //			Picture pic = new Picture();
			
			
// //			proj.setCompanyName("DEMAX AD");
// //			proj.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
// //			proj.setName("PROTOOOO");
// //			proj.setProjectManager("Who knows");
// //			proj.setStatus(1);
// //			project.insert(proj);
// //			project.getById(1);
// //			proj.setId(2);
// //			proj.setCompanyName("UPDATED");
// //			project.update(proj);
// //			project.getById(2).getCompanyName();
// //			project.delete(2);
// //			
// //			pic.setPicture(fsg);
// //			pic.setProductId(1);
// //			picture.insert(pic);
// //			System.out.println(picture.getById(5).getPicture());
// //			pic.setId(5);
// //			picture.update(pic);
// //			picture.getById(5);
// //			picture.delete(2);
			
// //			prod.setDescription("UPDATED");
// //			prod.setName("UPDATED");
// //			prod.setProjectId(1);
// //			prod.setQuantity(153);
// //			prod.setStatus(1);
// //			prod.setSerialNumber("UPDA");
// //			product.insert(prod);
// //			prod.setId(1);
// //			System.out.println(product.getById(1).getName());
// //			product.update(prod);
// //			System.out.println(product.getById(1).getName());
// //			product.delete(1);
			
			
// //			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
// //			LocalDateTime now = LocalDateTime.now();  
// //			acti.setDate(Timestamp.valueOf(now));
// //			acti.setActionName("hype2222");
// //			acti.setUserName("nope222");
// //			action.insert(acti);
// //			acti.setId(2);
// //			System.out.println(action.getById(3).getActionName());
// //			action.update(acti);
// //			System.out.println(action.getById(3).getActionName());
// //			action.delete(2);

			
// //			userEnt.setEmail("asdasdasadss@asdasdasda");
// //			userEnt.setFirstName("sdhAHSDAsg");
// //			userEnt.setLastName("gasdgdgs");
// //			userEnt.setPassword("shashashahshahshahahshassf");
// //			userEnt.setRole("bkqblqbqlbq");
// //			user.insert(userEnt);
// //			userEnt.setId(4);
// //			userEnt.setEmail("ahfhdjfsssh@ajfghsj.bg");
// //			user.update(userEnt);
// //			System.out.println(user.getById(4).extractFullName());
// //			user.delete(4);
// //			System.out.println(((UserDao)user).getUserByEmail("asdasdasadss@asdasdasda").extractFullName());
			
			
// //			ClassLoader cl = getClass().getClassLoader();
// //			File fsg = new File(cl.getResource("test.png").getFile());
// //			FileInputStream fff = new FileInputStream(fsg);
// //			System.out.println(fff.readAllBytes().length);
// //			System.out.println(p.getPicture());
// //			System.out.println(fis);
// //			System.out.println("Updated "+d.update(p));

// //			Product test = d.getById(2);
// //			System.out.println(test.getDescription());

// 			// PreparedStatement stmt = connection.prepareStatement("SELECT version()");
// //		} catch (SQLException e) {
// //			// TODO Auto-generated catch block
// //			e.printStackTrace();
// 		} catch (Exception e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}

	}
}
