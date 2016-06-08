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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import jcolibri.cbrcore.CBRCase;
//import jcolibri.examples.TravelRecommender.TravelDescription;
//import jcolibri.examples.TravelRecommender.TravelRecommender;
//import jcolibri.examples.TravelRecommender.TravelSolution;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.util.FileIO;

/**
 * Result dialog
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class ResultDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	
        JLabel carcassesType;
	JLabel mark;
	JLabel TiresType;
	JLabel track;
	JLabel weather;
	JLabel caseId;
        JLabel Result;
	
	ArrayList<RetrievalResult> cases;
	int currentCase;
	public ResultDialog(JFrame main)
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
		
		this.setTitle("Извлеченные решения");

		
		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile("resources/img/step3.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);
		
		
		/**********************************************************/
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8,2));
		//panel.setLayout(new SpringLayout());
		
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
	
	
	public void showCases(Collection<RetrievalResult> eval, Collection<CBRCase> selected)
	{
		cases = new ArrayList<RetrievalResult>();
		for(RetrievalResult rr: eval)
			if(selected.contains(rr.get_case()))
				cases.add(rr);
		currentCase = 0;
		showCase();
	}
	
	void showCase()
	{
		RetrievalResult rr = cases.get(currentCase);
		double sim = rr.getEval();
		
		CBRCase _case = rr.get_case();
		this.caseId.setText(_case.getID().toString()+" -> "+sim+" ("+(currentCase+1)+"/"+cases.size()+")");
		           
                InterfaceDescription desc = (InterfaceDescription) _case.getDescription();
                
		this.carcassesType.setText(desc.getCARCASSESTYPE().toString());
		this.mark.setText(desc.getMARK().toString());
		this.TiresType.setText(desc.getTIRESTYPE().toString());
		this.track.setText(desc.getTRACK().toString());
		this.weather.setText(desc.getWEATHER().toString());
		
		InterfaceSolution sol = (InterfaceSolution) _case.getSolution();
		this.Result.setText(sol.getRESULT().toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResultDialog qf = new ResultDialog(null);
		qf.setVisible(true);
		System.out.println("Завершение работы");
	}

	

}
