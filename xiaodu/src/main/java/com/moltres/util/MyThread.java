package com.moltres.util;

import java.util.concurrent.ThreadPoolExecutor;

import org.b3log.latke.ioc.LatkeBeanManager;
import org.b3log.latke.ioc.Lifecycle;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.xiaov.processor.XiaoVGetUpServlet;
import org.b3log.xiaov.service.QQService;

public class MyThread extends Thread{
	
	int i = 1;
	public MyThread()
	{
		
	}

	public MyThread(int i)
	{
		this.i = i;
	}
	
    private static final Logger LOGGER = Logger.getLogger(XiaoVGetUpServlet.class);

    /**
     * Bean manager.
     */
    private LatkeBeanManager beanManager;
    public void f()
    {
    	//ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 100000, unit, workQueue);
    }
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        beanManager = Lifecycle.getBeanManager();

        final QQService qqService = beanManager.getReference(QQService.class);
        qqService.initQQClient(i);
    }
}
