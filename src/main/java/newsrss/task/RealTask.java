package newsrss.task;

import newsrss.controller.BaseController;

public class RealTask implements Runnable{

	@Override
	public void run() {
		BaseController.spiderService.cronStart();
		
	}

}
