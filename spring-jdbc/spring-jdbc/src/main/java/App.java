import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("Loaded");
		JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		System.out.println("Got template");
		// read
		// DQL
		// selct * from emp where id=1;
		RowMapper<Employee> rMapper = (rs, r) -> {
			Employee emp = new Employee();
			emp.setId(rs.getInt(1));
			emp.setName(rs.getString(2));
			emp.setSal(rs.getDouble(3));
			return emp;
		};

		 List<Employee> list = jdbcTemplate.query("select * from emp", rMapper);
		System.out.println("emp data is " + list);
	}

	private static void createRowInDB(JdbcTemplate jdbcTemplate) {
		int rowsUpdated = jdbcTemplate.update("insert into emp values(?,?,?)", Integer.valueOf(255), "HK", 1256.3);
		System.out.println("rows inserted " + rowsUpdated);
	}

}
