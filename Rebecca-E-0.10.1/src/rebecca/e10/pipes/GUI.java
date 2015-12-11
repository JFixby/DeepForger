/*
Rebecca-E
    Copyright (C) 2008  by Sergey Stankevich (robotics@icstweb.org),

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


 */
package rebecca.e10.pipes;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;

public class GUI extends Pipe {
	Slot<String[]> out;
	String[] s;
	Object l = new Object();
	Form f;

	public GUI(Slot<String[]> out) {
		super("Do nothing.");
		this.out = out;
		this.registerOutSlot(out);
	}

	public void Execute() {
		synchronized (l) {

			this.out.PushDATA(s, this);
		}

	}

	public void Init() {
		this.s = new String[3];
		s[0] = "" + 0.08;
		s[1] = "" + 500;
		s[2] = "" + 50;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Form jtfTfDemo = new Form(s, l);
		jtfTfDemo.pack();
		jtfTfDemo.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		jtfTfDemo.setVisible(true);

	}

	public void ShutDown() {
		this.f.dispose();
	}
}

class Form extends JFrame implements ActionListener {

	JTextField jtfInput1;
	JTextField jtfInput2;
	JTextField jtfInput3;
	String newline = "\n";
	String[] s;
	Object l;
	JButton jbnButton1;

	public Form(String[] s, Object l) {
		this.l = l;
		this.setLocation(700, 400);
		this.s = s;
		createGui();

	}

	public void createGui() {
		jbnButton1 = new JButton("Set");
		jtfInput1 = new JTextField(20);
		jtfInput2 = new JTextField(20);
		jtfInput3 = new JTextField(20);
		jtfInput1.setText(s[0]);
		jtfInput2.setText(s[1]);
		jtfInput3.setText(s[2]);
		// jtfInput1.addActionListener(this);

		GridBagLayout gridBag = new GridBagLayout();
		Container contentPane = getContentPane();
		contentPane.setLayout(gridBag);
		GridBagConstraints gridCons1 = new GridBagConstraints();
		gridCons1.gridwidth = GridBagConstraints.REMAINDER;
		gridCons1.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(jtfInput1, gridCons1);
		contentPane.add(jtfInput2, gridCons1);
		contentPane.add(jtfInput3, gridCons1);
		contentPane.add(jbnButton1, gridCons1);
		jbnButton1.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == jbnButton1)
			synchronized (l) {
				this.s[0] = jtfInput1.getText();
				this.s[1] = jtfInput2.getText();
				this.s[2] = jtfInput3.getText();
			}
		// jtAreaOutput.append(text + newline);
		// jtfInput.selectAll();
	}

}