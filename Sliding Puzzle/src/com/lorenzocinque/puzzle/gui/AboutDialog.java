package com.lorenzocinque.puzzle.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;
	
	public class AboutDialog extends JDialog {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8150817175852236400L;

		public AboutDialog(JFrame parent) {
			super(parent, true);

			setUndecorated(true);
			AWTUtilities.setWindowOpaque(this, false);

			setTitle("About");

			JLabel label = new JLabel();
			label.setText("<html>Coded by Lorenzo Cinque<br>University of Florence<br>AI class 2012/2013<br>lore.cinque@gmail.com</html>");
			label.setForeground(Color.GREEN);
			label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 12));

			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new GridBagLayout());
			panel.add(label);
			panel.setPreferredSize(new Dimension(300, 200));

			// the following two lines are only needed because there is no
			// focusable component in here, and the "hit espace to close" requires
			// the focus to be in the dialog. If you have a button, a textfield,
			// or any focusable stuff, you don't need these lines.
			panel.setFocusable(true);
			panel.requestFocusInWindow();

			getContentPane().add(panel);

			SwingUtils.createDialogBackPanel(this, parent.getContentPane());
			SwingUtils.addEscapeToCloseSupport(this, true);
		}

}
