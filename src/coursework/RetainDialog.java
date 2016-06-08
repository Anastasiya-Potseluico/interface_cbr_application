/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import jcolibri.cbrcore.CBRCase;
import jcolibri.util.FileIO;

/**
 *
 * @author Анастасия
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.OntologyConnector;
import jcolibri.datatypes.Instance;
//import jcolibri.examples.TravelRecommender.TravelDescription;
//import jcolibri.examples.TravelRecommender.TravelRecommender;
//import jcolibri.examples.TravelRecommender.TravelSolution;
import jcolibri.util.FileIO;

/**
 * Retain dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class RetainDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static int numcases = 0;
	
	JLabel image;
	
	JLabel carcassesType;
	JLabel mark;
	JLabel TiresType;
	JLabel track;
	JLabel weather;
	JLabel caseId;
        JLabel Result;
	JTextField idEditor;
	JButton setId;
	JCheckBox saveCheck;
	
	ArrayList<CBRCase> cases;
	int currentCase;
	
	ArrayList<CBRCase> casesToRetain;
	
	public RetainDialog(JFrame main)
	{
		super(main,true);
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
		
		this.setTitle("Revise cases");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("resources/img/step6.png")));
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
		panel.add(carcassesType = new JLabel(""));
		
		panel.add(new JLabel("Марка шин"));
		panel.add(this.mark = new JLabel());
		
		panel.add(new JLabel("Тип шин"));
		panel.add(this.TiresType = new JLabel());
		
		panel.add(new JLabel("Место гонки"));
		panel.add(this.track = new JLabel());
		
		panel.add(new JLabel("Погода"));
		panel.add(this.weather = new JLabel());
		
		panel.add(label = new JLabel("Решение"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panel.add(label = new JLabel());

		
		panel.add(new JLabel("Результат"));
		panel.add(Result = new JLabel());
		
                Utils.makeCompactGrid(panel,
		                8, 2, //rows, cols
		                6, 6,        //initX, initY
		                30, 10); 
                
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
				currentCase = (currentCase+cases.size()-1) % cases.size();
				showCase();
			}
		});
		
		follow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				currentCase = (currentCase+1) % cases.size();
				showCase();
			}
		});
		
		casesPanel.add(casesIterPanel, BorderLayout.NORTH);
		
		
		JPanel defineIdsPanel = new JPanel();
		saveCheck = new JCheckBox("Сохранить Case с новым Id:");
		defineIdsPanel.add(saveCheck);
		saveCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				enableSaveCase();
			}
		});
		idEditor = new JTextField(20);
		defineIdsPanel.add(idEditor);
		setId = new JButton("Применить");
		defineIdsPanel.add(setId);
		
		setId.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setId();
			}
		});
		enableSaveCase();
		
		casesPanel.add(defineIdsPanel, BorderLayout.SOUTH);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(casesPanel,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		JButton ok = new JButton("Далее >>");
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
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
	
	void enableSaveCase()
	{
		idEditor.setEnabled(saveCheck.isSelected());
		setId.setEnabled(saveCheck.isSelected());
	}
	
	public void showCases(Collection<CBRCase> eval, int casebasesize)
	{
		cases = new ArrayList<CBRCase>(eval);
		casesToRetain = new ArrayList<CBRCase>();
		currentCase = 0;
		if(numcases<casebasesize)
			numcases = casebasesize+1;
		idEditor.setText("Racing"+(++numcases));
		showCase();
	}
	
	void showCase()
	{
		
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString()+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		InterfaceDescription desc = (InterfaceDescription) _case.getDescription();
                
		this.carcassesType.setText(desc.getCARCASSESTYPE().toString());
		this.mark.setText(desc.getMARK().toString());
		this.TiresType.setText(desc.getTIRESTYPE().toString());
		this.track.setText(desc.getTRACK().toString());
		this.weather.setText(desc.getWEATHER().toString());
		
		InterfaceSolution sol = (InterfaceSolution) _case.getSolution();
		this.Result.setText(sol.getRESULT().toString());
	}
	
	
	void setId()
	{
		CBRCase _case = cases.get(currentCase);
		cases.remove(_case);
		
		InterfaceDescription desc = (InterfaceDescription) _case.getDescription();
                OntologyConnector connector = (OntologyConnector)InterfaceWidgetRecommender.getInstance().getOntoConnector();
		desc.setCASEID(Instance.createInstance(idEditor.getText(), connector.getCaseMainConcept()));
		InterfaceSolution sol = (InterfaceSolution) _case.getSolution();
                
		sol.setId(Instance.createInstance(idEditor.getText(),connector.getCaseMainConcept()));
		casesToRetain.add(_case);
		
		currentCase = 0;
		idEditor.setText("Racing"+(++numcases));
		saveCheck.setSelected(false);
		enableSaveCase();
		showCase();
	}
	
	
	public Collection<CBRCase> getCasestoRetain()
	{
		return casesToRetain;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RetainDialog qf = new RetainDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	

}
