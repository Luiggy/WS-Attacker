/**
 * WS-Attacker - A Modular Web Services Penetration Testing Framework Copyright
 * (C) 2013 Christian Altmeier
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package wsattacker.plugin.intelligentdos.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.StringUtils;

import wsattacker.library.intelligentdos.helper.CommonParamItem;
import wsattacker.plugin.intelligentdos.ui.helper.CommonParamTableModel;
import wsattacker.plugin.intelligentdos.ui.helper.JTextFieldInteger;

/**
 * @author Christian Altmeier
 */
public class ConfigureCommonParams_NB
    extends javax.swing.JDialog
{

    private static final String DELETE = "delete";

    private final CommonParamItemsDocumentListener dl = new CommonParamItemsDocumentListener();

    private boolean ok;

    private final CommonParamTableModel commonParamTableModel;

    /**
     * Creates new form ConfigureCommonParams_NB
     * 
     * @param commonParamItems
     */
    public ConfigureCommonParams_NB( List<CommonParamItem> commonParamItems )
    {
        commonParamTableModel = new CommonParamTableModel( commonParamItems );

        initComponents();

        configureKeyBinding();

        numberOfRequests.setDocument( new JTextFieldInteger() );
        numberOfRequests.getDocument().addDocumentListener( dl );
        numberOfThreads.setDocument( new JTextFieldInteger() );
        numberOfThreads.getDocument().addDocumentListener( dl );
        milliesBetweenReques.setDocument( new JTextFieldInteger() );
        milliesBetweenReques.getDocument().addDocumentListener( dl );

        commonParamTable.getSelectionModel().addListSelectionListener( new ListSelectionListener()
        {
            @Override
            public void valueChanged( ListSelectionEvent event )
            {
                int selectedRow = commonParamTable.getSelectedRow();
                if ( selectedRow < 0 )
                {
                    jButton2.setEnabled( false );

                    numberOfRequests.setText( "" );
                    numberOfThreads.setText( "" );
                    milliesBetweenReques.setText( "" );
                }
                else
                {
                    jButton2.setEnabled( true );

                    CommonParamItem cpi = commonParamTableModel.get( selectedRow );
                    numberOfRequests.setText( String.valueOf( cpi.getNumberOfRequests() ) );
                    numberOfThreads.setText( String.valueOf( cpi.getNumberOfThreads() ) );
                    milliesBetweenReques.setText( String.valueOf( cpi.getMilliesBetweenRequests() ) );
                }
            }
        } );
    }

    private void configureKeyBinding()
    {
        InputMap inputMap = commonParamTable.getInputMap( JTable.WHEN_FOCUSED );
        ActionMap actionMap = commonParamTable.getActionMap();

        inputMap.put( KeyStroke.getKeyStroke( KeyEvent.VK_DELETE, 0 ), DELETE );
        actionMap.put( DELETE, new AbstractAction()
        {
            @Override
            public void actionPerformed( ActionEvent evt )
            {
                removeRow();
            }
        } );
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commonParamTable = new javax.swing.JTable();
        numberOfRequests = new javax.swing.JTextField();
        numberOfThreads = new javax.swing.JTextField();
        milliesBetweenReques = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        setResizable( false );

        jLabel1.setText( "Configure the common parameter for the DoS Attacks" );

        commonParamTable.setModel( commonParamTableModel );
        commonParamTable.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );
        jScrollPane1.setViewportView( commonParamTable );

        numberOfRequests.setHorizontalAlignment( javax.swing.JTextField.RIGHT );

        numberOfThreads.setHorizontalAlignment( javax.swing.JTextField.RIGHT );

        milliesBetweenReques.setHorizontalAlignment( javax.swing.JTextField.RIGHT );

        jButton1.setText( "add" );
        jButton1.setEnabled( false );
        jButton1.addActionListener( new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent evt )
            {
                jButton1ActionPerformed( evt );
            }
        } );

        jLabel2.setText( "Number of requests" );

        jLabel3.setText( "Number of threads" );

        jLabel4.setText( "Millies between requests" );

        jButton2.setText( "remove" );
        jButton2.setEnabled( false );
        jButton2.addActionListener( new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent evt )
            {
                jButton2ActionPerformed( evt );
            }
        } );

        jButton3.setText( "OK" );
        jButton3.addActionListener( new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent evt )
            {
                jButton3ActionPerformed( evt );
            }
        } );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup( layout.createSequentialGroup().addContainerGap().addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jScrollPane1,
                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                      503,
                                                                                                                                                                                                                                                                      Short.MAX_VALUE ).addGroup( layout.createSequentialGroup().addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel3 ).addComponent( jLabel1 ).addComponent( jButton3 ) ).addGap( 0,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      0,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      Short.MAX_VALUE ) ).addComponent( jSeparator1 ).addGroup( layout.createSequentialGroup().addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel4 ).addGroup( layout.createSequentialGroup().addComponent( jLabel2 ).addGap( 45,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  45,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  45 ).addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             false ).addComponent( numberOfThreads,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   129,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   Short.MAX_VALUE ).addComponent( numberOfRequests ).addComponent( milliesBetweenReques ) ) ) ).addGap( 18,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         18,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         18 ).addComponent( jButton1 ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        Short.MAX_VALUE ).addComponent( jButton2 ) ) ).addContainerGap() ) );
        layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup( layout.createSequentialGroup().addContainerGap().addComponent( jLabel1 ).addGap( 18,
                                                                                                                                                                                                    18,
                                                                                                                                                                                                    18 ).addComponent( jScrollPane1,
                                                                                                                                                                                                                       javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                       275,
                                                                                                                                                                                                                       javax.swing.GroupLayout.PREFERRED_SIZE ).addGap( 18,
                                                                                                                                                                                                                                                                        18,
                                                                                                                                                                                                                                                                        18 ).addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel2 ).addComponent( numberOfRequests,
                                                                                                                                                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.PREFERRED_SIZE ) ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 ).addComponent( numberOfThreads,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE ) ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel4 ).addComponent( milliesBetweenReques,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( jButton1 ).addComponent( jButton2 ) ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jSeparator1,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           10,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButton3 ).addContainerGap( javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    Short.MAX_VALUE ) ) );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed( java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_jButton1ActionPerformed

        final int requests = Integer.parseInt( numberOfRequests.getText() );
        final int threads = Integer.parseInt( numberOfThreads.getText() );
        final int millies = Integer.parseInt( milliesBetweenReques.getText() );

        CommonParamItem commonParamItem = new CommonParamItem( requests, threads, millies );
        commonParamTableModel.add( commonParamItem );
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed( java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_jButton2ActionPerformed
        removeRow();
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed( java.awt.event.ActionEvent evt )
    {// GEN-FIRST:event_jButton3ActionPerformed
        setVisible( false );
        dispose();

        ok = true;
    }// GEN-LAST:event_jButton3ActionPerformed

    public boolean showDialog()
    {
        setModal( true );
        setVisible( true );

        addWindowFocusListener( new WindowAdapter()
        {

            @Override
            public void windowClosing( WindowEvent e )
            {
                ok = false;
            }
        } );

        return ok;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable commonParamTable;

    private javax.swing.JButton jButton1;

    private javax.swing.JButton jButton2;

    private javax.swing.JButton jButton3;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JSeparator jSeparator1;

    private javax.swing.JTextField milliesBetweenReques;

    private javax.swing.JTextField numberOfRequests;

    private javax.swing.JTextField numberOfThreads;

    // End of variables declaration//GEN-END:variables

    public List<CommonParamItem> getCommonParams()
    {
        return commonParamTableModel.getItems();
    }

    private void removeRow()
    {
        int selectedRow = commonParamTable.getSelectedRow();
        if ( selectedRow != -1 )
        {
            commonParamTableModel.remove( selectedRow );
        }
    }

    private class CommonParamItemsDocumentListener
        implements DocumentListener, Serializable
    {

        /**
		 * 
		 */
        private static final long serialVersionUID = 1L;

        @Override
        public void insertUpdate( DocumentEvent e )
        {
            disableIfEmpty( e );
        }

        @Override
        public void removeUpdate( DocumentEvent e )
        {
            disableIfEmpty( e );
        }

        @Override
        public void changedUpdate( DocumentEvent e )
        {
            disableIfEmpty( e );
        }

        private void disableIfEmpty( DocumentEvent e )
        {
            if ( StringUtils.isNotBlank( numberOfRequests.getText() )
                && StringUtils.isNotBlank( numberOfThreads.getText() )
                && StringUtils.isNotBlank( milliesBetweenReques.getText() ) )
            {
                jButton1.setEnabled( true );
            }
            else
            {
                jButton1.setEnabled( false );
            }
        }

    }
}
