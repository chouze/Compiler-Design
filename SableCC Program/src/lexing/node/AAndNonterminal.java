/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AAndNonterminal extends PAndNonterminal
{
    private PLess _less_;
    private PAlist _alist_;

    public AAndNonterminal()
    {
        // Constructor
    }

    public AAndNonterminal(
        @SuppressWarnings("hiding") PLess _less_,
        @SuppressWarnings("hiding") PAlist _alist_)
    {
        // Constructor
        setLess(_less_);

        setAlist(_alist_);

    }

    @Override
    public Object clone()
    {
        return new AAndNonterminal(
            cloneNode(this._less_),
            cloneNode(this._alist_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAndNonterminal(this);
    }

    public PLess getLess()
    {
        return this._less_;
    }

    public void setLess(PLess node)
    {
        if(this._less_ != null)
        {
            this._less_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._less_ = node;
    }

    public PAlist getAlist()
    {
        return this._alist_;
    }

    public void setAlist(PAlist node)
    {
        if(this._alist_ != null)
        {
            this._alist_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._alist_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._less_)
            + toString(this._alist_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._less_ == child)
        {
            this._less_ = null;
            return;
        }

        if(this._alist_ == child)
        {
            this._alist_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._less_ == oldChild)
        {
            setLess((PLess) newChild);
            return;
        }

        if(this._alist_ == oldChild)
        {
            setAlist((PAlist) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
