package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Inventory.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // Insert Record
        Inventory inv = new Inventory();
        inv.setName("Laptop");
        inv.setDescription("Dell Laptop");
        inv.setDate(new Date());
        inv.setStatus("Available");

        session.save(inv);
        System.out.println("Record Inserted Successfully");

        tx.commit();

        // Delete Record
        session = sf.openSession();
        tx = session.beginTransaction();

        Inventory obj = session.get(Inventory.class, inv.getId());
        if(obj != null)
        {
            session.delete(obj);
            System.out.println("Record Deleted Successfully");
        }

        tx.commit();

        session.close();
        sf.close();
    }
}
