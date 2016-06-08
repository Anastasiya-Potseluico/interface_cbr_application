/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import jcolibri.cbrcore.CBRCase;
import jcolibri.datatypes.Instance;
import jcolibri.util.FileIO;
/**
 *
 * @author Анастасия
 */

class RevisionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	JComboBox carcassesType;
	JComboBox mark;
	JComboBox TiresType;
	JComboBox track;
	JComboBox weather;
	JLabel caseId;
        JTextField Result;
	
	ArrayList<CBRCase> cases;
	int currentCase;
	
	public RevisionDialog(JFrame main)
	{
		super(main, true);
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
		
		this.setTitle("Revise Cases");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("resources/img/step5.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(8,2));
		panel.setLayout(new SpringLayout());
		
		JLabel label;

		panel.add(label = new JLabel("Описание"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());

		
		panel.add(new JLabel("Тип каркаса"));
		String[] carcassesTypes = {"DIAGONAL", "RADIAL"};
		panel.add(carcassesType = new JComboBox(carcassesTypes));
                
                panel.add(new JLabel("Марка шин"));
		String[] marks = {"VAZ", "KAMA","PIRELLI"};
		panel.add(mark = new JComboBox(marks));
                
                panel.add(new JLabel("Тип шин"));
		String[] TiresTypes = {"PSESLICK", "SLICK","RAINY"};
		panel.add(TiresType = new JComboBox(TiresTypes));
                
                panel.add(new JLabel("Место гонки"));
		String[] tracks = {"AUTODROME", "CITY"};
		panel.add(track = new JComboBox(tracks));
                
                panel.add(new JLabel("Погода"));
		String[] weathers = {"WET", "DRY"};
		panel.add(weather = new JComboBox(weathers));
		
		panel.add(label = new JLabel("Решение"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());
                panel.add(new JLabel("Result"));
                panel.add(Result = new JTextField());
		
                Utils.makeCompactGrid(panel,
		                8, 2, //rows, cols
		                6, 6,        //initX, initY
		                30, 10);       //xPad, yPad
                
//		Lay out the panel.
		JPanel casesPanel = new JPanel();
		casesPanel.setLayout(new BorderLayout());
		casesPanel.add(panel, BorderLayout.CENTER);
		
		JPanel casesIterPanel = new JPanel();
		casesIterPanel.setLayout(new FlowLayout());
		JButton prev = new JButton("<<");
		casesIterPanel.add(prev);
		casesIterPanel.add(caseId = new JLabel("Case id"));
		JButton follow = new JButton(">>");
		casesIterPanel.add(follow);
		
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				currentCase = (currentCase+cases.size()-1) % cases.size();
				showCase();
			}
		});
		
		follow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				currentCase = (currentCase+1) % cases.size();
				showCase();
			}
		});
		
		casesPanel.add(casesIterPanel, BorderLayout.NORTH);
		
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(casesPanel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Применить изменения>>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveCase();
				next();
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
	
	void next()
	{
		this.setVisible(false);
	}
	
	
	public void showCases(Collection<CBRCase> cases)
	{
		this.cases = new ArrayList<CBRCase>(cases);
		currentCase = 0;
		showCase();
	}
	
	void showCase()
	{
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		InterfaceDescription desc = (InterfaceDescription) _case.getDescription();
                
		this.carcassesType.setSelectedItem(desc.getCARCASSESTYPE());
		this.mark.setSelectedItem(desc.getMARK());
		this.TiresType.setSelectedItem(desc.getTIRESTYPE());
		this.track.setSelectedItem(desc.getTRACK());
		this.weather.setSelectedItem(desc.getWEATHER());
		
		InterfaceSolution sol = (InterfaceSolution) _case.getSolution();
		this.Result.setText(sol.getRESULT().toString());
	}
	
	void saveCase()
	{
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		InterfaceDescription desc = (InterfaceDescription) _case.getDescription();
		
		desc.setMARK(Instance.createInstance((String)this.mark.getSelectedItem(), "MARK"));
                desc.setCARCASSESTYPE(Instance.createInstance((String)this.carcassesType.getSelectedItem(), "CARCASSESTYPE"));
                desc.setTIRESTYPE(Instance.createInstance((String)this.TiresType.getSelectedItem(), "TIRESTYPE"));
                desc.setTRACK(Instance.createInstance((String)this.track.getSelectedItem(), "TRACK"));
                desc.setWEATHER(Instance.createInstance((String)this.weather.getSelectedItem(), "WEATHER"));
		
		InterfaceSolution sol = (InterfaceSolution) _case.getSolution();
		sol.setRESULT(Instance.createInstance(sol.getRESULT().toString(), "RESULT"));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RevisionDialog qf = new RevisionDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
