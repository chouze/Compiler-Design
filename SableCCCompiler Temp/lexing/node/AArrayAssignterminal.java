/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class AArrayAssignterminal extends PAssignterminal
{
    private TLeftBracket _leftBracket_;
    private PExp _left_;
    private TRightBracket _rightBracket_;
    private TAssign _assign_;
    private PExp _right_;
    private TSemi _semi_;

    public AArrayAssignterminal()
    {
        // Constructor
    }

    public AArrayAssignterminal(
        @SuppressWarnings("hiding") TLeftBracket _leftBracket_,
        @SuppressWarnings("hiding") PExp _left_,
        @SuppressWarnings("hiding") TRightBracket _rightBracket_,
        @SuppressWarnings("hiding") TAssign _assign_,
        @SuppressWarnings("hiding") PExp _right_,
        @SuppressWarnings("hiding") TSemi _semi_)
    {
        // Constructor
        setLeftBracket(_leftBracket_);

        setLeft(_left_);

        setRightBracket(_rightBracket_);

        setAssign(_assign_);

        setRight(_right_);

        setSemi(_semi_);

    }

    @Override
    public Object clone()
    {
        return new AArrayAssignterminal(
            cloneNode(this._leftBracket_),
            cloneNode(this._left_),
            cloneNode(this._rightBracket_),
            cloneNode(this._assign_),
            cloneNode(this._right_),
            cloneNode(this._semi_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayAssignterminal(this);
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

    public PExp getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExp node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
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

    public TAssign getAssign()
    {
        return this._assign_;
    }

    public void setAssign(TAssign node)
    {
        if(this._assign_ != null)
        {
            this._assign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._assign_ = node;
    }

    public PExp getRight()
    {
        return this._right_;
    }

    public void setRight(PExp node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
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
            + toString(this._leftBracket_)
            + toString(this._left_)
            + toString(this._rightBracket_)
            + toString(this._assign_)
            + toString(this._right_)
            + toString(this._semi_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._leftBracket_ == child)
        {
            this._leftBracket_ = null;
            return;
        }

        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._rightBracket_ == child)
        {
            this._rightBracket_ = null;
            return;
        }

        if(this._assign_ == child)
        {
            this._assign_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
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
        if(this._leftBracket_ == oldChild)
        {
            setLeftBracket((TLeftBracket) newChild);
            return;
        }

        if(this._left_ == oldChild)
        {
            setLeft((PExp) newChild);
            return;
        }

        if(this._rightBracket_ == oldChild)
        {
            setRightBracket((TRightBracket) newChild);
            return;
        }

        if(this._assign_ == oldChild)
        {
            setAssign((TAssign) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PExp) newChild);
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