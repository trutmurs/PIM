package ch.zhaw.students.gui;
import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class EntryWindow extends ApplicationWindow {
  private static ArrayList contactList;
  private Table table_1;
  private Table table_2;
  private static TableViewer tableViewer_1;
  private static TableViewer tableViewer_2;
  private Table table_3;
  private Table table_4;
  public EntryWindow() {
    super(null);
    this.addMenuBar();

    contactList = new ArrayList();
  }

  protected Control createContents(Composite parent) {
    Shell shell = this.getShell();
    shell.setText("Personal Information Manager");
    shell.setSize(500, 300);

    SashForm form = new SashForm(parent, SWT.HORIZONTAL | SWT.NULL);
    
    TabFolder tabFolder = new TabFolder(form, SWT.NONE);
    
   
    
    TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
    tabItem.setText("Inbox");
    
    table_3 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
    table_3.setHeaderVisible(true);
    tabItem.setControl(table_3);
    
    TableColumn tableColumn_4 = new TableColumn(table_3, SWT.LEFT);
    tableColumn_4.setWidth(250);
    tableColumn_4.setText("Subject");
    
    TableColumn tableColumn_5 = new TableColumn(table_3, SWT.LEFT);
    tableColumn_5.setWidth(125);
    tableColumn_5.setText("E-Mail Address");
    
    TableColumn tableColumn_6 = new TableColumn(table_3, SWT.LEFT);
    tableColumn_6.setWidth(100);
    tableColumn_6.setText("Category");
    
   
     tableViewer_1 = new TableViewer(table_3);
    
   
    
    TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
    tabItem_1.setText("Contacts");
    
    table_4 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
    table_4.setHeaderVisible(true);
    tabItem_1.setControl(table_4);
    
    TableColumn tableColumn_8 = new TableColumn(table_4, SWT.LEFT);
    tableColumn_8.setWidth(150);
    tableColumn_8.setText("Name");
    
    TableColumn tableColumn_9 = new TableColumn(table_4, SWT.LEFT);
    tableColumn_9.setWidth(125);
    tableColumn_9.setText("E-Mail Address");
    
    TableColumn tableColumn_10 = new TableColumn(table_4, SWT.LEFT);
    tableColumn_10.setWidth(100);
    tableColumn_10.setText("Business Phone");
    
    TableColumn tableColumn_11 = new TableColumn(table_4, SWT.LEFT);
    tableColumn_11.setWidth(100);
    tableColumn_11.setText("Home Phone");
     tableViewer_2 = new TableViewer(table_4);
    form.setWeights(new int[] {1});

    return form;
    
  }

  public static void main(String[] args) {
    EntryWindow book = new EntryWindow();

    book.setBlockOnOpen(true);
    book.open();

    Display.getCurrent().dispose();
  }

  protected MenuManager createMenuManager() {
    MenuManager bar = new MenuManager("");

    MenuManager fileMenu = new MenuManager("&File");
    MenuManager helpMenu = new MenuManager("&Help");
    MenuManager settingsMenu = new MenuManager("&Settings");
    bar.add(fileMenu);
    bar.add(helpMenu);
    bar.add(settingsMenu);
    fileMenu.add(new NewContactAction(this));
    fileMenu.add(new ExitAction(this));

    helpMenu.add(new AboutAction(this));

    return bar;
  }

  public static void addContact(Contact c) {
    getContactList().add(c);

    Table table = tableViewer_2.getTable();

    TableItem item = new TableItem(table, SWT.NULL);
    item.setText(0, c.getGivenName() + " " + c.getFamilyName());
    item.setText(1, c.getEmailAddress());
    item.setText(2, c.getBusinessPhone());
    item.setText(3, c.getHomePhone());
  }

  public static ArrayList getContactList() {
    return contactList;
  }

  public static TableViewer getTbv() {
    return tableViewer_2;
  }

  public static void setContactList(ArrayList list) {
    contactList = list;
  }

  public static void setTbv(TableViewer viewer) {
    tableViewer_2 = viewer;
  }
}

class NewMailAction extends Action {
	  ApplicationWindow window;

	/*  public NewContactAction(ApplicationWindow w) {
	    window = w;
	    this.setText("New Mail");
	    this.setToolTipText("Create new email");
	  }

	  public void run() {
	    ContactWizard wizard = new ContactWizard();

	    WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
	    dialog.open();
	  }*/
	}


class NewContactAction extends Action {
  ApplicationWindow window;

  public NewContactAction(ApplicationWindow w) {
    window = w;
    this.setText("New Contact");
    this.setToolTipText("Create new contact");
  }

  public void run() {
    ContactWizard wizard = new ContactWizard();

    WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
    dialog.open();
  }
}

class ExitAction extends Action {
  ApplicationWindow window;

  public ExitAction(ApplicationWindow w) {
    window = w;
    this.setText("E&xit");

    this.setToolTipText("Exit the application");
  }

  public void run() {
    window.close();
  }
}

class AboutAction extends Action {
  ApplicationWindow window;

  public AboutAction(ApplicationWindow w) {
    window = w;
    this.setText("&PIM");

    this.setToolTipText("Exit PIM");
  }

  public void run() {
    MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
        "About PIM", "MdP: Methoden der Programmierung 2012, Fallstudie Personal Information Manager");
  }
}








