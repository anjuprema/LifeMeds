package com.lifemeds.admin.listener;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.annotations.common.util.impl.LoggerFactory;



@WebListener
public class SessionListener implements HttpSessionListener {

    //private static final Logger LOG= LoggerFactory.getLogger(SessionListener.class);

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        //LOG.info("New session is created. Adding Session to the counter.");
    	System.out.println("New session is created");
        counter.incrementAndGet();  //incrementing the counter
        updateSessionCounter(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //LOG.info("Session destroyed. Removing the Session from the counter.");
    	System.out.println("Session destroyed");
        counter.decrementAndGet();  //decrementing counter
        updateSessionCounter(se);
    }

    private void updateSessionCounter(HttpSessionEvent httpSessionEvent){
        //Let's set in the context
        httpSessionEvent.getSession().getServletContext()
                .setAttribute("activeSession", counter.get());
        //LOG.info("Total active session are {} ",counter.get());
    }
}