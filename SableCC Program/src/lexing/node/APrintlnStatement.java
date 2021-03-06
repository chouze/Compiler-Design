/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class APrintlnStatement extends PStatement
{
    private TPrintln _println_;
    private TLeftParen _leftParen_;
    private PExp _exp_;
    private TRightParen _rightParen_;
    private TSemi _semi_;

    public APrintlnStatement()
    {
        // Constructor
    }

    public APrintlnStatement(
        @SuppressWarnings("hiding") TPrintln _println_,
        @SuppressWarnings("hiding") TLeftParen _leftParen_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TRightParen _rightParen_,
        @SuppressWarnings("hiding") TSemi _semi_)
    {
        // Constructor
        setPrintln(_println_);

        setLeftParen(_leftParen_);

        setExp(_exp_);

        setRightParen(_rightParen_);

        setSemi(_semi_);

    }

    @Override
    public Object clone()
    {
        return new APrintlnStatement(
            cloneNode(this._println_),
            cloneNode(this._leftParen_),
            cloneNode(this._exp_),
            cloneNode(this._rightParen_),
            cloneNode(this._semi_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPrintlnStatement(this);
    }

    public TPrintln getPrintln()
    {
        return this._println_;
    }

    public void setPrintln(TPrintln node)
    {
        if(this._println_ != null)
        {
            this._println_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._println_ = node;
    }

    public TLeftParen getLeftParen()
    {
        return this._leftParen_;
    }

    public void setLeftParen(TLeftParen node)
    {
        if(this._leftParen_ != null)
        {
            this._leftParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftParen_ = node;
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

    public TRightParen getRightParen()
    {
        return this._rightParen_;
    }

    public void setRightParen(TRightParen node)
    {
        if(this._rightParen_ != null)
        {
            this._rightParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightParen_ = node;
    }

    public TSemi getSemi()
    {
        return this._semi_;
    }

    public void setSemi(TSemi node)
    {
        if(this._semi_ != null)
        {
            this._semi_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semi_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._println_)
            + toString(this._leftParen_)
            + toString(this._exp_)
            + toString(this._rightParen_)
            + toString(this._semi_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._println_ == child)
        {
            this._println_ = null;
            return;
        }

        if(this._leftParen_ == child)
        {
            this._leftParen_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._rightParen_ == child)
        {
            this._rightParen_ = null;
            return;
        }

        if(this._semi_ == child)
        {
            this._semi_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._println_ == oldChild)
        {
            setPrintln((TPrintln) newChild);
            return;
        }

        if(this._leftParen_ == oldChild)
        {
            setLeftParen((TLeftParen) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._rightParen_ == oldChild)
        {
            setRightParen((TRightParen) newChild);
            return;
        }

        if(this._semi_ == oldChild)
        {
            setSemi((TSemi) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
