
package jtextfieldfindreplacetext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author pawelstradomski
 */
public class JTextFieldFindReplaceText extends JFrame
{
    public JTextFieldFindReplaceText()
    {
        super("Obszar tekstowy, znajdz i zamien text");
        this.setBounds(500, 300, 500, 200);
        this.setDefaultCloseOperation(3);
        initComponent();
    }
    public void initComponent()
    {
     this.getContentPane().add(panelSuwaka, BorderLayout.NORTH);
     this.getContentPane().add(panel, BorderLayout.CENTER);
     panel.add(buttonZnajdz);
     panel.add(szukany);
     panel.add(buttonZamien);
     panel.add(etykieta);
     panel.add(nowyTekst);
     buttonZnajdz.addActionListener(new buttonZnajdzHandler());
     buttonZamien.addActionListener(new buttonZamienHandler());
     
     
//     obszarTekstowy.selectAll(); //zaznacza caly tekst
//     obszarTekstowy.select(0, 2);//zaznacza zakres od poz.0-2
//     obszarTekstowy.replaceSelection("Tak Tak");//zamienia zaznaczony tekst
//     obszarTekstowy.replaceRange("TakTak", 0, 2);//zamienia tekst od poz.0-2(dziala jak dwa powyzsze)
//     obszarTekstowy.insert("TakTak", 3);//wstawia tekst w wyznaczona pozycje
//     obszarTekstowy.append("DolaczaTekstNaKoncu");
//     obszarTekstowy.select(obszarTekstowy.getText().indexOf("test"), obszarTekstowy.getText().indexOf("test")+"test".length());
     
    }
    private class buttonZnajdzHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            poczatekSzukania = obszarTekstowy.getText().indexOf(szukany.getText(),poczatekSzukania+szukany.getText().length());//indexOf - szukana fraza/index
            
            System.out.println(poczatekSzukania);//wypisuje pozycje gdzie znanazlo indexOf
            if(poczatekSzukania == -1)
            {
                poczatekSzukania = obszarTekstowy.getText().indexOf(szukany.getText());
            }
            if(poczatekSzukania >= 0 && szukany.getText().length() >= 0)
            {
                obszarTekstowy.requestFocus();
                obszarTekstowy.select(poczatekSzukania, poczatekSzukania+szukany.getText().length());
            }
        }
        private int poczatekSzukania = 0;
        
    }
    private class buttonZamienHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(obszarTekstowy.getSelectionStart() != obszarTekstowy.getSelectionEnd())
            {
                zamienTekst();
            }
            else
            {
                buttonZnajdz.doClick();
                zamienTekst();
            }
            
        }
        private void zamienTekst()
        {
            obszarTekstowy.requestFocus();
            obszarTekstowy.replaceRange(nowyTekst.getText(), obszarTekstowy.getSelectionStart(), obszarTekstowy.getSelectionEnd());
            JOptionPane.showMessageDialog(rootPane, "Czy zamienic?"+" "+szukany.getText()+" "+"Na:"+ " "+nowyTekst.getText(),"uwaga",JOptionPane.YES_NO_OPTION);//YES NO not warking on MacOs
        }
        
    }
    
    private JTextArea obszarTekstowy = new JTextArea("Funkcja wyszukiwania index w tekscie",5,10);//7-wierszy,10-kolumn(kolumny niewidoczne bo mamy FlowLayout
    private JScrollPane panelSuwaka = new JScrollPane(obszarTekstowy);
    private JPanel panel = new JPanel();
    private JButton buttonZnajdz = new JButton("Znajdz");
    private JButton buttonZamien = new JButton("Zamien");
    private JLabel etykieta = new JLabel("Na:");
    private JTextField szukany = new JTextField(7);//7-okresla ile znakow bedzie widoczne w polu
    private JTextField nowyTekst = new JTextField(7);
    
    public static void main(String[] args) {
        new JTextFieldFindReplaceText().setVisible(true);
    }
    
}
