package menu;

import java.awt.*;
import javax.swing.*;

/**
 * @version 1.00 2015-02-24
 * @author Isaque Fontinele & Denny O'Dea
 */
public class Facade {
	public Facade() {
		
	}
	
	public void run(){
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new Menu();
              frame.setTitle("ActionTest");
              frame.setLocationRelativeTo(null);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}
}
