/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AMultidecl extends PMultidecl
{
    private TComma _comma_;
    private TIdentifier _identifier_;
    private PVardecltypeassign _vardecltypeassign_;

    public AMultidecl()
    {
        // Constructor
    }

    public AMultidecl(
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") PVardecltypeassign _vardecltypeassign_)
    {
        // Constructor
        setComma(_comma_);

        setIdentifier(_identifier_);

        setVardecltypeassign(_vardecltypeassign_);

    }

    @Override
    public Object clone()
    {
        return new AMultidecl(
            cloneNode(this._comma_),
            cloneNode(this._identifier_),
            cloneNode(this._vardecltypeassign_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultidecl(this);
    }

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public PVardecltypeassign getVardecltypeassign()
    {
        return this._vardecltypeassign_;
    }

    public void setVardecltypeassign(PVardecltypeassign node)
    {
        if(this._vardecltypeassign_ != null)
        {
            this._vardecltypeassign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._vardecltypeassign_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._comma_)
            + toString(this._identifier_)
            + toString(this._vardecltypeassign_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._vardecltypeassign_ == child)
        {
            this._vardecltypeassign_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._vardecltypeassign_ == oldChild)
        {
            setVardecltypeassign((PVardecltypeassign) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}