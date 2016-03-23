/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class ADefaultCaselist extends PCaselist
{
    private TDefault _default_;
    private TColon _colon_;
    private PStatement _statement_;

    public ADefaultCaselist()
    {
        // Constructor
    }

    public ADefaultCaselist(
        @SuppressWarnings("hiding") TDefault _default_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PStatement _statement_)
    {
        // Constructor
        setDefault(_default_);

        setColon(_colon_);

        setStatement(_statement_);

    }

    @Override
    public Object clone()
    {
        return new ADefaultCaselist(
            cloneNode(this._default_),
            cloneNode(this._colon_),
            cloneNode(this._statement_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefaultCaselist(this);
    }

    public TDefault getDefault()
    {
        return this._default_;
    }

    public void setDefault(TDefault node)
    {
        if(this._default_ != null)
        {
            this._default_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._default_ = node;
    }

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public PStatement getStatement()
    {
        return this._statement_;
    }

    public void setStatement(PStatement node)
    {
        if(this._statement_ != null)
        {
            this._statement_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._statement_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._default_)
            + toString(this._colon_)
            + toString(this._statement_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._default_ == child)
        {
            this._default_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._statement_ == child)
        {
            this._statement_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._default_ == oldChild)
        {
            setDefault((TDefault) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._statement_ == oldChild)
        {
            setStatement((PStatement) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
