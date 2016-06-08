/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;
import arq.query;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import java.awt.Dimension;
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.method.reuse.NumericDirectProportionMethod;
import jcolibri.util.FileIO;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import jcolibri.connector.OntologyConnector;

/**
 * Класс-синглтон для рекомендации виджетов для интерфейса
 * @author Анастасия
 */
public class InterfaceWidgetRecommender implements StandardCBRApplication { 

    /**
     * Единственный инстанс класса для рекомендациивиджетов
     */
    private static InterfaceWidgetRecommender _instance = null;
    
    // Диалоги для работы фреймворка
    private SimilarityDialog _similarityDialog;
    private ResultDialog _resultDialog;
    private AutoAdaptationDialog _autoAdaptationDialog;
    private RevisionDialog _revisionDialog;
    private RetainDialog _retainDialog;
    private static JFrame _mainFrame;
    private OntoBridge ontoBridge;
    
    // Коннектор к базе данных
    Connector _dbConnector;
    // База данных прецедентов
    CBRCaseBase _caseBase;
    
    /**
     * Метод для получения инстанса синглтона
     * @return 
     */ 
    public static InterfaceWidgetRecommender getInstance() {
        if(_instance == null) {
            _instance = new InterfaceWidgetRecommender();
        }
        return _instance;
    }
    
    private InterfaceWidgetRecommender() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Создать объект приложения
        InterfaceWidgetRecommender recommenderer= getInstance();
        // Показать главное окно программы
        recommenderer.ShowMainFrame();
        // Реализовать трехступенчатую работу приложения
        try {
            // Сконфигурировать приложение
            recommenderer.configure();
            // Вызвать функционал, необходимый до запуска цикла
            recommenderer.preCycle();
            // Вызвать диалог для создания запроса
            QueryDialog queryDialog = new QueryDialog(_mainFrame);
            // Вызвать цикл работы программы
            boolean cycleIsContinuing = true;
            while (cycleIsContinuing) {
                // Показать диалог для создания запроса
                queryDialog.setVisible(true);
                // Запустить выполнение запроса
                CBRQuery query = queryDialog.getQuery();
                // Запустить основной цикл CBR
                recommenderer.cycle(query);
                // Запросить повторное выполнение цикла
                int answer = javax.swing.JOptionPane.showConfirmDialog(null, 
                        "CBR-цикл завершен, запустить цикл снова?",
                        "Цикл завершен", javax.swing.JOptionPane.YES_NO_OPTION);
                cycleIsContinuing = (answer == javax.swing.JOptionPane.YES_OPTION);
            }
            // Вызвать пост-обработку после выполнения цикла
            recommenderer.postCycle();

        } catch (Exception exception) {
            // Отлов исключений программы
            javax.swing.JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        System.exit(0);
    }

    /**
     * Метод для конфигурации приложения: коннекторы, решатель и т.д.
     * @throws ExecutionException 
     */
    @Override
    public void configure() throws ExecutionException {
        try {
            //Emulate data base server
            //jcolibri.test.database.HSQLDBserver.init();
            
            //URL url = getClass().getResource("/resources/dbresources/databaseconfig.xml");
            URL url = getClass().getResource("/resources/dbresources/ontologyconfig.xml");
            
            _dbConnector = new OntologyConnector();
            _dbConnector.initFromXMLfile(url);
            _caseBase = new LinealCaseBase();
            ontoBridge = new OntoBridge();
            // Создать диалоги
            _similarityDialog = new SimilarityDialog(_mainFrame);
            _resultDialog = new ResultDialog(_mainFrame);
            _autoAdaptationDialog = new AutoAdaptationDialog(_mainFrame);
            _revisionDialog = new RevisionDialog(_mainFrame);
            _retainDialog = new RetainDialog(_mainFrame);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            throw new ExecutionException(exception);     
        }
        
    }

    /**
     * Метод для записи заданных уже кейсов в базу
     * @return Созданное хранилище прецедентов
     * @throws ExecutionException 
     */
    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        // Загрузка прецедентов 
        _caseBase.init(_dbConnector);		
	// Распечатка прецедентов
	java.util.Collection<CBRCase> cases = _caseBase.getCases();
	for(CBRCase c: cases)
            System.out.println(c);
	return _caseBase;
    }
    
    public Connector getOntoConnector() {
        return _dbConnector;
    }

    /**
     * Выполняет CBR-цикл приложения с заданным запросом
     * @param cbrq Запрос для выполнения
     * @throws ExecutionException 
     */    
    @Override
    public void cycle(CBRQuery cbrq) throws ExecutionException {
       // Obtain configuration for KNN
		_similarityDialog.setVisible(true);
		NNConfig simConfig = _similarityDialog.getSimilarityConfig();
		simConfig.setDescriptionSimFunction(new Average());
		
		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), cbrq, simConfig);
		
		// Select k cases
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, _similarityDialog.getK());
		
		// Show result
		_resultDialog.showCases(eval, _caseBase.getCases());
		_resultDialog.setVisible(true);
		
		// Show adaptation dialog
		_autoAdaptationDialog.setVisible(true);
		
		// !! Adapt depending on user selection
		if(_autoAdaptationDialog.adapt_Weather_Result())
		{
			// Compute a direct proportion between the "Weather" and "Result" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("WEATHER",InterfaceDescription.class), 
				 new Attribute("RESULT",InterfaceSolution.class), 
				 cbrq, selectedcases);
		}
		
		if(_autoAdaptationDialog.adapt_Tirestype_Result())
		{
			// Compute a direct proportion between the "Tires type" and "Result" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("TIRESTYPE",InterfaceDescription.class), 
				 						new Attribute("RESULT",InterfaceSolution.class), 
				 						cbrq, selectedcases);
		}
		
		// Revise
		_revisionDialog.showCases(selectedcases);
		_revisionDialog.setVisible(true);
		
		// Retain
		_retainDialog.showCases(selectedcases, _caseBase.getCases().size());
		_retainDialog.setVisible(true);
		Collection<CBRCase> casesToRetain = _retainDialog.getCasestoRetain();
		_caseBase.learnCases(casesToRetain);
    }

    /**
     * Выполняет код для завершения приложения: закрывает соединения и т.д.
     * @throws ExecutionException 
     */
    @Override
    public void postCycle() throws ExecutionException {
        _dbConnector.close();
    }
    
    /**
     * Метод для показа главного окна приложения 
     */
    private void ShowMainFrame() {
        _mainFrame = new JFrame("Interface Widgets Recommender");
        _mainFrame.setResizable(true);
        _mainFrame.setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/resources/img/jcolibri2.jpg")));
        _mainFrame.getContentPane().add(label);
        _mainFrame.pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();
        _mainFrame.setBounds((screenSize.width - _mainFrame.getWidth())/2,
                             (screenSize.height - _mainFrame.getHeight())/2,
                             _mainFrame.getWidth(), _mainFrame.getHeight());
        _mainFrame.setVisible(true);
    }
    
}
