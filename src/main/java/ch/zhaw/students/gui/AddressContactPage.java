package ch.zhaw.students.gui;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


class AddressContactPage extends WizardPage {
  
		  
  static String co=" Afghanistan, Albania, Algeria, American Samoa, Andorra, Angola, Anguilla, Antarctica, Antigua and Barbuda, Argentina, Armenia, Aruba, Australia, Austria, Azerbaijan, Bahamas, Bahrain, Bangladesh, Barbados, Belarus, Belgium, Belize, Benin, Bermuda, Bhutan, Bolivia, Bosnia and Herzegovina, Botswana, Bouvet Island, Brazil, British Indian Ocean Territory, Brunei Darussalam, Bulgaria, Burkina Faso, Burundi, Cambodia, Cameroon, Canada, Cape Verde, Cayman Islands, Central African Republic, Chad, Chile, China, Christmas Island, Cocos (Keeling) Islands, Colombia, Comoros, Congo, Congo, The Democratic Republic of The, Cook Islands, Costa Rica, Cote D'ivoire, Croatia, Cuba, Cyprus, Czech Republic, Denmark, Djibouti, Dominica, Dominican Republic, Ecuador, Egypt, El Salvador, Equatorial Guinea, Eritrea, Estonia, Ethiopia, Falkland Islands (Malvinas), Faroe Islands, Fiji, Finland, France, French Guiana, French Polynesia, French Southern Territories, Gabon, Gambia, Georgia, Germany, Ghana, Gibraltar, Greece, Greenland, Grenada, Guadeloupe, Guam, Guatemala, Guinea, Guinea-bissau, Guyana, Haiti, Heard Island and Mcdonald Islands, Holy See (Vatican City State), Honduras, Hong Kong, Hungary, Iceland, India, Indonesia, Iran, Islamic Republic of, Iraq, Ireland, Israel, Italy, Jamaica, Japan, Jordan, Kazakhstan, Kenya, Kiribati, Korea, Democratic People's Republic of, Korea, Republic of, Kuwait, Kyrgyzstan, Lao People's Democratic Republic, Latvia, Lebanon, Lesotho, Liberia, Libyan Arab Jamahiriya, Liechtenstein, Lithuania, Luxembourg, Macao, Macedonia, The Former Yugoslav Republic of, Madagascar, Malawi, Malaysia, Maldives, Mali, Malta, Marshall Islands, Martinique, Mauritania, Mauritius, Mayotte, Mexico, Micronesia, Federated States of, Moldova, Republic of, Monaco, Mongolia, Montserrat, Morocco, Mozambique, Myanmar, Namibia, Nauru, Nepal, Netherlands, Netherlands Antilles, New Caledonia, New Zealand, Nicaragua, Niger, Nigeria, Niue, Norfolk Island, Northern Mariana Islands, Norway, Oman, Pakistan, Palau, Palestinian Territory, Occupied, Panama, Papua New Guinea, Paraguay, Peru, Philippines, Pitcairn, Poland, Portugal, Puerto Rico, Qatar, Reunion, Romania, Russian Federation, Rwanda, Saint Helena, Saint Kitts and Nevis, Saint Lucia, Saint Pierre and Miquelon, Saint Vincent and The Grenadines, Samoa, San Marino, Sao Tome and Principe, Saudi Arabia, Senegal, Serbia and Montenegro, Seychelles, Sierra Leone, Singapore, Slovakia, Slovenia, Solomon Islands, Somalia, South Africa, South Georgia and The South Sandwich Islands, Spain, Sri Lanka, Sudan, Suriname, Svalbard and Jan Mayen, Swaziland, Sweden, Switzerland, Syrian Arab Republic, Taiwan, Province of China, Tajikistan, Tanzania, United Republic of, Thailand, Timor-leste, Togo, Tokelau, Tonga, Trinidad and Tobago, Tunisia, Turkey, Turkmenistan, Turks and Caicos Islands, Tuvalu, Uganda, Ukraine, United Arab Emirates, United Kingdom, United States, United States Minor Outlying Islands, Uruguay, Uzbekistan, Vanuatu, Venezuela, Viet Nam, Virgin Islands, British, Virgin Islands, U.S., Wallis and Futuna, Western Sahara, Yemen, Zambia, Zimbabwe";
  private static String[] COUNTRY = co.split(",");
  
  private Text addressLine1Text;

  private Text addressLine2Text;

  private Text cityText;

  private Text zipCodeText;

  private Combo stateCombo;

  private ISelection selection;

  private boolean isDisplaySet = false;

  public AddressContactPage(ISelection selection) {
    super("wizardPage");
    setTitle("New Contact");
    setDescription("Please enter contact!");
    this.selection = selection;
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    Label label = new Label(container, SWT.NULL);
    label.setText("&Address Line 1:");

    addressLine1Text = new Text(container, SWT.BORDER | SWT.MULTI);

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    addressLine1Text.setLayoutData(gd);
    addressLine1Text.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText("&Address Line 2:");

    addressLine2Text = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    addressLine2Text.setLayoutData(gd);
    addressLine2Text.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText("&City:");

    cityText = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    cityText.setLayoutData(gd);

    label = new Label(container, SWT.NULL);
    label.setText("&Country:");

    stateCombo = new Combo(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    stateCombo.setLayoutData(gd);
    stateCombo.setItems(COUNTRY);

    label = new Label(container, SWT.NULL);
    label.setText("&Zip Code:");

    zipCodeText = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    zipCodeText.setLayoutData(gd);

    // dialogChanged();
    setControl(container);
  }

  private void dialogChanged() {
    if (this.getGivenName().length() == 0) {
      updateStatus("Given name must be specified!");

      return;
    }

    if (this.getFamilyName().length() == 0) {
      updateStatus("Family name must be specified!");

      return;
    }

    updateStatus(null);
  }

  private void updateStatus(String message) {
    setErrorMessage(message);
    setPageComplete(message == null);
  }

  public String getFamilyName() {
    return addressLine2Text.getText();
  }

  public String getGivenName() {
    return addressLine1Text.getText();
  }

  public String getNickName() {
    return cityText.getText();
  }

  public String getBusinessPhone() {
    return zipCodeText.getText();
  }

  public void setNickName(String name) {
    cityText.setText(name);
  }

  private void createLine(Composite parent, int ncol) {
    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
        | SWT.BOLD);
    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
    gridData.horizontalSpan = ncol;
    line.setLayoutData(gridData);
  }
}
