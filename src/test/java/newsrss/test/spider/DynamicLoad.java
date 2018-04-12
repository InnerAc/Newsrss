package newsrss.test.spider;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class DynamicLoad {

	public void test(){
		JavaCompiler javac;
		javac = ToolProvider.getSystemJavaCompiler();
		javac.run(null, null, null, "-g","-verbose","Hello.java");
		
	}
}
