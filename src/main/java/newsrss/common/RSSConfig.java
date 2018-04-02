package newsrss.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import newsrss.controller.BaseController;
import newsrss.controller.TestController;
import newsrss.dao.Article;
import newsrss.dao.InterXML;
import newsrss.dao.University;
import newsrss.task.RealTask;

public class RSSConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", TestController.class);
		
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("config.properties");
		//sql
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.addMapping("article", "aid", Article.class);
        arp.addMapping("university", "uid", University.class);
        arp.addMapping("interxml", "xid", InterXML.class);
        me.add(arp);
        //cron
        Cron4jPlugin cp = new Cron4jPlugin();
        String cron = getProperty("cron");
        System.out.println(cron);
        cp.addTask(cron, new RealTask());
        me.add(cp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("baseUrl"));
		
	}
	
	@Override
	public void afterJFinalStart(){
		BaseController.spiderService.init();
	}

}
