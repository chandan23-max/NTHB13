package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.nt.entity.Product;


public class ProductTestAnn {

	public static void main(String[] args) {
		 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/nt/cfg/hibernate.cfg.xml").build();  
		    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build(); 
		    SessionFactory factory=meta.getSessionFactoryBuilder().build();
		    Session ses= factory.openSession();
		    Boolean flag=true;
		    Transaction tx=null;
		   
		    //create the object of table
		    Product p=new Product();
		    p.setPid(17);
		    p.setPname("mobile");
		    p.setQty(3);
		    p.setPrice(2001);
		    //ses.save(p);
			/*
			 * //commit the tx tx.commit(); System.out.println("Successfully saved");
			 * factory.close(); ses.close();
			 */
		    
		    try {
				//begin tx
				tx=ses.beginTransaction();
				ses.save(p);
				flag=true;
				
			}
			catch(HibernateException he) {
				flag=false;
				he.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				if(flag) {
					tx.commit();
					System.out.println("Object is saved ");
				}
				else {
					tx.rollback();
					System.out.println("Object is not saved");
				}
				factory.close();
				ses.close();
			}//finally
		    
		    
	
	}

}
