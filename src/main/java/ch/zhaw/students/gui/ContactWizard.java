package ch.zhaw.students.gui;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;


class ContactWizard extends Wizard {
  private BasicContactPage page1;

  private AddressContactPage page2;

  private ISelection selection;

  public ContactWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new BasicContactPage(selection);
    page2 = new AddressContactPage(selection);
    addPage(page1);
    addPage(page2);
  }

  public boolean performFinish() {
    Contact contact = new Contact();
    contact.setFamilyName(page1.getFamilyName());
    contact.setGivenName(page1.getGivenName());
    contact.setBusinessPhone(page1.getBusinessPhone());
    contact.setHomePhone(page1.getHomePhone());
    contact.setEmailAddress(page1.getEmail());

    EntryWindow.addContact(contact);

    return true;
  }
}