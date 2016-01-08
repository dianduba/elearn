package com.naijux.elearn.http;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.naijux.elearn.BookStore;
import com.naijux.elearn.BookStoreLoader;
import com.naijux.elearn.FileBookStoreLoader;

/**
 * Application Lifecycle Listener implementation class ElearnListener
 *
 */
@WebListener
public class ElearnListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ElearnListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	BookStoreLoader loader = new FileBookStoreLoader(event.getServletContext().getRealPath("/books"), 
    			                                         event.getServletContext().getContextPath());
    	loader.load(BookStore.getInstance());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         BookStore.getInstance().clear();
    }
	
}
