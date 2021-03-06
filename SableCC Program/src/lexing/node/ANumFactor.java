/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class ANumFactor extends PFactor
{
    private TIntNum _intNum_;

    public ANumFactor()
    {
        // Constructor
    }

    public ANumFactor(
        @SuppressWarnings("hiding") TIntNum _intNum_)
    {
        // Constructor
        setIntNum(_intNum_);

    }

    @Override
    public Object clone()
    {
        return new ANumFactor(
            cloneNode(this._intNum_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANumFactor(this);
    }

    public TIntNum getIntNum()
    {
        return this._intNum_;
    }

    public void setIntNum(TIntNum node)
    {
        if(this._intNum_ != null)
        {
            this._intNum_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._intNum_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._intNum_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._intNum_ == child)
        {
            this._intNum_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._intNum_ == oldChild)
        {
            setIntNum((TIntNum) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
