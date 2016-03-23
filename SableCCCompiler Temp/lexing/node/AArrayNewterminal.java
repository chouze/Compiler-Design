/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AArrayNewterminal extends PNewterminal
{
    private TInt _int_;
    private TLeftBracket _leftBracket_;
    private PExp _exp_;
    private TRightBracket _rightBracket_;

    public AArrayNewterminal()
    {
        // Constructor
    }

    public AArrayNewterminal(
        @SuppressWarnings("hiding") TInt _int_,
        @SuppressWarnings("hiding") TLeftBracket _leftBracket_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TRightBracket _rightBracket_)
    {
        // Constructor
        setInt(_int_);

        setLeftBracket(_leftBracket_);

        setExp(_exp_);

        setRightBracket(_rightBracket_);

    }

    @Override
    public Object clone()
    {
        return new AArrayNewterminal(
            cloneNode(this._int_),
            cloneNode(this._leftBracket_),
            cloneNode(this._exp_),
            cloneNode(this._rightBracket_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayNewterminal(this);
    }

    public TInt getInt()
    {
        return this._int_;
    }

    public void setInt(TInt node)
    {
        if(this._int_ != null)
        {
            this._int_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._int_ = node;
    }

    public TLeftBracket getLeftBracket()
    {
        return this._leftBracket_;
    }

    public void setLeftBracket(TLeftBracket node)
    {
        if(this._leftBracket_ != null)
        {
            this._leftBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftBracket_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TRightBracket getRightBracket()
    {
        return this._rightBracket_;
    }

    public void setRightBracket(TRightBracket node)
    {
        if(this._rightBracket_ != null)
        {
            this._rightBracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightBracket_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._int_)
            + toString(this._leftBracket_)
            + toString(this._exp_)
            + toString(this._rightBracket_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._int_ == child)
        {
            this._int_ = null;
            return;
        }

        if(this._leftBracket_ == child)
        {
            this._leftBracket_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._rightBracket_ == child)
        {
            this._rightBracket_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._int_ == oldChild)
        {
            setInt((TInt) newChild);
            return;
        }

        if(this._leftBracket_ == oldChild)
        {
            setLeftBracket((TLeftBracket) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._rightBracket_ == oldChild)
        {
            setRightBracket((TRightBracket) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
