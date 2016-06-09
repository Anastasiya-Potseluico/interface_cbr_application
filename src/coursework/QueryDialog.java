/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javax.swing.JDialog;
import javax.swing.JFrame;
import jcolibri.cbrcore.CBRQuery;

/**
 *
 * @author Анастасия
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
//import jcolibri.examples.TravelRecommender.TravelDescription;
//import jcolibri.examples.TravelRecommender.TravelRecommender;
//import jcolibri.examples.TravelRecommender.TravelDescription.AccommodationTypes;
//import jcolibri.examples.TravelRecommender.TravelDescription.Seasons;
import jcolibri.util.FileIO;

public class QueryDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	
	JComboBox carcassesType;
	JComboBox mark;
	JComboBox TiresType;
	JComboBox track;
	JComboBox weather;
	JLabel caseId;
        JTextField Result;
	
	public QueryDialog(JFrame parent)
	{
		super(parent,true);
		configureFrame();
	}
	
	private void configureFrame()
	{
		try
		{
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1)
		{
                    System.out.println(e1.toString());
		}
		
		this.setTitle("Configure Query");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("resources/img/step1.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(8,2));
		panel.setLayout(new SpringLayout());
		
		JLabel label;
		panel.add(label = new JLabel("Атрибут"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel("Значение"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
		panel.add(new JLabel("Тип каркаса"));
		String[] carcassesTypes = {"DIAGONAL", "RADIAL"};
		panel.add(carcassesType = new JComboBox(carcassesTypes));
                
                panel.add(new JLabel("Марка шин"));
		String[] marks = {"VAZ", "KAMA","PIRELLI"};
		panel.add(mark = new JComboBox(marks));
                
                panel.add(new JLabel("Тип шин"));
		String[] TiresTypes = {"PSESLICK", "SLICK", "RAINY"};
		panel.add(TiresType = new JComboBox(TiresTypes));
                
                panel.add(new JLabel("Место гонки"));
		String[] tracks = {"AUTODROME", "CITY"};
		panel.add(track = new JComboBox(tracks));
                
                panel.add(new JLabel("Погода"));
		String[] weathers = {"WET", "DRY"};
		panel.add(weather = new JComboBox(weathers));
                
		Utils.makeCompactGrid(panel,
		                6, 2, //rows, cols
		                6, 6,        //initX, initY
		                10, 10);       //xPad, yPad
		
//		Lay out the panel.
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(panel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Выполнить запрос >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setQuery();
			}
		});
		buttons.add(ok,BorderLayout.CENTER);
		JButton exit = new JButton("Выход");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					InterfaceWidgetRecommender.getInstance().postCycle();
				} catch (Exception ex) {
					org.apache.commons.logging.LogFactory.getLog(InterfaceWidgetRecommender.class).error(ex);
				}
				System.exit(-1);
			}
		});
		buttons.add(exit,BorderLayout.WEST);
		
		panelAux.add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(panelAux, BorderLayout.CENTER);
		
		/**********************************************************/
		
		
		this.pack();
		this.setSize(600, this.getHeight());
		this.setResizable(false);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
			(screenSize.height - this.getHeight()) / 2, 
			getWidth(),
			getHeight());
	}
	
	void setQuery()
	{
		this.setVisible(false);
	}
	
	public CBRQuery getQuery()
	{
            InterfaceDescription desc = new InterfaceDescription();
//            desc.setCARCASSESTYPE(Instance.createInstance(this.carcassesType.getSelectedItem().toString(), "CARCASSESTYPE"));
//            desc.setMARK(Instance.createInstance(this.mark.getSelectedItem().toString(), "MARK"));
//            desc.setTIRESTYPE(Instance.createInstance(this.TiresType.getSelectedItem().toString(), "TIRESTYPE"));
//            desc.setTRACK(Instance.createInstance(this.track.getSelectedItem().toString(), "TRACK"));
//            desc.setWEATHER(Instance.createInstance(this.weather.getSelectedItem().toString(), "WEATHER"));

		
		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		
		return query;
	}
	
	/**
	 * @param args
	public static void main(String[] args) {
		QueryDialog qf = new QueryDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	 */
	

}
