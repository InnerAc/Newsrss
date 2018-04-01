package newsrss.spider;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

public class LevelLimitScheduler extends PriorityScheduler {

    private int levelLimit = 3;

    public LevelLimitScheduler(int levelLimit) {
        this.levelLimit = levelLimit;
    }

    @Override
    public synchronized void push(Request request, Task task) {
        if(request.getExtra("_level") == null){
        	super.push(request, task);
        }else if (((Integer) request.getExtra("_level")) <= levelLimit) {
            super.push(request, task);
        }else {
			System.out.println(request.getUrl());
		}
    }
}
