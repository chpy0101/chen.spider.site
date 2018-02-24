import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class main {

	private SqlSessionFactory sqlsessionfactory;//存储SqlSessionFactory 对象

	public void star() throws Exception{
		//设置配置路径，mybitis是以SqlMapConfig.xml为主路径。因为SqlMapConfig中的mapper关联了user.xml
		//因为在config根目录下，所以可以直接引用而不用带config
		String resource = "SqlMapConfig.xml";
		//SqlMapConfig.xml读给输入流，使用mybitis的Resources类下的getResourceAsStream实现
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建Mybitis的SqlSessionFactory工厂类
		sqlsessionfactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
}
