/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AEpsilonFormallist extends PFormallist
{

    public AEpsilonFormallist()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AEpsilonFormallist();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEpsilonFormallist(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
