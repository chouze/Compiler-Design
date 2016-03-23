/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AIdentifierNewnonterminal extends PNewnonterminal
{
    private TIdentifier _identifier_;
    private TLeftParen _leftParen_;
    private TRightParen _rightParen_;

    public AIdentifierNewnonterminal()
    {
        // Constructor
    }

    public AIdentifierNewnonterminal(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TLeftParen _leftParen_,
        @SuppressWarnings("hiding") TRightParen _rightParen_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setLeftParen(_leftParen_);

        setRightParen(_rightParen_);

    }

    @Override
    public Object clone()
    {
        return new AIdentifierNewnonterminal(
            cloneNode(this._identifier_),
            cloneNode(this._leftParen_),
            cloneNode(this._rightParen_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIdentifierNewnonterminal(this);
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._leftParen_)
            + toString(this._rightParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._leftParen_ == child)
        {
            this._leftParen_ = null;
            return;
        }

        if(this._rightParen_ == child)
        {
            this._rightParen_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._leftParen_ == oldChild)
        {
            setLeftParen((TLeftParen) newChild);
            return;
        }

        if(this._rightParen_ == oldChild)
        {
            setRightParen((TRightParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
