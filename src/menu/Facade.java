package menu;

import java.awt.*;
import javax.swing.*;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
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
